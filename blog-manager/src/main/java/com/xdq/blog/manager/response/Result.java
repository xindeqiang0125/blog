package com.xdq.blog.manager.response;

public class Result {
    private int code;
    private Object data;

    public static Result success(Object data) {
        return new Result(1, data);
    }

    public static Result failed(Object data) {
        return new Result(0, data);
    }

    private Result(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
