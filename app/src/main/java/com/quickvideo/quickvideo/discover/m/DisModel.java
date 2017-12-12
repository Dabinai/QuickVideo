package com.quickvideo.quickvideo.discover.m;

import com.quickvideo.quickvideo.bean.PinDaoBean;
import com.quickvideo.quickvideo.clientutils.ApiService;
import com.quickvideo.quickvideo.clientutils.ClientUtils;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Dabin on 2017/12/10.
 */

public class DisModel implements DisIModel{
    private String catalogId;
    private String pnum;


    @Override
    public void loadfFragData(final BackFragCall backFragCall) {
        HashMap<String, String> map = new HashMap<>();
        map.put("catalogId", catalogId);
        map.put("pnum", pnum);
        ApiService apiService = ClientUtils.getDefault().create(ApiService.class);
        Observable<PinDaoBean> pinDaoData = apiService.getPinDaoData(map);
        pinDaoData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PinDaoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PinDaoBean pinDaoBean) {
                        backFragCall.completeFrag(pinDaoBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getUrl(String catalogId,String pnum){
        this.catalogId = catalogId;
        this.pnum = pnum;

    }
}
