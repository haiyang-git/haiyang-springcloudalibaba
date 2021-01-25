package com.haiyang.sca.openapi.vo;

import lombok.Data;
import org.apache.dubbo.rpc.AppResponse;
import org.apache.http.HttpStatus;

import java.io.Serializable;

/**
 * @author Administrator
 */
@Data
public class R<T> implements Serializable {
    /**
     * 返回值代码
     */
    private Integer code;
    /**
     * 返回的附加提示信息
     */
    private String message;
    /**
     * 返回的数据 若没有则为空
     */
    private T data;

    private R() {
    }

    private R(T data) {
        this.code = HttpStatus.SC_OK;
        this.message = "成功";
        this.data = data;
    }

    private R(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private R(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> RBuilder builder() {
        return new RBuilder();
    }

    /**
     * 成功
     *
     * @return
     */
    public static R success() {
        return builder().code(HttpStatus.SC_OK).build();
    }

    /**
     * 成功返回数据
     *
     * @return
     */
    public static <T>R success(T data) {
        return builder().code(HttpStatus.SC_OK).message("成功").data(data).build();
    }

    /**
     * 出错消息提示 该方法要求必须返回错误消息
     *
     * @param message 错误消息
     * @return
     */
    public static R error(Integer code, String message) {
        return builder().code(code).message(message).build();
    }


    /**
     * 内部类 用来解决static 不能使用泛型的问题
     *
     * @param <T>
     */
    private static class RBuilder<T> {
        /**
         * 返回值代码
         */
        private Integer code;
        /**
         * 返回的附加提示信息
         */
        private String message;
        /**
         * 返回的数据 若没有则为空
         */
        private T data;

        /**
         * 构建一个AppResponse 对象
         *
         * @return
         */
        public R build() {
            return new R(this.code, this.message, this.data);
        }

        /**
         * 链式调用 设置状态码
         *
         * @param  code
         * @return
         */
        public RBuilder code(Integer code) {
            this.code = code;
            return this;
        }

        /**
         * 链式调用 设置提示信息
         *
         * @param message 提示信息
         * @return
         */
        public RBuilder message(String message) {
            this.message = message;
            return this;
        }

        /**
         * 链式调用 设置返回数据
         *
         * @param data 返回数据
         * @return
         */
        public RBuilder data(T data) {
            this.data = data;
            return this;
        }
    }

}
