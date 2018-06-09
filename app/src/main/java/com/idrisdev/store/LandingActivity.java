package com.idrisdev.store;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

public class LandingActivity extends AppCompatActivity {

    private ScrollView mLoginForm;
    private ScrollView mRegisterForm;
    private TextView mTitle;
    private ConstraintLayout mButtonPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        //Initialize class scope views and variable
        mLoginForm = (ScrollView) findViewById(R.id.login_form);
        mRegisterForm = (ScrollView) findViewById(R.id.register_form);
        mTitle = (TextView) findViewById(R.id.login_title_tv);
        mButtonPanel = (ConstraintLayout) findViewById(R.id.button_panel);

        //Declare and Initialize buttons
        Button showLoginFormBtn = (Button) findViewById(R.id.show_login_form_btn);
        Button showRegisterFormBtn = (Button) findViewById(R.id.show_register_form_btn);
        Button loginActionBtn = (Button) findViewById(R.id.login_action_btn);
        Button loginBackBtn = (Button) findViewById(R.id.login_back_btn);
        Button registerActionBtn = (Button) findViewById(R.id.register_action_btn);
        Button registerBackBtn = (Button) findViewById(R.id.register_back_btn);


        showLoginFormBtn.setOnClickListener(button ->{
            //Might be redundant but double checking that you are tapping on Login Button
            if(button.getId() == R.id.show_login_form_btn){
                showLogin(true);
            }
        });

        showRegisterFormBtn.setOnClickListener(button ->{
            //Might be redundant but double checking that you are tapping on Register Button
            if(button.getId() == R.id.show_register_form_btn){
                showRegister(true);
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
    }

    /**
     * Attempts to process the registration using the registration form data
     */
    private void attemptRegister() {
        //TODO: Make actual registration
        Intent showMainActivity = new Intent(this, MainActivity.class);
        startActivity(showMainActivity);
    }

    /**
     * Attempts to process the login using the form data
     */
    private void attemptLogin() {
        //TODO: Update this method
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
                showButtonPanel();
            }

        }
    };

    /**
     * Shows or hides the register form
     * @param showForm used to show or hide the elements
     */
    private void showRegister(boolean showForm) {
        mRegisterForm.setVisibility(showForm ? View.VISIBLE : View.GONE);
        mLoginForm.setVisibility(showForm? View.GONE : View.VISIBLE);
        mTitle.setVisibility(showForm? View.GONE : View.VISIBLE);
        mButtonPanel.setVisibility(showForm? View.GONE : View.VISIBLE);
    }

    /**
     * Shows or hides the login form
     * @param showForm used to show or hide the elements
     */
    private void showLogin(boolean showForm) {
        mLoginForm.setVisibility(showForm ? View.VISIBLE : View.GONE);
        mRegisterForm.setVisibility(showForm ? View.GONE : View.VISIBLE);
        mTitle.setVisibility(showForm? View.GONE : View.VISIBLE);
        mButtonPanel.setVisibility(showForm? View.GONE : View.VISIBLE);
    }

    private void showButtonPanel(){
        mLoginForm.setVisibility(View.GONE);
        mRegisterForm.setVisibility(View.GONE);
        mTitle.setVisibility(View.VISIBLE);
        mButtonPanel.setVisibility( View.VISIBLE);
    }
}
