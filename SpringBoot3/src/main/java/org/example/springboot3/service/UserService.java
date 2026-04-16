package org.example.springboot3.service;


import org.example.springboot3.Entity.Book;
import org.example.springboot3.Entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();
    User findById(int id);
    Integer adduser(User user);
    void updateuser(User user);
    void deleteuser(int id);
    boolean isNameExists(String name);
    List<User> findByPage(int page, int size) ;
    public int countAll();
    String login(int id, String password);

}
