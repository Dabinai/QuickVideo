package com.quickvideo.quickvideo.classification.presenter;

import android.util.Log;

import com.quickvideo.quickvideo.classification.model.IClassificationModel;
import com.quickvideo.quickvideo.classification.view.IClassificationView;
import com.quickvideo.quickvideo.bean.ShouYeBean;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/12/5.
 */

public class IClassificationPresenter {
    private final IClassificationView view;
    private final IClassificationModel model;

    public IClassificationPresenter(IClassificationView view) {
        this.view = view;
        this.model = new IClassificationModel();
    }



        public void getMovieData(){
            Observable<ShouYeBean> observable = model.getDatas();
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ShouYeBean>() {


                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(ShouYeBean shouYeBean) {
                            if (shouYeBean != null) {
                                view.getDataSeccess();
                                view.showAdapter(shouYeBean.ret.list);
                                Log.d("-----", "onNext: " + shouYeBean.toString());
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            view.getDataSeccess();
                        }

                        @Override
                        public void onComplete() {

                        }


                    });
        }
}
