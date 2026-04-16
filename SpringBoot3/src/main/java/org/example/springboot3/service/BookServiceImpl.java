package org.example.springboot3.service;

import org.example.springboot3.Entity.Book;
import org.example.springboot3.dao.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class BookServiceImpl {
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public List<Book> findall() {
        return bookMapper.findallbook();
    }

    //加入了redis的效果
    public Book findById(int id) {
        String key = "book:" + id;
        //先从redis中找
        Book book = (Book) redisTemplate.opsForValue().get(key);
        if (book == null) {
            //没有再去数据库中找
            book = bookMapper.findbookById(id);
            redisTemplate.opsForValue().set(key, book, Duration.ofHours(1));

        }
        return book;
    }

    public List<Book> findByPage(int page, int size) {
        int offset = (page - 1) * size;
        return bookMapper.findbookByPage(offset, size);
    }

    public int countAll() {
        return bookMapper.countAll();
    }

    public void updataBook(Book book) {
        bookMapper.updatebook(book);
        return;
    }

    public void deleteBook(int id) {
        bookMapper.deletebook(id);
    }

    public void addBook(Book book) {
        //意思是根据新名字进行查询，如果不为空则说明已存在该名字
        if(bookMapper.findbookByName(book.getBookName()) != null){
            throw new IllegalArgumentException("已存在书名:" + book.getBookName());
        }
        //查找当前最大id，如果小于100001，说明没有书本，所以从当前开始，如果大于则+1
        int max_id = bookMapper.findMaxBookId();
        int new_id = max_id >=10001 ? max_id+1 : max_id;
        //新书status默认为1
        book.setBookStatus(1);
        bookMapper.insertbook(book);
        return;
    }




}

