package atguigu.com.onlineshoppingmall.adapter.homeadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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

public class RecommendAdapter extends BaseAdapter {
    private final Context context;
    private final List<HomePagerBean.ResultBean.RecommendInfoBean> datas;

    public RecommendAdapter(Context context, List<HomePagerBean.ResultBean.RecommendInfoBean> recommend_info) {
        this.context = context;
        this.datas = recommend_info;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_recommend, null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }

        HomePagerBean.ResultBean.RecommendInfoBean infoBean = datas.get(position);
        Glide.with(context).load(Constants.BASE_URL_IMAGE+infoBean.getFigure()).into(viewHolder.ivRecommend);
        viewHolder.tvName.setText(infoBean.getName());
        viewHolder.tvPrice.setText("￥"+infoBean.getCover_price());

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_recommend)
        ImageView ivRecommend;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.tv_price)
        TextView tvPrice;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
