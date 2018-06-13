package com.idrisdev.store.models;

import android.os.Parcel;
import android.os.Parcelable;


public class Cart implements Parcelable {
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
    private ProductList mItems;
    private double mCartPrice;

    /**
     * Basic constructor for the user's cart
     * empty for initialization
     */
    public Cart() {
        //Need Empty for initialization
        mItems = new ProductList();
        this.mCartPrice = 0.00;
    }

    /**
     * Basic Constructor for the user's cart can be used to hold an already existing cart
     *
     * @param items     ProductList items to be contained within the cart
     * @param cartPrice double Total price of the cart
     */
    public Cart(ProductList items, double cartPrice) {
        this.mItems = items;
        this.mCartPrice = cartPrice;
    }

    protected Cart(Parcel in) {
        mItems = in.readParcelable(ProductList.class.getClassLoader());
        mCartPrice = in.readDouble();
    }

    /**
     * Adds a product to the cart
     *
     * @param product Product desired product
     */
    public void addToCart(Product product) {
        this.mItems.addProduct(product);
        this.mCartPrice = this.mCartPrice + product.getPrice();
    }


    /*
        All Parcelable Related functions (including constructor) under this line
     */

    /**
     * Gets the Cart's Items
     *
     * @return ProductList items
     */
    public ProductList getCartItems() {
        return this.mItems;
    }

    /**
     * Gets the Cart's Total Price
     *
     * @return double CartPrice
     */
    public double getCartPrice() {
        return this.mCartPrice;
    }

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
        dest.writeParcelable(mItems, flags);
        dest.writeDouble(mCartPrice);
    }
}
