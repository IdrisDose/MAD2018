package com.idrisdev.store.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Idris on 6/5/2018.
 */

public class Product implements Parcelable{

    private int mId;
    private String mName;
    private String mDescription;
    private double mPrice;
    private boolean mHidden;
    private boolean mFree;

    /**
     * Basic constructor for the Product Object
     * @param id int
     * @param name String
     * @param description String
     * @param price double
     * @param hidden boolean
     * @param free boolean
     */
    public Product(int id, String name, String description, double price, boolean hidden, boolean free) {
        this.mId = id;
        this.mName = name;
        this.mDescription = description;
        this.mPrice = price;
        this.mHidden = hidden;
        this.mFree = free;
    }

    protected Product(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
        mDescription = in.readString();
        mPrice = in.readDouble();
        mHidden = in.readByte() != 0;
        mFree = in.readByte() != 0;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public double getPrice() {
        return mPrice;
    }
    public String getDisplayPrice(){
        return "$"+this.getPrice();
    }

    public void setPrice(double mPrice) {
        this.mPrice = mPrice;
    }


    public boolean isHidden() {
        return mHidden;
    }

    public void setHidden(boolean mHidden) {
        this.mHidden = mHidden;
    }

    public boolean isFree() {
        return mFree;
    }

    public void setFree(boolean mFree) {
        this.mFree = mFree;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mId);
        parcel.writeString(mName);
        parcel.writeString(mDescription);
        parcel.writeDouble(mPrice);
        parcel.writeByte((byte) (mHidden ? 1 : 0));
        parcel.writeByte((byte) (mFree ? 1 : 0));
    }
}
