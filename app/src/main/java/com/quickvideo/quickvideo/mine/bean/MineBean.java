package com.quickvideo.quickvideo.mine.bean;

/**
 * Created by powersen on 2017/12/7.
 */

public class MineBean {
    private String name;
    private int icon;

    public MineBean() {

    }

    public MineBean(String name, int icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
