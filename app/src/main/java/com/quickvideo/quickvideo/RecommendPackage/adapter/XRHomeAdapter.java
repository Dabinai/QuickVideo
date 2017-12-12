package com.quickvideo.quickvideo.RecommendPackage.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.quickvideo.quickvideo.R;


import com.quickvideo.quickvideo.activity.PageVideoActivity;
import com.quickvideo.quickvideo.activity.SearchsActivity;
import com.quickvideo.quickvideo.bean.FirsEvent;
import com.quickvideo.quickvideo.bean.ShouYeBean;
import com.quickvideo.quickvideo.clientutils.GlideImage;

import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Long° Engagement on 2017/12/5.
 */

public class XRHomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ShouYeBean shouYeBean;
    Context context;
    ArrayList mlist;
    int screenWidth;

    public XRHomeAdapter(ShouYeBean shouYeBean, Context context, int screenWidth) {
        this.shouYeBean = shouYeBean;
        this.context = context;
        this.screenWidth = screenWidth;
    }

    private enum Item_Type {
        Type_one, Type_two, Type_three;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      /*  if (viewType == Item_Type.Type_one.ordinal()) {
            View mView = LayoutInflater.from(context).inflate(R.layout.recommend_1, null);
            ViewHolderA viewHolderA = new ViewHolderA(mView);
            return viewHolderA;

        } else */if (viewType == Item_Type.Type_two.ordinal()) {
            View mView2 = LayoutInflater.from(context).inflate(R.layout.recommend_2, null);
            ViewHolderB viewHolderB = new ViewHolderB(mView2);
            return viewHolderB;
        } else if (viewType == Item_Type.Type_three.ordinal()) {
            View mView3 = LayoutInflater.from(context).inflate(R.layout.recommend_3, null);
            ViewHolderC viewHolderC = new ViewHolderC(mView3);
            return viewHolderC;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
      /*  if (holder instanceof ViewHolderA) {
            mlist = new ArrayList();
            for (int i = 0; i < shouYeBean.ret.list.get(0).childList.size(); i++) {
                mlist.add(shouYeBean.ret.list.get(0).childList.get(i).pic);
            }
            ((ViewHolderA) holder).mbanner.setImageLoader(new GlideImage());
            ((ViewHolderA) holder).mbanner.setImages(mlist);
            ((ViewHolderA) holder).mbanner.start();
            ((ViewHolderA) holder).mbanner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {

                    String dataId = shouYeBean.ret.list.get(0).childList.get(position).dataId;
                    Intent intent = new Intent(context, PageVideoActivity.class);
                    EventBus.getDefault().postSticky(new FirsEvent(dataId));
                    context.startActivity(intent);
                }
            });

        } else*/ if (holder instanceof ViewHolderB) {
        } else if (holder instanceof ViewHolderC) {
            List<ShouYeBean.RetBean.ListBean.ChildListBean> childList = shouYeBean.ret.list.get(4).childList;
            HomeAdapter_three adapter_three = new HomeAdapter_three(childList, context,screenWidth);
            ((ViewHolderC) holder).Home_rv.setLayoutManager(new LinearLayoutManager(context));
            ((ViewHolderC) holder).Home_rv.setAdapter(adapter_three);
            adapter_three.setOnItemClieckLinster(new HomeAdapter_three.OnItemClieckLinster() {
                @Override
                public void onItemClickListener(View view, int pos) {
                    String dataId1 = shouYeBean.ret.list.get(4).childList.get(pos).dataId;
                    Intent intent2 = new Intent(context, PageVideoActivity.class);
                    EventBus.getDefault().postSticky(new FirsEvent(dataId1));
                    context.startActivity(intent2);
                }
            });

        }
    }

    @Override
    public int getItemViewType(int position) {
      /*  if (position == 0) {
            return Item_Type.Type_one.ordinal();
        } else */if (position == 0) {
            return Item_Type.Type_two.ordinal();

        } else if (position == 1) {
            return Item_Type.Type_three.ordinal();
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }

    }

  /*  class ViewHolderA extends RecyclerView.ViewHolder {
        public Banner mbanner;
        // RelativeLayout
        RelativeLayout rlGoSearch;

        public ViewHolderA(View itemView) {
            super(itemView);
            mbanner = itemView.findViewById(R.id.Slider);
            rlGoSearch = itemView.findViewById(R.id.rlGoSearch);
            rlGoSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(context, "ssss", Toast.LENGTH_SHORT).show();
                  context.startActivity(new Intent(context,SearchsActivity.class));
                }
            });
        }
    }*/

    class ViewHolderB extends RecyclerView.ViewHolder {
        TextView tv_2;

        public ViewHolderB(View itemView) {
            super(itemView);
            tv_2 = itemView.findViewById(R.id.tv_2);
        }
    }

    class ViewHolderC extends RecyclerView.ViewHolder {
        RecyclerView Home_rv;

        public ViewHolderC(View itemView) {
            super(itemView);
            Home_rv = itemView.findViewById(R.id.Home_rv);
        }
    }
}
