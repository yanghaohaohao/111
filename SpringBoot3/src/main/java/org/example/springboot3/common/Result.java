package org.example.springboot3.common;

public class Result<T> {
    private int total;
    private Integer code;
    private String msg;
    private T data;
    private int size;
    private int totalPages;
    private int currentPage;

    public static final String MSG_SUCCESS = "操作成功";
    public static final String MSG_ADD_SUCCESS = "新增成功";
    public static final String MSG_UPDATE_SUCCESS = "更新成功";
    public static final String MSG_DELETE_SUCCESS = "删除成功";
    public static final String MSG_QUERY_SUCCESS = "查询成功";

    private Result(){}

    private Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private Result(Integer code, String msg, T data, int size, int total, int totalPages, int currentPage) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.size = size;
        this.total = total;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
    }

    public Integer getCode() { return code; }
    public String getMsg() { return msg; }
    public T getData() { return data; }
    public int getSize() { return size; }
    public int getTotal() { return total; }
    public int getTotalPages() { return totalPages; }
    public int getCurrentPage() { return currentPage; }

    public static <T> Result<T> success(T data) {
        return new Result<T>(200, MSG_SUCCESS, data, 0, 0, 0, 0);
    }

    public static <T> Result<T> success(T data, int size, int total, int totalPages, int currentPage) {
        return new Result<T>(200, MSG_SUCCESS, data, size, total, totalPages, currentPage);
    }

    public static <T> Result<T> success() {
        return new Result<T>(200, MSG_SUCCESS, null);
    }
    public static <T> Result<T> success(String msg, T data) {
        return new Result<T>(200, msg, data);
    }
    public static <T> Result<T> success(String msg) {
        return new Result<T>(200, msg, null);
    }
    public static <T> Result<T> error(Integer code, String msg) {
        return new Result<T>(code, msg, null);
    }
    public static <T> Result<T> error(Integer code, String msg, T data) {
        return new Result<T>(code, msg, data);
    }
    public static <T> Result<T> error(String msg) {
        return new Result<T>(500, msg, null);
    }
    public static <T> Result<T> error() {
        return new Result<T>(500, "操作失败", null);
    }
}