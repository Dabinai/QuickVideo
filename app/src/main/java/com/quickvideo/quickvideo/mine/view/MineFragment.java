package com.quickvideo.quickvideo.mine.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.quickvideo.quickvideo.MainActivity;
import com.quickvideo.quickvideo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Dabin on 2017/12/4.
 * 我的,MineFragment
 * 1.历史HistoryActivity，标题底部，显示列表
 * 2.缓存（待开发）
 * 3.收藏（点击事件，跳转到收藏页CollectionActivity）
 * 4.主题（换肤，点击弹出DiaLog）
 */

public class MineFragment extends Fragment {
    //背景图片
    @BindView(R.id.img_my_bg)
    ImageView imgMyBg;

    //设置
    @BindView(R.id.my_settings)
    ImageView mySettings;

    //历史栏
    @BindView(R.id.rel1_lishi)
    RelativeLayout rel1Lishi;

    //用来显示历史数据的GridView
    @BindView(R.id.linear_lishi_girdview)
    GridView linearLishiGirdview;

    //包裹显示历史数据的GridView的父布局
    @BindView(R.id.linear_lishi)
    LinearLayout linearLishi;


    //缓存栏
    @BindView(R.id.rel2_huancun)
    RelativeLayout rel2Huancun;

    @BindView(R.id.rel3_shoucang_img)
    ImageView rel3ShoucangImg;

    //收藏栏
    @BindView(R.id.rel3_shoucang)
    RelativeLayout rel3Shoucang;

    @BindView(R.id.rel4_theme_img)
    ImageView rel4ThemeImg;

    //主题栏
    @BindView(R.id.rel4_theme)
    RelativeLayout rel4Theme;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.frag_mine, null);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.my_settings, R.id.rel1_lishi, R.id.rel2_huancun, R.id.rel3_shoucang, R.id.rel4_theme})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_settings:
                break;
            case R.id.rel1_lishi:
                break;
            case R.id.rel2_huancun:
                break;
            case R.id.rel3_shoucang:
                break;
            case R.id.rel4_theme:
                Intent i = new Intent(getActivity(), ThemeActivity.class);
                startActivityForResult(i,0);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                getActivity().finish();

                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);
            }
        }
    }
}
