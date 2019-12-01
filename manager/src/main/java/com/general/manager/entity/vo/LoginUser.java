package com.general.manager.entity.vo;

/**
 * @author: Hens
 * @DateTime: 2019/11/30 11:39
 * @Description: TODO
 */
public class LoginUser {

    private String username;

    private String password;

    public LoginUser() {
    }

    public LoginUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
