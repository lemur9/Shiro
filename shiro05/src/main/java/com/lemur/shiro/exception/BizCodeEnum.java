package com.lemur.shiro.exception;

import lombok.Getter;

public enum BizCodeEnum {
    UNKNOWN_EXCEPTION(10000, "系统未知异常"),
    VAILD_EXCEPTION(10001, "参数格式校验失败"),

    COMMON_EXCEPTION(11111,"通用异常");

    @Getter
    private final int code;
    @Getter
    private final String message;

    BizCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
