package com.quickvideo.quickvideo.VideoList.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.quickvideo.quickvideo.R;
import com.quickvideo.quickvideo.VideoList.Presenter.VideoListPresenter;
import com.quickvideo.quickvideo.VideoList.adapter.MyVideoListAdapter;
import com.quickvideo.quickvideo.bean.PinDaoBean;
import com.quickvideo.quickvideo.client.OnClickRecyclerListner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class VideoListActivity extends SwipeBackActivity  implements VideoListView {

    @BindView(R.id.video_img)
    ImageView videoImg;
    @BindView(R.id.xrecyclerview)
    XRecyclerView xrecyclerview;
    @BindView(R.id.videoListhatv)
    TextView videoTv;
    private Handler handler = new Handler();
    private MyVideoListAdapter myVideoListAdapter;
    private int pnum;
    List<PinDaoBean.RetBean.ListBean> dlist = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        Log.d("CReate",title);
        videoTv.setText(title);

        GridLayoutManager manager = new GridLayoutManager(this, 3);
        xrecyclerview.setLayoutManager(manager);
        VideoListPresenter presenter = new VideoListPresenter(this);
        presenter.getVideoData(pnum + "");




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
                    myVideoListAdapter = new MyVideoListAdapter(VideoListActivity.this, vlist);
                    xrecyclerview.setAdapter(myVideoListAdapter);

                    myVideoListAdapter.setLisner(new OnClickRecyclerListner() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Toast.makeText(VideoListActivity.this, "能跳", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onLongItemClick(View view, int position) {

                        }
                    });
                }
            });

        }


        xrecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                pnum = 1;
                dlist = vlist;
                VideoListPresenter presenter = new VideoListPresenter(VideoListActivity.this);
                presenter.getVideoData(1 + "");
                xrecyclerview.refreshComplete();

            }

            @Override
            public void onLoadMore() {
                VideoListPresenter presenter = new VideoListPresenter(VideoListActivity.this);
                pnum++;
                dlist.addAll(vlist);
                presenter.getVideoData(pnum + "");
                myVideoListAdapter.notifyDataSetChanged();
                xrecyclerview.loadMoreComplete();
            }

        });

    }

    @OnClick(R.id.video_img)
    public void onViewClicked() {
        finish();
    }
}
