package com.quickvideo.quickvideo.VideoList.Model;

import com.quickvideo.quickvideo.bean.PinDaoBean;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/12/6.
 */

public interface VideoListModel {
            Observable<PinDaoBean> getData(String pnum);
}
