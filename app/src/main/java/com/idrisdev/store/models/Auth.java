package com.idrisdev.store.models;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.idrisdev.store.LandingActivity;
import com.idrisdev.store.SettingsActivity;

import java.util.ArrayList;

/**
 * Created by Idris on 6/10/2018.
 */
public class Auth {

    private String mEmail;
    private String mPassword;
    private User mUser;

    public Auth(String email, String password){
        this.mEmail = email;
        this.mPassword = password;
    }

    public void setUser(User user){
        this.mUser = user;
    }

    /**
     * Attempts to login using the credentials provided
     * @param password String - the entered password for validation.
     * @return true if user is validaded/authenticated.
     */
    public boolean attemptLogin(String password){
        //TODO: Update this method to actually login so we can properly authenticate.
        boolean isValid = this.mPassword.equals(password);
        if(isValid){
            this.mUser.setOrders(new ArrayList<>());
            this.mUser.setActive(isValid);
        }
        return isValid;
    }


    public boolean isEmailValid() {
        return this.mEmail.equals(mUser.getEmail());
    }
}
