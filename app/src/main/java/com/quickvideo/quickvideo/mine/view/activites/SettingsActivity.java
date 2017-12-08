package com.quickvideo.quickvideo.mine.view.activites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.quickvideo.quickvideo.R;
import com.quickvideo.quickvideo.allbasic.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lenovo on 2017/12/7.
 */

public class SettingsActivity extends BaseActivity {
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
                Toast.makeText(SettingsActivity.this,"onclick!!!",Toast.LENGTH_LONG).show();
                break;
            case R.id.setting_clear:
                break;
            case R.id.setting_about_us:
                break;
            case R.id.setting_fankui:
                break;
        }
    }
}
