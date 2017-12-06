package com.quickvideo.quickvideo.mine.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quickvideo.quickvideo.R;

/**
 * Created by Dabin on 2017/12/4.
 * 我的,MineFragment
 * 1.历史HistoryActivity，标题底部，显示列表
 * 2.缓存（待开发）
 * 3.收藏（点击事件，跳转到收藏页CollectionActivity）
 * 4.主题（换肤，点击弹出DiaLog）
 */

public class MineFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.frag_mine, null);
        return view;
    }
}
