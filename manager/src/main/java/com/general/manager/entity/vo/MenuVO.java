package com.general.manager.entity.vo;

import java.awt.*;
import java.util.Objects;
import java.util.Set;

/**
 * @author: Hens
 * @DateTime: 2019/11/30 18:09
 * @Description: TODO
 */
public class MenuVO {

    private Integer mid;
    /**
     * 菜单名
     */
    private String menuName;

    /**
     * 路径
     */
    private String path;

    /**
     * 权重
     */
    private Integer menuOrder;

    /**
     * 是否展示
     */
    private Byte isShow;

    /**
     * 父类id
     */
    private Byte parentId;

    private static final long serialVersionUID = 1L;

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    public Byte getParentId() {
        return parentId;
    }

    public void setParentId(Byte parentId) {
        this.parentId = parentId;
    }

    public Integer getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    public Byte getIsShow() {
        return isShow;
    }

    public void setIsShow(Byte isShow) {
        this.isShow = isShow;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        MenuVO menu = (MenuVO) o;
        return Objects.equals(mid, menu.mid) &&
                Objects.equals(menuName, menu.menuName) &&
                Objects.equals(path, menu.path) &&
                Objects.equals(menuOrder, menu.menuOrder) &&
                Objects.equals(isShow, menu.isShow) &&
                Objects.equals(parentId, menu.parentId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mid, menuName, path, menuOrder, isShow, parentId);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "mid=" + mid +
                ", menuName='" + menuName + '\'' +
                ", path='" + path + '\'' +
                ", menuOrder=" + menuOrder +
                ", isShow=" + isShow +
                ", parentId=" + parentId +
                '}';
    }

    public MenuVO() {
    }

    public MenuVO(Integer mid, String menuName, String path, Integer menuOrder, Byte isShow, Byte parentId) {
        this.mid = mid;
        this.menuName = menuName;
        this.path = path;
        this.menuOrder = menuOrder;
        this.isShow = isShow;
        this.parentId = parentId;
    }
}
