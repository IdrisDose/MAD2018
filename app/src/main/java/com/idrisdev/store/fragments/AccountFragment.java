package com.idrisdev.store.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.idrisdev.store.R;
import com.idrisdev.store.models.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    private static User sUser;

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View accountView = inflater.inflate(R.layout.fragment_account, container, false);

        //Gets the Static User Singleton
        sUser = User.getInstance();

        TextView userName = accountView.findViewById(R.id.account_name);
        TextView ownedProducts = accountView.findViewById(R.id.account_owned_products);
        TextView userEmail = accountView.findViewById(R.id.account_email);
        TextView userStatus = accountView.findViewById(R.id.account_status);
        Button changeEmailButton = accountView.findViewById(R.id.change_email_action);
        Button changePasswordButton = accountView.findViewById(R.id.change_password_action);

        userName.setText(getString(R.string.account_name, sUser.getName()));
        ownedProducts.setText(getString(R.string.user_product_count, sUser.getOrderCount()));
        userEmail.setText(getString(R.string.account_email_string, sUser.getEmail()));
        userStatus.setText(getString(R.string.account_status, sUser.getActivePretty()));

        //Fires when the Change Email button is clicked/tapped
        changeEmailButton.setOnClickListener(button -> {
            //TODO: Allow for changing of Email (via Webserver)
            //There was an error I was stuck on in the web portion
            // I think my website security was too strict.
            Snackbar.make(button, "Error Can't update Email. (Disabled)", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        });

        //Fires when the Change Password button is clicked/tapped
        changePasswordButton.setOnClickListener(button -> {
            //TODO: Allow for changing of Password (via Webserver)
            //There was an error I was stuck on in the web portion
            // I think my website security was too strict.
            Snackbar.make(button, "Error Can't update Password. (Disabled)", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        });

        return accountView;
    }


}
