package com.haiyang.sca.openapi.base;

import lombok.Data;
import org.apache.http.HttpStatus;

@Data
public class R<T> {
    private Integer code;
    private String message;
    private T data;

    private R() {
    }

    public R(T data) {
        this.code= HttpStatus.SC_OK;
        this.message = "SC_OK";
        this.data = data;
    }

    public R(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
