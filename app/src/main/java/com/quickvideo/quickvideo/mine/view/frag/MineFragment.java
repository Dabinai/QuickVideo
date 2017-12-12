package com.quickvideo.quickvideo.mine.view.frag;

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
import android.widget.Toast;

import com.quickvideo.quickvideo.MainActivity;
import com.quickvideo.quickvideo.R;
import com.quickvideo.quickvideo.activity.LSSCActivity;
import com.quickvideo.quickvideo.activity.SettingsActivity;
import com.quickvideo.quickvideo.activity.ThemeActivity;
import com.quickvideo.quickvideo.clientutils.DataCleanManager;
import com.quickvideo.quickvideo.mine.adapter.MineGridAdapter;
import com.quickvideo.quickvideo.mine.bean.Bean;
import com.quickvideo.quickvideo.mine.sqlite.OpenHelperManager;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by powersen on 2017/12/4.
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
    GridView lishiGirdview;

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
    private List<Bean> beanList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.frag_mine, null);
        unbinder = ButterKnife.bind(this, view);
//        MinePresenter presenter = new MinePresenter(this);
//        presenter.getGridDatas();
        OpenHelperManager manager = new OpenHelperManager(getActivity());
        beanList = manager.queryData();
        lishiGirdview.setAdapter(new MineGridAdapter(getActivity(), beanList));
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
//                跳转到设置界面
                Intent intent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.rel1_lishi:
//                跳转到历史界面
                Intent intent1 = new Intent(getActivity(), LSSCActivity.class);
                intent1.putExtra("flag", 1);
                startActivity(intent1);
                break;
            case R.id.rel2_huancun:
                showToast("缓存已清理！");
                DataCleanManager.cleanInternalCache(getActivity());
                DataCleanManager.cleanDatabases(getActivity());
                DataCleanManager.cleanSharedPreference(getActivity());
                break;
            case R.id.rel3_shoucang:
//                跳转到收藏界面
                Intent intent2 = new Intent(getActivity(), LSSCActivity.class);
                startActivity(intent2);
                break;
            case R.id.rel4_theme:
//                更改主题界面
                Intent i = new Intent(getActivity(), ThemeActivity.class);
                startActivityForResult(i, 0);
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

//    @Override
//    public void showOk() {
//        //showToast("成功");
//    }
//
//    @Override
//    public void showError() {
//        showToast("失败");
//    }
//
//    @Override
//    public void showAdapter(List<Bean> list) {
//
////        lishiGirdview.setAdapter(new MineGridAdapter(getActivity(), list));
//    }


    public void showToast(String string) {
        Toast.makeText(getActivity(), string, Toast.LENGTH_LONG).show();
    }
}
