package com.quickvideo.quickvideo.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.quickvideo.quickvideo.R;
import com.quickvideo.quickvideo.mine.bean.Consts;


/**
 * Created by powersen on 2017/12/6.
 * 切换主题Activity
 */

public class ThemeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theme_layout);

    }
    public  void onClick(View v){
        SharedPreferences preferences = getSharedPreferences(Consts.SHARE_NAME,MODE_PRIVATE);
        int typeBefor = preferences.getInt("theme_type", 0);
        int typeSetted = typeBefor;
        int themeId;
        int id = v.getId();
        switch (id){
            case R.id.btn0:
                typeSetted = 0;
                break;
            case R.id.btn1:
                typeSetted = 1;
                break;
            case R.id.btn2:
                typeSetted = 2;
                break;
            case R.id.btn3:
                typeSetted = 3;
                break;
            case R.id.btn4:
                typeSetted = 4;
                break;
            case R.id.btn5:
                typeSetted = 5;
                break;
            case R.id.btn6:
                typeSetted = 6;
                break;
            case R.id.btn7:
                typeSetted = 7;
                break;
        }
        if (typeBefor != typeSetted) {
            preferences.edit().putInt("theme_type", typeSetted).commit();
            setResult(Activity.RESULT_OK);
        }
        finish();
    }
}
