package com.cooiut.shoppinglist;

import android.os.Parcel;
import android.os.Parcelable;

public class StoreItem implements Parcelable {
    private String item, key;
    private double quantity;

    public StoreItem() {
    }

    public StoreItem(String key, String item, double quantity) {
        this.key = key;
        this.item = item;
        this.quantity = quantity;
    }

    protected StoreItem(Parcel in) {
        item = in.readString();
        key = in.readString();
        quantity = in.readDouble();
    }

    public static final Creator<StoreItem> CREATOR = new Creator<StoreItem>() {
        @Override
        public StoreItem createFromParcel(Parcel in) {
            return new StoreItem(in);
        }

        @Override
        public StoreItem[] newArray(int size) {
            return new StoreItem[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(item);
        dest.writeString(key);
        dest.writeDouble(quantity);
    }
}
