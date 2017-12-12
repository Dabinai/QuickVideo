package com.quickvideo.quickvideo.fragments;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.quickvideo.quickvideo.activity.PageVideoActivity;
import com.quickvideo.quickvideo.allbasic.Myapp;
import com.quickvideo.quickvideo.R;

import com.quickvideo.quickvideo.allbasic.BaseMVPFragment;
import com.quickvideo.quickvideo.bean.FirsEvent;
import com.quickvideo.quickvideo.bean.PinDaoBean;
import com.quickvideo.quickvideo.clientutils.MyDialog;
import com.quickvideo.quickvideo.discover.DiscoverAdapterHua;
import com.quickvideo.quickvideo.discover.cardswipelayout.CardConfig;
import com.quickvideo.quickvideo.discover.cardswipelayout.CardItemTouchHelperCallback;
import com.quickvideo.quickvideo.discover.cardswipelayout.CardLayoutManager;
import com.quickvideo.quickvideo.discover.cardswipelayout.OnSwipeListener;
import com.quickvideo.quickvideo.discover.p.DisPresenter;
import com.quickvideo.quickvideo.discover.v.DisIView;
import com.quickvideo.quickvideo.mine.bean.Bean;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Dabin on 2017/12/4.
 * 发现：DiscoverFragment
 * 仿探探卡片滑动+换一批（分页换数据），
 * 点击事件（点击跳到VideoInfoActivity）
 */

public class DiscoverFragment extends BaseMVPFragment<DisPresenter> implements DisIView {

    @BindView(R.id.discover_button)
    Button discoverButton;
    @BindView(R.id.discover_recycler)
    RecyclerView discoverRecycler;
    Unbinder unbinder;

    private int page = 1;
    private DiscoverAdapterHua discoverAdapterHua;
    private PinDaoBean pinDaoBean;

    @Override
    public int getLayout() {
        return R.layout.frag_discover;
    }
    @Override
    public void showFragData(PinDaoBean pinDaoBean) {
        this.pinDaoBean = pinDaoBean;
        initData();
    }

    public void initData() {
        final List<PinDaoBean.RetBean.ListBean> list = pinDaoBean.ret.list;
        discoverRecycler.setItemAnimator(new DefaultItemAnimator());
        discoverAdapterHua = new DiscoverAdapterHua(getActivity(), pinDaoBean.ret.list);
        discoverRecycler.setAdapter(discoverAdapterHua);
        CardItemTouchHelperCallback cardCallback = new CardItemTouchHelperCallback(discoverRecycler.getAdapter(), list);
        cardCallback.setOnSwipedListener(new OnSwipeListener<PinDaoBean.RetBean.ListBean>() {
            @Override
            public void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {
                DiscoverAdapterHua.MyHolder myHolder = (DiscoverAdapterHua.MyHolder) viewHolder;
                viewHolder.itemView.setAlpha(1 - Math.abs(ratio) * 0.2f);
                if (direction == CardConfig.SWIPING_LEFT) {
                    myHolder.dislikeImageView.setAlpha(Math.abs(ratio));
                } else if (direction == CardConfig.SWIPING_RIGHT) {
                    myHolder.likeImageView.setAlpha(Math.abs(ratio));
                } else {
                    myHolder.dislikeImageView.setAlpha(0f);
                    myHolder.likeImageView.setAlpha(0f);
                }
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, PinDaoBean.RetBean.ListBean mybean, int direction) {
                DiscoverAdapterHua.MyHolder myHolder = (DiscoverAdapterHua.MyHolder) viewHolder;
                viewHolder.itemView.setAlpha(1f);
                myHolder.dislikeImageView.setAlpha(0f);
                myHolder.likeImageView.setAlpha(0f);
            }

            @Override
            public void onSwipedClear() {
                Toast.makeText(getActivity(), "data clear", Toast.LENGTH_SHORT).show();
                final MyDialog mMyDialog = new MyDialog(getActivity());
                mMyDialog.show();

                discoverRecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        shuaiXin();
                        mMyDialog.dismiss();
                        discoverRecycler.getAdapter().notifyDataSetChanged();
                    }
                }, 3000L);
            }
        });
        final ItemTouchHelper touchHelper = new ItemTouchHelper(cardCallback);
        final CardLayoutManager cardLayoutManager = new CardLayoutManager(discoverRecycler, touchHelper);
        discoverRecycler.setLayoutManager(cardLayoutManager);
        touchHelper.attachToRecyclerView(discoverRecycler);
        discoverAdapterHua.setOnItemClickLitener(new DiscoverAdapterHua.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), "" + list.get(position).title, Toast.LENGTH_SHORT).show();
                EventBus.getDefault().postSticky(new FirsEvent(list.get(position).dataId));
                getActivity().startActivity(new Intent(getActivity(), PageVideoActivity.class));


                /**
                 * 在点击的时候获取值然后存到数据库
                 * 历史界面就可以接收显示数据
                 */
                String title = list.get(position).title;
                String pic = list.get(position).pic;
                Bean bean = new Bean(title,pic,position);
                //使用数据库存储数据
                boolean b = Myapp.getManager().ifEquals(bean);
                if (b) {

                } else {
                    Myapp.getManager().putData(bean);
                }
            }
        });
    }



    @OnClick(R.id.discover_button)
    public void onViewClicked() {
        page++;
        shuaiXin();
    }



    @Override
    public void initInject() {
    }

    @Override
    public void initView(LayoutInflater inflater) {
        super.initView(inflater);
       shuaiXin();
    }
    public void shuaiXin(){
        mPresenter = new DisPresenter(this);
        mPresenter.setUrl("402834815584e463015584e539330016", page + "");
        mPresenter.showFrag();
    }
}
