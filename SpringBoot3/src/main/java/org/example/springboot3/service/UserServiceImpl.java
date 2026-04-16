package org.example.springboot3.service;

import org.example.springboot3.Entity.Book;
import org.example.springboot3.Entity.User;
import org.example.springboot3.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {

        return userMapper.findAll();
    }

    @Override
    public int countAll() {
        return userMapper.countAll();
    }

    @Override
    public List<User> findByPage(int page, int size) {
        int offset = (page - 1) * size;
        return userMapper.findUserByPage(offset, size);
    }

    @Override
    public User findById(int id) {
        return userMapper.findById(id);
    }

    @Override
    public Integer adduser(User user) {
        if (userMapper.findByName(user.getName()) != null) {
            throw new IllegalArgumentException("用户名已存在: " + user.getName());
        }
        int maxId = userMapper.findMaxId();
        int newId = maxId >= 1010 ? maxId + 1 : 1010;
        user.setId(newId);
        userMapper.insert(user);
        return newId;
    }

    @Override
    public String login(int id, String password){
        if(userMapper.findPassword(id).equals(password)){
            return "login success";
        }
        return "login fail";
    }

    @Override
    public boolean isNameExists(String name) {
        return userMapper.findByName(name) != null;
    }

    @Override
    public void updateuser(User user) {
        User existing = userMapper.findById(user.getId());
        if (existing == null) {
            throw new IllegalArgumentException("用户不存在，无法更新");
        }
        userMapper.update(user);
    }

    @Override
    public void deleteuser(int id) {
        userMapper.deleteById(id);
    }

}
