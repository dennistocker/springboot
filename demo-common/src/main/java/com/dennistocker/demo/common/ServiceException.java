package com.dennistocker.demo.common;

/**
 * @date 2021/5/25 10:48 上午
 */
public class ServiceException extends RuntimeException {

    private Integer code;
    private String msg;

    public ServiceException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public ServiceException(ResultCode code) {
        super(code.getMsg());
        this.code = code.getCode();
        this.msg = code.getMsg();
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
