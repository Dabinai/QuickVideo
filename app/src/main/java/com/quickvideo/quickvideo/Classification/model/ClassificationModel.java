package com.quickvideo.quickvideo.Classification.model;

import com.quickvideo.quickvideo.bean.ShouYeBean;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/12/5.
 */

public interface ClassificationModel {
    Observable<ShouYeBean> getDatas();
}
