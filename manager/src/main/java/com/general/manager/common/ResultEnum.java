package com.general.manager.common;

/**
 * @author: Hens
 * @DateTime: 2019/11/29 17:57
 * @Description: TODO
 */
public enum ResultEnum {
    SUCCESS(1, "success"),
    FAIL(-1, "fail"),
    PASSWORD_ERRO(1004, "password_invalid"),
    USERNAME_ERRO(1003, "user_not_exists"),
    USERNAME_ISEXIST(1005, "username had been exist"),
    SERVER_ERROR(500, "服务器错误");

    private Integer code;

    private String message;

    public Integer code(){
        return this.code;
    }

    public String message(){return this.message; }

    ResultEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode(String name) {
        for(ResultEnum resultEnum: ResultEnum.values()){
            if(resultEnum.name().equals(name)){
                return resultEnum.code;
            }
        }
        return null;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage(String name) {
        for(ResultEnum resultEnum: ResultEnum.values()){
            if(resultEnum.name().equals(name)){
                return resultEnum.message;
            }
        }
        return name;
    }

    public void setMessage(String message) {

        this.message = message;
    }
}
