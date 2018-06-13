package com.idrisdev.store.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.idrisdev.store.R;
import com.idrisdev.store.models.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    private User mUser;

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View accountView = inflater.inflate(R.layout.fragment_account, container, false);

        Bundle bundle = this.getArguments();
        if(bundle != null){
            this.mUser = bundle.getParcelable("user");
        }

        TextView userName = accountView.findViewById(R.id.account_name);
        TextView ownedProducts = accountView.findViewById(R.id.account_owned_products);
        TextView userEmail = accountView.findViewById(R.id.account_email);
        TextView userStatus = accountView.findViewById(R.id.account_status);

        userName.setText(getString(R.string.account_name,this.mUser.getName()));
        ownedProducts.setText(getString(R.string.user_product_count, this.mUser.getOrderCount()));
        userEmail.setText(getString(R.string.account_email_string,this.mUser.getEmail()));
        userStatus.setText(getString(R.string.account_status,this.mUser.getActivePretty()));

        return accountView;
    }

}
