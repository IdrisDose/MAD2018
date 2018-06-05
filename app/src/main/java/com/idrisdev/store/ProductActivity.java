package com.idrisdev.store;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.idrisdev.store.models.Product;

public class ProductActivity extends AppCompatActivity {
    private Product mProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent parentActivity = getIntent();
        mProduct = parentActivity.getExtras().getParcelable("product");

        TextView tv_Name = findViewById(R.id.layoutProductName);
        TextView tv_Description = findViewById(R.id.layoutProductDescription);
        TextView tv_Price = findViewById(R.id.layoutProductPrice);

        tv_Name.setText(mProduct.getName());
        tv_Description.setText(mProduct.getDescription());
        tv_Price.setText(mProduct.getDisplayPrice());
    }

}
