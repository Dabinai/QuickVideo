package com.quickvideo.quickvideo.Classification.model;


import com.quickvideo.quickvideo.bean.ShouYeBean;
import com.quickvideo.quickvideo.client.API;
import com.quickvideo.quickvideo.client.ApiService;
import com.quickvideo.quickvideo.client.ClientUtils;


import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/12/5.
 */

public class IClassificationModel implements ClassificationModel {
    @Override
    public Observable<ShouYeBean> getDatas() {

        return ClientUtils.getclientData(API.BASE_URL).create(ApiService.class).getShouYeData();
    }
}
