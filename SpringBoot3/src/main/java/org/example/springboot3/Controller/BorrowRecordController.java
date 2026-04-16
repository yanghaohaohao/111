package org.example.springboot3.Controller;

import org.example.springboot3.Entity.BorrowRecord;
import org.example.springboot3.annotation.AdminOnly;
import org.example.springboot3.common.Result;
import org.example.springboot3.dao.BorrowRecordMapper;
import org.example.springboot3.service.BorrowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BorrowRecordController {
    @Autowired
    private BorrowRecordService borrowRecordService;
    @Autowired
    private BorrowRecordMapper borrowRecordMapper;

    //获取所有借阅记录
    @AdminOnly
    @GetMapping("/borrowRecord")
    public Result<List<BorrowRecord>> getBorrowRecord() {
        return Result.success(borrowRecordService.findAll());
    }

    //按页获取借阅记录
    @AdminOnly
    @GetMapping("/borrowRecord/page")
    public Result<List<BorrowRecord>> getBorrowRecordByPage(@RequestParam(required = false, defaultValue = "1") int page, @RequestParam(required = false, defaultValue = "10") int size) {
        List<BorrowRecord> records = borrowRecordService.findByPage(page, size);
        int total = borrowRecordService.countAll();
        int totalPages = (total + size - 1) / size;
        return Result.success(records, size, total, totalPages, page);
    }

    //按借阅id获取借阅记录
    @AdminOnly
    @GetMapping("/borrowRecord/{id}")
    public Result<BorrowRecord> getBorrowRecordById(@PathVariable int id) {
        return Result.success(borrowRecordService.findById(id));
    }

    //按用户id获取借阅记录
    @AdminOnly
    @GetMapping("/borrowRecord/user/{id}")
    public Result<List<BorrowRecord>> getAllBorrowRecordById(@PathVariable int id) {
        return Result.success(borrowRecordService.findBorrowRecordByUserId(id));
    }

    //查找当前用户借阅记录
    @GetMapping("/borrowRecord/my")
    public Result<List<BorrowRecord>> getMyBorrowRecord() {
        return Result.success(borrowRecordService.findMyBorrowRecord());
    }

    //按书本状态获取借阅记录
    @AdminOnly
    @GetMapping("/borrowRecordByStatus/{status}")
    public Result<List<BorrowRecord>> getBorrowRecordByStatus(@PathVariable int status) {
        return Result.success(borrowRecordService.findByStatus(status));
    }

    //查找当前用户未归还记录
    @GetMapping("/borrowRecord/unReturn")
    public Result<List<BorrowRecord>> getUnReturnBorrowRecord() {
        return Result.success(borrowRecordService.findMyUnReturnBorrowRecord());
    }

    //添加借阅记录
    @PostMapping("/borrowRecord")
    public Result<String> addBorrowRecord(@RequestBody BorrowRecord borrowRecord) {
        return Result.success(borrowRecordService.addBorrowRecord(borrowRecord));
    }

    //（管理员）修改借阅记录
    @AdminOnly
    @PutMapping("/borrowRecord/{id}")
    public Result<String> updateBorrowRecord(@PathVariable long id) {
        return Result.success(borrowRecordService.updateBorrowRecord(id));
    }

    //用户归还书本
    @PutMapping("/borrowRecord/returnByBookId/{bookId}")
    public Result<String> returnBorrowRecord(@PathVariable int bookId) {
        BorrowRecord borrowRecord = borrowRecordMapper.findActiveBorrowRecordByBookId(bookId);
        return Result.success(borrowRecordService.updateBorrowRecord(borrowRecord.getBorrowId()));
    }


}
