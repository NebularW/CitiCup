package com.flower.result;

/**
 * @title: Result
 * @Author: Stanton JY
 * @Date: 2022/2/19 0:14
 */


import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {
    /**
     * 返回结果是否成功
     */
    //value = "返回结果是否成功"
    private boolean flag;

    /**
     * 返回的消息
     */
    //value = "返回的消息", required = true
    private String message;

    /**
     * 返回的结果集 可能是对象、集合
     */
    //value = "返回的结果集 可能是对象、集合"
    private Object data;

    /**
     * 没有返回数据只用返回两个参数
     *
     * @param flag    是否成功
     * @param message 返回信息
     */
    public Result(boolean flag, String message) {
        this.flag = flag;
        this.message = message;
    }

    /**
     * 返回结果
     *
     * @param flag    返回是否成功
     * @param message 返回信息
     * @param data    返回结果
     */
    public Result(boolean flag, String message, Object data) {
        this.flag = flag;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回
     *
     * @param message
     * @param data
     * @return
     */
    public static Result success(String message, Object data) {
        return new Result(true, message, data);
    }

    /**
     * 成功返回
     *
     * @param message
     * @return
     */
    public static Result success(String message) {
        return new Result(true, message);
    }

    /**
     * 失败返回
     *
     * @param message
     * @param data
     * @return
     */
    public static Result fail(String message, Object data) {
        return new Result(false, message, data);
    }

    /**
     * 失败返回
     *
     * @param message
     * @return
     */
    public static Result fail(String message) {
        return new Result(false, message);
    }


}
