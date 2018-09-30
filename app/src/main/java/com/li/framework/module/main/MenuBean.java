package com.li.framework.module.main;

/**
 * @author li
 * @since 2018/9/29
 */
public class MenuBean {

    private String title;
    private int id;

    public MenuBean(String title, int id) {
        this.title = title;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
