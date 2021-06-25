package com.dennistocker.demo.common;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @date 2021/5/24 5:38 下午
 */

@Data
public class Result<T> implements Serializable {
    private Integer code = 0;
    private String msg = "success";
    private T data;

    private Result (T data) {
        this.data = data;
    };

    private Result (Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(data);
    }

    public static Result<String> failure(String msg) {
        return Result.failure(ResultCode.FAILED.getCode(), msg);
    }

    public static Result<String> failure(Integer code, String msg) {
        return Result.failure(code, msg, "");
    }

    public static <T> Result<T> failure(Integer code, String msg, T data) {
        return new Result<>(code, msg, data);
    }

    public static Result<String> failure(ResultCode code) {
        return Result.failure(code.getCode(), code.getMsg());
    }

    public static Result<String> failure(HttpStatus status) {
        return Result.failure(status.value(), status.getReasonPhrase());
    }

    public static Result<String> failure(ServiceException e) {
        return Result.failure(e.getCode(), e.getMsg(), e.getMessage());
    }
}
