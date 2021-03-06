package com.idrisdev.store.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.idrisdev.store.R;
import com.idrisdev.store.models.Product;
import com.idrisdev.store.models.ProductList;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private static final String TAG = "StoreApp";
    private Context mContext;
    private ProductList mProducts;

    /**
     * ProductAdapter basic constructor
     *
     * @param context  the context needed to be targeted
     * @param products a product list to be displayed
     */
    public ProductAdapter(Context context, ProductList products) {
        this.mContext = context;
        this.mProducts = products;
    }

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     * <p>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p>
     * The new ViewHolder will be used to display items of the adapter using
     * onBindViewHolder(ViewHolder, int, List). Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(ViewHolder, int)
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(this.mContext).inflate(R.layout.product_item, parent, false);

        return new ViewHolder(itemView);
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     * <p>
     * Note that unlike {@link ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link ViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p>
     * Override onBindViewHolder(ViewHolder, int, List) instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Product mProduct = mProducts.getAllProducts().get(position);
        holder.mName.setText(mProduct.getName());
        holder.mDescription.setText(mProduct.getDescription());
        holder.mPrice.setText(mProduct.getDisplayPrice());


        // Sets the onClickListener of a product item to change to a 'more details' activity.
        // USES: a Lamba Expression with input of 'view'
        holder.mProductLayout.setOnClickListener(view -> {
            //Doesn't need an action because we can only add to cart not remove
            Snackbar.make(view, "No Available Option", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        });
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return mProducts.getSize();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mName;
        TextView mDescription;
        TextView mPrice;
        RelativeLayout mProductLayout;

        ViewHolder(View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.product_name_tv);
            mDescription = itemView.findViewById(R.id.product_description_tv);
            mPrice = itemView.findViewById(R.id.product_item_price_tv);
            mProductLayout = itemView.findViewById(R.id.product_layout_rl);
        }
    }

}
