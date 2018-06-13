package com.idrisdev.store.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class ProductList implements Parcelable {
    private ArrayList<Product> mProducts;

    /**
     * Basic Constructor for the ProductList class
     */
    public ProductList(){
        this.mProducts = new ArrayList<>();
    }

    /**
     * Basic Constructor for the ProductList Class
     * @param products ArrayList<Product>
     */
    public ProductList(ArrayList<Product> products){
        this.mProducts = products;
    }

    /**
     * Used to get the free product in the ProductList
     * @return ArrayList<Product>
     */
    public ArrayList<Product> getFreeProducts(){
        ArrayList<Product> freeProducts = new ArrayList<>();

        //Foreach product in this ProductList check if free
        // if product is free add to freeProducts arraylist
        // if not next item
        for (Product product : this.mProducts){
            if(product.isFree()){
                freeProducts.add(product);
            }
        }

        return freeProducts;
    }

    /**
     * Used to get the new products in the ProductList
     * @return ArrayList<Product>
     */
    public ArrayList<Product> getNewProducts(){
        ArrayList<Product> newProducts = new ArrayList<>();

        //Foreach product in this ProductList check if free
        // if product is free add to freeProducts arraylist
        // if not next item
        for (Product product : this.mProducts){
            if(product.isNew()){
                newProducts.add(product);
            }
        }

        return newProducts;
    }

    /**
     * Used to get all product in the current ProductList
     * @return ArrayList<Product>
     */
    public ArrayList<Product> getAllProducts(){
        return this.mProducts;
    }

    /**
     * Used to add a product to the ProductList
     * @param product Product
     */
    public void addProduct(Product product){
        this.mProducts.add(product);
    }

    /**
     * Used to get the ProductList size
     * @return int
     */
    public int getSize(){
        return mProducts.size();
    }


    /*
        All Parcelable Related functions (including constructor) under this line
     */

    protected ProductList(Parcel in) {
        mProducts = in.createTypedArrayList(Product.CREATOR);
    }

    public static final Creator<ProductList> CREATOR = new Creator<ProductList>() {
        @Override
        public ProductList createFromParcel(Parcel in) {
            return new ProductList(in);
        }

        @Override
        public ProductList[] newArray(int size) {
            return new ProductList[size];
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
        dest.writeTypedList(mProducts);
    }
}
