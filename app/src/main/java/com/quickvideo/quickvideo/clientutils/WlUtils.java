package com.quickvideo.quickvideo.clientutils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

/**
 * Created by Dabin on 2017/12/10.
 */

public class WlUtils {
    public static void getNetInfor(final Activity activity) {
        //首先是获取网络连接管理者
        ConnectivityManager manager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        //网络状态存在并且是已连接状态
        if (info != null && info.isConnected()) {
            //Toast.makeText(activity, "网络已连接", Toast.LENGTH_SHORT).show();
            //执行下面的操作

        } else {
            Toast.makeText(activity, "网络连接失败", Toast.LENGTH_SHORT).show();
            //下面的这种写法你应该看得懂
            new AlertDialog.Builder(activity)
                    .setTitle("请检查网络连接")
                    .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (android.os.Build.VERSION.SDK_INT > 10) {
                                //安卓系统3.0以上打开设置界面，也可以直接用ACTION_WIRELESS_SETTINGS打开到wifi界面
                                activity.startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
                            } else {
                                activity.startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
                            }
                        }
                    })
                    .show();
        }
    }
}
