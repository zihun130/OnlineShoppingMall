package atguigu.com.onlineshoppingmall.app;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import atguigu.com.onlineshoppingmall.MainActivity;
import atguigu.com.onlineshoppingmall.R;
import atguigu.com.onlineshoppingmall.adapter.HomeAdapter;
import atguigu.com.onlineshoppingmall.adapter.classifyadapter.ExpandableListViewAdapter;
import atguigu.com.onlineshoppingmall.adapter.classifyadapter.GoodsListAdapter;
import atguigu.com.onlineshoppingmall.domain.GoodsBean;
import atguigu.com.onlineshoppingmall.domain.GoodsListBean;
import atguigu.com.onlineshoppingmall.utils.Constants;
import atguigu.com.onlineshoppingmall.view.SpaceItemDecoration;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;

public class GoodsListActivity extends AppCompatActivity {

    @InjectView(R.id.ib_goods_list_back)
    ImageButton ibGoodsListBack;
    @InjectView(R.id.tv_goods_list_search)
    TextView tvGoodsListSearch;
    @InjectView(R.id.ib_goods_list_home)
    ImageButton ibGoodsListHome;
    @InjectView(R.id.tv_goods_list_sort)
    TextView tvGoodsListSort;
    @InjectView(R.id.tv_goods_list_price)
    TextView tvGoodsListPrice;
    @InjectView(R.id.iv_goods_list_arrow)
    ImageView ivGoodsListArrow;
    @InjectView(R.id.ll_goods_list_price)
    LinearLayout llGoodsListPrice;
    @InjectView(R.id.tv_goods_list_select)
    TextView tvGoodsListSelect;
    @InjectView(R.id.ll_goods_list_head)
    LinearLayout llGoodsListHead;
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    @InjectView(R.id.ib_drawer_layout_back)
    ImageButton ibDrawerLayoutBack;
    @InjectView(R.id.tv_ib_drawer_layout_title)
    TextView tvIbDrawerLayoutTitle;
    @InjectView(R.id.ib_drawer_layout_confirm)
    TextView ibDrawerLayoutConfirm;
    @InjectView(R.id.rb_select_hot)
    RadioButton rbSelectHot;
    @InjectView(R.id.rb_select_new)
    RadioButton rbSelectNew;
    @InjectView(R.id.rg_select)
    RadioGroup rgSelect;
    @InjectView(R.id.tv_drawer_price)
    TextView tvDrawerPrice;
    @InjectView(R.id.rl_selector_price)
    RelativeLayout rlSelectorPrice;
    @InjectView(R.id.tv_drawer_recommend)
    TextView tvDrawerRecommend;
    @InjectView(R.id.rl_select_recommend_theme)
    RelativeLayout rlSelectRecommendTheme;
    @InjectView(R.id.tv_drawer_type)
    TextView tvDrawerType;
    @InjectView(R.id.rl_select_type)
    RelativeLayout rlSelectType;
    @InjectView(R.id.btn_select_all)
    Button btnSelectAll;
    @InjectView(R.id.ll_select_root)
    LinearLayout llSelectRoot;
    @InjectView(R.id.btn_drawer_layout_cancel)
    Button btnDrawerLayoutCancel;
    @InjectView(R.id.btn_drawer_layout_confirm)
    Button btnDrawerLayoutConfirm;
    @InjectView(R.id.iv_price_no_limit)
    ImageView ivPriceNoLimit;
    @InjectView(R.id.rl_price_nolimit)
    RelativeLayout rlPriceNolimit;
    @InjectView(R.id.iv_price_0_15)
    ImageView ivPrice015;
    @InjectView(R.id.rl_price_0_15)
    RelativeLayout rlPrice015;
    @InjectView(R.id.iv_price_15_30)
    ImageView ivPrice1530;
    @InjectView(R.id.rl_price_15_30)
    RelativeLayout rlPrice1530;
    @InjectView(R.id.iv_price_30_50)
    ImageView ivPrice3050;
    @InjectView(R.id.rl_price_30_50)
    RelativeLayout rlPrice3050;
    @InjectView(R.id.iv_price_50_70)
    ImageView ivPrice5070;
    @InjectView(R.id.rl_price_50_70)
    RelativeLayout rlPrice5070;
    @InjectView(R.id.iv_price_70_100)
    ImageView ivPrice70100;
    @InjectView(R.id.rl_price_70_100)
    RelativeLayout rlPrice70100;
    @InjectView(R.id.iv_price_100)
    ImageView ivPrice100;
    @InjectView(R.id.rl_price_100)
    RelativeLayout rlPrice100;
    @InjectView(R.id.et_price_start)
    EditText etPriceStart;
    @InjectView(R.id.v_price_line)
    View vPriceLine;
    @InjectView(R.id.et_price_end)
    EditText etPriceEnd;
    @InjectView(R.id.rl_select_price)
    RelativeLayout rlSelectPrice;
    @InjectView(R.id.ll_price_root)
    LinearLayout llPriceRoot;
    @InjectView(R.id.btn_drawer_theme_cancel)
    Button btnDrawerThemeCancel;
    @InjectView(R.id.btn_drawer_theme_confirm)
    Button btnDrawerThemeConfirm;
    @InjectView(R.id.iv_theme_all)
    ImageView ivThemeAll;
    @InjectView(R.id.rl_theme_all)
    RelativeLayout rlThemeAll;
    @InjectView(R.id.iv_theme_note)
    ImageView ivThemeNote;
    @InjectView(R.id.rl_theme_note)
    RelativeLayout rlThemeNote;
    @InjectView(R.id.iv_theme_funko)
    ImageView ivThemeFunko;
    @InjectView(R.id.rl_theme_funko)
    RelativeLayout rlThemeFunko;
    @InjectView(R.id.iv_theme_gsc)
    ImageView ivThemeGsc;
    @InjectView(R.id.rl_theme_gsc)
    RelativeLayout rlThemeGsc;
    @InjectView(R.id.iv_theme_origin)
    ImageView ivThemeOrigin;
    @InjectView(R.id.rl_theme_origin)
    RelativeLayout rlThemeOrigin;
    @InjectView(R.id.iv_theme_sword)
    ImageView ivThemeSword;
    @InjectView(R.id.rl_theme_sword)
    RelativeLayout rlThemeSword;
    @InjectView(R.id.iv_theme_food)
    ImageView ivThemeFood;
    @InjectView(R.id.rl_theme_food)
    RelativeLayout rlThemeFood;
    @InjectView(R.id.iv_theme_moon)
    ImageView ivThemeMoon;
    @InjectView(R.id.rl_theme_moon)
    RelativeLayout rlThemeMoon;
    @InjectView(R.id.iv_theme_quanzhi)
    ImageView ivThemeQuanzhi;
    @InjectView(R.id.rl_theme_quanzhi)
    RelativeLayout rlThemeQuanzhi;
    @InjectView(R.id.iv_theme_gress)
    ImageView ivThemeGress;
    @InjectView(R.id.rl_theme_gress)
    RelativeLayout rlThemeGress;
    @InjectView(R.id.ll_theme_root)
    LinearLayout llThemeRoot;
    @InjectView(R.id.btn_drawer_type_cancel)
    Button btnDrawerTypeCancel;
    @InjectView(R.id.btn_drawer_type_confirm)
    Button btnDrawerTypeConfirm;
    @InjectView(R.id.expandableListView)
    ExpandableListView expandableListView;
    @InjectView(R.id.ll_type_root)
    LinearLayout llTypeRoot;
    @InjectView(R.id.dl_left)
    DrawerLayout dlLeft;

    private int position;

    /**
     * 请求网络
     */
    private String[] urls = new String[]{
            Constants.CLOSE_STORE,
            Constants.GAME_STORE,
            Constants.COMIC_STORE,
            Constants.COSPLAY_STORE,
            Constants.GUFENG_STORE,
            Constants.STICK_STORE,
            Constants.WENJU_STORE,
            Constants.FOOD_STORE,
            Constants.SHOUSHI_STORE,
    };
    private GoodsListBean.ResultBean result;
    private int click_count;
    private ExpandableListViewAdapter expandableListViewAdapter;
    private GoodsListAdapter listadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);
        ButterKnife.inject(this);
        getData();
        getDataNet(urls[position]);
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
        GoodsListBean goodsListBean = JSON.parseObject(response, GoodsListBean.class);
        result = goodsListBean.getResult();
        if(result!=null){
            listadapter=new GoodsListAdapter(this,result);
            recyclerview.setAdapter(listadapter);
            GridLayoutManager manager = new GridLayoutManager(this, 2);
            recyclerview.setLayoutManager(manager);

            //设置分割线
            recyclerview.addItemDecoration(new SpaceItemDecoration(10));

            //设置item的点击事件
            listadapter.setOnItemClickListener(new GoodsListAdapter.OnItemClickListener() {
                @Override
                public void setOnItemClickListener(GoodsListBean.ResultBean.PageDataBean data) {

                    //商品或者产品，货物
                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setProduct_id(data.getProduct_id());//产品id
                    goodsBean.setCover_price(data.getCover_price());//价格
                    goodsBean.setName(data.getName());//名称
                    goodsBean.setFigure(data.getFigure());//图片地址

                    Intent intent = new Intent(GoodsListActivity.this, GoodsInfoActivity.class);
                    intent.putExtra(HomeAdapter.GOODS_BEAN, goodsBean);
                    startActivity(intent);

                }
            });

            showSelectorLayout();


        }

    }


    private void getData() {
        position=getIntent().getIntExtra("position",0);
    }

    @OnClick({R.id.ib_goods_list_back, R.id.tv_goods_list_search, R.id.ib_goods_list_home, R.id.tv_goods_list_sort, R.id.tv_goods_list_price, R.id.tv_goods_list_select, R.id.ib_drawer_layout_back, R.id.ib_drawer_layout_confirm, R.id.rl_selector_price, R.id.rl_select_recommend_theme, R.id.rl_select_type, R.id.btn_drawer_layout_cancel, R.id.btn_drawer_layout_confirm, R.id.btn_drawer_theme_cancel, R.id.btn_drawer_theme_confirm, R.id.btn_drawer_type_cancel, R.id.btn_drawer_type_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_goods_list_back:
                finish();
                break;
            case R.id.tv_goods_list_search:
                break;
            case R.id.ib_goods_list_home:
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("checkedid", R.id.rb_home);//跳转到主页面
                startActivity(intent);
                finish();
                break;
            case R.id.tv_goods_list_sort:
                click_count = 0;
                //设置价格箭头向下
                ivGoodsListArrow.setBackgroundResource(R.drawable.new_price_sort_normal);
//                Toast.makeText(this, "综合排序", Toast.LENGTH_SHORT).show();
                //把综合设置高亮
                tvGoodsListSort.setTextColor(Color.parseColor("#ed4141"));


                //其他设置默认-价格和筛选
                tvGoodsListSelect.setTextColor(Color.parseColor("#333538"));
                //价格文字变成默认黑色
                tvGoodsListPrice.setTextColor(Color.parseColor("#333538"));

                break;
            case R.id.tv_goods_list_price:
                click_count++;

                if (click_count % 2 == 1) {
                    // 箭头向下红
                    ivGoodsListArrow.setBackgroundResource(R.drawable.new_price_sort_desc);
                } else {
                    // 箭头向上红
                    ivGoodsListArrow.setBackgroundResource(R.drawable.new_price_sort_asc);
                }

//                Toast.makeText(this, "价格排序", Toast.LENGTH_SHORT).show();
                //价格设置高亮
                tvGoodsListPrice.setTextColor(Color.parseColor("#ed4141"));

                //其他设置默认-综合和筛选
                tvGoodsListSort.setTextColor(Color.parseColor("#333538"));
                tvGoodsListSelect.setTextColor(Color.parseColor("#333538"));
                break;
            case R.id.tv_goods_list_select:
                click_count = 0;
                //设置价格箭头向下
                ivGoodsListArrow.setBackgroundResource(R.drawable.new_price_sort_normal);
//                Toast.makeText(this, "筛选排序", Toast.LENGTH_SHORT).show();

                //筛选设置高亮
                tvGoodsListSelect.setTextColor(Color.parseColor("#ed4141"));

                //其他设置默认-综合和价格
                tvGoodsListPrice.setTextColor(Color.parseColor("#333538"));
                tvGoodsListSort.setTextColor(Color.parseColor("#333538"));


                //显示筛选菜单
                dlLeft.openDrawer(Gravity.RIGHT);
                showSelectorLayout();//显示筛选-隐藏其他
                break;
            case R.id.ib_drawer_layout_back:
                //关闭筛选菜单
                dlLeft.closeDrawers();
                break;
            case R.id.ib_drawer_layout_confirm:
                break;
            case R.id.rl_selector_price:
                llPriceRoot.setVisibility(View.VISIBLE);
                showPriceLayout();
                break;
            case R.id.rl_select_recommend_theme:
                llThemeRoot.setVisibility(View.VISIBLE);
                showThemeLayout();
                break;
            case R.id.rl_select_type:
                llTypeRoot.setVisibility(View.VISIBLE);
                showTypeLayout();
                break;
            case R.id.btn_drawer_layout_cancel:
                showSelectorLayout();
                llSelectRoot.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_drawer_layout_confirm:
                break;
            case R.id.btn_drawer_theme_cancel:
                showSelectorLayout();
                llSelectRoot.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_drawer_theme_confirm:
                break;
            case R.id.btn_drawer_type_cancel:
                showSelectorLayout();
                llSelectRoot.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_drawer_type_confirm:
                break;
        }
    }
    //筛选页面
    private void showSelectorLayout() {
        llPriceRoot.setVisibility(View.GONE);
        llThemeRoot.setVisibility(View.GONE);
        llTypeRoot.setVisibility(View.GONE);
    }
    //价格页面
    private void showPriceLayout() {
        llSelectRoot.setVisibility(View.GONE);
        llThemeRoot.setVisibility(View.GONE);
        llTypeRoot.setVisibility(View.GONE);
    }

    //主题页面
    private void showThemeLayout() {
        llSelectRoot.setVisibility(View.GONE);
        llPriceRoot.setVisibility(View.GONE);
        llTypeRoot.setVisibility(View.GONE);
    }

    //类别页面
    private void showTypeLayout() {
        llSelectRoot.setVisibility(View.GONE);
        llPriceRoot.setVisibility(View.GONE);
        llThemeRoot.setVisibility(View.GONE);

        //初始化ExpandableListView
        initExpandableListView();
    }

    private ArrayList<String> group;
    private ArrayList<List<String>> child;
    private void initExpandableListView() {


        group = new ArrayList<>();
        child = new ArrayList<>();
        //去掉默认箭头
//        expandableListView.setGroupIndicator(null);

        //添加数据
        addInfo("全部", new String[]{});
        addInfo("上衣", new String[]{"古风", "和风", "lolita", "日常"});
        addInfo("下装", new String[]{"日常", "泳衣", "汉风", "lolita", "创意T恤"});
        addInfo("外套", new String[]{"汉风", "古风", "lolita", "胖次", "南瓜裤", "日常"});



        //设置适配器
        expandableListViewAdapter = new ExpandableListViewAdapter(this, group, child);
        expandableListView.setAdapter(expandableListViewAdapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                expandableListViewAdapter.isChildSelectable(groupPosition,childPosition);
                expandableListViewAdapter.notifyDataSetChanged();
                return true;
            }
        });

        // 这里是控制如果列表没有孩子菜单不展开的效果
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent,
                                        View v, int groupPosition, long id) {
                if (child.get(groupPosition).isEmpty()) {// isEmpty没有
                    return true;
                } else {
                    return false;
                }
            }
        });




    }

    /**
     * 添加数据信息
     *
     * @param g
     * @param c
     */
    private void addInfo(String g, String[] c) {
        group.add(g);
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < c.length; i++) {
            list.add(c[i]);
        }
        child.add(list);
    }

}
