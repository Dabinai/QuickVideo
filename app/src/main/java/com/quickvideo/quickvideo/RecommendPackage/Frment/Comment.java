package com.quickvideo.quickvideo.RecommendPackage.Frment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quickvideo.quickvideo.R;
import com.quickvideo.quickvideo.RecommendPackage.adapter.PLAdapter;
import com.quickvideo.quickvideo.bean.FirsEvent;
import com.quickvideo.quickvideo.bean.PingLunBean;
import com.quickvideo.quickvideo.clientutils.API;
import com.quickvideo.quickvideo.clientutils.ApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Long° Engagement on 2017/12/6.
 */

public class Comment extends Fragment {
    String url;
    @BindView(R.id.comment_rv)
    RecyclerView commentRv;
    Unbinder unbinder;
    String wjj;
    @BindView(R.id.mySwipeRefreshLayouta)
    SwipeRefreshLayout mySwipeRefreshLayouta;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.comment, null);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        url = getActivity().getIntent().getStringExtra("id");
        commentRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        Retrofit build2 = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService2 = build2.create(ApiService.class);
        HashMap<String, String> map2 = new HashMap<>();
        map2.put("mediaId", wjj);
        Observable<PingLunBean> getdatil = apiService2.getPLunData(map2);
        getdatil.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PingLunBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(PingLunBean pingLunBean) {
                        pingLunBean = pingLunBean;
                        commentRv.setVisibility(View.VISIBLE);
                        //设置适配器
                        final PLAdapter datilAdapter = new PLAdapter(getActivity(), pingLunBean);

                        commentRv.setAdapter(datilAdapter);
                        mySwipeRefreshLayouta.setColorSchemeResources(android.R.color.holo_blue_bright,
                                android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
                        //设置下拉刷新监听
                        mySwipeRefreshLayouta.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                            @Override
                            public void onRefresh() {
                                new Handler().postDelayed(new Runnable() {
                                    public void run() {
                                        datilAdapter.notifyDataSetChanged();
                                        //停止刷新动画
                                        mySwipeRefreshLayouta.setRefreshing(false);
                                    }
                                }, 3000);
                            }
                        });
                    }
                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
        return view;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(sticky = true)
    public void wjj(FirsEvent firsEvent) {
        wjj = firsEvent.getWjj();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
