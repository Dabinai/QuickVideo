package com.quickvideo.quickvideo.VideoList.View;

import com.quickvideo.quickvideo.bean.PinDaoBean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/6.
 */

public interface VideoListView {

    void showError();

    void showOk();

    void showData(List<PinDaoBean.RetBean.ListBean> vlist);
}
