package com.quickvideo.quickvideo.RecommendPackage.RecommendPresenterPackage;

import com.quickvideo.quickvideo.RecommendPackage.RecommendVModePackage.RecModel;
import com.quickvideo.quickvideo.RecommendPackage.RecommendViewPackage.Reco;
import com.quickvideo.quickvideo.bean.ShouYeBean;

/**
 * Created by Long° Engagement on 2017/12/5.
 */

public class RecommendPresenter implements RecModel.OnFinish{
   private  final Reco reco;
   private  final  RecModel recModel;

    public RecommendPresenter(Reco reco) {
        this.reco = reco;
        recModel = new RecModel(this);
    }

    public void getmessage(){
        recModel.gethomedata();
    }

    @Override
    public void onfinishLister(ShouYeBean shouYeBean) {
            reco.getHomeMessage(shouYeBean);
    }
}
