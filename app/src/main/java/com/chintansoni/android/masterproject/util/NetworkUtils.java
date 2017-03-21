package com.chintansoni.android.masterproject.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.chintansoni.android.masterproject.R;

/**
 * Created by Chintan Soni - Senior Software Engineer (Android).
 */

public class NetworkUtils {
    public static boolean isInternetAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isInternetAvailable = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if (!isInternetAvailable)
            Toast.makeText(context, R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
        return isInternetAvailable;
    }
}
