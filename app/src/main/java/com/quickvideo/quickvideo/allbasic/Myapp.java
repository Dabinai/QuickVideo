package com.quickvideo.quickvideo.allbasic;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.quickvideo.quickvideo.clientutils.WlUtils;
import com.quickvideo.quickvideo.mine.sqlite.OpenHelperManager;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017/12/5.
 */

public class Myapp extends Application {
    private static OpenHelperManager manager = null;
    public static Context mcontext;
    public static Set<Activity> allActivities;
    {
        //PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        // PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
    }


    @Override
    public void onCreate() {
        super.onCreate();
        UMShareAPI.get(this);

        Fresco.initialize(this);
        if (mcontext == null) {
            mcontext = this;
        }
        Logger.addLogAdapter(new AndroidLogAdapter());

        manager = new OpenHelperManager(this);
    }

    /*
    * 获取数据库管理类单例
    * */
    public static OpenHelperManager getManager() {
        return manager;
    }

    /*
    * 登记Activity
    * */
    public static void registerActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<Activity>();
        }
        WlUtils.getNetInfor(act);

        allActivities.add(act);
        //Logger.d("添加成功");
        //Log.d("Ddddd", "registerActivity: 添加成功");
    }

    /*
    * 移除Activity
    * */
    public static void unregisterActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
            Log.d("Ddddd", "unregisterActivity: 移除成功");
        }
    }

    /*
    * 退出APP销毁Activity
    * */
    public static void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    if (act != null && !act.isFinishing()) {
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
