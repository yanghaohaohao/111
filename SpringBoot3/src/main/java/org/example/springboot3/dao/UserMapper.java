package org.example.springboot3.dao;

import org.apache.ibatis.annotations.*;
import org.example.springboot3.Entity.Book;
import org.example.springboot3.Entity.User;

import java.util.List;
//接口层
/*
* Mapper注解：
*   1、使此接口能被Mybatis框架识别
*   2、SpringBoot会自动创建该接口的实现类对象，交给ioc管理
* */

@Mapper
public interface UserMapper {
    //查找
    @Select("select * from User")
    public abstract List<User> findAll();

    @Select("select * from User where id = #{id}")
    public abstract User findById(int id);

    @Select("select * from User LIMIT #{offset},#{size}")
    public abstract List<User> findUserByPage(
            @Param("offset")Integer offset, @Param("size")Integer size);

    @Select("select * from User where name = #{name}")
    public abstract User findByName(String name);

    @Select("select ifnull(max(id), 1010-1) from User")
    public abstract int findMaxId();

    @Select("select count(*) from User")
    public abstract int countAll();

    //增加
    @Insert("insert into User (id,name,password) values (#{id},#{name},#{password})")
    public abstract void insert(User user);

    //删除
    @Delete("delete from User where id = #{id}")
    public abstract void deleteById(int id);

    //更新（不允许修改id）
    @Update("update User set name = #{name}, password = #{password} where id = #{id}")
    public void update(User user);

    //登录
    @Select("select password from User where id = #{id}")
    public String findPassword(int id);



}
