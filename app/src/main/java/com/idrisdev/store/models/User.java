package com.idrisdev.store.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Idris on 6/5/2018.
 */

public class User implements Parcelable{
    private int mId;
    private String mName;
    private String mEmail;
    private boolean mActive;
    private String mPassword;
    private ArrayList<Product> mOrders;
    private Cart mCart;


    /**
     * Basic Constructor for the User Object/Model
     * @param id int - ID of the User
     * @param name String - Name of the User
     * @param email String - Email of the User
     */
    public User(int id, String name, String email) {
        this.mId = id;
        this.mName = name;
        this.mEmail = email;
    }

    private User(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
        mEmail = in.readString();
        mActive = in.readByte() != 0;
        mOrders = in.createTypedArrayList(Product.CREATOR);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    /**
     * Gets the User's ID
     * @return String - This User's ID
     */
    public int getId() {
        return mId;
    }

    /**
     * Gets the User's Name
     * @return String - This User's Name
     */
    public String getName() {
        return mName;
    }

    /**
     * Gets this User's Email
     * @return String - This User's Email
     */
    public String getEmail() {
        return mEmail;
    }

    /**
     * Used to check if the User is Active
     * @return boolen This User's active status
     */
    public boolean isActive() {
        return mActive;
    }

    /**
     * Sets the User's ID
     * @param id int
     */
    public void setId(int id) {
        this.mId = id;
    }

    /**
     * Sets the User's Name
     * @param name String
     */
    public void setName(String name) {
        this.mName = name;
    }

    /**
     * Sets the User's Email
     * @param email String
     */
    public void setEmail(String email) {
        this.mEmail = email;
    }

    /**
     * Sets the User's Active Status
     * @param active boolean
     */
    public void setActive(boolean active) {
        this.mActive = active;
    }

    /**
     * Sets the User's orders
     * @param products List<Product>
     */
    public void setOrders(ArrayList<Product> products){
        this.mOrders = products;
    }

    /**
     * Add a product to the User's orders
     * @param product Product
     */
    public void addOrder(Product product){
        this.mOrders.add(product);
    }

    /**
     * Gets the user's owned products
     * @return mOrders OwnedProducts
     */
    public ArrayList<Product> getOrders() {
        return mOrders;
    }

    public Cart getCart(){
        return this.mCart;
    }

    public void setCart(Cart cart) {
        this.mCart = cart;
    }

    /**
     * Gets the size of this users order list
     * @return the size of mOrders.
     */
    public int getOrderCount(){
        return this.mOrders.size();
    }
    //TODO: Remove this later
    public void setPassword(String password){
        this.mPassword = password;
    }

    //TODO: Remove;
    public String getPassword(){ return this.mPassword; }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mId);
        parcel.writeString(mName);
        parcel.writeString(mEmail);
        parcel.writeByte((byte) (mActive ? 1 : 0));
        parcel.writeTypedList(mOrders);
    }



}
