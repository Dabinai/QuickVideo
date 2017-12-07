package com.quickvideo.quickvideo.Classification.App;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017/12/5.
 */

public class Myapp extends Application{
    public static Context mcontext;
    public static Set<Activity> allActivities;
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        if(mcontext == null){
            mcontext =this;
        }
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    //登记Activity
    public static void registerActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<Activity>();
        }
        allActivities.add(act);
        //Logger.d("添加成功");
        //Log.d("Ddddd", "registerActivity: 添加成功");
    }

    //移除Activity
    public static void unregisterActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
            Log.d("Ddddd", "unregisterActivity: 移除成功");
        }
    }

    //退出APP销毁Activity
    public static void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    if (act != null && act.isFinishing()) {
                        act.finish();
                    }
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
        Log.d("Ddddd", "unregisterActivity: 销毁成功");
    }



}
