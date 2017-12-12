package com.quickvideo.quickvideo.discover.m;

import com.quickvideo.quickvideo.bean.PinDaoBean;

/**
 * Created by Dabin on 2017/12/10.
 */

public interface DisIModel {
    void loadfFragData(BackFragCall backFragCall);

    interface BackFragCall{
        void completeFrag(PinDaoBean pinDaoBean);
    }

}
