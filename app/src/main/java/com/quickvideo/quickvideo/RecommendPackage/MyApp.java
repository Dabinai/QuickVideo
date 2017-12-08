package com.quickvideo.quickvideo.RecommendPackage;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;


/**
 * Created by Long° Engagement on 2017/12/6.
 */

public class MyApp extends Application {
    public static Context context;
    public static MyApp mInstance;
    public static Context getContext() {
        return context;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        mInstance = this;
        context=getApplicationContext();
        ImageLoaderConfiguration aDefault = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(aDefault);

    }
    public static MyApp getInstance() {
        return mInstance;
    }
}
