package com.chintansoni.android.masterproject.service.api;

import android.content.Context;
import android.content.Intent;

import com.chintansoni.android.masterproject.service.ApiService;
import com.chintansoni.android.masterproject.service.BaseApi;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;

public class CitiesApi extends BaseApi {

    public static final String ACTION = "Cities";
    // Login Api parameters
    public static final String URL = "cities";
    public static boolean isRunning = false;

    public static void fire(Retrofit retrofit) {
        if (isRunning) {
            showProgressDialog();
        } else {
            mRetrofit = retrofit;
            showProgressDialog();
            isRunning = true;
            Service service = retrofit.create(Service.class);
            Call<JsonObject> call = service.cities();
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

    public static Intent getServiceIntent(Context context) {
        Intent intent = new Intent(context, ApiService.class);
        intent.setAction(ACTION);
        return intent;
    }

    interface Service {
        @GET(URL)
        Call<JsonObject> cities();
    }

    public class ApiResponse {
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("data")
        @Expose
        private List<Datum> data = null;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<Datum> getData() {
            return data;
        }

        public void setData(List<Datum> data) {
            this.data = data;
        }

        public class Datum {

            @SerializedName("id")
            @Expose
            private int id;
            @SerializedName("sort_order")
            @Expose
            private int sortOrder;
            @SerializedName("created_at")
            @Expose
            private String createdAt;
            @SerializedName("created_by_id")
            @Expose
            private Object createdById;
            @SerializedName("updated_at")
            @Expose
            private Object updatedAt;
            @SerializedName("updated_by_id")
            @Expose
            private Object updatedById;
            @SerializedName("slug")
            @Expose
            private String slug;
            @SerializedName("live")
            @Expose
            private int live;
            @SerializedName("name")
            @Expose
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getSortOrder() {
                return sortOrder;
            }

            public void setSortOrder(int sortOrder) {
                this.sortOrder = sortOrder;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public Object getCreatedById() {
                return createdById;
            }

            public void setCreatedById(Object createdById) {
                this.createdById = createdById;
            }

            public Object getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(Object updatedAt) {
                this.updatedAt = updatedAt;
            }

            public Object getUpdatedById() {
                return updatedById;
            }

            public void setUpdatedById(Object updatedById) {
                this.updatedById = updatedById;
            }

            public String getSlug() {
                return slug;
            }

            public void setSlug(String slug) {
                this.slug = slug;
            }

            public int getLive() {
                return live;
            }

            public void setLive(int live) {
                this.live = live;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            @Override
            public String toString() {
                return this.name;
            }
        }
    }
}
