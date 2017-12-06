package com.quickvideo.quickvideo.Classification.App;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Administrator on 2017/12/5.
 */

public class Myapp extends Application{
    public static Myapp mcontext;
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        if(mcontext == null){
            mcontext =this;
        }
    }
}
