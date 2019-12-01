package com.general.manager.entity.dto;

import com.general.manager.entity.Menu;
import lombok.Data;

import java.util.Set;
@Data
public class RoleDTO {

    private Integer rid;

    private String role;

    private Integer weight;

    private Set<Menu> menus;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }
}
