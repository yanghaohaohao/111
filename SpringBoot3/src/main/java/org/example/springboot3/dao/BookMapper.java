package org.example.springboot3.dao;


import org.apache.ibatis.annotations.*;
import org.example.springboot3.Entity.Book;
import org.example.springboot3.Entity.User;

import java.util.List;

//sql语句中以表中列名为标准，#{}中则以Entity中的属性名为准.


@Mapper
public interface BookMapper {

    @Select("select * from book")
    public abstract List<Book> findallbook();

    //意思是从offset开始，共size个数据
    @Select("select * from book LIMIT #{offset},#{size}")
    public abstract List<Book> findbookByPage(
            @Param("offset") Integer offset, @Param("size") Integer size);

    @Select("select * from book where book_id = #{id}")
    public abstract Book findbookById(int id);

    @Select("select * from book where book_name = #{bookName}")
    public abstract Book findbookByName(String name);

    @Select("select count(*) from book")
    public abstract int countAll();

    @Select("select ifnull(max(book_id), 10000-1) from book")
    public abstract int findMaxBookId();

    //不允许修改id
    @Update("update book set book_name = #{bookName} where book_id = #{bookId}")
    public abstract void updatebook(Book book);

    @Insert("insert into book (book_id, book_name) values (#{bookId}, #{bookName})")
    public abstract void insertbook(Book book);

    @Delete("delete from book where book_id = #{id}")
    public abstract void deletebook(int id);

    @Update("update book set book_status = 0 where book_id = #{bookId}")
    public abstract void updateBookStatusToBorrowed(int bookId);

    @Update("update book set book_status = 1 where book_id = #{bookId}")
    public abstract void updateBookStatusToAvailable(int bookId);

}
