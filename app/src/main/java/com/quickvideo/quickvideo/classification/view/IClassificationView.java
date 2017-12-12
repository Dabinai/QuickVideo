package com.quickvideo.quickvideo.classification.view;

import com.quickvideo.quickvideo.bean.ShouYeBean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/5.
 */

public interface IClassificationView {
    void getDataSeccess();

    void getDataFailed();

    void showAdapter(List<ShouYeBean.RetBean.ListBean> list);
}
