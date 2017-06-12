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

public class ChannelAdapter extends BaseAdapter {
    private final Context context;
    private final List<HomePagerBean.ResultBean.ChannelInfoBean> datas;

    public ChannelAdapter(Context context, List<HomePagerBean.ResultBean.ChannelInfoBean> channel_info) {
        this.context = context;
        this.datas = channel_info;
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
            convertView = View.inflate(context, R.layout.item_channel, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //根据位置得到对应的数据
        HomePagerBean.ResultBean.ChannelInfoBean infoBean = datas.get(position);

        viewHolder.tvChannel.setText(infoBean.getChannel_name());

        Glide.with(context)
                .load(Constants.BASE_URL_IMAGE+infoBean.getImage())
                .into(viewHolder.ivChannel);


        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_channel)
        ImageView ivChannel;
        @InjectView(R.id.tv_channel)
        TextView tvChannel;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
