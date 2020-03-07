package com.cooiut.shoppinglist;

public class StoreItem {
    private String item, key;
    private double quantity;

    public StoreItem() {
    }


    public StoreItem(String key, String item, double quantity) {
        this.key = key;
        this.item = item;
        this.quantity = quantity;
    }

    public String getItem() {
        return item;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getKey() {
        return key;
    }

    public void setQuantity(double num) {
        quantity = num;
    }
}
