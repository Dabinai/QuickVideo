package com.quickvideo.quickvideo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.quickvideo.quickvideo.Classification.App.Myapp;
import com.quickvideo.quickvideo.allbasic.BaseActivity;
import com.quickvideo.quickvideo.bean.MenuBean;
import com.quickvideo.quickvideo.fragments.ClassificationFragment;
import com.quickvideo.quickvideo.fragments.DiscoverFragment;
import com.quickvideo.quickvideo.fragments.RecommendFragment;
import com.quickvideo.quickvideo.leftmenu.utils.YijianDaiLog;
import com.quickvideo.quickvideo.leftmenu.view.WelfareActivity;
import com.quickvideo.quickvideo.mainui.MenusAdapter;
import com.quickvideo.quickvideo.mainui.ResideLayout;
import com.quickvideo.quickvideo.mine.view.frag.MineFragment;
import com.quickvideo.quickvideo.utils.NonSwipeableViewPager;
import com.yinglan.alphatabs.AlphaTabsIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
//2017.1205


public class MainActivity extends BaseActivity {
    @BindView(R.id.user_icon)
    SimpleDraweeView userIcon;
    private Long firstTime = 0L;
    @BindView(R.id.menu)
    ListView menu_list;
    @BindView(R.id.reside_layout)
    ResideLayout resideLayout;
    @BindView(R.id.about)
    TextView about;
    @BindView(R.id.theme)
    TextView theme;
    @BindView(R.id.alphaIndicator)
    AlphaTabsIndicator alphaIndicator;
    @BindView(R.id.myviewpager)
    NonSwipeableViewPager myviewpager;
    private List<Fragment> fragList = new ArrayList<>();

    private ArrayList<MenuBean> menuBeans;
    private MenusAdapter menusAdapter;

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

        //按钮列表
        initMenu();

    }

    @Override
    public int getLayout() {

        return R.layout.activity_main;
    }
    //按钮列表
    private void initMenu() {
        //加载本地图片
        Uri uri = Uri.parse("res://com.quickvideo.quickvideo/" + R.mipmap.photo);
        userIcon.setImageURI(uri);

        menuBeans = new ArrayList<>();
        menuBeans.add(new MenuBean("我的收藏", R.mipmap.shoucang));
        menuBeans.add(new MenuBean("我的下载", R.mipmap.xiazai));
        menuBeans.add(new MenuBean("福利", R.mipmap.fuli));
        menuBeans.add(new MenuBean("分享", R.mipmap.fenxiang));
        menuBeans.add(new MenuBean("建议反馈", R.mipmap.yijian));
        menuBeans.add(new MenuBean("设置", R.mipmap.shezi));

        menusAdapter = new MenusAdapter(this, menuBeans);
        menu_list.setAdapter(menusAdapter);
        //监听
        menu_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            private NiftyDialogBuilder dialogBuilder;

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        Toast.makeText(MainActivity.this, "我的收藏", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(MainActivity.this, "我的下载", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(MainActivity.this, "福利", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, WelfareActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        Toast.makeText(MainActivity.this, "分享", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
//                        Toast.makeText(MainActivity.this, "建议反馈", Toast.LENGTH_SHORT).show();
                        YijianDaiLog yijianDaiLog = new YijianDaiLog(MainActivity.this);
                        yijianDaiLog.showDialog();
                        break;
                    case 5:
                        Toast.makeText(MainActivity.this, "设置", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });


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


    //头像---关于--主题，点击

    @OnClick({R.id.user_icon, R.id.about, R.id.theme})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.about:
                Toast.makeText(MainActivity.this, "关于", Toast.LENGTH_SHORT).show();
                break;
            case R.id.theme:
                Toast.makeText(MainActivity.this, "主题", Toast.LENGTH_SHORT).show();
                break;
            case R.id.user_icon:
                Toast.makeText(MainActivity.this, "登陆头像", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
