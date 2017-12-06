package com.quickvideo.quickvideo.VideoList.Model;

import com.quickvideo.quickvideo.bean.PinDaoBean;
import com.quickvideo.quickvideo.client.API;
import com.quickvideo.quickvideo.client.ApiService;
import com.quickvideo.quickvideo.client.ClientUtils;

import java.util.HashMap;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/12/6.
 */

public class IVideoListModel implements VideoListModel{
    @Override
    public Observable<PinDaoBean> getData() {
        HashMap<String,String> map = new HashMap<>();
        map.put("catalogId","402834815584e463015584e539330016");
        map.put("pnum","5");
        return ClientUtils.getclientData(API.BASE_URL).create(ApiService.class).getPinDaoData(map);
    }
}