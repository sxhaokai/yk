package com.yunke.entity;

/**
 * Created by hao.kai on 2017/6/8.
 */

public class YunkeStatusEntity implements YunkeEntity {
    private String message;
    private String code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
