package com.quickvideo.quickvideo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.quickvideo.quickvideo.R;
import com.quickvideo.quickvideo.RecommendPackage.RecommendPresenterPackage.RecommendPresenter;
import com.quickvideo.quickvideo.RecommendPackage.RecommendViewPackage.Reco;
import com.quickvideo.quickvideo.RecommendPackage.adapter.XRHomeAdapter;
import com.quickvideo.quickvideo.activity.PageVideoActivity;
import com.quickvideo.quickvideo.activity.SearchActivity;
import com.quickvideo.quickvideo.activity.SearchsActivity;
import com.quickvideo.quickvideo.bean.FirsEvent;
import com.quickvideo.quickvideo.bean.ShouYeBean;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by Dabin on 2017/12/4.
 * 精选页RecommendFragment，
 * banner+XRecyclerview+搜索框+点击事件
 * 点击跳转到VideoInfoActivity
 */

public class RecommendFragment extends Fragment implements Reco {
    XRecyclerView xr;
    //SwipeRefreshLayout mySwipeRefreshLayout;
    XRHomeAdapter xrHomeAdapter;
    private XBanner xBanner;
    private RelativeLayout relativeLayout;

    //private Toolbar mToolBar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.frag_recommend, null);
        xr = view.findViewById(R.id.shouye_XR);
       /* mySwipeRefreshLayout = view.findViewById(R.id.mySwipeRefreshLayout);
        mToolBar = (Toolbar) view.findViewById(R.id.toolbar);
        mToolBar.setLogo(getResources().getDrawable(R.mipmap.ic_launcher));
        mToolBar.setTitle("这是标题");

        mToolBar.inflateMenu(R.menu.menu);*/

        View abnv = View.inflate(getActivity(), R.layout.recommend_1, null);
        xBanner = abnv.findViewById(R.id.Slider);
        relativeLayout = abnv.findViewById(R.id.rlGoSearch);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(),SearchsActivity.class));
            }
        });
        xr.addHeaderView(abnv);
        RecommendPresenter recommendPresenter = new RecommendPresenter(this);
        recommendPresenter.getmessage();
        xr.setLayoutManager(new LinearLayoutManager(getActivity()));


        return view;
    }
    @Override
    public void getHomeMessage(final ShouYeBean shouYeBean) {

        final ArrayList mlist = new ArrayList();
        for (int i = 0; i < shouYeBean.ret.list.get(0).childList.size(); i++) {
            mlist.add(shouYeBean.ret.list.get(0).childList.get(i).pic);
        }
        xBanner.setData(mlist, null);
        xBanner.setmAutoPalyTime(2500);
        // 设置XBanner的页面切换特效
        xBanner.setPageTransformer(Transformer.Zoom);
        // 设置XBanner页面切换的时间，即动画时长
//        mybanner.setPageChangeDuration(1000);
        xBanner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                Glide.with(getActivity()).load(mlist.get(position)).into((ImageView) view);
            }
        });
        xBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {

            private String dataId;

            @Override
            public void onItemClick(XBanner banner, int position) {
                String dataId = shouYeBean.ret.list.get(0).childList.get(position).dataId;
                Intent intent = new Intent(getActivity(), PageVideoActivity.class);
                EventBus.getDefault().postSticky(new FirsEvent(dataId));
                getActivity().startActivity(intent);
            }
        });

        xrHomeAdapter = new XRHomeAdapter(shouYeBean,getActivity(),getMetrics());
        xr.setAdapter(xrHomeAdapter);
        //设置卷内的颜色
      /*  mySwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
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
        });*/

    }

    //获取屏幕宽度,高度
    public int getMetrics(){

        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;

        Log.d("首页", "onCreate: 屏幕宽度"+ screenWidth +"------高度:"+screenHeight);
        return screenWidth;
    }

}
