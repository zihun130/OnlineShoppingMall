package com.atguigu.shoppingmall_1020.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;

import com.atguigu.shoppingmall_1020.domain.GoodsBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sun on 2017/6/14.
 */

public class CartStorage {

    public static final String JSON_CART = "json_cart";
    private static CartStorage intance;
    private static Context mContext;
    private SparseArray<GoodsBean> sparseArray;

    public CartStorage(Context mContext) {
        this.mContext=mContext;
        sparseArray=new SparseArray<>();
        listTosparseArray();

    }
    //本地加载到内存
    private void listTosparseArray() {
        List<GoodsBean> list=getAllData();
        for(int i = 0; i <list.size() ; i++) {
            GoodsBean goodsBean = list.get(i);
            sparseArray.put(Integer.parseInt(goodsBean.getProduct_id()),goodsBean);

        }
    }
    //得到全部内存数据
    public List<GoodsBean> getAllData() {
        List<GoodsBean> list=new ArrayList<>();
        String string = CacheUtils.getString(mContext, JSON_CART);
        if(!TextUtils.isEmpty(string)){
            list= new Gson().fromJson(string, new TypeToken<List<GoodsBean>>() {
            }.getType());
        }
        return list;
    }

    //单例 线程锁,每次只允许单线程通行
    public static CartStorage getInstance(Context context){
        if(intance==null){
            mContext=context;
            //对重要部分进行同步,提高效率
            synchronized (CartStorage.class){
                //二次检查,保证线程的安全性
                if(intance==null){
                    intance=new CartStorage(mContext);
                }
            }

        }
        return intance;
    }


    //添加数据
    public void addData(GoodsBean goodsBean){
        //从内存得到数据
        GoodsBean temp = sparseArray.get(Integer.parseInt(goodsBean.getProduct_id()));
        if(temp!=null){
            temp.setNumber(temp.getNumber());
        }else {
            //列表中不存在没有数据
            temp=goodsBean;
            temp.setNumber(1);
        }
        //添加入内存
        sparseArray.put(Integer.parseInt(temp.getProduct_id()),temp);
        //保存本地
        commit();
    }

    //删除数据
    public void deleteData(GoodsBean goodsBean){
        sparseArray.delete(Integer.parseInt(goodsBean.getProduct_id()));
        commit();
    }
    //更新数据
    public void upData(GoodsBean goodsBean){
        sparseArray.put(Integer.parseInt(goodsBean.getProduct_id()),goodsBean);
        commit();
    }
    //提交数据的方法
    private void commit () {
        List<GoodsBean> list=sparseArrayTolist();
        String json = new Gson().toJson(list);
        CacheUtils.putString(mContext,JSON_CART,json);
    }
    //从内存提取数据到本地
    private List<GoodsBean> sparseArrayTolist() {
        List<GoodsBean> list=getAllData();
        for(int i = 0; i <sparseArray.size() ; i++) {
            GoodsBean goodsBean = sparseArray.valueAt(i);
            list.add(goodsBean);
        }
        return list;
    }
    //查找数据,查看是否存在该物品
    public GoodsBean findData(String id){

        GoodsBean goodsBean = sparseArray.get(Integer.parseInt(id));
        return goodsBean;
    }
}
