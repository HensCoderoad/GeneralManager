package com.general.manager.common;

/**
 * @author: Hens
 * @DateTime: 2019/12/1 1:27
 * @Description: 异常枚举类
 */
public enum ExceptionEnum {
    // 添加信息到数据库失败
    ORDER_INSERT_FAILURE("001", "添加数据失败"),

    /**
     * 数据已存在
     */
    SUCCESS_IS_HAVE("208", "数据已存在"),

    /**
     * 没有结果
     */
    NOT_DATA("911", "没有结果"),

    /**
     * 没有登录
     */
    NOT_LOGIN("600", "没有登录"),

    /**
     * 发生异常
     */
    EXCEPTION("401", "发生异常"),

    /**
     * 系统错误
     */
    SYS_ERROR("402", "系统错误"),

    /**
     * 参数错误
     */
    PARAMS_ERROR("403", "参数错误 "),

    /**
     * 不支持或已经废弃
     */
    NOT_SUPPORTED("410", "不支持或已经废弃"),

    /**
     * AuthCode错误
     */
    INVALID_AUTHCODE("444", "无效的AuthCode"),

    /**
     * 太频繁的调用
     */
    TOO_FREQUENT("445", "太频繁的调用"),

    /**
     * 未知的错误
     */
    UNKNOWN_ERROR("499", "未知错误"),

    /**
     * 未设置方法
     */
    NOT_METHOD("4004", "未设置方法");;

    private String code;

    private String message;

    public static String getMessage(String code) {
        for (ExceptionEnum d : ExceptionEnum.values()) {
            if (d.getCode().equals(code)) {
                return d.getMessage();
            }
        }
        return null;
    }

    ExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
