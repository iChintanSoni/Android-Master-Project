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
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class LoginApi extends BaseApi {

    public static final String ACTION = "Login";
    public static final String ARG_EMAIL = "Email";
    public static final String ARG_PASSWORD = "Password";

    // Login Api parameters
    public static final String URL = "auth/login";
    public static final String FORM_DATA_EMAIL = "email";
    public static final String FORM_DATA_PASSWORD = "password";

    public static boolean isRunning = false;

    public static void fire(Retrofit retrofit, Bundle bundle) {
        if (isRunning) {
            showProgressDialog();
        } else {
            mRetrofit = retrofit;
            showProgressDialog();
            isRunning = true;
            String email = bundle.getString(ARG_EMAIL);
            String password = bundle.getString(ARG_PASSWORD);
            Service service = retrofit.create(Service.class);
            Call<JsonObject> call = service.login(email, password);
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

    public static Intent getServiceIntent(Context context, String email, String password) {
        Intent intent = new Intent(context, ApiService.class);
        intent.setAction(ACTION);
        Bundle bundle = new Bundle();
        bundle.putString(ARG_EMAIL, email);
        bundle.putString(ARG_PASSWORD, password);
        intent.putExtras(bundle);
        return intent;
    }

    interface Service {
        @FormUrlEncoded
        @POST(URL)
        Call<JsonObject> login(@Field(FORM_DATA_EMAIL) String email, @Field(FORM_DATA_PASSWORD) String password);
    }

    public class ApiResponse {
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("status_code")
        @Expose
        private long statusCode;
        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("token")
        @Expose
        private String token;
        @SerializedName("data")
        @Expose
        private Data data;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public long getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(long statusCode) {
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

            @SerializedName("id")
            @Expose
            private long id;
            @SerializedName("sort_order")
            @Expose
            private long sortOrder;
            @SerializedName("created_at")
            @Expose
            private String createdAt;
            @SerializedName("created_by_id")
            @Expose
            private Object createdById;
            @SerializedName("updated_at")
            @Expose
            private String updatedAt;
            @SerializedName("updated_by_id")
            @Expose
            private Object updatedById;
            @SerializedName("deleted_at")
            @Expose
            private Object deletedAt;
            @SerializedName("email")
            @Expose
            private String email;
            @SerializedName("username")
            @Expose
            private String username;
            @SerializedName("password")
            @Expose
            private String password;
            @SerializedName("display_name")
            @Expose
            private String displayName;
            @SerializedName("first_name")
            @Expose
            private Object firstName;
            @SerializedName("last_name")
            @Expose
            private Object lastName;
            @SerializedName("activated")
            @Expose
            private long activated;
            @SerializedName("enabled")
            @Expose
            private long enabled;
            @SerializedName("permissions")
            @Expose
            private Object permissions;
            @SerializedName("last_login_at")
            @Expose
            private Object lastLoginAt;
            @SerializedName("remember_token")
            @Expose
            private Object rememberToken;
            @SerializedName("activation_code")
            @Expose
            private String activationCode;
            @SerializedName("reset_code")
            @Expose
            private String resetCode;
            @SerializedName("last_activity_at")
            @Expose
            private Object lastActivityAt;
            @SerializedName("ip_address")
            @Expose
            private Object ipAddress;
            @SerializedName("profile_picture_id")
            @Expose
            private Object profilePictureId;
            @SerializedName("city_id")
            @Expose
            private long cityId;
            @SerializedName("phone")
            @Expose
            private String phone;
            @SerializedName("dob")
            @Expose
            private Object dob;
            @SerializedName("street_address")
            @Expose
            private Object streetAddress;
            @SerializedName("bank_name")
            @Expose
            private Object bankName;
            @SerializedName("bank_account_number")
            @Expose
            private Object bankAccountNumber;
            @SerializedName("bank_account_name")
            @Expose
            private Object bankAccountName;
            @SerializedName("bank_swift_code")
            @Expose
            private Object bankSwiftCode;
            @SerializedName("subscribe_email")
            @Expose
            private long subscribeEmail;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public long getSortOrder() {
                return sortOrder;
            }

            public void setSortOrder(long sortOrder) {
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

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }

            public Object getUpdatedById() {
                return updatedById;
            }

            public void setUpdatedById(Object updatedById) {
                this.updatedById = updatedById;
            }

            public Object getDeletedAt() {
                return deletedAt;
            }

            public void setDeletedAt(Object deletedAt) {
                this.deletedAt = deletedAt;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getDisplayName() {
                return displayName;
            }

            public void setDisplayName(String displayName) {
                this.displayName = displayName;
            }

            public Object getFirstName() {
                return firstName;
            }

            public void setFirstName(Object firstName) {
                this.firstName = firstName;
            }

            public Object getLastName() {
                return lastName;
            }

            public void setLastName(Object lastName) {
                this.lastName = lastName;
            }

            public long getActivated() {
                return activated;
            }

            public void setActivated(long activated) {
                this.activated = activated;
            }

            public long getEnabled() {
                return enabled;
            }

            public void setEnabled(long enabled) {
                this.enabled = enabled;
            }

            public Object getPermissions() {
                return permissions;
            }

            public void setPermissions(Object permissions) {
                this.permissions = permissions;
            }

            public Object getLastLoginAt() {
                return lastLoginAt;
            }

            public void setLastLoginAt(Object lastLoginAt) {
                this.lastLoginAt = lastLoginAt;
            }

            public Object getRememberToken() {
                return rememberToken;
            }

            public void setRememberToken(Object rememberToken) {
                this.rememberToken = rememberToken;
            }

            public String getActivationCode() {
                return activationCode;
            }

            public void setActivationCode(String activationCode) {
                this.activationCode = activationCode;
            }

            public String getResetCode() {
                return resetCode;
            }

            public void setResetCode(String resetCode) {
                this.resetCode = resetCode;
            }

            public Object getLastActivityAt() {
                return lastActivityAt;
            }

            public void setLastActivityAt(Object lastActivityAt) {
                this.lastActivityAt = lastActivityAt;
            }

            public Object getIpAddress() {
                return ipAddress;
            }

            public void setIpAddress(Object ipAddress) {
                this.ipAddress = ipAddress;
            }

            public Object getProfilePictureId() {
                return profilePictureId;
            }

            public void setProfilePictureId(Object profilePictureId) {
                this.profilePictureId = profilePictureId;
            }

            public long getCityId() {
                return cityId;
            }

            public void setCityId(long cityId) {
                this.cityId = cityId;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public Object getDob() {
                return dob;
            }

            public void setDob(Object dob) {
                this.dob = dob;
            }

            public Object getStreetAddress() {
                return streetAddress;
            }

            public void setStreetAddress(Object streetAddress) {
                this.streetAddress = streetAddress;
            }

            public Object getBankName() {
                return bankName;
            }

            public void setBankName(Object bankName) {
                this.bankName = bankName;
            }

            public Object getBankAccountNumber() {
                return bankAccountNumber;
            }

            public void setBankAccountNumber(Object bankAccountNumber) {
                this.bankAccountNumber = bankAccountNumber;
            }

            public Object getBankAccountName() {
                return bankAccountName;
            }

            public void setBankAccountName(Object bankAccountName) {
                this.bankAccountName = bankAccountName;
            }

            public Object getBankSwiftCode() {
                return bankSwiftCode;
            }

            public void setBankSwiftCode(Object bankSwiftCode) {
                this.bankSwiftCode = bankSwiftCode;
            }

            public long getSubscribeEmail() {
                return subscribeEmail;
            }

            public void setSubscribeEmail(long subscribeEmail) {
                this.subscribeEmail = subscribeEmail;
            }
        }
    }
}
