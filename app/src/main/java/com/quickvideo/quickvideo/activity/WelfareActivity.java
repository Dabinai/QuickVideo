package com.quickvideo.quickvideo.activity;

import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.util.Log;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.quickvideo.quickvideo.R;
import com.quickvideo.quickvideo.allbasic.BaseActivity;
import com.quickvideo.quickvideo.clientutils.API;
import com.quickvideo.quickvideo.clientutils.ApiService;
import com.quickvideo.quickvideo.leftmenu.menubean.GankBean;
import com.quickvideo.quickvideo.leftmenu.view.WelfareAdapter;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WelfareActivity extends BaseActivity {

    private static final String TAG = "WelfareActivity";
    @BindView(R.id.Welfare_XRecyclerView)
    XRecyclerView WelfareXRecyclerView;
    private int screenWidth;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //2.声名为瀑布流的布局方式: 3列,垂直方向
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        //3.为recyclerView设置布局管理器
        WelfareXRecyclerView.setLayoutManager(staggeredGridLayoutManager);

        //初始化数据
        initData(20,1);
        //改mvp时再做刷新加载
        WelfareXRecyclerView.setPullRefreshEnabled(false);
        WelfareXRecyclerView.setLoadingMoreEnabled(false);

    }

    //布局
    @Override
    public int getLayout() {
        return R.layout.activity_welfare;
    }

    //获取数据
    private void initData(int num,int page) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.FULI_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<GankBean> girlList = apiService.getGirlList(num, page);
        girlList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankBean>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GankBean gankBean) {
                        Log.d(TAG, "onNext:---- " + gankBean.isError() + "====" + gankBean.getResults().get(0).getUrl().toLowerCase());
                        if (null!=gankBean){
                            WelfareAdapter welfareAdapter = new WelfareAdapter(WelfareActivity.this, gankBean,getMetrics());
                            WelfareXRecyclerView.setAdapter(welfareAdapter);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    //获取屏幕宽度,高度
    public int getMetrics(){

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;

        Log.d(TAG, "onCreate: 屏幕宽度"+ screenWidth +"------高度:"+screenHeight);
        return screenWidth;
    }


}