package com.idrisdev.store;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.idrisdev.store.adapters.AllProductAdapter;
import com.idrisdev.store.models.ProductList;
import com.idrisdev.store.models.User;

public class AllProductsActivity extends AppCompatActivity {

    private ProductList mProducts;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_products);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle arguments = getIntent().getExtras();
        if(arguments != null){
            this.mProducts = arguments.getParcelable("products");
            this.mUser = arguments.getParcelable("user");
        }else{
            this.mProducts = new ProductList();
            this.mUser = new User(0, "null","null");
        }

        RecyclerView allProductsRv = findViewById(R.id.all_products_activity_rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        allProductsRv.setLayoutManager(layoutManager);
        allProductsRv.setItemAnimator(new DefaultItemAnimator());
        allProductsRv.setAdapter(new AllProductAdapter(getApplicationContext(),this.mProducts,this.mUser));

        TextView allProductsActivityTitle = findViewById(R.id.all_products_activity_title);
        allProductsActivityTitle.setText(getString(R.string.products_title_with_count, this.mProducts.getSize()));

    }

}
