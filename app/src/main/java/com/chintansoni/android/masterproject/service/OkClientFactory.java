package com.chintansoni.android.masterproject.service;

import android.app.Application;
import android.support.annotation.NonNull;

import com.chintansoni.android.masterproject.BuildConfig;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkClientFactory {

    private static final Class TAG = OkClientFactory.class;

    // Cache size for the OkHttpClient
    private static final int DISK_CACHE_SIZE = 50 * 1024 * 1024; // 50MB

    private OkClientFactory() {
    }

    @NonNull
    public static OkHttpClient provideOkHttpClient(Application application) {
        // Install an HTTP cache in the application cache directory.
        File cacheDir = new File(application.getCacheDir(), "https");
        Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);

        OkHttpClient.Builder builder = new OkHttpClient.Builder().cache(cache);
        builder.connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }

        return builder.build();
    }
}
