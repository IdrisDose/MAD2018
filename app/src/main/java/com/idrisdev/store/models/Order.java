package com.idrisdev.store.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Idris on 6/5/2018.
 */
public class Order implements Parcelable{

    private int mId;
    private User mUser;
    private Product mProduct;

    public Order(int id, User user, Product product){
        this.mId = id;
        this.mUser = user;
        this.mProduct = product;
    }

    protected Order(Parcel in) {
        mId = in.readInt();
        mUser = in.readParcelable(User.class.getClassLoader());
        mProduct = in.readParcelable(Product.class.getClassLoader());
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mId);
        parcel.writeParcelable(mUser, i);
        parcel.writeParcelable(mProduct, i);
    }
}
