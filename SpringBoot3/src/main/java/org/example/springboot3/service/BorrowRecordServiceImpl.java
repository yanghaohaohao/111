package org.example.springboot3.service;

import org.example.springboot3.Entity.Book;
import org.example.springboot3.Entity.BorrowRecord;
import org.example.springboot3.Entity.User;
import org.example.springboot3.common.UserContext;
import org.example.springboot3.dao.BookMapper;
import org.example.springboot3.dao.BorrowRecordMapper;
import org.example.springboot3.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BorrowRecordServiceImpl implements BorrowRecordService {

    private final BorrowRecordMapper borrowRecordMapper;

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    public BorrowRecordServiceImpl(BorrowRecordMapper borrowRecordMapper) {
        this.borrowRecordMapper = borrowRecordMapper;
    }

    //——————————————————————————管理员功能——————————————————————————
    @Override
    public List<BorrowRecord> findAll() {
        return borrowRecordMapper.fingallBorrowRecord();
    }

    @Override
    public BorrowRecord findById(int userid) {
        return borrowRecordMapper.findBorrowRecordByUserId(userid);
    }

    //————————————————————————————所有用户功能————————————————————————————
    @Override
    public List<BorrowRecord> findByPage(int page, int size) {
        int offset = (page - 1) * size;
        return borrowRecordMapper.findByPage(offset, size);
    }

    @Override
    public int countAll() {
        return borrowRecordMapper.countAll();
    }

    @Override
    @Transactional
    public String addBorrowRecord(BorrowRecord borrowRecord) {
        Integer currentUserId = UserContext.getCurrentUserId();
        if (currentUserId == null) {
            return "user not logged in";
        }
        User user = userMapper.findById(currentUserId);
        if (user == null) {
            return "user not found";
        }

        Book book = bookMapper.findbookById(borrowRecord.getBookId());
        if (book == null) {
            return "book not found";
        }
        if (book.getBookStatus() == 0) {
            return "book are borrowed";
        }

        borrowRecord.setUserId(currentUserId);
        borrowRecord.setBookName(book.getBookName());
        borrowRecord.setBorrowTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        borrowRecord.setStatus(0);
        borrowRecordMapper.insertBorrowRecord(borrowRecord);
        bookMapper.updateBookStatusToBorrowed(book.getBookId());
        return "add Success!";
    }

    @Override
    @Transactional
    public String updateBorrowRecord(long borrowId) {
        BorrowRecord record = borrowRecordMapper.findByBorrowId(borrowId);
        if (record == null) {
            return "borrow record not found";
        }
        if (record.getStatus() == 1) {
            return "book already returned";
        }

        record.setStatus(1);
        record.setReturnTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        borrowRecordMapper.updateBorrowRecord(record);
        bookMapper.updateBookStatusToAvailable(record.getBookId());
        return "return Success!";
    }

    @Override
    public List<BorrowRecord> findMyBorrowRecord(){
        Integer currentUserId = UserContext.getCurrentUserId();
        if (currentUserId == null) {
            throw new RuntimeException("user not logged in");
        }
        return borrowRecordMapper.findMyBorrowRecord(currentUserId);
    }

    @Override
    public List<BorrowRecord> findMyUnReturnBorrowRecord(){
        Integer currentUserId = UserContext.getCurrentUserId();
        if (currentUserId == null) {
            throw new RuntimeException("user not logged in");
        }
        return borrowRecordMapper.findMyUnReturnBorrowRecord(currentUserId);
    }



    //————————————————————————————管理员——————————————————————
    @Override
    @Transactional
    public String deleteBorrowRecordById(int id) {
        BorrowRecord record = borrowRecordMapper.findByBorrowId(id);
        if (record != null) {
            bookMapper.updateBookStatusToAvailable(record.getBookId());
        }
        borrowRecordMapper.deleteBorrowRecordById(id);
        return "delete Success!";
    }
    @Override
    public List<BorrowRecord> findBorrowRecordByUserId(int userid) {
        return borrowRecordMapper.findAllByUserId(userid);
    }

    @Override
    public List<BorrowRecord> findByStatus(int status){
        return borrowRecordMapper.findByStatus(status);
    }


}
