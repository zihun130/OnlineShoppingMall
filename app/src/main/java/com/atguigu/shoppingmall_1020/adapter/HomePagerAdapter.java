package com.atguigu.shoppingmall_1020.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.shoppingmall_1020.R;
import com.atguigu.shoppingmall_1020.adapter.homeadapter.ChannelGridAdapter;
import com.atguigu.shoppingmall_1020.adapter.homeadapter.HotAdapter;
import com.atguigu.shoppingmall_1020.adapter.homeadapter.RecommendAdapter;
import com.atguigu.shoppingmall_1020.adapter.homeadapter.SecKillAdapter;
import com.atguigu.shoppingmall_1020.adapter.homeadapter.ViewPagerAdapter;
import com.atguigu.shoppingmall_1020.app.GridInfoActivity;
import com.atguigu.shoppingmall_1020.app.WebViewActivity;
import com.atguigu.shoppingmall_1020.domain.GoodsBean;
import com.atguigu.shoppingmall_1020.domain.HomeBean;
import com.atguigu.shoppingmall_1020.domain.webviewbean;
import com.atguigu.shoppingmall_1020.utils.Constants;
import com.atguigu.shoppingmall_1020.utils.DensityUtil;
import com.atguigu.shoppingmall_1020.utils.GlidImageLoader;
import com.atguigu.shoppingmall_1020.view.ScrollGridView;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.zhy.magicviewpager.transformer.RotateYTransformer;

import java.util.ArrayList;
import java.util.List;

import cn.iwgang.countdownview.CountdownView;

/**
 * Created by sun on 2017/6/12.
 */

public class HomePagerAdapter extends RecyclerView.Adapter {
    public static final String GOODS_BEAN = "goods_bean";
    public static final String WEBVIEW_BEAN = "webview_bean";


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
        } else if (viewType == RECOMMEND) {
            return new RecommendViewHolder(context, inflate.inflate(R.layout.recommend_item, null));
        } else if (viewType == HOT) {
            return new HotViewHolder(context, inflate.inflate(R.layout.hot_item, null));
        }

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
        }  else if (getItemViewType(position) == RECOMMEND) {
            RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
            recommendViewHolder.setData(result.getRecommend_info());
        }else if (getItemViewType(position) == HOT) {
            HotViewHolder hotViewHolder = (HotViewHolder) holder;
            hotViewHolder.setData(result.getHot_info());
        }


    }

    @Override
    public int getItemCount() {
        return 6;
    }

    private class BannerViewHolder extends RecyclerView.ViewHolder {

        private final Context context;
        private final Banner banner;

        public BannerViewHolder(Context context, View itemView) {
            super(itemView);
            this.context=context;
            banner= (Banner) itemView.findViewById(R.id.banner);
        }

        public void setData(final List<HomeBean.ResultBean.BannerInfoBean> banner_info) {
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
                            int realposition=position;
                            if(realposition<=banner_info.size()){
                                String product_id = "";
                                String name = "";
                                String cover_price = "";

                                if(realposition==0){
                                    product_id = "627";
                                    cover_price = "32.00";
                                    name = "剑三T恤批发";
                                }else if(realposition==1){
                                    product_id = "21";
                                    cover_price = "8.00";
                                    name = "同人原创】剑网3 剑侠情缘叁 Q版成男 口袋胸针";
                                }else {
                                    product_id = "1341";
                                    cover_price = "50.00";
                                    name = "【蓝诺】《天下吾双》 剑网3同人本";
                                }

                                String image = banner_info.get(realposition).getImage();
                                GoodsBean goodsBean = new GoodsBean();
                                goodsBean.setName(name);
                                goodsBean.setCover_price(cover_price);
                                goodsBean.setFigure(image);
                                goodsBean.setProduct_id(product_id);

                                Intent intent = new Intent(context, GridInfoActivity.class);
                                intent.putExtra(GOODS_BEAN, goodsBean);
                                context.startActivity(intent);




                            }


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
                    webviewbean webviewbean = new webviewbean();
                    webviewbean.setIcon_url(actInfoBean.getIcon_url());
                    webviewbean.setName(actInfoBean.getName());
                    webviewbean.setUrl(Constants.BASE_URL_IMAGE+actInfoBean.getUrl());
                    Intent intent=new Intent(context, WebViewActivity.class);
                    intent.putExtra(WEBVIEW_BEAN,webviewbean);
                    context.startActivity(intent);
                }
            });
        }
    }

    private boolean isFrist=false;
    private class SeckillViewHolder extends RecyclerView.ViewHolder {

        private final Context context;
        private RecyclerView rv_seckill;
        private TextView     tv_more_seckill;
        private CountdownView  countdownview;
        private SecKillAdapter adapter;

        Handler handler=new Handler();
        private HomeBean.ResultBean.SeckillInfoBean seckillinfo;

        private Runnable refreshTimeSecKill=new Runnable() {
            @Override
            public void run() {
                long currentTime=System.currentTimeMillis();

                if(currentTime>=Long.parseLong(seckillinfo.getEnd_time())){
                    handler.removeCallbacksAndMessages(null);
                }else {
                    countdownview.updateShow(Long.parseLong(seckillinfo.getEnd_time())-currentTime);
                    handler.postDelayed(refreshTimeSecKill,1000);
                }
            }
        };

        public void startRefreshTime() {
            handler.postDelayed(refreshTimeSecKill, 10);
        }
        public SeckillViewHolder(Context context, View itemView) {
            super(itemView);
            this.context=context;
            rv_seckill = (RecyclerView) itemView.findViewById(R.id.rv_seckill);
            tv_more_seckill = (TextView) itemView.findViewById(R.id.tv_more_seckill);
            countdownview = (CountdownView) itemView.findViewById(R.id.countdownview);
        }

        public void setData(final HomeBean.ResultBean.SeckillInfoBean seckill_info) {
            this.seckillinfo=seckill_info;
            adapter=new SecKillAdapter(context,seckill_info);
            rv_seckill.setAdapter(adapter);
            rv_seckill.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));

            if(!isFrist){
                isFrist=true;
                long totleTime=Long.parseLong(seckillinfo.getEnd_time())-Long.parseLong(seckillinfo.getStart_time());
                long currTime=System.currentTimeMillis();
                seckillinfo.setEnd_time(currTime+totleTime+"");
                startRefreshTime();
            }

            adapter.setOnItemClickListener(new SecKillAdapter.OnItemClickListener() {
                @Override
                public void OnItemClick(int position) {
                    GoodsBean goodsBean = new GoodsBean();
                    HomeBean.ResultBean.SeckillInfoBean.ListBean hotInfoBean = seckill_info.getList().get(position);
                    goodsBean.setName(hotInfoBean.getName());
                    goodsBean.setProduct_id(hotInfoBean.getProduct_id());
                    goodsBean.setCover_price(hotInfoBean.getCover_price());
                    goodsBean.setFigure(hotInfoBean.getFigure());

                    Intent intent=new Intent(context, GridInfoActivity.class);
                    intent.putExtra(GOODS_BEAN,goodsBean);
                    context.startActivity(intent);
                }
            });

        }
    }

    private class RecommendViewHolder extends RecyclerView.ViewHolder {

        private final Context context;
        private TextView tv_more_recommend;
        private GridView gv_recommend;
        private RecommendAdapter adapter;

        public RecommendViewHolder(Context context, View itemView) {
            super(itemView);
            this.context=context;
            tv_more_recommend = (TextView) itemView.findViewById(R.id.tv_more_recommend);
            gv_recommend = (GridView) itemView.findViewById(R.id.gv_recommend);
        }

        public void setData(final List<HomeBean.ResultBean.RecommendInfoBean> recommend_info) {
                    adapter=new RecommendAdapter(context,recommend_info);
                    gv_recommend.setAdapter(adapter);
            gv_recommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    GoodsBean goodsBean = new GoodsBean();
                    HomeBean.ResultBean.RecommendInfoBean hotInfoBean = recommend_info.get(position);
                    goodsBean.setName(hotInfoBean.getName());
                    goodsBean.setProduct_id(hotInfoBean.getProduct_id());
                    goodsBean.setCover_price(hotInfoBean.getCover_price());
                    goodsBean.setFigure(hotInfoBean.getFigure());

                    Intent intent=new Intent(context, GridInfoActivity.class);
                    intent.putExtra(GOODS_BEAN,goodsBean);
                    context.startActivity(intent);
                }
            });
        }
    }

    private class HotViewHolder extends RecyclerView.ViewHolder {

        private final Context context;
        private TextView tv_more_hot;
        private ScrollGridView gv_hot;
        private HotAdapter adapter;

        public HotViewHolder(Context context, View itemView) {
            super(itemView);
            this.context=context;
            gv_hot = (ScrollGridView) itemView.findViewById(R.id.gv_hot);
            tv_more_hot = (TextView) itemView.findViewById(R.id.tv_more_hot);

        }

        public void setData(final List<HomeBean.ResultBean.HotInfoBean> hot_info) {
            adapter=new HotAdapter(context,hot_info);
            gv_hot.setAdapter(adapter);
            gv_hot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    GoodsBean goodsBean = new GoodsBean();
                    HomeBean.ResultBean.HotInfoBean hotInfoBean = hot_info.get(position);
                    goodsBean.setName(hotInfoBean.getName());
                    goodsBean.setProduct_id(hotInfoBean.getProduct_id());
                    goodsBean.setCover_price(hotInfoBean.getCover_price());
                    goodsBean.setFigure(hotInfoBean.getFigure());

                    Intent intent=new Intent(context, GridInfoActivity.class);
                    intent.putExtra(GOODS_BEAN,goodsBean);
                    context.startActivity(intent);
                }
            });
        }
    }
}
