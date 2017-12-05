package com.quickvideo.quickvideo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quickvideo.quickvideo.R;

/**
 * Created by Dabin on 2017/12/4.
 * 精选页RecommendFragment，
 * banner+XRecyclerview+搜索框+点击事件
 * 点击跳转到VideoInfoActivity
 */

public class RecommendFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.frag_recommend, null);
        return view;
    }
}
