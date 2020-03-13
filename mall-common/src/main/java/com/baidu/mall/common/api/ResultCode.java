package com.baidu.mall.common.api;

/**
 * 枚举一些常用API码
 */
public enum ResultCode implements ErrorCode {
    SUCCESS(200,"操作成功"),
    FAILED(500,"操作失败"),
    VALIDATA_FAILED(404,"参数校验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限");
    private int code;
    private String msg;

    private ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;

    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
