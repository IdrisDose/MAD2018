package com.idrisdev.store.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.idrisdev.store.R;
import com.idrisdev.store.adapters.ProductAdapter;
import com.idrisdev.store.models.Product;
import com.idrisdev.store.models.User;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends Fragment {

    private ArrayList<Product> mProducts;
    private User mUser;
    private  RecyclerView mRecyclerView;
    private TextView mProductText;
    private TextView mOwnedProductsText;

    public ProductsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View productView = inflater.inflate(R.layout.fragment_products, container, false);

        Bundle bundle = this.getArguments();
        if(bundle != null){
             this.mUser = bundle.getParcelable("user");
             this.mProducts = bundle.getParcelableArrayList("products");
        }

        mRecyclerView = productView.findViewById(R.id.products_fragment_rv);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(inflater.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(new ProductAdapter(inflater.getContext(),this.mUser.getOrders()));

        mOwnedProductsText = productView.findViewById(R.id.product_fragment_owned_products);
        mOwnedProductsText.setText(getString(R.string.product_owned_title,mUser.getOrderCount()));

        mProductText = productView.findViewById(R.id.product_all_count);
        mProductText.setText(getString(R.string.product_all_title,mProducts.size()));
        return productView;
    }

}
