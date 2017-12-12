package com.quickvideo.quickvideo.mine.presenter;

import com.quickvideo.quickvideo.allbasic.Myapp;
import com.quickvideo.quickvideo.mine.bean.Bean;
import com.quickvideo.quickvideo.mine.model.MineModel;
import com.quickvideo.quickvideo.mine.view.MineView;

import java.util.List;

/**
 * Created by powersen on 2017/12/7.
 */

public class MinePresenter {
    private final MineModel mineModel;
    private final MineView mineView;

    public MinePresenter(MineView mineView) {
        this.mineModel = new MineModel();
        this.mineView = mineView;
    }

    public void getGridDatas() {
        List<Bean> beanList = Myapp.getManager().queryData();
        beanList = mineModel.getData();
        if (beanList != null) {
            mineView.showAdapter(beanList);
            mineView.showOk();
        } else {
            mineView.showError();
        }

    }
}
