package com.quickvideo.quickvideo.RecommendPackage.RecommendVModePackage;

import com.quickvideo.quickvideo.bean.ShouYeBean;
import com.quickvideo.quickvideo.clientutils.API;
import com.quickvideo.quickvideo.clientutils.ApiService;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Long° Engagement on 2017/12/5.
 */

public class RecModel implements RecommendMode {
    ShouYeBean shouYeBean;
      OnFinish onFinish;
    public  interface OnFinish{
        void  onfinishLister(ShouYeBean shouYeBean);
    }

    public RecModel(OnFinish onFinish) {
        this.onFinish = onFinish;
    }

    @Override
    public void gethomedata() {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = build.create(ApiService.class);
        Observable<ShouYeBean> gethome = apiService.getShouYeData();
        gethome.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShouYeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ShouYeBean shouYeBean) {
                            shouYeBean = shouYeBean;
                            onFinish.onfinishLister(shouYeBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }

                });
    }
}
