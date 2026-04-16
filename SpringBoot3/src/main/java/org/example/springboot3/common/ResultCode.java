package org.example.springboot3.common;
//状态码，未使用
public class ResultCode {
    public static final int SUCCESS = 200;
    public static final int ERROR = 500;
    public static final int NOT_FIND = 404;     //查询失败
    public static final int BAD_REQUEST = 400 ; //参数错误，状态错误
    public static final int UNAUTHORIZED = 401 ;//未登录
    public static final int FORBIDDEN = 403 ;   //无权限


}
