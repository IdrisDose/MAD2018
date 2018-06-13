package com.idrisdev.store;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.idrisdev.store.adapters.ProductAdapter;
import com.idrisdev.store.models.User;

public class CartActivity extends AppCompatActivity {

    private User mUser;
    private RelativeLayout mCartProgress;
    private LinearLayout mCartItemContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.mUser = extras.getParcelable("user");
        }else{
            this.mUser = new User(0,"null","null");
        }

        mCartProgress = findViewById(R.id.cart_progress_container);
        mCartItemContainer = findViewById(R.id.cart_container);

        RecyclerView cartItemRv = findViewById(R.id.cart_items_container);
        RecyclerView.LayoutManager cartItemLayoutManager = new LinearLayoutManager(getApplicationContext());
        cartItemRv.setLayoutManager(cartItemLayoutManager);
        cartItemRv.setAdapter(new ProductAdapter(getApplicationContext(), this.mUser.getCart().getCartItems()));
        cartItemRv.setItemAnimator(new DefaultItemAnimator());

        FloatingActionButton fab = findViewById(R.id.cart_checkout_btn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CheckoutTask().execute();
            }
        });
    }

    private void showProgress(boolean show){
        mCartProgress.setVisibility(show ? View.VISIBLE : View.GONE);
        mCartItemContainer.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    private class CheckoutTask extends AsyncTask<Void,Void,Boolean>{


        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param voids The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }

        /**
         * Runs on the UI thread before {@link #doInBackground}.
         *
         * @see #onPostExecute
         * @see #doInBackground
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgress(true);
        }

        /**
         * <p>Runs on the UI thread after {@link #doInBackground}. The
         * specified result is the value returned by {@link #doInBackground}.</p>
         * <p>
         * <p>This method won't be invoked if the task was cancelled.</p>
         *
         * @param aBoolean The result of the operation computed by {@link #doInBackground}.
         * @see #onPreExecute
         * @see #doInBackground
         * @see #onCancelled(Object)
         */
        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            showProgress(false);
        }
    }

}
