package com.atguigu.shoppingmall_1020.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.shoppingmall_1020.R;
import com.atguigu.shoppingmall_1020.domain.GoodsBean;
import com.atguigu.shoppingmall_1020.utils.CartStorage;
import com.atguigu.shoppingmall_1020.utils.Constants;
import com.atguigu.shoppingmall_1020.view.XaddSubView;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by sun on 2017/6/14.
 */

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.MyViewHolder> {
    private final Context context;
    private final List<GoodsBean> datas;
    private final TextView tvShopcartTotal;
    private final CheckBox checkboxAll;
    private final CheckBox checkboxDeleteAll;

    class MyViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.cb_gov)
        CheckBox cbGov;
        @InjectView(R.id.iv_gov)
        ImageView ivGov;
        @InjectView(R.id.tv_desc_gov)
        TextView tvDescGov;
        @InjectView(R.id.tv_price_gov)
        TextView tvPriceGov;
        @InjectView(R.id.addSubView)
        XaddSubView addSubView;

        public MyViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //2.设置状态取反-设置Bean对象
                    GoodsBean goodsBean = datas.get(getLayoutPosition());
                    goodsBean.setChecked(!goodsBean.isChecked());
                    //4.刷新适配器
                    notifyItemChanged(getLayoutPosition());
                    //3.显示总价格
                    showTotalPrice();
                    checkAll();
                }
            });
        }
    }


    public void checkAll_none(boolean isChecked){
        if(datas!=null && datas.size()>0){
            int number=0;
            for(int i = 0; i <datas.size() ; i++) {
                GoodsBean goodsBean = datas.get(i);
                goodsBean.setChecked(isChecked);
                notifyItemChanged(i);

            }
        }else {
            checkboxAll.setChecked(false);
        }
    }
    public void checkAll() {
        if (datas != null && datas.size() > 0) {
            int number = 0;
            for (int i = 0; i < datas.size(); i++) {
                GoodsBean goodsBean = datas.get(i);
                if (!goodsBean.isChecked()) {
                    checkboxDeleteAll.setChecked(false);
                    checkboxAll.setChecked(false);
                }else {
                    number++;
                }
            }
            if(number==datas.size()){
                checkboxAll.setChecked(true);
                checkboxDeleteAll.setChecked(true);
            }
        }else {
            checkboxAll.setChecked(false);
            checkboxDeleteAll.setChecked(false);
        }
    }

    public ShoppingCartAdapter(Context context, List<GoodsBean> allData, TextView tvShopcartTotal, CheckBox checkboxAll, CheckBox checkboxDeleteAll) {
        this.context = context;
        this.datas = allData;
        this.tvShopcartTotal = tvShopcartTotal;
        this.checkboxAll = checkboxAll;
        this.checkboxDeleteAll = checkboxDeleteAll;

        showTotalPrice();
    }

    public void showTotalPrice() {
        tvShopcartTotal.setText("合计:" + getTotalPrice());
    }

    private double getTotalPrice() {
        double total = 0;
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                if (datas.get(i).isChecked()) {
                    total = total + datas.get(i).getNumber() * Double.parseDouble(datas.get(i).getCover_price());

                }
            }
        }
        return total;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_shopping_cart, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final GoodsBean goodsBean = datas.get(position);
        //绑定数据,默认选中
        holder.cbGov.setChecked(goodsBean.isChecked());
        Glide.with(context).load(Constants.BASE_URL_IMAGE + goodsBean.getFigure()).into(holder.ivGov);
        holder.tvDescGov.setText(goodsBean.getName());//商品名字
        holder.tvPriceGov.setText("￥" + goodsBean.getCover_price());//价格
        //设置value
        holder.addSubView.setValue(goodsBean.getNumber());
        //至少要购买一个
        holder.addSubView.setMinvalue(1);
        //设置商品库存
        holder.addSubView.setMaxvalue(100);
        //设置增加和减少按钮的监听
        holder.addSubView.setOnNumberChangeListener(new XaddSubView.OnNumberChangerListener() {
            @Override
            public void numberChange(int value) {
                //1.设置Bean对象中
                goodsBean.setNumber(value);
                //2.重新计算总价格
                showTotalPrice();
                //3.同步到内存和本地中
                CartStorage.getInstance(context).upData(goodsBean);

            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void deleteData(){
        if(datas!=null && datas.size()>0){
            for(int i = 0; i <datas.size() ; i++) {
                GoodsBean goodsBean = datas.get(i);
                if(goodsBean.isChecked()){
                    datas.remove(goodsBean);
                    CartStorage.getInstance(context).deleteData(goodsBean);
                    notifyItemChanged(i);
                    i--;
                }

            }
        }
    }
}
