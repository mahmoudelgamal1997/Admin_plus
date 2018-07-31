package com.example2017.android.admin;

/**
 * Created by M7moud on 12-Feb-18.
 */
public class ShopVisit_listItem {

    String ShopName;
    String visits;

    public ShopVisit_listItem() {
    }

    public ShopVisit_listItem(String shopName, String visits) {
        ShopName = shopName;
        this.visits = visits;
    }

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String shopName) {
        ShopName = shopName;
    }

    public String getVisits() {
        return visits;
    }

    public void setVisits(String visits) {
        this.visits = visits;
    }
}
