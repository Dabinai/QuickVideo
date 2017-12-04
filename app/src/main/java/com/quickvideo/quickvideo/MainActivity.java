package com.quickvideo.quickvideo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.quickvideo.quickvideo.fragments.ClassificationFragment;
import com.quickvideo.quickvideo.fragments.DiscoverFragment;
import com.quickvideo.quickvideo.fragments.MineFragment;
import com.quickvideo.quickvideo.fragments.RecommendFragment;
import com.yinglan.alphatabs.AlphaTabsIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends FragmentActivity {

    @BindView(R.id.alphaIndicator)
    AlphaTabsIndicator alphaIndicator;
    @BindView(R.id.myviewpager)
    ViewPager myviewpager;
private List<Fragment> fragList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    private void initFrag() {
        fragList.add(new RecommendFragment());
        fragList.add(new ClassificationFragment());
        fragList.add(new DiscoverFragment());
        fragList.add(new MineFragment());
    }
}
