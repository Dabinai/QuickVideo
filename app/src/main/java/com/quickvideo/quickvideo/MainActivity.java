package com.quickvideo.quickvideo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.quickvideo.quickvideo.activity.LSSCActivity;
import com.quickvideo.quickvideo.activity.ThemeActivity;
import com.quickvideo.quickvideo.activity.WelfareActivity;
import com.quickvideo.quickvideo.allbasic.Myapp;
import com.quickvideo.quickvideo.allbasic.BaseActivity;
import com.quickvideo.quickvideo.bean.MenuBean;
import com.quickvideo.quickvideo.fragments.ClassificationFragment;
import com.quickvideo.quickvideo.fragments.DiscoverFragment;
import com.quickvideo.quickvideo.fragments.RecommendFragment;
import com.quickvideo.quickvideo.leftmenu.utils.YijianDaiLog;
import com.quickvideo.quickvideo.activity.LisVideotActivity;
import com.quickvideo.quickvideo.mainui.MenusAdapter;
import com.quickvideo.quickvideo.mainui.ResideLayout;


import com.quickvideo.quickvideo.mine.view.frag.MineFragment;

import com.quickvideo.quickvideo.utils.NonSwipeableViewPager;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;
import com.yinglan.alphatabs.AlphaTabsIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    final int WAIT_TIME = 200;
    public final static String Banner_Stop = "Banner_Stop";

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
//                        Toast.makeText(MainActivity.this, "我的收藏", Toast.LENGTH_SHORT).show();

                        //                跳转到收藏界面
                        Intent intent2 = new Intent(MainActivity.this, LSSCActivity.class);
                        startActivity(intent2);
                        break;
                    case 1:
                        Toast.makeText(MainActivity.this, "我的下载", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, LisVideotActivity.class));
                        break;
                    case 2:
                        Toast.makeText(MainActivity.this, "福利", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, WelfareActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        Toast.makeText(MainActivity.this, "分享", Toast.LENGTH_SHORT).show();
                        UMWeb web = new UMWeb("https://daohang.qq.com/?fr=hmpage");
                        web.setTitle("This is music title");//标题
                        web.setDescription("my description");//描述

                        new ShareAction(MainActivity.this)
                                .setPlatform(SHARE_MEDIA.QQ)//传入平台
                                .withMedia(web)
                                .setCallback(shareListener)//回调监听器
                                .share();
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
                //                点击弹出关于我们对话框
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                View view2 = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_about_us_layout, null);
                builder1.setView(view2);
                final AlertDialog guanyudialog = builder1.create();
                TextView close = view2.findViewById(R.id.close);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        guanyudialog.dismiss();
                    }
                });
                guanyudialog.show();
                break;
            case R.id.theme:
                //                更改主题界面
                Intent i = new Intent(MainActivity.this, ThemeActivity.class);
                startActivityForResult(i, 0);

                break;
            case R.id.user_icon:
                Toast.makeText(MainActivity.this, "登陆头像", Toast.LENGTH_SHORT).show();
                UMShareAPI.get(MainActivity.this).getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ,umAuthListener);
                break;
        }
    }
    UMAuthListener umAuthListener  = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            System.out.println("uid========"+map.get("uid"));
            System.out.println("name========"+map.get("name"));
            System.out.println("iconurl========"+map.get("iconurl"));
            Toast.makeText(MainActivity.this , "登录成功"+map.get("name"),Toast.LENGTH_SHORT).show();

            //ImageLoader.getInstance().displayImage(map.get("iconurl"),userIcon);
            //设置QQ名字
            // mname.setText(map.get("name"));
            userIcon.setImageURI(map.get("iconurl"));
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {

        }
    };

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(MainActivity.this,"成功了",Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(MainActivity.this,"失败"+t.getMessage(),Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(MainActivity.this,"取消了",Toast.LENGTH_LONG).show();

        }
    };

    //处理主题
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                this.finish();

                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
            }
        }
        UMShareAPI.get(MainActivity.this).onActivityResult( requestCode, resultCode, data);
    }
}
