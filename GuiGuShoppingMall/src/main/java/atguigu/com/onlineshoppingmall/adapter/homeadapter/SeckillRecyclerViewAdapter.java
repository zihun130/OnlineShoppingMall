package atguigu.com.onlineshoppingmall.adapter.homeadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import atguigu.com.onlineshoppingmall.R;
import atguigu.com.onlineshoppingmall.domain.HomePagerBean;
import atguigu.com.onlineshoppingmall.utils.Constants;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by sun on 2017/6/12.
 */

public class SeckillRecyclerViewAdapter extends RecyclerView.Adapter<SeckillRecyclerViewAdapter.MyViewHolder> {
    private final Context context;
    private final List<HomePagerBean.ResultBean.SeckillInfoBean.ListBean> datas;

    private LayoutInflater inflater;


    public SeckillRecyclerViewAdapter(Context context, HomePagerBean.ResultBean.SeckillInfoBean seckill_info) {
        this.context = context;
        this.datas = seckill_info.getList();
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_seckill, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        HomePagerBean.ResultBean.SeckillInfoBean.ListBean listBean = datas.get(position);
        //2.绑定数据
        Glide.with(context)
                .load(Constants.BASE_URL_IMAGE + listBean.getFigure())
                .into(holder.ivFigure);
        //设置价格
        holder.tvCoverPrice.setText("￥" + listBean.getCover_price());
        holder.tvOriginPrice.setText("￥" + listBean.getOrigin_price());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_figure)
        ImageView ivFigure;
        @InjectView(R.id.tv_cover_price)
        TextView tvCoverPrice;
        @InjectView(R.id.tv_origin_price)
        TextView tvOriginPrice;
        @InjectView(R.id.ll_root)
        LinearLayout llRoot;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.OnItemClick(getLayoutPosition());
                    }

                }
            });
        }
    }


    public interface OnItemClickListener {
        public void OnItemClick(int position);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
