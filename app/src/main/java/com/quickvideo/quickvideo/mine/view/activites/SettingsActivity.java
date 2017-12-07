package com.quickvideo.quickvideo.mine.view.activites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.quickvideo.quickvideo.R;
import com.quickvideo.quickvideo.allbasic.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lenovo on 2017/12/7.
 */

public class SettingsActivity extends BaseActivity {
    @Override
    public int getLayout() {
        return R.layout.setting_layout;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
