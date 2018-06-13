package com.idrisdev.store;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.idrisdev.store.models.Auth;
import com.idrisdev.store.models.Product;
import com.idrisdev.store.models.User;
import com.idrisdev.store.utils.HttpHelper;
import com.idrisdev.store.utils.NetworkHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class LandingActivity extends AppCompatActivity {
    private static final String TAG = "StoreApp";

    private ScrollView mLoginForm;
    private ScrollView mRegisterForm;
    private RelativeLayout mLandingContainer;
    private RelativeLayout mProgressbarContainer;
    private EditText mEmailEt;
    private EditText mPasswordEt;

    //TODO: Remove this later:
    private Auth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        //Initialize class scope views and variable
        mLoginForm = findViewById(R.id.login_form);
        mRegisterForm = findViewById(R.id.register_form);
        mLandingContainer = findViewById(R.id.landing_container_rl);
        mProgressbarContainer = findViewById(R.id.progress_container_rl);
        TextView noNetwork = findViewById(R.id.no_network_landing);

        //Declare and Initialize buttons
        Button showLoginFormBtn = findViewById(R.id.show_login_form_btn);
        Button showRegisterFormBtn = findViewById(R.id.show_register_form_btn);
        Button loginActionBtn = findViewById(R.id.login_action_btn);
        Button loginBackBtn = findViewById(R.id.login_back_btn);
        Button registerActionBtn = findViewById(R.id.register_action_btn);
        Button registerBackBtn = findViewById(R.id.register_back_btn);
        boolean hasNetwork = NetworkHelper.hasNetworkAccess(getApplicationContext());

        //Disabled buttons if network is disabled/no access
        showLoginFormBtn.setEnabled(hasNetwork);
        showRegisterFormBtn.setEnabled(hasNetwork);

        //Display No network if there is no network.
        noNetwork.setVisibility(hasNetwork ? View.GONE : View.VISIBLE);

        //Fires when the user clicks/taps the Sign-in button to show the Login form
        showLoginFormBtn.setOnClickListener(button ->{
            //Might be redundant but double checking that you are tapping on Login Button
            if(button.getId() == R.id.show_login_form_btn){
                showView(mLoginForm);
            }
        });

        //Fires when the user clicks/taps the Register button to show the Register form.
        showRegisterFormBtn.setOnClickListener(button ->{
            //Might be redundant but double checking that you are tapping on Register Button
            if(button.getId() == R.id.show_register_form_btn){
                attemptRegister(button);
            }
        });

        //Fires when the user clicks the login button on the login form
        loginActionBtn.setOnClickListener(button -> {
            if(button.getId() == R.id.login_action_btn){
                attemptLogin();
            }
        });

        //Fires when the user taps/clicks the register button on the registration form
        registerActionBtn.setOnClickListener(button -> {
            if(button.getId() == R.id.register_action_btn){
                attemptRegister(button);
            }
        });

        //Fires when the user taps the back button on the login form
        loginBackBtn.setOnClickListener(button -> {
            //Double checking if either the Register back button or login back button was tapped.
            if(button.getId() == R.id.register_back_btn || button.getId() == R.id.login_back_btn){
                showView(mLandingContainer);
            }
        });

        //Fires when the user taps the back button on the register form
        registerBackBtn.setOnClickListener(button ->{
            //Double checking if either the Register back button or login back button was tapped.
            if(button.getId() == R.id.register_back_btn || button.getId() == R.id.login_back_btn){
                showView(mLandingContainer);
            }
        });
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        mEmailEt = findViewById(R.id.login_email);
        mPasswordEt = findViewById(R.id.login_password);

        String email = mEmailEt.getText().toString();
        String password = mPasswordEt.getText().toString();

        // Reset errors.
        mEmailEt.setError(null);
        mPasswordEt.setError(null);

        boolean cancel = false;
        View focusView = null;

        mAuth = new Auth(email,password);

        //Checks if the email or password is empty
        if( mAuth.isValid() ){
            mEmailEt.setError(getString(R.string.error_field_required));
            focusView = mEmailEt;
            cancel = true;
        }

        if(!cancel){
            showView(mProgressbarContainer);
            new UserLoginTask(mAuth).execute();
        }else{
            focusView.requestFocus();
        }
    }


    /**
     * Attempts to process the registration using the registration form data
     */
    private void attemptRegister(View button) {
        //TODO: Allow for inserting of new User (via Webserver)
        //There was an error I was stuck on in the web portion
        // I think my website security was too strict.
        Snackbar.make(button, "Error can't register new user. (Disabled)", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    /**
     * Shows the main activity
     * @param user the user obtained from the Login AsyncTask so we can setup the User Singleton
     */
    private void showMainActivity(User user){
        User appUser = User.getInstance();
        appUser.setId(user.getId());
        appUser.setName(user.getName());
        appUser.setEmail(user.getEmail());

        Intent showMainActivity = new Intent(this, MainActivity.class);
        showMainActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(showMainActivity);
        this.finish();
    }



    /**
     * Shows only the view parsed in the parameters
     * @param viewToShow View - The parsed view to set visible.
     */
    private void showView(View viewToShow){
        //Hides all the views
        mRegisterForm.setVisibility(View.GONE);
        mLoginForm.setVisibility(View.GONE);
        mLandingContainer.setVisibility(View.GONE);
        mProgressbarContainer.setVisibility(View.GONE);

        //Shows the view parsed in via the viewToShow parameter
        viewToShow.setVisibility(View.VISIBLE);
    }


    /**
     * The User Login Task used to log the user in
     */
    private class UserLoginTask extends AsyncTask<Void, Void, User>{
        private Auth mAuth;

        /**
         * Basic Constructor for the UserLoginTask
         * @param auth the Auth object to attempt the login
         */
        UserLoginTask(Auth auth){
            mAuth = auth;
        }
        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param voids The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected User doInBackground(Void... voids) {

            try {
                Gson gson = new Gson();

                String authJson = gson.toJson(mAuth);
                String test = HttpHelper.loginURL(authJson);
                User user = gson.fromJson(test,User.class);

                return user;

            } catch (IOException e) {
                Log.e(TAG, "doInBackground: ", e);
            }
            return null;
        }

        /**
         * Runs on the UI thread before {@link #doInBackground}.
         *
         * @see #onPostExecute
         * @see #doInBackground
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showView(mProgressbarContainer);
        }

        /**
         * <p>Runs on the UI thread after {@link #doInBackground}. The
         * specified result is the value returned by {@link #doInBackground}.</p>
         * <p>
         * <p>This method won't be invoked if the task was cancelled.</p>
         *
         * @param user The result of the operation computed by {@link #doInBackground}.
         * @see #onPreExecute
         * @see #doInBackground
         * @see #onCancelled(Object)
         */
        @Override
        protected void onPostExecute(User user) {
            if(user.getId() == 0){
                invalidUserNameOrPassword();
                super.onPostExecute(user);
            }else{
                showMainActivity(user);
            }

        }
    }

    /**
     * Fires when the username or password is sent back as invalid.
     */
    private void invalidUserNameOrPassword() {
        showView(mLoginForm);
        mEmailEt.setError(getString(R.string.error_invalid_email));
        mEmailEt.requestFocus();
    }

    //TODO: Make this actually work
    //TODO: Allow for inserting of new Users (via Webserver)
    //There was an error I was stuck on in the web portion
    // I think my website security was too strict.
    private class UserRegisterTask extends AsyncTask<Void, Void, Boolean>{

        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param voids The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected Boolean doInBackground(Void... voids) {
            return null;
        }
    }


}
