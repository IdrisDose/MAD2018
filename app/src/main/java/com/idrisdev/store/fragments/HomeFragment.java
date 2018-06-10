package com.idrisdev.store.fragments;


import android.annotation.SuppressLint;
import android.content.res.Resources;
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
public class HomeFragment extends Fragment {

    private User mUser;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inf = inflater.inflate(R.layout.fragment_home, container, false);
        //Get Parcelable Object (User)
        Bundle bundle = this.getArguments();
        if(bundle != null){
            mUser = bundle.getParcelable("user");
        }

        TextView name = inf.findViewById(R.id.home_fragment_title);
        name.setText(getString(R.string.home_fragment_text,mUser.getName()));

        // Inflate the layout for this fragment
        return inf;

    }

}
