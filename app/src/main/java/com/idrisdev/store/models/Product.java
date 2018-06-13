package com.idrisdev.store.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product implements Parcelable {

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
    @SerializedName("id")
    private int mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("price")
    private double mPrice;
    @SerializedName("hidden")
    private boolean mHidden;
    @SerializedName("free")
    private boolean mFree;
    @SerializedName("new")
    private boolean mNew;

    /**
     * Basic constructor for the Product Object
     *
     * @param id          int
     * @param name        String
     * @param description String
     * @param price       double
     * @param hidden      boolean
     * @param free        boolean
     */
    public Product(int id, String name, String description, double price, boolean hidden, boolean free, boolean isNew) {
        this.mId = id;
        this.mName = name;
        this.mDescription = description;
        this.mPrice = price;
        this.mHidden = hidden;
        this.mFree = free;
        this.mNew = isNew;
    }

    /**
     * Basic constructor for the Product Object
     *
     * @param id          int
     * @param name        String
     * @param description String
     */
    public Product(int id, String name, String description) {
        this.mId = id;
        this.mName = name;
        this.mDescription = description;
        this.mPrice = this.randomNumGeneratorDouble();
        this.mFree = this.mPrice == 00.00;
        this.mHidden = false;
        this.mNew = id % 2 > 0;
    }

    private Product(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
        mDescription = in.readString();
        mPrice = in.readDouble();
        mHidden = in.readByte() != 0;
        mFree = in.readByte() != 0;
    }

    /**
     * A Helper function used to round the prices
     *
     * @param value  double
     * @param places int
     * @return double - formatted double
     */
    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * Used to get the ID of the Product
     *
     * @return int ID
     */
    public int getId() {
        return mId;
    }

    /**
     * Used to set the ID of the Product
     *
     * @param mId int
     */

    public void setId(int mId) {
        this.mId = mId;
    }

    /**
     * Used to get the Name of the product
     *
     * @return String
     */
    public String getName() {
        return mName;
    }

    /**
     * Used to set the Name of the product
     *
     * @param mName String
     */
    public void setName(String mName) {
        this.mName = mName;
    }

    /**
     * Used to get the Product's Description
     *
     * @return String
     */
    public String getDescription() {
        return mDescription;
    }

    /**
     * Used to set the Product's Description
     *
     * @param mDescription String
     */
    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    /**
     * Used to get the Product's Price
     *
     * @return double
     */
    public double getPrice() {
        return mPrice;
    }

    /**
     * Used to set the Product's Price
     *
     * @param mPrice double
     */
    public void setPrice(double mPrice) {
        this.mPrice = mPrice;
    }

    /**
     * Used to set the Product's Display (pretty/formatted) Price
     *
     * @return String
     */
    public String getDisplayPrice() {
        return "$" + this.mPrice;
    }

    /**
     * Used to check if the product is hidden
     *
     * @return boolean
     */
    public boolean isHidden() {
        return mHidden;
    }

    /**
     * Used to set if the product is hiddden
     *
     * @param mHidden boolean
     */
    public void setHidden(boolean mHidden) {
        this.mHidden = mHidden;
    }

    /**
     * Used to check if the product is Free
     *
     * @return boolean
     */
    public boolean isFree() {
        return mFree;
    }

    /**
     * Used to set if the product is free
     *
     * @param mFree boolean
     */
    public void setFree(boolean mFree) {
        this.mFree = mFree;
    }

    /**
     * Used to check if the product is New
     *
     * @return boolean
     */
    public boolean isNew() {
        return mNew;
    }


     /*
        All Parcelable Related functions (including constructor) under this line
     */

    /**
     * Used to set if the product is new
     *
     * @param isNew boolean
     */
    public void setNew(boolean isNew) {
        this.mNew = isNew;
    }

    /**
     * Random number (double) generator using Math.random
     *
     * @return random number between 1 and 20
     */
    private double randomNumGeneratorDouble() {

        //Got this neat little random num from here
        //https://dzone.com/articles/random-number-generation-in-java

        double generatedNumber = ((Math.random() * ((20 - 1) + 1)) + 1);
        return round(generatedNumber, 2);
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
