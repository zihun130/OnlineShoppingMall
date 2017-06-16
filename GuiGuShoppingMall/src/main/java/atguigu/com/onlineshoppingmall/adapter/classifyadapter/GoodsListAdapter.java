package atguigu.com.onlineshoppingmall.adapter.classifyadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import atguigu.com.onlineshoppingmall.R;
import atguigu.com.onlineshoppingmall.domain.GoodsListBean;
import atguigu.com.onlineshoppingmall.utils.Constants;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by sun on 2017/6/15.
 */

public class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.MyViewHolder> {
    private final Context context;
    private final List<GoodsListBean.ResultBean.PageDataBean> datas;

    public GoodsListAdapter(Context context, GoodsListBean.ResultBean result) {
        this.context = context;
        this.datas = result.getPage_data();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_goods_list, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        GoodsListBean.ResultBean.PageDataBean pageDataBean = datas.get(position);
        Glide.with(context)
                .load(Constants.BASE_URL_IMAGE+pageDataBean.getFigure())
                .into(holder.ivHot);
        holder.tvName.setText(pageDataBean.getName());
        holder.tvPrice.setText("￥"+pageDataBean.getCover_price());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        @InjectView(R.id.iv_hot)
        ImageView ivHot;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.tv_price)
        TextView tvPrice;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener != null){
                        onItemClickListener.setOnItemClickListener(datas.get(getLayoutPosition()));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        public void setOnItemClickListener(GoodsListBean.ResultBean.PageDataBean data);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
