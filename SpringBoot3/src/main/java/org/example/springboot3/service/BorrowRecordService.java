package org.example.springboot3.service;

import org.example.springboot3.Entity.BorrowRecord;
import org.example.springboot3.Entity.User;

import java.util.List;


public interface BorrowRecordService {
    List<BorrowRecord> findAll();
    BorrowRecord findById(int id);
    List<BorrowRecord> findByPage(int page, int size);
    int countAll();
    String addBorrowRecord(BorrowRecord borrowRecord);
    String updateBorrowRecord(long id);
    String deleteBorrowRecordById(int id);
    List<BorrowRecord> findByStatus(int status);
    List<BorrowRecord> findBorrowRecordByUserId(int userid);
    List<BorrowRecord> findMyBorrowRecord();
    List<BorrowRecord> findMyUnReturnBorrowRecord();
}
