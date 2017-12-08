package com.quickvideo.quickvideo.RecommendPackage.Frment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.quickvideo.quickvideo.R;
import com.quickvideo.quickvideo.RecommendPackage.Video.PageVideo;
import com.quickvideo.quickvideo.RecommendPackage.adapter.DetailsAdapter;
import com.quickvideo.quickvideo.RecommendPackage.adapter.PLAdapter;
import com.quickvideo.quickvideo.bean.FirsEvent;
import com.quickvideo.quickvideo.bean.XiangQingBean;
import com.quickvideo.quickvideo.client.API;
import com.quickvideo.quickvideo.client.ApiService;

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

public class Intro extends Fragment {
    @BindView(R.id.intro_rv)
    RecyclerView introRv;
    Unbinder unbinder;
    @BindView(R.id.intro_tv1)
    TextView introTv1;
    @BindView(R.id.intro_tv2)
    TextView introTv2;
    private String url;
    String wjj;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.intro, null);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        introRv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        Retrofit build = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = build.create(ApiService.class);
        HashMap<String, String> map = new HashMap<>();
        map.put("mediaId", wjj);
        final Observable<XiangQingBean> getshow = apiService.getXiangQData(map);
        getshow.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<XiangQingBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(XiangQingBean xiangQingBean) {
                        xiangQingBean = xiangQingBean;
                        //赋值
                        introTv1.setText("导演"+xiangQingBean.ret.director);
                        introTv2.setText("演员："+xiangQingBean.ret.actors);
                        DetailsAdapter adapter = new DetailsAdapter(getActivity(), xiangQingBean);
                        final XiangQingBean finalXiangQingBean = xiangQingBean;
                        adapter.setOnItemClieckLinster(new DetailsAdapter.OnItemClieckLinster() {
                            @Override
                            public void onItemClickListener(View view, int pos) {
                                // String dataId1 = shouYeBean.ret.list.get(4).childList.get(pos).dataId;
                                //EventBus.getDefault().postSticky(new FirsEvent(dataId1));
                                final String dataId = finalXiangQingBean.ret.list.get(0).childList.get(pos).dataId;
                                Toast.makeText(getActivity(),dataId,Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), PageVideo.class);
                                EventBus.getDefault().postSticky(new FirsEvent(dataId));
                                getActivity().finish();
                                startActivity(intent);
                            }
                        });
                        introRv.setVisibility(View.VISIBLE);
                        introRv.setAdapter(adapter);
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
