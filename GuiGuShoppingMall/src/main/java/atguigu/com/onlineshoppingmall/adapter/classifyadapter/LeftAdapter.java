package atguigu.com.onlineshoppingmall.adapter.classifyadapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import atguigu.com.onlineshoppingmall.R;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by sun on 2017/6/15.
 */

public class LeftAdapter extends BaseAdapter {
    private final Context context;
    private final String[] titles;
    private int prePosition;

    public LeftAdapter(Context context, String[] titles) {
        this.context = context;
        this.titles = titles;
    }

    @Override
    public int getCount() {
        return titles.length;
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
            convertView = View.inflate(context, R.layout.item_left_listview, null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }

        viewHolder.tvTitle.setText(titles[position]);


        if(prePosition ==position){
            //刚才被点击的item
            convertView.setBackgroundResource(R.drawable.type_item_background_selector);
            //选中项背景
            viewHolder.tvTitle.setTextColor(Color.parseColor("#fd3f3f"));
        }else {
            //设置默认
            convertView.setBackgroundResource(R.drawable.bg2);  //其他项背景
            viewHolder.tvTitle.setTextColor(Color.parseColor("#323437"));
        }

        return convertView;
    }

    public void changeSelected(int position) {
        this.prePosition = position;
    }

    static class ViewHolder {
        @InjectView(R.id.tv_title)
        TextView tvTitle;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
