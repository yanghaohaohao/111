package org.example.springboot3.dao;

import org.apache.ibatis.annotations.*;
import org.example.springboot3.Entity.BorrowRecord;
import java.util.List;

@Mapper
public interface BorrowRecordMapper {

    @Select("select * from borrow_record")
    public abstract List<BorrowRecord> fingallBorrowRecord();

    @Select("select * from borrow_record LIMIT #{offset},#{size}")
    public abstract List<BorrowRecord> findByPage(int offset, int size);

    @Select("select count(*) from borrow_record")
    public abstract int countAll();

    @Select("select * from borrow_record where user_id = #{id}")
    public abstract BorrowRecord findBorrowRecordByUserId(int userId);

    @Select("select * from borrow_record where user_id = #{userId}")
    public abstract List<BorrowRecord> findAllByUserId(int userId);

    @Select("select * from borrow_record where borrow_id = #{borrowId}")
    public abstract BorrowRecord findByBorrowId(long borrowId);

    @Select("select * from borrow_record where status = #{status}")
    public abstract List<BorrowRecord> findByStatus(int status);

    @Select("select * from borrow_record where user_id = #{userId}")
    public abstract List<BorrowRecord> findMyBorrowRecord(int userId);

    @Select("select * from borrow_record where user_id = #{userId} and status = 0")
    public abstract List<BorrowRecord> findMyUnReturnBorrowRecord(int userId);

    @Insert("insert into borrow_record(user_id,book_id,book_name,borrow_time,return_time,status)" +
            " values (#{userId},#{bookId},#{bookName},#{borrowTime},#{returnTime},#{status})")
    public abstract void insertBorrowRecord(BorrowRecord borrowRecord);

    @Update("update borrow_record set status = #{status}, return_time = #{returnTime} where borrow_id = #{borrowId}")
    public abstract void updateBorrowRecord(BorrowRecord borrowRecord);

    @Delete("delete from borrow_record where borrow_id = #{borrowId}")
    public abstract void deleteBorrowRecordById(long borrowId);

    @Select("select * from borrow_record where book_id = #{bookId} and status = 0")
    BorrowRecord findActiveBorrowRecordByBookId(int bookId);

}
