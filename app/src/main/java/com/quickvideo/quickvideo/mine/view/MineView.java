package com.quickvideo.quickvideo.mine.view;

import com.quickvideo.quickvideo.mine.bean.Bean;
import com.quickvideo.quickvideo.mine.bean.MineBean;

import java.util.List;

/**
 * Created by powersen on 2017/12/7.
 */

public interface MineView {
    void showOk();

    void showError();

    void showAdapter(List<Bean> list);
}
