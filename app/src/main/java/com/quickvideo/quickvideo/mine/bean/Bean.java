package com.quickvideo.quickvideo.mine.bean;

/**
 * Created by lenovo on 2017/12/8.
 */

public class Bean {
    public String name;
    public String pic;
    public int tag;

    public Bean() {

    }

    public Bean(String name, String pic) {
        this.name = name;
        this.pic = pic;
    }

    public Bean(String name, String pic, int tag) {
        this.name = name;
        this.pic = pic;
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "name='" + name + '\'' +
                ", pic='" + pic + '\'' +
                ", tag=" + tag +
                '}';
    }
}
