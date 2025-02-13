package com.ykkj.model;

/**
 * @author lw
 * @version 1.0.0
 * @ClassName ResultMsg.java
 * @Description TODO
 * @createTime 2022年09月08日
 */

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 封装统一返回结果
 */
@Data
@AllArgsConstructor
public class ResultMsg {


    private String message;
    //结果码
    private Integer code = 200;
    private Boolean success;
    private Object details;

    public ResultMsg(boolean success, Object details) {
        this.success = success;
        this.details = details;
    }

    public ResultMsg(Integer code, boolean success, String msg) {
        this.code = code;
        this.success = success;
        this.message = msg;
    }

    public ResultMsg(boolean success) {
        this.success = success;
    }

    public ResultMsg(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public static ResultMsg success(Object obj){
        return new ResultMsg(true,obj);
    }

    public static ResultMsg success(){
        return new ResultMsg(true);
    }

    public static ResultMsg error(String message){
        return new ResultMsg(false,message);
    }

    public static ResultMsg error(Integer code,String message){
        return new ResultMsg(code,false,message);
    }

}

