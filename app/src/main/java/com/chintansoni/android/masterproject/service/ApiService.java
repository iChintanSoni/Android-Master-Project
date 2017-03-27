package com.chintansoni.android.masterproject.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.chintansoni.android.masterproject.service.api.CitiesApi;
import com.chintansoni.android.masterproject.service.api.LoginApi;
import com.chintansoni.android.masterproject.service.api.NotificationsDeleteApi;
import com.chintansoni.android.masterproject.service.api.UploadImageApi;
import com.chintansoni.android.masterproject.util.NetworkUtils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService extends Service {

    // TODO: Replace with your base URL
    public static final String BASE_URL = "<Replace with your base URL>";

    private static final String API_URL = BASE_URL + "api/";

    private OkHttpClient mOkHttpClient;

    public ApiService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mOkHttpClient = OkClientFactory.provideOkHttpClient(getApplication());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null)
            return super.onStartCommand(intent, flags, startId);

        // This is the only place we need to check for internet connection
        if (!NetworkUtils.isInternetAvailable(this))
            return super.onStartCommand(intent, flags, startId);

        String action = intent.getAction();
        switch (action) {
            case LoginApi.ACTION:
                LoginApi.fire(provideRestAdapter(API_URL), intent.getExtras());
                break;
            case CitiesApi.ACTION:
                CitiesApi.fire(provideRestAdapter(API_URL));
                break;
            case NotificationsDeleteApi.ACTION:
                NotificationsDeleteApi.fire(provideRestAdapter(API_URL), intent.getExtras());
                break;
            case UploadImageApi.ACTION:
                UploadImageApi.fire(this, provideRestAdapter(API_URL), intent.getExtras());
                break;
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private Retrofit provideRestAdapter(String url) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(mOkHttpClient)
                .build();
    }
}
