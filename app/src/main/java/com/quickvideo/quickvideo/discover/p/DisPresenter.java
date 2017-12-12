package com.quickvideo.quickvideo.discover.p;

import com.quickvideo.quickvideo.allbasic.BaseIPresenter;
import com.quickvideo.quickvideo.bean.PinDaoBean;
import com.quickvideo.quickvideo.discover.m.DisIModel;
import com.quickvideo.quickvideo.discover.m.DisModel;
import com.quickvideo.quickvideo.discover.v.DisIView;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * Created by Dabin on 2017/12/10.
 */

public class DisPresenter implements BaseIPresenter<DisIView>{

    DisModel model;
    WeakReference<DisIView> weakReference;
    public DisPresenter(DisIView disIView){
        model = new DisModel();
        attach(disIView);
    }

    @Override
    public void attach(DisIView view) {
        weakReference = new WeakReference(view);
    }

    @Override
    public void detach() {
        if(weakReference != null){
            weakReference.clear();
        }
    }

    public void  showFrag(){
      model.loadfFragData(new DisIModel.BackFragCall() {
          @Override
          public void completeFrag(PinDaoBean pinDaoBean) {
              weakReference.get().showFragData(pinDaoBean);
          }
      });
    }

    public void setUrl(String catalogId,String pnum){
        model.getUrl(catalogId, pnum);
    }
}
