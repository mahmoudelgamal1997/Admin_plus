package com.example2017.android.admin;

/**
 * Created by M7moud on 22-Jan-18.
 */
public class item_in_deatails_listview {

    String code, shop, date;

    public item_in_deatails_listview(String code, String shop, String date) {
        this.code = code;
        this.shop = shop;
        this.date = date;
    }

    public item_in_deatails_listview() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
