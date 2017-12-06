package com.quickvideo.quickvideo.VideoList.Presenter;

import com.quickvideo.quickvideo.VideoList.Model.IVideoListModel;
import com.quickvideo.quickvideo.VideoList.Model.VideoListModel;
import com.quickvideo.quickvideo.VideoList.View.VideoListView;
import com.quickvideo.quickvideo.bean.PinDaoBean;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/12/6.
 */

public class VideoListPresenter {
    private final VideoListModel model;
    private final VideoListView view;

    public VideoListPresenter(VideoListView view) {
        this.view = view;
        this.model = new IVideoListModel();
    }

    public void getVideoData(String pnum){
        Observable<PinDaoBean> observable = model.getData(pnum);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PinDaoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PinDaoBean pinDaoBean) {
                        if(pinDaoBean != null){
                            view.showOk();
                            view.showData(pinDaoBean.ret.list);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                            view.showError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
