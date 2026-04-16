package org.example.springboot3.Entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class Book {
    private Integer bookId;

    @NotBlank(message = "书名不能为空")
    @Pattern(regexp = "^\\S+$", message = "书名不能包含空格")
    private String bookName;
    private Integer bookStatus;

    public Book() {}

    public Book(Integer bookId, String bookName, Integer bookStatus) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookStatus = bookStatus;
    }

    public Integer getBookId() {
        return bookId;
    }
    public void setBookId(Integer bookId) {

        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getBookStatus() {
        return bookStatus;
    }
    public void setBookStatus(Integer bookStatus) {
        this.bookStatus = bookStatus;
    }

    @Override
    public String toString() {
        return "book [bookId=" + bookId + ", bookName=" + bookName + ", bookStatus=" + bookStatus + "]";
    }
}
