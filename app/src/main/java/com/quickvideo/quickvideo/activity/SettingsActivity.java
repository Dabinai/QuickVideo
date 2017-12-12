package com.quickvideo.quickvideo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.quickvideo.quickvideo.R;
import com.quickvideo.quickvideo.allbasic.MySwipeBackActivity;
import com.quickvideo.quickvideo.leftmenu.utils.YijianDaiLog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lenovo on 2017/12/7.
 */

public class SettingsActivity extends MySwipeBackActivity {
    @BindView(R.id.setting_tuijian)
    RelativeLayout settingTuijian;
    @BindView(R.id.setting_clear)
    RelativeLayout settingClear;
    @BindView(R.id.setting_about_us)
    RelativeLayout settingAboutUs;
    @BindView(R.id.setting_fankui)
    RelativeLayout settingFankui;

    @Override
    public int getLayout() {
        return R.layout.setting_layout;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.setting_tuijian, R.id.setting_clear, R.id.setting_about_us, R.id.setting_fankui})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_tuijian:
//               点击弹出推荐对话框
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
                View view1 = LayoutInflater.from(SettingsActivity.this).inflate(R.layout.dialog_tuijian, null);
                builder.setView(view1);
                final AlertDialog dialog = builder.create();
                view1.findViewById(R.id.dialog_copy).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SettingsActivity.this, "已复制到粘贴板", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                view1.findViewById(R.id.tuijian_dialog_close).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                break;
            case R.id.setting_clear:
                Toast.makeText(SettingsActivity.this, "缓存已清理", Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting_about_us:
//                点击弹出关于我们对话框
                AlertDialog.Builder builder1 = new AlertDialog.Builder(SettingsActivity.this);
                View view2 = LayoutInflater.from(SettingsActivity.this).inflate(R.layout.dialog_about_us_layout, null);
                builder1.setView(view2);
                final AlertDialog guanyudialog = builder1.create();
                TextView close = view2.findViewById(R.id.close);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        guanyudialog.dismiss();
                    }
                });
                guanyudialog.show();

                break;
            case R.id.setting_fankui:
                //反馈
                YijianDaiLog yijianDaiLog = new YijianDaiLog(SettingsActivity.this);
                yijianDaiLog.showDialog();
                break;
        }
    }
}
