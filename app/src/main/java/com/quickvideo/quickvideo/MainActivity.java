package com.quickvideo.quickvideo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Toast;

import com.quickvideo.quickvideo.Classification.App.Myapp;
import com.quickvideo.quickvideo.allbasic.BaseActivity;
import com.quickvideo.quickvideo.fragments.ClassificationFragment;
import com.quickvideo.quickvideo.fragments.DiscoverFragment;
import com.quickvideo.quickvideo.mine.view.frag.MineFragment;
import com.quickvideo.quickvideo.fragments.RecommendFragment;
import com.quickvideo.quickvideo.utils.NonSwipeableViewPager;
import com.yinglan.alphatabs.AlphaTabsIndicator;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
//2017.1205





public class MainActivity extends BaseActivity{
    private Long firstTime = 0L;

    @BindView(R.id.alphaIndicator)
    AlphaTabsIndicator alphaIndicator;
    @BindView(R.id.myviewpager)
    NonSwipeableViewPager myviewpager;
    private List<Fragment> fragList = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        initFrag();
        myviewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override

            public Fragment getItem(int position) {
                return fragList.get(position);
            }

            @Override
            public int getCount() {
                return fragList.size();
            }
        });
        //设置ViewPager
        alphaIndicator.setViewPager(myviewpager);
    }

    @Override
    public int getLayout() {

        return  R.layout.activity_main;
    }

    private void initFrag() {
        fragList.add(new RecommendFragment());
        fragList.add(new ClassificationFragment());
        fragList.add(new DiscoverFragment());
        fragList.add(new MineFragment());
    }

    //返回键
    @Override
    public void onBackPressed() {
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 1500) {
            Toast.makeText(mContext, "再按一次退出", Toast.LENGTH_SHORT).show();
            firstTime = secondTime;
        } else {
            Myapp.exitApp();
        }
    }
}
