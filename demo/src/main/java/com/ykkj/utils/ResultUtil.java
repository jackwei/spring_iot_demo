package com.ykkj.utils;


/**
 * 统一返回结果集
 */
public class ResultUtil {

    /**
     * 返回成功，传入返回体具体出參
     * @param object
     * @return
     */
    public static Result success(Object object){
        Result result = new Result();
        result.setStatus(0);
        result.setResult(true);
        result.setMsg("success");
        result.setData(object);
        return result;
    }
    /*
     * 返回正确信息，在已知的范围内
     * @param exceptionEnum
     * @return
     */
    public static Result success(ExceptionEnum exceptionEnum){
        Result result = new Result();
        result.setStatus(exceptionEnum.getCode());
        result.setResult(true);
        result.setMsg(exceptionEnum.getMsg());
        result.setData(null);
        return result;
    }
    /**
     * 提供给部分不需要出參的接口
     * @return
     */
    public static Result success(){
        return success("");
    }

    /**
     * 自定义错误信息
     * @param code
     * @param msg
     * @return
     */
    public static Result error(Integer code,String msg){
        Result result = new Result();
        result.setStatus(code);
        result.setResult(false);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }
    /**
     * 返回异常，传入返回体具体出參
     * @param object
     * @return
     */
    public static Result error(Object object){
        Result result = new Result();
        result.setStatus(-1);
        result.setResult(false);
        result.setMsg("fail");
        result.setData(object);
        return result;
    }
    /**
     * 返回异常，传入返回体具体出參
     * @param object
     * @return
     */
    public static Result error(String msg,Object object){
        Result result = new Result();
        result.setStatus(-1);
        result.setResult(false);
        result.setMsg(msg);
        result.setData(object);
        return result;
    }
    /**
     * 返回异常信息，在已知的范围内
     * @param exceptionEnum
     * @return
     */
    public static Result error(ExceptionEnum exceptionEnum){
        Result result = new Result();
        result.setStatus(exceptionEnum.getCode());
        result.setResult(false);
        result.setMsg(exceptionEnum.getMsg());
        result.setData(null);
        return result;
    }
    

    /**
     * 自定义错误信息status统一为-1
     * @param msg
     * @return
     */
    public static Result error(String msg){
        Result result = new Result();
        result.setStatus(-1);
        result.setResult(false);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }
}
