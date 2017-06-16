package com.atguigu.shoppingmall_1020.domain;

import java.io.Serializable;

/**
 * Created by sun on 2017/6/13.
 */

public class GoodsBean implements Serializable {

    /**
     * cover_price : 199.00
     * figure : /1447232577216.jpg
     * name : 【漫踪】原创 宫崎骏 龙猫 可爱雪地靴动漫保暖鞋周边冬季毛绒鞋子
     * product_id : 2691
     */

    private String cover_price;
    private String figure;
    private String name;
    private String product_id;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    private boolean isChecked = true;
    private int number = 1;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }



    public String getCover_price() {
        return cover_price;
    }

    public void setCover_price(String cover_price) {
        this.cover_price = cover_price;
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
}
