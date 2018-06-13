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
import com.idrisdev.store.models.ProductList;
import com.idrisdev.store.models.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends Fragment {

    private ProductList mProducts;
    private User mUser;

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
             this.mProducts = bundle.getParcelable("products");
        }

        RecyclerView mRecyclerView = productView.findViewById(R.id.products_fragment_rv);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(inflater.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(new ProductAdapter(inflater.getContext(),this.mUser.getOrders()));

        TextView mOwnedProductsText = productView.findViewById(R.id.product_fragment_owned_products);
        mOwnedProductsText.setText(getString(R.string.product_owned_title,mUser.getOrderCount()));

        TextView mProductText = productView.findViewById(R.id.product_all_count);
        mProductText.setText(getString(R.string.products_title_with_count,mProducts.getSize()));

        AppCompatButton mViewAllBtn = productView.findViewById(R.id.view_all_products_button);
        mViewAllBtn.setOnClickListener(viewAllProducts);

        return productView;
    }

    private AppCompatButton.OnClickListener viewAllProducts = button -> {
          if(button.getId() == R.id.view_all_products_button){
              Intent allProductsScreen = new Intent(button.getContext(), AllProductsActivity.class);
              allProductsScreen.putExtra("products",this.mProducts);
              allProductsScreen.putExtra("user",this.mUser);
              startActivity(allProductsScreen);
          }
    };

}
