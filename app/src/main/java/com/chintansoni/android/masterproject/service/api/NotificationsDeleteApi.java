package com.chintansoni.android.masterproject.service.api;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.chintansoni.android.masterproject.service.ApiService;
import com.chintansoni.android.masterproject.service.BaseApi;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.DELETE;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class NotificationsDeleteApi extends BaseApi {

    public static final String ACTION = "NotificationsDelete";
    // Login Api parameters
    public static final String URL = "delete-notification/{id}";
    public static final String ARG_TOKEN = "Token";
    public static final String ARG_ID = "Id";
    public static final String PARAMETER_ID = "id";
    public static final String PARAMETER_TOKEN = "token";
    public static boolean isRunning = false;

    public static void fire(Retrofit retrofit, Bundle bundle) {
        if (isRunning) {
            showProgressDialog();
        } else {
            mRetrofit = retrofit;
            showProgressDialog();
            isRunning = true;

            String token = bundle.getString(ARG_TOKEN);
            String id = bundle.getString(ARG_ID);

            Service service = retrofit.create(Service.class);
            Call<JsonObject> call = service.notificationsDelete(id, token);
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    isRunning = false;
                    parseResponse(response, ApiResponse.class);
                    hideProgressDialog();
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    isRunning = false;
                    hideProgressDialog();
                }
            });
        }
    }

    public static Intent getServiceIntent(Context context, String token, String id) {
        Intent intent = new Intent(context, ApiService.class);
        intent.setAction(ACTION);
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TOKEN, token);
        bundle.putString(ARG_ID, id);
        intent.putExtras(bundle);
        return intent;
    }

    interface Service {
        @DELETE(URL)
        Call<JsonObject> notificationsDelete(@Path(PARAMETER_ID) String id, @Query(PARAMETER_TOKEN) String token);
    }

    public class ApiResponse {
        @SerializedName("status")
        @Expose
        private int status;
        @SerializedName("status_code")
        @Expose
        private int statusCode;
        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("token")
        @Expose
        private String token;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
