package org.example.springboot3.Entity;

public class BorrowRecord {

    private Long borrowId;
    private Integer userId;
    private Integer bookId;
    private String bookName;
    private String borrowTime;
    private String returnTime;
    private Integer status;

    public BorrowRecord() {
    }

    public BorrowRecord(Long borrowId, Integer userId, Integer bookId, String bookName, String borrowTime, String returnTime, Integer status) {

        this.borrowId = borrowId;
        this.userId = userId;
        this.bookId = bookId;
        this.bookName = bookName;
        this.borrowTime = borrowTime;
        this.returnTime = returnTime;
        this.status = status;

    }
    public Long getBorrowId() {
        return borrowId;
    }
    public void setBorrowId(Long borrowId) {
        this.borrowId = borrowId;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
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
    public String getBorrowTime() {
        return borrowTime;
    }
    public void setBorrowTime(String borrowTime) {
        this.borrowTime = borrowTime;
    }
    public String getReturnTime() {
        return returnTime;
    }
    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BorrowRecord["+"borrowId:"+borrowId+"userId="+userId+"borrowTime"+borrowTime+"returnTime="+returnTime+"status="+status+"]";
    }
}
