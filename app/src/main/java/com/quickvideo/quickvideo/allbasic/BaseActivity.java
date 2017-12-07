package com.quickvideo.quickvideo.allbasic;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.quickvideo.quickvideo.Classification.App.Myapp;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Dabin on 2017/12/7.
 * BaseActivity
 */

public abstract class BaseActivity extends AppCompatActivity{
    private static final String TAG = "BaseActivity";
    public Activity mContext;
    //butterknife创建
    private Unbinder munbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: + oncreate");
        Logger.addLogAdapter(new AndroidLogAdapter()); //初始化Logger
        init();
        setContentView(getLayout()); //设置沉浸式
        getIntentData();
        mContext = this;
        munbinder = ButterKnife.bind(this); //初始化Butterknife
        initView();
        initEvent();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: onStop" );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: onDestroy");
        if(munbinder != null) munbinder.unbind(); //解绑buterknife
        Myapp.unregisterActivity(this); //移除Activity
    }

    //初始化设置
    public void init(){
        setTranslucentStatus(true); //沉浸式
        onPreCreate();      //更换直题
        Myapp.registerActivity(this); //登记Activity
    }

    //沉浸式
    public void setTranslucentStatus(boolean on) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (on) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            win.setAttributes(winParams);
        }
    }

    //更换直题  暂时不用或许开发使用
    public void onPreCreate(){}
    //初始化布局文件
    public abstract int getLayout();
    //初始化数据
    public void getIntentData(){};
    //初始化View
    public void initView(){}

    public void initEvent(){};
}
