package com.idrisdev.store.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.idrisdev.store.AllProductsActivity;
import com.idrisdev.store.R;
import com.idrisdev.store.adapters.ProductAdapter;
import com.idrisdev.store.models.Product;
import com.idrisdev.store.models.ProductList;
import com.idrisdev.store.models.User;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends Fragment {

    private ProductList mProducts;
    private static User sUser;

    public ProductsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View productView = inflater.inflate(R.layout.fragment_products, container, false);

        //Checks to see if the bundle parsed in via it's intent is empty
        Bundle bundle = this.getArguments();
        if(bundle != null){
            //If it's not set this classes' productList to the one parsed in
             this.mProducts = bundle.getParcelable("products");
        }else{
            //else replace with empty so that it app doesn't crash completely
            this.mProducts = new ProductList(new ArrayList<>());
        }

        //Sets the sUser to the User Singleton (still don't know if I really need to or I can just target User.getIntance)
        sUser = User.getInstance();

        RecyclerView mRecyclerView = productView.findViewById(R.id.products_fragment_rv);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(inflater.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(new ProductAdapter(inflater.getContext(), sUser.getOrders()));

        TextView mOwnedProductsText = productView.findViewById(R.id.product_fragment_owned_products);
        mOwnedProductsText.setText(getString(R.string.product_owned_title, sUser.getOrderCount()));

        TextView mProductText = productView.findViewById(R.id.product_all_count);
        mProductText.setText(getString(R.string.products_title_with_count,mProducts.getSize()));

        AppCompatButton mViewAllBtn = productView.findViewById(R.id.view_all_products_button);

        //Fires when the view all button is tapped
        mViewAllBtn.setOnClickListener(button -> {
            if(button.getId() == R.id.view_all_products_button){
                Intent allProductsScreen = new Intent(button.getContext(), AllProductsActivity.class);
                allProductsScreen.putExtra("products",this.mProducts);
                startActivity(allProductsScreen);
            }
        });

        return productView;
    }
}
