package com.idrisdev.store;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.idrisdev.store.models.User;

public class LandingActivity extends AppCompatActivity {

    private ScrollView mLoginForm;
    private ScrollView mRegisterForm;
    private TextView mTitle;
    private RelativeLayout mLandingContainer;
    private RelativeLayout mProgressbarContainer;

    //TODO: Remove this later:
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        //Initialize class scope views and variable
        mLoginForm = findViewById(R.id.login_form);
        mRegisterForm = findViewById(R.id.register_form);
        mTitle = findViewById(R.id.login_title_tv);
        mLandingContainer = findViewById(R.id.landing_container_rl);
        mProgressbarContainer = findViewById(R.id.progress_container_rl);

        //Declare and Initialize buttons
        Button showLoginFormBtn = findViewById(R.id.show_login_form_btn);
        Button showRegisterFormBtn = findViewById(R.id.show_register_form_btn);
        Button loginActionBtn = findViewById(R.id.login_action_btn);
        Button loginBackBtn = findViewById(R.id.login_back_btn);
        Button registerActionBtn = findViewById(R.id.register_action_btn);
        Button registerBackBtn = findViewById(R.id.register_back_btn);


        showLoginFormBtn.setOnClickListener(button ->{
            //Might be redundant but double checking that you are tapping on Login Button
            if(button.getId() == R.id.show_login_form_btn){
                showView(mLoginForm);
            }
        });

        showRegisterFormBtn.setOnClickListener(button ->{
            //Might be redundant but double checking that you are tapping on Register Button
            if(button.getId() == R.id.show_register_form_btn){
                showView(mRegisterForm);
            }
        });
        loginActionBtn.setOnClickListener(button -> {
            if(button.getId() == R.id.login_action_btn){
                attemptLogin();
            }
        });
        registerActionBtn.setOnClickListener(button -> {
            if(button.getId() == R.id.register_action_btn){
                attemptRegister();
            }
        });

        //Set the button listener the same for both back buttons.
        loginBackBtn.setOnClickListener(backButtonListener);
        registerBackBtn.setOnClickListener(backButtonListener);

        //TODO: Remove this for later
        user = new User(1, "Idris","idris@test.com");
        user.setPassword("secret");
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */

    //TODO: Fix this
    private void attemptLogin() {
        EditText emailEt = findViewById(R.id.login_email);
        EditText passwordEt = findViewById(R.id.login_password);

        String email = emailEt.getText().toString();
        String password = passwordEt.getText().toString();

        // Reset errors.
        emailEt.setError(null);
        passwordEt.setError(null);

        boolean cancel = false;
        View focusView = null;


        //TODO: Update this method
        if(TextUtils.isEmpty(password) || TextUtils.isEmpty(email)){
            emailEt.setError(getString(R.string.error_field_required));
            focusView = emailEt;
            cancel = true;
        }

        if(!user.getEmail().equals(email)){
            emailEt.setError(getString(R.string.error_invalid_email));
            focusView = emailEt;
            cancel = true;
        }else if(!user.attemptLogin(password)){
            passwordEt.setError(getString(R.string.error_incorrect_password));
            focusView = passwordEt;
            cancel = true;
        }


        if(!cancel){
            //Show (FAKE FOR NOW) Progress Bar
            showView(mProgressbarContainer);

            //swap to MainActivity
            Intent showMainActivity = new Intent(this, MainActivity.class);
            showMainActivity.putExtra("user",user);
            startActivity(showMainActivity);
        }else{
            focusView.requestFocus();
        }
    }


    /**
     * Attempts to process the registration using the registration form data
     */
    private void attemptRegister() {
        //TODO: Make actual registration
        Intent showMainActivity = new Intent(this, MainActivity.class);
        startActivity(showMainActivity);
    }


    Button.OnClickListener backButtonListener = new Button.OnClickListener() {
        /**
         * Called when a view has been clicked.
         *
         * @param button The view that was clicked.
         */
        @Override
        public void onClick(View button) {

            //Double checking if either the Register back button or login back button was tapped.
            if(button.getId() == R.id.register_back_btn || button.getId() == R.id.login_back_btn){
                showView(mLandingContainer);
            }

        }
    };

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

    //TODO: Fix this later
    @SuppressLint("StaticFieldLeak")
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean>{

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
            return false;
        }
    }

    //TODO: Fix this later
    @SuppressLint("StaticFieldLeak")
    public class UserRegisterTask extends AsyncTask<Void, Void, Boolean>{

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
