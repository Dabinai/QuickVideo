package com.quickvideo.quickvideo.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quickvideo.quickvideo.R;
import com.quickvideo.quickvideo.RecommendPackage.RecommendPresenterPackage.RecommendPresenter;
import com.quickvideo.quickvideo.RecommendPackage.RecommendViewPackage.Reco;
import com.quickvideo.quickvideo.RecommendPackage.adapter.XRHomeAdapter;
import com.quickvideo.quickvideo.bean.ShouYeBean;

/**
 * Created by Dabin on 2017/12/4.
 * 精选页RecommendFragment，
 * banner+XRecyclerview+搜索框+点击事件
 * 点击跳转到VideoInfoActivity
 */

public class RecommendFragment extends Fragment implements Reco {
    RecyclerView xr;
    SwipeRefreshLayout mySwipeRefreshLayout;
    XRHomeAdapter xrHomeAdapter;
    private Toolbar mToolBar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.frag_recommend, null);
        xr = view.findViewById(R.id.shouye_XR);
        mySwipeRefreshLayout = view.findViewById(R.id.mySwipeRefreshLayout);
        mToolBar = (Toolbar) view.findViewById(R.id.toolbar);
        mToolBar.setLogo(getResources().getDrawable(R.mipmap.ic_launcher));
        mToolBar.setTitle("这是标题");

        mToolBar.inflateMenu(R.menu.menu);
        RecommendPresenter recommendPresenter = new RecommendPresenter(this);
        recommendPresenter.getmessage();
        xr.setLayoutManager(new LinearLayoutManager(getActivity()));


        return view;
    }
    @Override
    public void getHomeMessage(ShouYeBean shouYeBean) {
        xrHomeAdapter = new XRHomeAdapter(shouYeBean,getActivity());
        xr.setAdapter(xrHomeAdapter);
        //设置卷内的颜色
        mySwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        //设置下拉刷新监听
        mySwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        xrHomeAdapter.notifyDataSetChanged();
                        //停止刷新动画
                        mySwipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });

    }
}
