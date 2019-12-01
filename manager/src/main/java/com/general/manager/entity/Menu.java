package com.general.manager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Objects;

/**
 * menu
 * @author 
 */
@TableName("menu")
public class Menu implements Serializable {
    @TableId(type=IdType.AUTO)
    private Integer mid;
    /**
     * 菜单名
     */
    @TableField(value = "menu_name")
    private String menuName;

    /**
     * 路径
     */
    private String path;

    /**
     * 权重
     */
    @TableField(value = "menu_order")
    private Integer menuOrder;

    /**
     * 是否展示
     */
    @TableField(value = "is_show")
    private Byte isShow;

    /**
     * 父类id
     */
    @TableField(value = "parent_id")
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
        Menu menu = (Menu) o;
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

    public Menu() {
    }

    public Menu(Integer mid, String menuName, String path, Integer menuOrder, Byte isShow, Byte parentId) {
        this.mid = mid;
        this.menuName = menuName;
        this.path = path;
        this.menuOrder = menuOrder;
        this.isShow = isShow;
        this.parentId = parentId;
    }
}