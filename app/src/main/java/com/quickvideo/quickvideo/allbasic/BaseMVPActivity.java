package com.quickvideo.quickvideo.allbasic;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by Dabin on 2017/12/7.
 * BaseMVPActivity
 */

public abstract class BaseMVPActivity<T extends BaseIPresenter> extends BaseActivity {

    public T presenter;//传过来的Presenter
    public abstract void createPresenter();//创建Presenter
    public abstract void initView(); //初始化布局

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createPresenter();
        initView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(presenter!=null) presenter.detach(); //取消V与P的关联

    }
}
