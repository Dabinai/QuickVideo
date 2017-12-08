package com.quickvideo.quickvideo.bean;

import android.widget.ImageView;

/**
 * Created by xue on 2017-12-07.
 */

public class MenuBean {

    private String menu_item_name;
    private int menu_item_icon;

    public MenuBean(String menu_item_name, int menu_item_icon) {
        this.menu_item_name = menu_item_name;
        this.menu_item_icon = menu_item_icon;
    }

    public String getMenu_item_name() {
        return menu_item_name;
    }

    public void setMenu_item_name(String menu_item_name) {
        this.menu_item_name = menu_item_name;
    }

    public int getMenu_item_icon() {
        return menu_item_icon;
    }

    public void setMenu_item_icon(int menu_item_icon) {
        this.menu_item_icon = menu_item_icon;
    }
}
