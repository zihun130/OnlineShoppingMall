package atguigu.com.onlineshoppingmall.shoppingcart;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import atguigu.com.onlineshoppingmall.MainActivity;
import atguigu.com.onlineshoppingmall.R;
import atguigu.com.onlineshoppingmall.adapter.ShoppingCartAdapter;
import atguigu.com.onlineshoppingmall.base.BaseFragment;
import atguigu.com.onlineshoppingmall.domain.GoodsBean;
import atguigu.com.onlineshoppingmall.utils.CartStorage;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by sun on 2017/6/11.
 */

public class ShoppingCartFragment extends BaseFragment {

    private static final int ACTION_EDITS = 1;
    private static final int ACTION_COMPLETE = 2;
    @InjectView(R.id.tv_shopcart_edit)
    TextView tvShopcartEdit;
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    @InjectView(R.id.checkbox_all)
    CheckBox checkboxAll;
    @InjectView(R.id.tv_shopcart_total)
    TextView tvShopcartTotal;
    @InjectView(R.id.btn_check_out)
    Button btnCheckOut;
    @InjectView(R.id.ll_check_all)
    LinearLayout llCheckAll;
    @InjectView(R.id.checkbox_delete_all)
    CheckBox checkboxDeleteAll;
    @InjectView(R.id.btn_delete)
    Button btnDelete;
    @InjectView(R.id.btn_collection)
    Button btnCollection;
    @InjectView(R.id.ll_delete)
    LinearLayout llDelete;
    @InjectView(R.id.iv_empty)
    ImageView ivEmpty;
    @InjectView(R.id.tv_empty_cart_tobuy)
    TextView tvEmptyCartTobuy;
    @InjectView(R.id.ll_empty_shopcart)
    LinearLayout llEmptyShopcart;
    private ShoppingCartAdapter adapter;
    private List<GoodsBean> datas;

    @Override
    public View initview() {
        View view = View.inflate(context, R.layout.shoppingcart_item, null);
        ButterKnife.inject(this, view);
        tvShopcartEdit.setText("编辑");
        tvShopcartEdit.setTag(ACTION_EDITS);
        tvShopcartEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int action= (int) tvShopcartEdit.getTag();
                if(action==ACTION_EDITS){
                    showDelete();
                }else {
                    hideDelete();
                }
            }
        });



        return view;
    }

    private void hideDelete() {
        tvShopcartEdit.setText("编辑");
        tvShopcartEdit.getTag(ACTION_EDITS);
        //显示结算
        llCheckAll.setVisibility(View.VISIBLE);
        //隐藏删除
        llDelete.setVisibility(View.GONE);

        if(adapter!=null){
            //改为非勾选
            adapter.checkAll_none(true);
            adapter.checkAll();
            adapter.showTotalPrice();
        }

    }

    private void showDelete() {
        tvShopcartEdit.setText("完成");
        tvShopcartEdit.setTag(ACTION_COMPLETE);
        //隐藏结算
        llCheckAll.setVisibility(View.GONE);
        //显示删除
        llDelete.setVisibility(View.VISIBLE);

        if(adapter != null){
            adapter.checkAll_none(false);
            adapter.checkAll();
        }

    }

    @Override
    public void initdata() {
        super.initdata();
    }

    @Override
    public void onResume() {
        super.onResume();
        hideDelete();//设置编辑状态
        datas = CartStorage.getInstance(context).getAllData();
        if(datas != null && datas.size() >0){
            //有数据-设置适配器
            adapter  = new ShoppingCartAdapter(context,datas,checkboxAll,tvShopcartTotal,checkboxDeleteAll);
            recyclerview.setAdapter(adapter);
            //设置布局管理器
            recyclerview.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
            //把显示没有数据的页面-隐藏
            llEmptyShopcart.setVisibility(View.GONE);

            //设置item的点击事件
            MyOnItemClickListener itemClickListener = new MyOnItemClickListener();
            adapter.setOnItemClickListener(itemClickListener);

            //默认校验
            adapter.checkAll();
        }else{
            //没有数据
            //把显示没有数据的页面-显示
            llEmptyShopcart.setVisibility(View.VISIBLE);
        }
    }


    class MyOnItemClickListener implements ShoppingCartAdapter.OnItemClickListener{

        @Override
        public void onItemClick(View view, int position) {
            //1.得到CheckBox的状态

            GoodsBean  goodsBean = datas.get(position);
            boolean isChecked = goodsBean.isChecked();
            //2.设置状态取反-设置Bean对象中
            goodsBean.setChecked(!isChecked);

            //3.显示总价格
            adapter.showTotalPrice();
            //4.刷新适配器
            adapter.notifyItemChanged(position);

            //5.校验全选CheckBox和删除的CheckBox
            adapter.checkAll();

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.tv_shopcart_edit, R.id.checkbox_all, R.id.btn_check_out, R.id.checkbox_delete_all, R.id.btn_delete, R.id.btn_collection, R.id.tv_empty_cart_tobuy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_shopcart_edit:
                break;
            case R.id.checkbox_all:
                //1.得到CheckBox的状态:true和false
                boolean isChecked = checkboxAll.isChecked();

                //2.把列表中所有的数据搜都设置true或者fals
                adapter.checkAll_none(isChecked);
                //4.重新计算总价格
                adapter.showTotalPrice();
                break;
            case R.id.btn_check_out:
                break;
            case R.id.checkbox_delete_all:
                isChecked = checkboxDeleteAll.isChecked();

                //2.把列表中所有的数据搜都设置true或者fals
                adapter.checkAll_none(isChecked);

                //3.刷新适配器

                //4.重新计算总价格
                adapter.showTotalPrice();
                break;
            case R.id.btn_delete:
                adapter.deleteData();
                //显示空数据
                adapter.checkAll();
                //显示空页面
                showEempty();
                break;
            case R.id.btn_collection:
                break;
            case R.id.tv_empty_cart_tobuy:
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("checkedid",R.id.rb_home);//跳转到主页面
                startActivity(intent);
                break;
        }
    }

    private void showEempty() {
        if(datas == null || datas.size()==0){
            llEmptyShopcart.setVisibility(View.VISIBLE);
        }
    }
}
