package com.quickvideo.quickvideo.mine.presenter;

import com.quickvideo.quickvideo.mine.bean.MineBean;
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
        List<MineBean> data = mineModel.getData();
        if (data != null) {
            mineView.showAdapter(data);
            mineView.showOk();
        } else {
            mineView.showError();
        }

    }
}
