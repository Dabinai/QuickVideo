package com.quickvideo.quickvideo.mine.model;

import com.quickvideo.quickvideo.R;
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
    private List<MineBean> list;

    @Override
    public List<MineBean> getData() {
        MineBean bean = new MineBean();
        list = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            bean.setIcon(images[i]);
            bean.setName(titles[i]);
            list.add(bean);
        }

        return list;
    }
}
