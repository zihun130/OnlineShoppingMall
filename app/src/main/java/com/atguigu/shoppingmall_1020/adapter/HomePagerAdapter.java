package com.atguigu.shoppingmall_1020.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.atguigu.shoppingmall_1020.R;
import com.atguigu.shoppingmall_1020.adapter.homeadapter.ChannelGridAdapter;
import com.atguigu.shoppingmall_1020.adapter.homeadapter.ViewPagerAdapter;
import com.atguigu.shoppingmall_1020.domain.HomeBean;
import com.atguigu.shoppingmall_1020.utils.Constants;
import com.atguigu.shoppingmall_1020.utils.DensityUtil;
import com.atguigu.shoppingmall_1020.utils.GlidImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.zhy.magicviewpager.transformer.RotateYTransformer;

import java.util.ArrayList;
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
        } else if (viewType == CHANNEL) {
            return new ChannelViewHolder(context, inflate.inflate(R.layout.channel_item, null));
        } else if (viewType == ACT) {
            return new ActViewHolder(context, inflate.inflate(R.layout.act_item, null));
        } else if (viewType == SECKILL) {
            return new SeckillViewHolder(context, inflate.inflate(R.layout.seckill_item, null));
        } /*else if (viewType == RECOMMEND) {
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
        } else if (getItemViewType(position) == CHANNEL) {
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            channelViewHolder.setData(result.getChannel_info());
        } else if (getItemViewType(position) == ACT) {
            ActViewHolder actViewHolder = (ActViewHolder) holder;
            actViewHolder.setData(result.getAct_info());
        }else if (getItemViewType(position) == SECKILL) {
            SeckillViewHolder seckillViewHolder = (SeckillViewHolder) holder;
            seckillViewHolder.setData(result.getSeckill_info());
        } /* else if (getItemViewType(position) == RECOMMEND) {
            RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
            recommendViewHolder.setData(result.getRecommend_info());
        }else if (getItemViewType(position) == HOT) {
            HotViewHolder hotViewHolder = (HotViewHolder) holder;
            hotViewHolder.setData(result.getHot_info());
        }*/


    }

    @Override
    public int getItemCount() {
        return 4;
    }

    private class BannerViewHolder extends RecyclerView.ViewHolder {

        private final Context context;
        private final Banner banner;

        public BannerViewHolder(Context context, View itemView) {
            super(itemView);
            this.context=context;
            banner= (Banner) itemView.findViewById(R.id.banner);
        }

        public void setData(List<HomeBean.ResultBean.BannerInfoBean> banner_info) {
            List<String> images = new ArrayList<>();
            for(int i = 0; i <banner_info.size() ; i++) {
                images.add(Constants.BASE_URL_IMAGE+banner_info.get(i).getImage());
            }
            //banner设置图片
            banner.setImages(images)
                    .setImageLoader(new GlidImageLoader())
                    .setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(int position) {

                            Toast.makeText(context, "position==" + position, Toast.LENGTH_SHORT).show();
                        }
                    })
                    .start();
        }
    }


    private class ChannelViewHolder extends RecyclerView.ViewHolder {

        private final Context context;
        private final GridView gridview;
        private ChannelGridAdapter adapter;

        public ChannelViewHolder(Context context, View itemView) {
            super(itemView);
            this.context=context;
            gridview= (GridView) itemView.findViewById(R.id.gv);
        }

        public void setData(List<HomeBean.ResultBean.ChannelInfoBean> channel_info) {
            adapter=new ChannelGridAdapter(context,channel_info);
            gridview.setAdapter(adapter);

            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private class ActViewHolder extends RecyclerView.ViewHolder {

        private final Context context;
        private ViewPager act_viewpager;
        private ViewPagerAdapter adapter;

        public ActViewHolder(Context context, View itemView) {
            super(itemView);
            this.context=context;
            act_viewpager = (ViewPager) itemView.findViewById(R.id.act_viewpager);

        }

        public void setData(final List<HomeBean.ResultBean.ActInfoBean> act_info) {
            adapter=new ViewPagerAdapter(context,act_info);
             act_viewpager.setAdapter(adapter);

            //设置图片间距
            act_viewpager.setPageMargin(DensityUtil.dip2px(context, 20));
            //优化效果
            act_viewpager.setPageTransformer(true, new
                    RotateYTransformer());

            //设置点击事件
            adapter.setOnItemClickListener(new ViewPagerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    HomeBean.ResultBean.ActInfoBean actInfoBean = act_info.get(position);
                    Toast.makeText(context, "" + actInfoBean.getName(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    private class SeckillViewHolder extends RecyclerView.ViewHolder {

        public SeckillViewHolder(View itemView) {
            super(itemView);
        }

        public void setData(HomeBean.ResultBean.SeckillInfoBean seckill_info) {

        }
    }
}
