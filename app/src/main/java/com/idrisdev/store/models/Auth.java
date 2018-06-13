package com.idrisdev.store.models;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;


public class Auth {
    @SerializedName("email")
    private String mUsername;

    @SerializedName("password")
    private String mPassword;

    /**
     * Basic Constructor for Auth
     * @param username
     * @param password
     */
    public Auth(String username, String password){
        mUsername = username;
        mPassword = password;
    }

    /**
     * Gets the Auth's Username
     * @return String the Auth's username
     */
    public String getUsername() {
        return mUsername;
    }

    /**
     * Sets the Auth's username
     * @param username String desired username
     */
    public void setUsername(String username) {
        this.mUsername = username;
    }

    /**
     * Gets the Auth's Password
     * @return String the Auth's Password
     */
    public String getPassword() {
        return mPassword;
    }

    /**
     * Sets the Auth's Password
     * @param password String the desired password
     */
    public void setPassword(String password) {
        this.mPassword = password;
    }

    /**
     * Checks see if the password and username is valid
     * @return if they are valid then return true else false
     */
    public boolean isValid(){
        //Also known as IsEmpty?
        return TextUtils.isEmpty(mUsername) || TextUtils.isEmpty(mPassword);
    }
}
