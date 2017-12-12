package com.quickvideo.quickvideo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.quickvideo.quickvideo.R;
import com.quickvideo.quickvideo.VideoList.Presenter.VideoListPresenter;
import com.quickvideo.quickvideo.VideoList.View.VideoListView;
import com.quickvideo.quickvideo.VideoList.adapter.MyVideoListAdapter;
import com.quickvideo.quickvideo.allbasic.Myapp;
import com.quickvideo.quickvideo.bean.FirsEvent;
import com.quickvideo.quickvideo.bean.PinDaoBean;
import com.quickvideo.quickvideo.clientutils.OnClickRecyclerListner;
import com.quickvideo.quickvideo.mine.bean.Bean;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 *
 */

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
    int scWidth;
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

        getMetrics();


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
                    myVideoListAdapter = new MyVideoListAdapter(VideoListActivity.this, vlist,scWidth);
                    xrecyclerview.setAdapter(myVideoListAdapter);

                    myVideoListAdapter.setLisner(new OnClickRecyclerListner() {
                        @Override
                        public void onItemClick(View view, int position) {
                            //Toast.makeText(VideoListActivity.this, "能跳"+position, Toast.LENGTH_SHORT).show();
                            EventBus.getDefault().postSticky(new FirsEvent(vlist.get(position).dataId));
                            startActivity(new Intent(VideoListActivity.this, PageVideoActivity.class));

                            /**
                             * 在点击的时候获取值然后存到数据库
                             * 历史界面就可以接收显示数据
                             */
                            String title = vlist.get(position).title;
                            String pic = vlist.get(position).pic;
                            Bean bean = new Bean(title,pic,position);
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
    //获取屏幕宽度,高度
    public int getMetrics(){

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;

        Log.d("专题", "onCreate: 屏幕宽度"+ screenWidth +"------高度:"+screenHeight);
        return scWidth;
    }
}
