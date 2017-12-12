package com.quickvideo.quickvideo.activity;

import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RadioButton;
import android.widget.Toast;

import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.quickvideo.quickvideo.R;
import com.quickvideo.quickvideo.RecommendPackage.Frment.Comment;
import com.quickvideo.quickvideo.RecommendPackage.Frment.Intro;
import com.quickvideo.quickvideo.allbasic.MySwipeBackActivity;
import com.quickvideo.quickvideo.bean.FirsEvent;
import com.quickvideo.quickvideo.bean.XiangQingBean;
import com.quickvideo.quickvideo.clientutils.API;
import com.quickvideo.quickvideo.clientutils.ApiService;

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

public class PageVideoActivity  extends MySwipeBackActivity {
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
    boolean a = true;
    Animation animation;
    String pic;
    String title;
  /*  @BindView(R.id.voide_img)
    ImageView voideImg;*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        Toast.makeText(mContext, wjj, Toast.LENGTH_SHORT).show();
        Observable<XiangQingBean> getshow = apiService.getXiangQData(map);
        getshow.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<XiangQingBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(XiangQingBean xiangQingBean) {
                        if (xiangQingBean.msg.equals("视频已下线")) {
                            Toast.makeText(mContext, "视频已下线", Toast.LENGTH_SHORT).show();
                        } else {
                            String smoothURL = xiangQingBean.ret.smoothURL;
                            pic = xiangQingBean.ret.pic;
                            title = xiangQingBean.ret.title;

                            playerView = new PlayerView(PageVideoActivity.this)
                                    .setTitle(xiangQingBean.ret.title)
                                    .setScaleType(PlayStateParams.fitparent)
                                    .hideMenu(true)
                                    .forbidTouch(false)
                                    .setPlaySource(smoothURL)
                                    .startPlay();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });

       /* voideImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (a) {
                    a = false;
                    *//**
         * 在点击的时候获取值然后存到数据库
         * 收藏
         * 界面就可以接收显示数据
         *//*
                    Bean bean = new Bean(title, pic);
                    //使用数据库存储数据
                    boolean b = Myapp.getManager().ifEqualsTwo(bean);
                    if (b) {

                    } else {
                        Myapp.getManager().putShuju(bean);
                    }
                    //  voideImg.startAnimation(animation);
                    createAnimationSet();

                    Toast.makeText(PageVideo.this, "收藏成功", Toast.LENGTH_SHORT).show();
                } else {
                    a = true;
                    Glide.with(PageVideo.this).load(R.mipmap.collection).into(voideImg);
                    Toast.makeText(PageVideo.this, "取消收藏", Toast.LENGTH_SHORT).show();
                }
            }

            });*/



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
            layoutParams.height = 250;

        }
        super.onConfigurationChanged(newConfig);
        Log.e("TAG", "onConfigurationChanged");
        //  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);  //设置横屏
    }

    @Override
    public void onStop() {
        super.onStop();
        if (playerView != null) {
            playerView.stopPlay();
        }

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

   /* @OnClick(R.id.voide_img)
    public void onViewClicked() {
    }*/


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
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_page_video;
    }


    @Subscribe(sticky = true)
    public void wjj(FirsEvent firsEvent) {
        wjj = firsEvent.getWjj();

    }

    private void createAnimationSet() {
        AnimationSet animationSet = new AnimationSet(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 0.5f, 1f, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animationSet.setRepeatCount(Animation.INFINITE); // 动画重复次数，INFINITE表示多次

        animationSet.setRepeatMode(Animation.REVERSE);// 重复模式，REVERSE表示从尾倒播
        //TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f, Animation.ABSOLUTE, 100);    animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(rotateAnimation);
        //animationSet.addAnimation(translateAnimation);

        animationSet.setDuration(2000);
        animationSet.setRepeatCount(-1);
        //voideImg.startAnimation(animationSet);
     /*   animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Glide.with(PageVideo.this).load(R.mipmap.collection_select).into(voideImg);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });*/

    }
}
