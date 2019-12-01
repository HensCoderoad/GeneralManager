package com.general.manager.common;

import java.io.Serializable;

/**
 * @author: Hens
 * @DateTime: 2019/12/1 0:48
 * @Description: 分页数据
 */
public class Pagenation implements Serializable {
    /**
     * 总数据量
     */
    private Long total;
    /**
     * 当前id
     */
    private Long current;
    /**
     * 总页数
     */
    private Long pages;
    /**
     * 每页数量
     */
    private Long size;

    public Pagenation() {
    }

    @Override
    public String toString() {
        return "Pagenation{" +
                "total=" + total +
                ", current=" + current +
                ", pages=" + pages +
                ", size=" + size +
                '}';
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getCurrent() {
        return current;
    }

    public void setCurrent(Long current) {
        this.current = current;
    }

    public Long getPages() {
        return pages;
    }

    public void setPages(Long pages) {
        this.pages = pages;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Pagenation(Long total, Long current, Long pages, Long size) {
        this.total = total;
        this.current = current;
        this.pages = pages;
        this.size = size;
    }
}
