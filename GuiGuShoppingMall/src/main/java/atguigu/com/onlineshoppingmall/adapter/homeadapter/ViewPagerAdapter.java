package atguigu.com.onlineshoppingmall.adapter.homeadapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import atguigu.com.onlineshoppingmall.domain.HomePagerBean;
import atguigu.com.onlineshoppingmall.utils.Constants;

/**
 * Created by sun on 2017/6/12.
 */

public class ViewPagerAdapter extends PagerAdapter {
    private final Context context;
    private final List<HomePagerBean.ResultBean.ActInfoBean> datas;

    public ViewPagerAdapter(Context context, List<HomePagerBean.ResultBean.ActInfoBean> act_info) {
        this.context=context;
        this.datas=act_info;
    }

    @Override
    public int getCount() {
        return datas==null ? 0 : datas.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView imageView=new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        HomePagerBean.ResultBean.ActInfoBean actInfoBean = datas.get(position);
        //联网请求
        Glide.with(context).load(Constants.BASE_URL_IMAGE+actInfoBean.getIcon_url()).into(imageView);

        container.addView(imageView);


        //设置点击事件
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(mContext, "position=="+position, Toast.LENGTH_SHORT).show();
                if(clickListener != null){
                    clickListener.onItemClick(position);
                }
            }
        });
        return imageView;

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


    public interface OnItemClickListener{
        public void onItemClick(int position);
    }

    private OnItemClickListener clickListener;

    /**
     * 设置点击item的监听
     * @param l
     */
    public void setOnItemClickListener( OnItemClickListener l){
        this.clickListener = l;
    }
}
