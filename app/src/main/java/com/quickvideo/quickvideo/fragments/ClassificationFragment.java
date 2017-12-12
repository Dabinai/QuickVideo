package com.quickvideo.quickvideo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.quickvideo.quickvideo.activity.VideoListActivity;
import com.quickvideo.quickvideo.allbasic.Myapp;
import com.quickvideo.quickvideo.classification.adapter.MyClassificationAdapter;
import com.quickvideo.quickvideo.classification.presenter.IClassificationPresenter;
import com.quickvideo.quickvideo.classification.view.IClassificationView;
import com.quickvideo.quickvideo.R;
import com.quickvideo.quickvideo.bean.ShouYeBean;
import com.quickvideo.quickvideo.clientutils.OnClickRecyclerListner;
import com.quickvideo.quickvideo.mine.bean.Bean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Dabin on 2017/12/4.
 * 专题，ClassificationFragment
 * XRecyclerview,点击事件（跳转到VideoListActivity）
 */

public class ClassificationFragment extends Fragment implements IClassificationView {
    @BindView(R.id.recyclerview)
    XRecyclerView recyclerview;
    Unbinder unbinder;
    @BindView(R.id.classifiation_tv)
    TextView classifiationTv;
    private Handler handler = new Handler();
    private MyClassificationAdapter myClassificationAdapter;
    private String trim;
    int screenWidth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.frag_classification, null);


        unbinder = ButterKnife.bind(this, view);
        IClassificationPresenter presenter = new IClassificationPresenter(this);
        presenter.getMovieData();

        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        recyclerview.setLayoutManager(manager);
        //trim = classifiationTv.getText().toString().trim();


        recyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                recyclerview.refreshComplete();
            }

            @Override
            public void onLoadMore() {

            }
        });

        getMetrics();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void getDataSeccess() {
        //Toast.makeText(getActivity(), "成功1", Toast.LENGTH_LONG).show();
    }

    @Override
    public void getDataFailed() {

        Toast.makeText(getActivity(), "失败1", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showAdapter(final List<ShouYeBean.RetBean.ListBean> list) {
        if (list != null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    myClassificationAdapter = new MyClassificationAdapter(getActivity(), list,screenWidth);
                    recyclerview.setAdapter(myClassificationAdapter);
                    //点击事件
                    myClassificationAdapter.setLisner(new OnClickRecyclerListner() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Intent intent = new Intent(getActivity(), VideoListActivity.class);
                            intent.putExtra("title", list.get(position).title);
                            startActivity(intent);
                            /**
                             * 在点击的时候获取值然后存到数据库
                             * 历史界面就可以接收显示数据
                             */
                            String title = list.get(position).title;
                            String pic = list.get(position).childList.get(0).pic;
                            Bean bean = new Bean(title, pic, position);
                            //使用数据库存储数据
                            boolean b = Myapp.getManager().ifEquals(bean);
                            if (b) {

                            } else {
                                Myapp.getManager().putData(bean);
                            }
                        }

                        @Override
                        public void onLongItemClick(View view, int position) {

                        }
                    });
                }
            });

        }


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
