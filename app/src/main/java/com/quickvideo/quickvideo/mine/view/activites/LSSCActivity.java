package com.quickvideo.quickvideo.mine.view.activites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.GridView;

import com.quickvideo.quickvideo.R;
import com.quickvideo.quickvideo.allbasic.BaseActivity;
import com.quickvideo.quickvideo.mine.adapter.MineGridAdapter;
import com.quickvideo.quickvideo.mine.bean.MineBean;
import com.quickvideo.quickvideo.mine.presenter.MinePresenter;
import com.quickvideo.quickvideo.mine.view.MineView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by powersen on 2017/12/7.
 * 历史/收藏 界面
 */

public class LSSCActivity extends BaseActivity implements MineView {
    @BindView(R.id.ls_sc_gridview)
    GridView lsScGridview;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        MinePresenter presenter = new MinePresenter(this);
        presenter.getGridDatas();
    }

    @Override
    public void initView() {
        super.initView();

    }

    @Override
    public void getIntentData() {
        super.getIntentData();

    }

    @Override
    public void showOk() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showAdapter(List<MineBean> list) {
        lsScGridview.setAdapter(new MineGridAdapter(LSSCActivity.this, list));
    }

    @Override
    public int getLayout() {
        return R.layout.lishi_shoucang_layout;
    }
}
