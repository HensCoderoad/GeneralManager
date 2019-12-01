package com.general.manager.common;

/**
 * @author: Hens
 * @DateTime: 2019/12/1 1:26
 * @Description: 自定义异常
 */
public class DefineException extends Exception {
    private ExceptionEnum defineEnum;

    private String message;

    public DefineException(ExceptionEnum defineEnum, String message) {
        super(message);
        this.defineEnum = defineEnum;
        this.message = message;
    }


    public DefineException(String message, Throwable cause, ExceptionEnum defineEnum) {
        super(message, cause);
        this.defineEnum = defineEnum;
        this.message = message;
    }

    public DefineException(ExceptionEnum defineEnum) {
        this.defineEnum = defineEnum;
    }

    public ExceptionEnum getDefineEnum() {
        return defineEnum;
    }

    public void setDefineEnum(ExceptionEnum defineEnum) {
        this.defineEnum = defineEnum;
    }

}
