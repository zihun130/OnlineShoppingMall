package com.atguigu.shoppingmall_1020.shoppingcart.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.shoppingmall_1020.R;
import com.atguigu.shoppingmall_1020.adapter.ShoppingCartAdapter;
import com.atguigu.shoppingmall_1020.base.BaseFragment;
import com.atguigu.shoppingmall_1020.domain.GoodsBean;
import com.atguigu.shoppingmall_1020.utils.CartStorage;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 作者：尚硅谷-杨光福 on 2017/2/22 11:36
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：购物车Fragment
 */
public class ShoppingCartFragment extends BaseFragment {

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
    private List<GoodsBean> allData;
    private ShoppingCartAdapter adapter;

    //编辑状态
    private static final int ACTION_EDIT = 1;
    //完成状态
    private static final int ACTION_COMPLETE = 2;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.shoppingcart_item, null);
        ButterKnife.inject(this, view);
        tvShopcartEdit.setTag(ACTION_EDIT);
        return view;
    }

    /**
     * 1.把数据绑定到控件上的时候，重新该方法
     * 2.联网请求，把得到的数据绑定到视图上
     */
    @Override
    public void initData() {
        super.initData();

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
                int tag= (int) tvShopcartEdit.getTag();
               if(tag==ACTION_EDIT){
                   showDelete();
               }else {
                   hideDelete();
               }

                break;
            case R.id.checkbox_all:
                boolean checked=checkboxAll.isChecked();
                adapter.checkAll_none(checked);
                adapter.showTotalPrice();
                break;
            case R.id.btn_check_out:
                break;
            case R.id.checkbox_delete_all:
                boolean checkAll=checkboxDeleteAll.isChecked();
                adapter.checkAll_none(checkAll);
                break;
            case R.id.btn_delete:
                adapter.deleteData();
                adapter.checkAll();
                showEmpty();
                break;
            case R.id.btn_collection:
                break;
            case R.id.tv_empty_cart_tobuy:
                break;
        }
    }

    private void hideDelete() {
        tvShopcartEdit.setText("编辑");
        tvShopcartEdit.setTag(ACTION_EDIT);
        llCheckAll.setVisibility(View.VISIBLE);
        llDelete.setVisibility(View.GONE);
        adapter.checkAll_none(false);
        adapter.checkAll();
        adapter.showTotalPrice();
    }

    private void showDelete() {
        tvShopcartEdit.setText("完成");
        tvShopcartEdit.setTag(ACTION_COMPLETE);
        llCheckAll.setVisibility(View.GONE);
        llDelete.setVisibility(View.VISIBLE);
        adapter.checkAll_none(false);
        adapter.checkAll();
        adapter.showTotalPrice();
    }

    private void showEmpty() {
        if(allData==null && allData.size()==0){
            llEmptyShopcart.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        allData = CartStorage.getInstance(mContext).getAllData();
        if(allData!=null && allData.size()>0){
            llEmptyShopcart.setVisibility(View.GONE);
            adapter=new ShoppingCartAdapter(mContext,allData,tvShopcartTotal,checkboxAll,checkboxDeleteAll);
            recyclerview.setAdapter(adapter);
            recyclerview.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        }else {
            llEmptyShopcart.setVisibility(View.VISIBLE);
        }
    }
}
