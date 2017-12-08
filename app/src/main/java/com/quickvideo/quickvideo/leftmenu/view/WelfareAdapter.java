package com.quickvideo.quickvideo.leftmenu.view;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.quickvideo.quickvideo.R;
import com.quickvideo.quickvideo.leftmenu.menubean.GankBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by xue on 2017-12-08.
 * 福利页适配器
 */

public class WelfareAdapter extends XRecyclerView.Adapter<WelfareAdapter.ViewHolder> {

    Context mcontext;
    GankBean gankBean;
    int screenWidth;//屏幕宽度
    private List<Integer> heightList;//装产出的随机数

    public WelfareAdapter(Context mcontext, GankBean gankBean, int screenWidth) {
        this.mcontext = mcontext;
        this.gankBean = gankBean;
        this.screenWidth = screenWidth;

        //记录为每个控件产生的随机高度,避免滑回到顶部出现空白
        heightList = new ArrayList<>();
        for (int i = 0; i < gankBean.getResults().size(); i++) {
            int height = new Random().nextInt(200) + 350;//[350,550)的随机数
            heightList.add(height);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.welfare_item, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Uri uri = Uri.parse(gankBean.getResults().get(position).getUrl());
        holder.girls_img.setImageURI(uri);

        //由于需要实现瀑布流的效果,所以就需要动态的改变控件的高度了
        ViewGroup.LayoutParams params =holder.girls_img.getLayoutParams();
        params.height=heightList.get(position);
        params.width=screenWidth/2;
        holder.girls_img.setLayoutParams(params);

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                //重试之后要加载的图片URI地址
                .setUri(uri)
                //设置点击重试是否开启
                .setTapToRetryEnabled(true)
                //设置旧的Controller
                .setOldController(holder.girls_img.getController())
                //构建
                .build();
        //设置DraweeController
        holder.girls_img.setController(controller);
    }

    @Override
    public int getItemCount() {
        if (gankBean!=null){
            return gankBean.getResults().size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView girls_img;

        public ViewHolder(View itemView) {
            super(itemView);
            girls_img = itemView.findViewById(R.id.girls_img);
        }

    }



}
