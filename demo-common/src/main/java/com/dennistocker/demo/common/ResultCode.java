package com.dennistocker.demo.common;

/**
 * @date 2021/5/25 10:19 上午
 */
public enum ResultCode {

    SUCCESS(0, "success"),
    FAILED(1, "failed"),
    INVALID_PARAMETER(2, "Invalid Parameter");


    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
