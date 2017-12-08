package com.quickvideo.quickvideo.serchaall;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.TextView;
import android.widget.Toast;


import com.quickvideo.quickvideo.R;
import com.quickvideo.quickvideo.RecommendPackage.Video.PageVideo;
import com.quickvideo.quickvideo.SearchBean;
import com.quickvideo.quickvideo.bean.FirsEvent;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/12/7.
 */

public class SearchActivity extends Activity {
    @BindView(R.id.recy)
    RecyclerView recy;
    @BindView(R.id.text)
    TextView text;
    private String keyword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scond);
        ButterKnife.bind(this);
        keyword = getIntent().getStringExtra("keyword");

        ok();
    }
    private void ok() {
        OkHttpUtils.get()
                .url("http://api.svipmovie.com/front/searchKeyWordApi/getVideoListByKeyWord.do?keyword="+keyword.toString())
                .build()
                .execute(new StringCallback() {

                    private List<SearchBean.RetBean.ListBean> list;
                    SearchBean bean;
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String s = response.toString();
                        bean = SearchBean.objectFromData(s);
                        SearchBean.RetBean ret = bean.getRet();
                        String all = ret.getAll();
                        list = ret.getList();
                        if (all.equals("0")){



                            Toast.makeText(SearchActivity.this,"失败"+keyword.toString(),Toast.LENGTH_SHORT).show();
                                text.setText("搜索错误，请重新输入");
                        }else{
                            LinearLayoutManager layoutManager = new LinearLayoutManager(SearchActivity.this );
                            StaggeredGridLayoutManager layoutManager2 = new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.HORIZONTAL);

//设置布局管理器
                            recy .setLayoutManager(new GridLayoutManager( SearchActivity.this,3));
//设置为垂直布局，这也是默认的

                            layoutManager.setOrientation(OrientationHelper. VERTICAL) ;
                         MyRecyclerAdapter recyclerAdapter = new MyRecyclerAdapter(SearchActivity.this,list);
                                   recyclerAdapter.setOnItemClickListener(new MyRecyclerAdapter.OnItemClickListener() {
                                       @Override
                                       public void onClick(int position) {
                                            SearchBean.RetBean.ListBean listBean = list.get(position);
                                           String shareURL = listBean.getShareURL();
//                                           Intent intent = new Intent(SearchActivity.this,PlayActivity.class);
//                                           intent.putExtra("shareURL", shareURL);
//                                           startActivity(intent);
                                           String dataId = bean.getRet().getList().get(position).getDataId();
                                           //String shareURL1 = bean.getRet().getList().get(position).getShareURL();
                                           Intent intent2 = new Intent(SearchActivity.this, PageVideo.class);
                                           EventBus.getDefault().postSticky(new FirsEvent(dataId));
                                           startActivity(intent2);
                                       }

                                       @Override
                                       public void onLongClick(int position) {
                                        //   Toast.makeText(SearchActivity.this,"onLongClick事件       您点击了第："+position+"个Item",Toast.LENGTH_SHORT).show();

                                       }
                                   });
//设置Adapter
                            recy.setAdapter(recyclerAdapter);

                        }
                    }
                });
    }
}
