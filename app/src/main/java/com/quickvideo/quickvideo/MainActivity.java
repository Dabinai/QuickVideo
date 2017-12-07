package com.quickvideo.quickvideo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;

import com.quickvideo.quickvideo.allbasic.BaseActivity;
import com.quickvideo.quickvideo.fragments.ClassificationFragment;
import com.quickvideo.quickvideo.fragments.DiscoverFragment;
import com.quickvideo.quickvideo.mine.view.MineFragment;
import com.quickvideo.quickvideo.fragments.RecommendFragment;
import com.quickvideo.quickvideo.utils.NonSwipeableViewPager;
import com.yinglan.alphatabs.AlphaTabsIndicator;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
//2017.1205

public class MainActivity extends BaseActivity{

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
}
