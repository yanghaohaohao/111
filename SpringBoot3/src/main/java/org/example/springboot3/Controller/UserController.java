package org.example.springboot3.Controller;



import org.example.springboot3.Entity.Book;
import org.example.springboot3.Entity.User;
import org.example.springboot3.annotation.AdminOnly;
import org.example.springboot3.common.JwtUtil;
import org.example.springboot3.common.Result;
import org.example.springboot3.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.util.List;

//与网页连接的接口

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //查询
    @AdminOnly
    @GetMapping("/user/all")
    public Result<List<User>> getUser() {
        return Result.success(userService.findAll());
    }

    @AdminOnly
    @GetMapping("/user/{id}")
    public Result<User> getUserById(@PathVariable int id) {

        return Result.success(userService.findById(id));
    }

    @AdminOnly
    @GetMapping("/user/page")
    public Result<List<User>> getBookByPage(@RequestParam(required = false, defaultValue = "1") int page, @RequestParam(required = false, defaultValue = "10") int size) {
        List<User> users = userService.findByPage(page, size);
        int total = userService.countAll();
        int totalPages = (total + size - 1) / size;
        return Result.success(users, size, total, totalPages, page);
    }

    //修改
    @AdminOnly
    @PutMapping("/user")
    public Result<String> putUser(@Valid @RequestBody User user) {
        userService.updateuser(user);
        return Result.success("user updated success!");
    }

    //删除
    @AdminOnly
    @DeleteMapping("/user/{id}")
    public Result<String> deleteUserById(@PathVariable int id) {
        userService.deleteuser(id);
        return Result.success("delete success!");
    }

    //增加、逻辑用post
    @PostMapping("/user/new")
    public Result<Integer> postUser(@Valid @RequestBody User user) {
        Integer newUserId = userService.adduser(user);
        return Result.success("add user success!", newUserId);
    }

    @PostMapping("/user/login")
    public Result<String> login(@RequestParam int id, @RequestParam String password, HttpSession session) {
        String result = userService.login(id, password);
        if ("login success".equals(result)) {
            User user = userService.findById(id);
            String token = JwtUtil.generateToken(id, user.getRole());
            return Result.success("登录成功", token);
        }
        return Result.error(401, "用户名或密码错误");
    }





}
