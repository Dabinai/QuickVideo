package com.quickvideo.quickvideo.VideoList.View;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.widget.ImageView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.quickvideo.quickvideo.R;
import com.quickvideo.quickvideo.VideoList.Presenter.VideoListPresenter;
import com.quickvideo.quickvideo.VideoList.adapter.MyVideoListAdapter;
import com.quickvideo.quickvideo.bean.PinDaoBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideoListActivity extends AppCompatActivity implements VideoListView {

    @BindView(R.id.video_img)
    ImageView videoImg;
    @BindView(R.id.xrecyclerview)
    XRecyclerView xrecyclerview;
    private Handler handler = new Handler();
    private MyVideoListAdapter myVideoListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);
        ButterKnife.bind(this);
        GridLayoutManager manager = new GridLayoutManager(this,3);
        xrecyclerview.setLayoutManager(manager);
        VideoListPresenter presenter = new VideoListPresenter(this);
        presenter.getVideoData();
    }

    @Override
    public void showError() {

    }

    @Override
    public void showOk() {

    }

    @Override
    public void showData(final List<PinDaoBean.RetBean.ListBean> vlist) {
        if (vlist != null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                myVideoListAdapter = new MyVideoListAdapter(VideoListActivity.this,vlist);
                xrecyclerview.setAdapter(myVideoListAdapter);
                }
            });

        }
    }

    @OnClick(R.id.video_img)
    public void onViewClicked() {
        finish();
    }
}
