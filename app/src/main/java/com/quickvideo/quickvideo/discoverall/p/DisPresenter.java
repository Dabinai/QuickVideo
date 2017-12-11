package com.quickvideo.quickvideo.discoverall.p;

import com.quickvideo.quickvideo.allbasic.BaseIPresenter;
import com.quickvideo.quickvideo.bean.PinDaoBean;
import com.quickvideo.quickvideo.discoverall.m.DisIModel;
import com.quickvideo.quickvideo.discoverall.m.DisModel;
import com.quickvideo.quickvideo.discoverall.v.DisIView;

import java.lang.ref.SoftReference;

/**
 * Created by Dabin on 2017/12/10.
 */

public class DisPresenter implements BaseIPresenter<DisIView>{

    DisModel model;
    SoftReference<DisIView> softReference;
    public DisPresenter(DisIView disIView){
        model = new DisModel();
        attach(disIView);
    }

    @Override
    public void attach(DisIView view) {
        softReference = new SoftReference(view);
    }

    @Override
    public void detach() {
        if(softReference != null){
            softReference.clear();
        }
    }

    public void  showFrag(){
      model.loadfFragData(new DisIModel.BackFragCall() {
          @Override
          public void completeFrag(PinDaoBean pinDaoBean) {
              softReference.get().showFragData(pinDaoBean);
          }
      });
    }

    public void setUrl(String catalogId,String pnum){
        model.getUrl(catalogId, pnum);
    }
}
