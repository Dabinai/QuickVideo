package com.quickvideo.quickvideo.mine.model;

import com.quickvideo.quickvideo.R;
import com.quickvideo.quickvideo.mine.bean.Bean;
import com.quickvideo.quickvideo.mine.bean.MineBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by powersen on 2017/12/7.
 */

public class MineModel implements MineImodel {
    //图片的文字标题
    private String[] titles = new String[]
            {"pic1", "pic2", "pic3" };
    //图片ID数组
    private int[] images = new int[]{
            R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round,
            R.mipmap.ic_launcher_round};
    private List<Bean> list;

    @Override
    public List<Bean> getData() {
       Bean bean = new Bean();
        list = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            bean.setName(list.get(i).getName());
            bean.setPic(list.get(i).getPic());
            bean.setTag(list.get(i).getTag());
            list.add(bean);
        }
        return list;
    }
}
