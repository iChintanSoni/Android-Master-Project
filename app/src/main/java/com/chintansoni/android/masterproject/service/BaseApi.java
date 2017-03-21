package com.chintansoni.android.masterproject.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by darshan on 22/2/17.
 */

public abstract class BaseApi {

    protected static Retrofit mRetrofit;

    public static void showProgressDialog() {
        postEvent(EventBusMessage.EVENT_SHOW_PROGRESS_DIALOG, null);
    }

    public static void hideProgressDialog() {
        postEvent(EventBusMessage.EVENT_HIDE_PROGRESS_DIALOG, null);
    }

    public static void postEvent(String event, Object object) {
        EventBus.getDefault().post(new EventBusMessage(event, object));
    }

    public static void parseResponse(Response<JsonObject> response, Class type) {
        if (response.isSuccessful()) {
            if (isSuccessResponse(response.body())) {
                postEvent(EventBusMessage.EVENT_RESPONSE, new Gson().fromJson(response.body(), type));
            } else
                postEvent(EventBusMessage.EVENT_RESPONSE, new Gson().fromJson(response.body(), DefaultErrorResponse.class));
        } else {
            parseError(response);
        }
    }

    private static void parseError(Response<JsonObject> response) {
        if (response.code() == 400) {
            Converter<ResponseBody, DefaultErrorResponse> converterDefault = mRetrofit.responseBodyConverter(DefaultErrorResponse.class, new Annotation[0]);
            try {
                DefaultErrorResponse defaultErrorResponse = converterDefault.convert(response.errorBody());
                postEvent(EventBusMessage.EVENT_RESPONSE, defaultErrorResponse);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else if (response.code() == 500) {
            Converter<ResponseBody, Error500Response> converter500 = mRetrofit.responseBodyConverter(Error500Response.class, new Annotation[0]);
            try {
                Error500Response error500Response = converter500.convert(response.errorBody());
                postEvent(EventBusMessage.EVENT_RESPONSE, error500Response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


//        ResponseBody responseBody = response.errorBody();
//        try {
//            JsonObject jsonObject = (JsonObject) new JsonParser().parse(response.errorBody().string());
//            jsonObject.get
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    // TODO: to change the success / error parsing when apis get fixed
    public static boolean isSuccessResponse(JsonObject jsonObject) {
        if (jsonObject.get("status_code") != null) {
            if (jsonObject.get("status_code").getAsInt() == 200) {
                if (jsonObject.get("status") != null) {
                    try {
                        return !(jsonObject.get("status").getAsInt() == 0);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                        try {
                            return jsonObject.get("status").getAsString().equals("ok");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            } else
                return false;
        } else {
            if (jsonObject.get("status") != null) {
                try {
                    return !(jsonObject.get("status").getAsInt() == 0);
                } catch (Exception exception) {
                    exception.printStackTrace();
                    try {
                        return jsonObject.get("status").getAsString().equals("ok");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        return false;
                    }
                }
            } else {
                try {
                    return jsonObject.get("data") != null;
                } catch (Exception exception) {
                    exception.printStackTrace();
                    try {
                        return jsonObject.get("data").getAsJsonArray() != null;
                    } catch (Exception exception1) {
                        exception.printStackTrace();
                        return false;
                    }
                }
            }
        }
    }

    public static class EventBusMessage {

        public static final String EVENT_SHOW_PROGRESS_DIALOG = "ShowProgressDialog";
        public static final String EVENT_HIDE_PROGRESS_DIALOG = "HideProgressDialog";
        public static final String EVENT_RESPONSE = "Response";
        public String event;
        public Object object;

        public EventBusMessage(String event, Object object) {
            this.event = event;
            this.object = object;
        }
    }

    public static class DefaultErrorResponse {
        @SerializedName("status")
        @Expose
        private long status;
        @SerializedName("message")
        @Expose
        private String message;

        public long getStatus() {
            return status;
        }

        public void setStatus(long status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class Error500Response {
        @SerializedName("error")
        @Expose
        private Error error;

        public Error getError() {
            return error;
        }

        public void setError(Error error) {
            this.error = error;
        }

        public class Error {

            @SerializedName("message")
            @Expose
            private String message;
            @SerializedName("status_code")
            @Expose
            private int statusCode;

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public int getStatusCode() {
                return statusCode;
            }

            public void setStatusCode(int statusCode) {
                this.statusCode = statusCode;
            }

        }
    }

    public static class Error422Response {

    }
}
