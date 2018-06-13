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
public class HomeFragment extends Fragment {

    private static User sUser;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View homeView = inflater.inflate(R.layout.fragment_home, container, false);
        //Get Parcelable Object (User)

        sUser = User.getInstance();

        TextView name = homeView.findViewById(R.id.home_fragment_title);
        TextView ownedProducts = homeView.findViewById(R.id.home_owned_products);
        TextView openTickets = homeView.findViewById(R.id.home_open_tickets);
        TextView newsContainer = homeView.findViewById(R.id.home_recent_news);

        name.setText(getString(R.string.home_fragment_text,sUser.getName()));
        ownedProducts.setText(getString(R.string.user_product_count,sUser.getOrderCount()));
        openTickets.setText(getString(R.string.user_open_ticket_count,0));

        //Sets the news container but since there is no news it's 'No Recent News.'
        newsContainer.setText(getString(R.string.default_no_news));
        // Inflate the layout for this fragment
        return homeView;

    }

}
