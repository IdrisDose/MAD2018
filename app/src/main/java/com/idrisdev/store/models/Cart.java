package com.idrisdev.store.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Idris on 6/12/2018.
 */
public class Cart implements Parcelable {
    private ProductList mItems;
    private double mCartPrice;

    public Cart(){
        //Need Empty for initialization
        mItems = new ProductList();
        this.mCartPrice = 0.00;
    }

    public Cart(ProductList items, double cartPrice){
        this.mItems = items;
        this.mCartPrice = cartPrice;
    }

    public void addToCart(Product product){
        this.mItems.addProduct(product);
        this.mCartPrice = this.mCartPrice+product.getPrice();
    }

    public ProductList getCartItems(){
        return this.mItems;
    }

    public double getCartPrice(){
        return this.mCartPrice;
    }


    /*
        All Parcelable Related functions (including constructor) under this line
     */

    protected Cart(Parcel in) {
        mItems = in.readParcelable(ProductList.class.getClassLoader());
        mCartPrice = in.readDouble();
    }

    public static final Creator<Cart> CREATOR = new Creator<Cart>() {
        @Override
        public Cart createFromParcel(Parcel in) {
            return new Cart(in);
        }

        @Override
        public Cart[] newArray(int size) {
            return new Cart[size];
        }
    };

    /**
     * Describe the kinds of special objects contained in this Parcelable
     * instance's marshaled representation. For example, if the object will
     * include a file descriptor in the output of {@link #writeToParcel(Parcel, int)},
     * the return value of this method must include the
     * {@link #CONTENTS_FILE_DESCRIPTOR} bit.
     *
     * @return a bitmask indicating the set of special object types marshaled
     * by this Parcelable object instance.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mItems,flags);
        dest.writeDouble(mCartPrice);
    }
}
