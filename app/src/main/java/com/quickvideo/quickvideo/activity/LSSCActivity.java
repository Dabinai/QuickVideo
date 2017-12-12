package com.quickvideo.quickvideo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.GridView;

import com.quickvideo.quickvideo.R;
import com.quickvideo.quickvideo.allbasic.MySwipeBackActivity;
import com.quickvideo.quickvideo.allbasic.Myapp;
import com.quickvideo.quickvideo.mine.adapter.LSSCAdapter;
import com.quickvideo.quickvideo.mine.bean.Bean;

import java.util.List;

import butterknife.BindView;

/**
 * Created by powersen on 2017/12/7.
 * 历史/收藏 界面
 */

public  class LSSCActivity extends MySwipeBackActivity {
    @BindView(R.id.ls_sc_gridview)
    GridView lsScGridview;
    private int flag;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        getIntentData();
        List<Bean> queryData = Myapp.getManager().queryData();
        List<Bean> queryShuju = Myapp.getManager().queryShuju();
        if (flag == 1) {
            lsScGridview.setAdapter(new LSSCAdapter(this, queryData));
        } else {
            lsScGridview.setAdapter(new LSSCAdapter(this, queryShuju));
        }
    }

    @Override
    public void initView() {
        super.initView();

    }

    @Override
    public void getIntentData() {
        super.getIntentData();
        //获取标识，判断数据来自收藏还是历史
        flag = getIntent().getIntExtra("flag", 0);
    }


    @Override
    public int getLayout() {
        return R.layout.lishi_shoucang_layout;
    }
}
