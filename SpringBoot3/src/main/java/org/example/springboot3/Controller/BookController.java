package org.example.springboot3.Controller;

import org.example.springboot3.Entity.Book;
import org.example.springboot3.annotation.AdminOnly;
import org.example.springboot3.common.Result;
import org.example.springboot3.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    BookServiceImpl bookServiceImpl;

    //增删改查

    @GetMapping("/book/all")
    public Result<List<Book>> getBook() {

        return Result.success(bookServiceImpl.findall());
    }

    @GetMapping("/book/page")
    public Result<List<Book>> getBookByPage(@RequestParam(required = false, defaultValue = "1") int page, @RequestParam(required = false, defaultValue = "10") int size) {
        List<Book> books = bookServiceImpl.findByPage(page, size);
        int total = bookServiceImpl.countAll();
        int totalPages = (total + size - 1) / size;
        return Result.success(books, size, total, totalPages, page);
    }

    @GetMapping("/book/{id}")
    public Result<Book> getBookById(@PathVariable int id) {
        Book book =  bookServiceImpl.findById(id);
        //System.out.println("查询结果: " + book);
        return Result.success(book);

    }


    @AdminOnly//修改书本信息
    @PutMapping("/book")
    public Result<String> updateBook(@RequestBody Book book) {
        bookServiceImpl.updataBook(book);
        return Result.success("update success!");
    }

    @AdminOnly//添加书本
    @PostMapping("/book")
    public Result<String> addBook(@RequestBody Book book) {
        bookServiceImpl.addBook(book);
        return Result.success("insert success!");
    }

    @AdminOnly//删除书本
    @DeleteMapping("/book/{id}")
    public Result<String> deleteBook(@PathVariable int id) {
        bookServiceImpl.deleteBook(id);
        return Result.success("delete success!");
    }


}
