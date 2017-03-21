package com.chintansoni.android.masterproject.service.api;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.chintansoni.android.masterproject.service.ApiService;
import com.chintansoni.android.masterproject.service.BaseApi;
import com.chintansoni.android.masterproject.util.File.FileUtils;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public class UploadImageApi extends BaseApi {

    public static final String ACTION = "UploadImage";
    private static final String ARG_FILE = "File";
    private static final String ARG_FOLDER = "Folder";
    private static final String ARG_TOKEN = "Token";

    private static final String URL = "upload";
    private static final String FORM_DATA_UPLOAD = "upload";
    private static final String FORM_DATA_FOLDER = "folder";
    private static final String PARAMETER_TOKEN = "token";

    private static boolean isRunning = false;

    public static void fire(Context context, Retrofit retrofit, Bundle bundle) {
        if (isRunning) {
            showProgressDialog();
        } else {
            mRetrofit = retrofit;
            showProgressDialog();
            isRunning = true;
            String file = bundle.getString(ARG_FILE);
            String folder = bundle.getString(ARG_FOLDER);
            String token = bundle.getString(ARG_TOKEN);

            // First working for file
            // create RequestBody instance from file
            Uri fileUri = Uri.parse(file);
            RequestBody requestFile = RequestBody.create(
                    MediaType.parse(context.getContentResolver().getType(fileUri)),
                    FileUtils.getFile(context, fileUri)
            );
            // MultipartBody.Part is used to send also the actual file name
            MultipartBody.Part body = MultipartBody.Part.createFormData(FORM_DATA_UPLOAD, FileUtils.getFile(context, fileUri).getName(), requestFile);

            // Now working for passing text along with file.
            RequestBody description = RequestBody.create(MultipartBody.FORM, folder);

            Service service = retrofit.create(Service.class);
            Call<JsonObject> call = service.uploadImage(body, description, token);
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

    public static Intent getServiceIntent(Context context, String file, String folder, String token) {
        Intent intent = new Intent(context, ApiService.class);
        intent.setAction(ACTION);
        Bundle bundle = new Bundle();
        bundle.putString(ARG_FILE, file);
        bundle.putString(ARG_FOLDER, folder);
        bundle.putString(ARG_TOKEN, token);
        intent.putExtras(bundle);
        return intent;
    }

    interface Service {
        @Multipart
        @POST(URL)
        Call<JsonObject> uploadImage(@Part MultipartBody.Part file, @Part(FORM_DATA_FOLDER) RequestBody description, @Query(PARAMETER_TOKEN) String token);
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
        @SerializedName("data")
        @Expose
        private Data data;

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

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

        public class Data {

            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("folder_id")
            @Expose
            private int folderId;
            @SerializedName("disk_id")
            @Expose
            private int diskId;
            @SerializedName("size")
            @Expose
            private int size;
            @SerializedName("mime_type")
            @Expose
            private String mimeType;
            @SerializedName("extension")
            @Expose
            private String extension;
            @SerializedName("entry_type")
            @Expose
            private String entryType;
            @SerializedName("deleted_at")
            @Expose
            private Object deletedAt;
            @SerializedName("width")
            @Expose
            private int width;
            @SerializedName("height")
            @Expose
            private int height;
            @SerializedName("created_at")
            @Expose
            private String createdAt;
            @SerializedName("created_by_id")
            @Expose
            private int createdById;
            @SerializedName("sort_order")
            @Expose
            private int sortOrder;
            @SerializedName("id")
            @Expose
            private int id;
            @SerializedName("updated_at")
            @Expose
            private String updatedAt;
            @SerializedName("updated_by_id")
            @Expose
            private int updatedById;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getFolderId() {
                return folderId;
            }

            public void setFolderId(int folderId) {
                this.folderId = folderId;
            }

            public int getDiskId() {
                return diskId;
            }

            public void setDiskId(int diskId) {
                this.diskId = diskId;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public String getMimeType() {
                return mimeType;
            }

            public void setMimeType(String mimeType) {
                this.mimeType = mimeType;
            }

            public String getExtension() {
                return extension;
            }

            public void setExtension(String extension) {
                this.extension = extension;
            }

            public String getEntryType() {
                return entryType;
            }

            public void setEntryType(String entryType) {
                this.entryType = entryType;
            }

            public Object getDeletedAt() {
                return deletedAt;
            }

            public void setDeletedAt(Object deletedAt) {
                this.deletedAt = deletedAt;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public int getCreatedById() {
                return createdById;
            }

            public void setCreatedById(int createdById) {
                this.createdById = createdById;
            }

            public int getSortOrder() {
                return sortOrder;
            }

            public void setSortOrder(int sortOrder) {
                this.sortOrder = sortOrder;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }

            public int getUpdatedById() {
                return updatedById;
            }

            public void setUpdatedById(int updatedById) {
                this.updatedById = updatedById;
            }

        }
    }
}
