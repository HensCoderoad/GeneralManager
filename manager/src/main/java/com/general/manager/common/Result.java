package com.general.manager.common;

import java.io.Serializable;

/**
 * @author: Hens
 * @DateTime: 2019/11/29 17:56
 * @Description: to response data
 */
public class Result implements Serializable {
    // 返回状态码
    private Integer code;
    // 返回状态
    private String status;
    // 返回数据
    private Object data;
    /**
     * 成功无数据传入
     * @return
     */
    public static Result success(){
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS);
        result.setStatus(ResultEnum.SUCCESS);
        return result;
    }
    /**
     * 成功返回数据
     * @param data
     * @return
     */
    public static Result success(Object data){
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS);
        result.setData(data);
        result.setStatus(ResultEnum.SUCCESS);
        return result;
    }

    /**
     * 失败
     * @param resultEnum
     * @return
     */
    public static Result failure(ResultEnum resultEnum){
        Result result = new Result();
        result.setCode(resultEnum);
        return result;
    }

    /**
     * 失败有返回值
     * @param resultEnum
     * @param object
     * @return
     */
    public static Result failure(ResultEnum resultEnum, Object object){
        Result result = new Result();
        result.setCode(resultEnum);
        result.setData(object);
        return result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(final ResultEnum code) {
        this.code = code.code();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final ResultEnum message) {
        this.status = message.message();
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
