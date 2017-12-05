package com.quickvideo.quickvideo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.quickvideo.quickvideo.Classification.adapter.MyClassificationAdapter;
import com.quickvideo.quickvideo.Classification.presenter.IClassificationPresenter;
import com.quickvideo.quickvideo.Classification.view.IClassificationView;
import com.quickvideo.quickvideo.R;
import com.quickvideo.quickvideo.bean.ShouYeBean;
import com.quickvideo.quickvideo.client.OnClickRecyclerListner;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Dabin on 2017/12/4.
 * 专题，ClassificationFragment
 * XRecyclerview,点击事件（跳转到VideoListActivity）
 */

public class ClassificationFragment extends Fragment implements IClassificationView{
    @BindView(R.id.recyclerview)
    XRecyclerView recyclerview;
    Unbinder unbinder;
    private Handler handler = new Handler();
    private MyClassificationAdapter myClassificationAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.frag_classification, null);


        unbinder = ButterKnife.bind(this, view);
        IClassificationPresenter presenter = new IClassificationPresenter(this);
        presenter.getMovieData();

        GridLayoutManager manager = new GridLayoutManager(getActivity(),2);
        recyclerview.setLayoutManager(manager);

        recyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                recyclerview.refreshComplete();
            }

            @Override
            public void onLoadMore() {

            }
        });




        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }



    @Override
    public void getDataSeccess() {
        Toast.makeText(getActivity(), "成功1", Toast.LENGTH_LONG).show();
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
                    myClassificationAdapter = new MyClassificationAdapter(getActivity(),list);
                    recyclerview.setAdapter(myClassificationAdapter);
                    //点击事件
                    myClassificationAdapter.setLisner(new OnClickRecyclerListner() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Toast.makeText(getActivity(), "能跳", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onLongItemClick(View view, int position) {

                        }
                    });
                }
            });

        }

    }
}
