package atguigu.com.onlineshoppingmall.adapter.classifyadapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import atguigu.com.onlineshoppingmall.R;
import atguigu.com.onlineshoppingmall.domain.LableBean;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by sun on 2017/6/15.
 */

public class LableAdapter extends BaseAdapter {
    private final Context context;
    private final List<LableBean.ResultBean> result;

    private int[] colors = {
            Color.parseColor("#f0a420"), Color.parseColor("#4ba5e2"), Color.parseColor("#f0839a"),
            Color.parseColor("#4ba5e2"), Color.parseColor("#f0839a"), Color.parseColor("#f0a420"),
            Color.parseColor("#f0839a"), Color.parseColor("#f0a420"), Color.parseColor("#4ba5e2")
    };

    public LableAdapter(Context context, List<LableBean.ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @Override
    public int getCount() {
        return result == null ? 0 : result.size();
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
            convertView = View.inflate(context, R.layout.item_lable, null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }

        LableBean.ResultBean resultBean = result.get(position);
        viewHolder.tvTag.setText(resultBean.getName());
        viewHolder.tvTag.setTextColor(colors[position % colors.length]);
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.tv_tag)
        TextView tvTag;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
