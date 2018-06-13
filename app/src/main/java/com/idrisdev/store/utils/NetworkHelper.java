package com.idrisdev.store.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetworkHelper {
    private static final String TAG = "StoreApp";

    /**
     * Checks to see if the application has network
     *
     * @param context Context
     * @return boolean (True if the context has network; False if no network)
     */
    public static boolean hasNetworkAccess(Context context) {
        ConnectivityManager connManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        try {
            NetworkInfo activeNetwork = connManager.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        } catch (Exception e) {
            //Dirty I know but I don't know the Exceptions
            Log.e(TAG, "hasNetworkAccess: ", e);
            return false;
        }
    }
}
