package com.quickvideo.quickvideo.RecommendPackage.Video;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.quickvideo.quickvideo.R;
import com.quickvideo.quickvideo.RecommendPackage.Frment.Comment;
import com.quickvideo.quickvideo.RecommendPackage.Frment.Intro;
import com.quickvideo.quickvideo.bean.FirsEvent;
import com.quickvideo.quickvideo.bean.XiangQingBean;
import com.quickvideo.quickvideo.client.API;
import com.quickvideo.quickvideo.client.ApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PageVideo extends AppCompatActivity {
    List<Fragment> list;
    PlayerView playerView;
    MyPageAdapter adapter;
    @BindView(R.id.jianjie)
    RadioButton jianjie;
    @BindView(R.id.pinlun)
    RadioButton pinlun;
    @BindView(R.id.VP)
    ViewPager VP;
    String wjj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_video);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        Retrofit build = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = build.create(ApiService.class);
        HashMap<String, String> map = new HashMap<>();
        map.put("mediaId", wjj);
        Observable<XiangQingBean> getshow = apiService.getXiangQData(map);
        getshow.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<XiangQingBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(XiangQingBean xiangQingBean) {
                        String smoothURL = xiangQingBean.ret.smoothURL;
                        playerView = new PlayerView(PageVideo.this)
                                .setTitle(xiangQingBean.ret.title)
                                .setScaleType(PlayStateParams.fitparent)
                                .hideMenu(true)
                                .forbidTouch(false)
                                .setPlaySource(smoothURL)
                                .startPlay();
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        list = new ArrayList<>();
        list.add(new Intro());
        list.add(new Comment());
        adapter = new MyPageAdapter(getSupportFragmentManager());
        VP.setAdapter(adapter);
        VP.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        jianjie.setTextColor(Color.GREEN);
                        pinlun.setTextColor(Color.WHITE);
                        break;
                    case 1:
                        jianjie.setTextColor(Color.WHITE);
                        pinlun.setTextColor(Color.GREEN);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        jianjie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VP.setCurrentItem(0);
            }
        });
        pinlun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VP.setCurrentItem(1);
            }
        });

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
            int height = windowManager.getDefaultDisplay().getHeight();
            ViewGroup.LayoutParams layoutParams = findViewById(R.id.app_video_box).getLayoutParams();
            layoutParams.height = height;

        } else {
            WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
            int height = windowManager.getDefaultDisplay().getHeight();
            ViewGroup.LayoutParams layoutParams = findViewById(R.id.app_video_box).getLayoutParams();
            layoutParams.height = 180;

        }
        super.onConfigurationChanged(newConfig);
        Log.e("TAG", "onConfigurationChanged");
        //  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);  //设置横屏
    }

    @Override
    protected void onStop() {
        super.onStop();
        playerView.stopPlay();
    }

    @OnClick({R.id.jianjie, R.id.pinlun})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.jianjie:
                break;
            case R.id.pinlun:
                break;

        }
    }


    class MyPageAdapter extends FragmentPagerAdapter {

        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(sticky = true)
    public void wjj(FirsEvent firsEvent) {
        wjj = firsEvent.getWjj();

    }


}
