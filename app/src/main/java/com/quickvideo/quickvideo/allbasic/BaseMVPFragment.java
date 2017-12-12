package com.quickvideo.quickvideo.allbasic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by Dabin on 2017/12/10.
 * BaseMVPFragment
 */

public abstract  class BaseMVPFragment<T extends BaseIPresenter> extends BaseFragment{
    public T mPresenter;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initInject();
        if(mPresenter != null){
            mPresenter.attach(this);
        }
        super.onViewCreated(view, savedInstanceState);

    }
    /*
    * 销毁时解绑V、P
    *
    * */
    @Override
    public void onDestroy() {
        if (mPresenter != null) mPresenter.detach();
        mPresenter = null;
        super.onDestroy();
    }

    public abstract void initInject();
}
