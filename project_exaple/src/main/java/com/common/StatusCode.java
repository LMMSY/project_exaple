package com.common;

import lombok.Getter;

import java.io.Serializable;

@Getter
public enum StatusCode implements Serializable {

    SUCCESS(0,"success"),
    SEARCH_NULL(0,"查询无数据"),
    NO_BEACON(1002,"工人附近无信标"),
    DATABASE_EXCEPTION(1003,"数据库异常"),
    UNKNOWN_EXCEPTION(9999,"未知异常");

    private Integer code;
    private String message;
    StatusCode(Integer code, String message){
        this.code = code;
        this.message = message;
    }

}
