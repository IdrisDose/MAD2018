package com.idrisdev.store.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.math.RoundingMode;

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

    public Product(int id, String name, String description){
        this.mId = id;
        this.mName = name;
        this.mDescription = description;
        this.mPrice = this.randomNumGeneratorDouble();

        this.mFree = this.mPrice == 00.00;
        this.mHidden = false;
    }

    private Product(Parcel in) {
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
        return "$"+this.mPrice;
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

    /**
     * Random number (double) generator using Math.random
     * @return random number between 1 and 20
     */
    private double randomNumGeneratorDouble(){

        //Got this neat little random num from here
        //https://dzone.com/articles/random-number-generation-in-java

        double generatedNumber = ((Math.random() * ((20 - 1) + 1)) + 1);
        return round(generatedNumber,2);
    }



    /**
     * A Helper function used to round the prices
     * @param value double
     * @param places int
     * @return double - formatted double
     */
    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
