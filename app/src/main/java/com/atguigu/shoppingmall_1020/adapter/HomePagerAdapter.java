package com.atguigu.shoppingmall_1020.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atguigu.shoppingmall_1020.R;
import com.atguigu.shoppingmall_1020.domain.HomeBean;

import java.util.List;

/**
 * Created by sun on 2017/6/12.
 */

public class HomePagerAdapter extends RecyclerView.Adapter {

    private static final int BANNER = 0;
    private static final int CHANNEL = 1;
    private static final int ACT = 2;
    private static final int SECKILL = 3;
    private static final int RECOMMEND = 4;
    private static final int HOT = 5;
    private final Context context;
    public int currentType = BANNER;
    private final HomeBean.ResultBean result;
    private LayoutInflater inflate;

    public HomePagerAdapter(Context mContext, HomeBean.ResultBean result) {
        this.context=mContext;
        this.result=result;
        inflate=LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        if(position==BANNER){
            currentType=BANNER;
        }else if (position == CHANNEL) {
            currentType = CHANNEL;
        } else if (position == ACT) {
            currentType = ACT;
        } else if (position == SECKILL) {
            currentType = SECKILL;
        } else if (position == RECOMMEND) {
            currentType = RECOMMEND;
        } else if (position == HOT) {
            currentType = HOT;
        }
        return currentType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(context, inflate.inflate(R.layout.banner_viewpager, null));
        } /*else if (viewType == CHANNEL) {
            return new ChannelViewHolder(context, inflate.inflate(R.layout.channel_item, null));
        } else if (viewType == ACT) {
            return new ActViewHolder(context, inflate.inflate(R.layout.act_item, null));
        } else if (viewType == SECKILL) {
            return new SeckillViewHolder(context, inflate.inflate(R.layout.seckill_item, null));
        } else if (viewType == RECOMMEND) {
            return new RecommendViewHolder(context, inflate.inflate(R.layout.recommend_item, null));
        } else if (viewType == HOT) {
            return new HotViewHolder(context, inflate.inflate(R.layout.hot_item, null));
        }
*/
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            //设置数据Banner的数据
            bannerViewHolder.setData(result.getBanner_info());
        } /*else if (getItemViewType(position) == CHANNEL) {
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            channelViewHolder.setData(result.getChannel_info());
        } else if (getItemViewType(position) == ACT) {
            ActViewHolder actViewHolder = (ActViewHolder) holder;
            actViewHolder.setData(result.getAct_info());
        } else if (getItemViewType(position) == SECKILL) {
            SeckillViewHolder seckillViewHolder = (SeckillViewHolder) holder;
            seckillViewHolder.setData(result.getSeckill_info());
        } else if (getItemViewType(position) == RECOMMEND) {
            RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
            recommendViewHolder.setData(result.getRecommend_info());
        }else if (getItemViewType(position) == HOT) {
            HotViewHolder hotViewHolder = (HotViewHolder) holder;
            hotViewHolder.setData(result.getHot_info());
        }*/


    }

    @Override
    public int getItemCount() {
        return 1;
    }

    private class BannerViewHolder extends RecyclerView.ViewHolder {

        public BannerViewHolder(Context context, View itemView) {
            super(itemView);
        }

        public void setData(List<HomeBean.ResultBean.BannerInfoBean> banner_info) {

        }
    }
}
