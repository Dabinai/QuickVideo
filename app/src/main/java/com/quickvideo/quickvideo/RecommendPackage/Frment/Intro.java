package com.quickvideo.quickvideo.RecommendPackage.Frment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.quickvideo.quickvideo.R;

import com.quickvideo.quickvideo.RecommendPackage.adapter.DetailsAdapter;
import com.quickvideo.quickvideo.activity.PageVideoActivity;
import com.quickvideo.quickvideo.bean.FirsEvent;
import com.quickvideo.quickvideo.bean.XiangQingBean;
import com.quickvideo.quickvideo.clientutils.API;
import com.quickvideo.quickvideo.clientutils.ApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    @BindView(R.id.text_content)
    TextView textContent;
    @BindView(R.id.spread)
    TextView spread;
    @BindView(R.id.shrink_up)
    TextView shrinkUp;
    @BindView(R.id.show_more)
    RelativeLayout showMore;
    private String url;
    String wjj;
    private static final int VIDEO_CONTENT_DESC_MAX_LINE = 1;// 默认展示最大行数3行
    private static final int SHOW_CONTENT_NONE_STATE = 0;// 扩充
    private static final int SHRINK_UP_STATE = 1;// 收起状态
    private static final int SPREAD_STATE = 2;// 展开状态
    private static int mState = SHRINK_UP_STATE;//默认收起状态

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
                        if(!xiangQingBean.msg.equals("视频已下线")){
                            xiangQingBean = xiangQingBean;
                            //赋值
                            introTv1.setText("导演" + xiangQingBean.ret.director);
                            introTv2.setText("演员：" + xiangQingBean.ret.actors);
                            textContent.setText("简介: "+xiangQingBean.ret.description);

                            DetailsAdapter adapter = new DetailsAdapter(getActivity(), xiangQingBean);
                            final XiangQingBean finalXiangQingBean = xiangQingBean;
                            adapter.setOnItemClieckLinster(new DetailsAdapter.OnItemClieckLinster() {
                                @Override
                                public void onItemClickListener(View view, int pos) {
                                    // String dataId1 = shouYeBean.ret.list.get(4).childList.get(pos).dataId;
                                    //EventBus.getDefault().postSticky(new FirsEvent(dataId1));
                                    final String dataId = finalXiangQingBean.ret.list.get(0).childList.get(pos).dataId;
                                    Toast.makeText(getActivity(), dataId, Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getActivity(), PageVideoActivity.class);
                                    EventBus.getDefault().postSticky(new FirsEvent(dataId));
                                    getActivity().finish();
                                    startActivity(intent);
                                }
                            });
                            introRv.setVisibility(View.VISIBLE);
                            introRv.setAdapter(adapter);
                        }

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
        if(firsEvent.getWjj().equals("")){
            wjj = "70cddbf9d84b4c72bd4311952f03b6d4";
        }else{
            wjj = firsEvent.getWjj();
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick(R.id.show_more)
    public void onViewClicked() {
        if (mState == SPREAD_STATE) {
            textContent.setMaxLines(VIDEO_CONTENT_DESC_MAX_LINE);
            textContent.requestLayout();
            shrinkUp.setVisibility(View.GONE);
            spread.setVisibility(View.VISIBLE);
            mState = SHRINK_UP_STATE;
        } else if (mState == SHRINK_UP_STATE) {
            textContent.setMaxLines(Integer.MAX_VALUE);
            textContent.requestLayout();
            shrinkUp.setVisibility(View.VISIBLE);
            spread.setVisibility(View.GONE);
            mState = SPREAD_STATE;
        }
    }
}
