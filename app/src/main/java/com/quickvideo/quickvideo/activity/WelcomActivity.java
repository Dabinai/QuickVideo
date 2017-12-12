package com.quickvideo.quickvideo.activity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.quickvideo.quickvideo.MainActivity;
import com.quickvideo.quickvideo.R;
import com.quickvideo.quickvideo.allbasic.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;

public class WelcomActivity extends BaseActivity {

    @BindView(R.id.iv_welcome_bg)
    ImageView ivWelcomeBg;
    private int v;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_welcom;
    }

    @Override
    public void initEvent() {
        super.initEvent();
        List<String> imgs = new ArrayList<>();
        imgs.add("file:///android_asset/a.jpg");
        imgs.add("file:///android_asset/b.jpg");
        imgs.add("file:///android_asset/c.jpg");
//        imgs.add("file:///android_asset/d.jpg");
        imgs.add("file:///android_asset/e.jpg");
        imgs.add("file:///android_asset/f.jpg");
        imgs.add("file:///android_asset/g.jpg");

        Random random = new Random();
        v = random.nextInt(imgs.size());
        Glide.with(this).load(imgs.get(v)).into(ivWelcomeBg);
        /*ivWelcomeBg.setImageDrawable(getResources().getDrawable(R.mipmap.bg_colorful));*/
        ivWelcomeBg.animate().scaleX(1.12f).scaleY(1.12f).setDuration(2000).setStartDelay(100).start();
        ivWelcomeBg.animate().setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                startActivity(new Intent(WelcomActivity.this,MainActivity.class));
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }


}
