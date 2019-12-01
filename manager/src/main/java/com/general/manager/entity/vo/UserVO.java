package com.general.manager.entity.vo;

import com.general.manager.entity.Role;
import lombok.Data;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
@Data
public class UserVO implements Serializable {

    private Long uid;

    private String username;

    private String nickname;

    private Set<Role> roles;

    private Date createTime;

    private Date updateTime;

    public UserVO(Long uid, String username, String nickname) {
        this.uid = uid;
        this.username = username;
        this.nickname = nickname;
    }

    public UserVO() {
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", roles=" + roles +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
