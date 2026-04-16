package org.example.springboot3.Entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.jspecify.annotations.NonNull;

public class User {
    //建议用Integer，int不传参的话默认0，Integer默认NULL
    //id无需加注释限制，因为已经设定自动填充
    private Integer id;
    private String role;

    @NotBlank(message = "姓名不能为空")
    @Pattern(regexp = "^\\S+$", message = "姓名不能包含空格")
    private String name;

    @Min(value = 1,message = "密码必须大于0")
    private Integer password;
    public User(){

    }

    public User(Integer id, String name, Integer password, String role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;

    }
    public int getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getPassword() {
        return password;
    }
    public void setPassword(int password) {
        this.password = password;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", password=" + password + ", role =" + role + "]";
    }
}
