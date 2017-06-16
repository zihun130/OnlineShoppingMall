package atguigu.com.onlineshoppingmall.adapter.classifyadapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import atguigu.com.onlineshoppingmall.R;
import atguigu.com.onlineshoppingmall.adapter.HomeAdapter;
import atguigu.com.onlineshoppingmall.app.GoodsInfoActivity;
import atguigu.com.onlineshoppingmall.app.GoodsListActivity;
import atguigu.com.onlineshoppingmall.domain.ClassifyBean;
import atguigu.com.onlineshoppingmall.domain.GoodsBean;
import atguigu.com.onlineshoppingmall.utils.Constants;
import atguigu.com.onlineshoppingmall.utils.DensityUtil;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by sun on 2017/6/15.
 */

public class RightAdapter extends RecyclerView.Adapter {
    private static final int HOT = 0;
    private static final int COMMON = 1;
    private final Context context;
    private final List<ClassifyBean.ResultBean.HotProductListBean> hot_result;
    private final List<ClassifyBean.ResultBean.ChildBean> child;
    private int currentType = HOT;

    public RightAdapter(Context context, List<ClassifyBean.ResultBean> result) {
        this.context = context;
        this.hot_result = result.get(0).getHot_product_list();
        child = result.get(0).getChild();
    }

    @Override
    public int getItemCount() {
        return 1 + child.size();
    }



    @Override
    public int getItemViewType(int position) {
        if (position == HOT) {
            currentType = HOT;
        } else {
            currentType = COMMON;
        }
        return currentType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HOT) {
            return new HotViewHolder(context, View.inflate(context, R.layout.item_hot_right, null));
        } else if (viewType == COMMON) {
            return new CommonViewHolder(context, View.inflate(context, R.layout.item_common_right, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == HOT) {
            HotViewHolder viewHolder = (HotViewHolder) holder;
            viewHolder.setData(hot_result);
        } else if (getItemViewType(position) == COMMON) {
            CommonViewHolder viewHolder = (CommonViewHolder) holder;
            int realPosition = position - 1;
            viewHolder.setData(child.get(realPosition), realPosition);
        }

    }


    class HotViewHolder extends RecyclerView.ViewHolder {

        private final Context context;
        @InjectView(R.id.ll_hot_right)
        LinearLayout llHotRight;
        @InjectView(R.id.hsl_hot_right)
        HorizontalScrollView hslHotRight;

        public HotViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            ButterKnife.inject(this,itemView);
        }

        public void setData(final List<ClassifyBean.ResultBean.HotProductListBean> hot_result) {

            for (int i = 0; i < hot_result.size(); i++) {
                ClassifyBean.ResultBean.HotProductListBean listBean = hot_result.get(i);
                //线性布局
                LinearLayout myLinear = new LinearLayout(context);
                //参数
                LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, -2);
                llParams.setMargins(DensityUtil.dip2px(context, 5), 0, DensityUtil.dip2px(context, 5), DensityUtil.dip2px(context, 20));
                myLinear.setGravity(Gravity.CENTER);
                myLinear.setOrientation(LinearLayout.VERTICAL);

                //设置参数
                //图片

                ImageView imageView = new ImageView(context);
                LinearLayout.LayoutParams ivParams = new LinearLayout.LayoutParams(DensityUtil.dip2px(context, 80), DensityUtil.dip2px(context, 80));
                ivParams.setMargins(0, 0, 0, DensityUtil.dip2px(context, 10));

                //联网请求图片
                Glide.with(context).load(Constants.BASE_URL_IMAGE + listBean.getFigure()).into(imageView);
                //添加到线性布局
                myLinear.addView(imageView, ivParams);

                // 文本

                LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                TextView textView = new TextView(context);
                textView.setTextColor(Color.parseColor("#ed3f3f"));

                textView.setText("￥" + listBean.getCover_price());
                //添加到线性布局
                myLinear.addView(textView, tvParams);


                //整条的点击事件
                myLinear.setTag(i);
                myLinear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = (int) v.getTag();
                        String cover_price = hot_result.get(position).getCover_price();
                        String name = hot_result.get(position).getName();
                        String figure = hot_result.get(position).getFigure();
                        String product_id = hot_result.get(position).getProduct_id();


                        GoodsBean goodsBean = new GoodsBean();
                        goodsBean.setProduct_id(product_id);
                        goodsBean.setFigure(figure);
                        goodsBean.setCover_price(cover_price);
                        goodsBean.setName(name);

                        Intent intent = new Intent(context, GoodsInfoActivity.class);
                        intent.putExtra(HomeAdapter.GOODS_BEAN, goodsBean);
                        context.startActivity(intent);


                    }
                });


                //添加到
                llHotRight.addView(myLinear, llParams);
            }
            

        }
    }

    class CommonViewHolder extends RecyclerView.ViewHolder {

        private final Context context;
        @InjectView(R.id.iv_ordinary_right)
        ImageView ivOrdinaryRight;
        @InjectView(R.id.tv_ordinary_right)
        TextView tvOrdinaryRight;
        @InjectView(R.id.ll_root)
        LinearLayout llRoot;

        public CommonViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            ButterKnife.inject(this, itemView);
        }

        public void setData(ClassifyBean.ResultBean.ChildBean childBean, final int realPosition) {
            Glide.with(context).load(Constants.BASE_URL_IMAGE + childBean.getPic()).into(ivOrdinaryRight);
            tvOrdinaryRight.setText(childBean.getName());

            llRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (realPosition <= 8) {
                        Intent intent = new Intent(context, GoodsListActivity.class);
                        intent.putExtra("position", realPosition);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}
