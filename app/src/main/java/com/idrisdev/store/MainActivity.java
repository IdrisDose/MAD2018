package com.idrisdev.store;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.idrisdev.store.models.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MAD";
    private ArrayList<Product> mProducts = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private ProductAdapter mProductAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mProductAdapter = new ProductAdapter(getApplicationContext(),this.mProducts);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mProductAdapter);


        prepareProductData();


    }

    public class ProductCatalogAsyncTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }
    }

    /**
     * Adds a new product and notifies the product adapter
     * @param id int
     * @param name String
     * @param description String
     */
    private void addProduct(int id, String name, String description){
        Product product = new Product(id,name,description);
        this.mProducts.add(product);
        mProductAdapter.notifyDataSetChanged();
    }


    /**
     * prepares the product data for usage in this activity
     */
    private void prepareProductData(){
        // TODO: allow for json download via web
        // (http://idris.tech/products)
        for(int i = 0; i<this.randomNumGenerator(); i++){
            this.addProduct(i, "Product "+i, "A random description");
        }
    }

    /**
     * Random number generator using Math.random
     * @return random number between 1 and 20
     */
    private int randomNumGenerator(){

        //Got this neat little random num from here
        //https://dzone.com/articles/random-number-generation-in-java
        return (int)((Math.random() * ((20 - 1) + 1)) + 1);
    }

}
