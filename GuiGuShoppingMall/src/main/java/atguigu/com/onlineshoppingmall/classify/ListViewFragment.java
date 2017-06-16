package atguigu.com.onlineshoppingmall.classify;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import atguigu.com.onlineshoppingmall.R;
import atguigu.com.onlineshoppingmall.adapter.classifyadapter.LeftAdapter;
import atguigu.com.onlineshoppingmall.adapter.classifyadapter.RightAdapter;
import atguigu.com.onlineshoppingmall.base.BaseFragment;
import atguigu.com.onlineshoppingmall.domain.ClassifyBean;
import atguigu.com.onlineshoppingmall.utils.Constants;
import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by sun on 2017/6/15.
 */

public class ListViewFragment extends BaseFragment {
    @InjectView(R.id.lv_left)
    ListView lvLeft;
    @InjectView(R.id.rv_right)
    RecyclerView rvRight;

    private String[] titles = new String[]{"小裙子", "上衣", "下装", "外套", "配件", "包包", "装扮", "居家宅品",
            "办公文具", "数码周边", "游戏专区"};

    private String[] urls = new String[]{Constants.SKIRT_URL, Constants.JACKET_URL, Constants.PANTS_URL, Constants.OVERCOAT_URL,
            Constants.ACCESSORY_URL, Constants.BAG_URL, Constants.DRESS_UP_URL, Constants.HOME_PRODUCTS_URL, Constants.STATIONERY_URL,
            Constants.DIGIT_URL, Constants.GAME_URL};

    private LeftAdapter leftAdapter;
    private RightAdapter rightadapter;


    @Override
    public View initview() {
        View view = View.inflate(context, R.layout.fragment_listview, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initdata() {
        super.initdata();
        leftAdapter=new LeftAdapter(context,titles);
        lvLeft.setAdapter(leftAdapter);
        lvLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                leftAdapter.changeSelected(position);
                leftAdapter.notifyDataSetChanged();

                getDataNet(urls[position]);
            }
        });

        getDataNet(urls[0]);

    }

    private void getDataNet(String url) {
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                          processData(response);
                    }
                });
    }

    private void processData(String response) {
        ClassifyBean classifyBean = JSON.parseObject(response, ClassifyBean.class);
        List<ClassifyBean.ResultBean> result = classifyBean.getResult();
        rightadapter=new RightAdapter(context,result);
        rvRight.setAdapter(rightadapter);

        //设置布局管理器

        GridLayoutManager manager = new GridLayoutManager(context,3);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(position ==0){
                    return  3;
                }else{
                    return  1;
                }
            }
        });
        //设置布局管理器
        rvRight.setLayoutManager(manager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
