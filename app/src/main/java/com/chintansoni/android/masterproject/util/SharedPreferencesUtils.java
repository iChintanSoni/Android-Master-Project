package com.chintansoni.android.masterproject.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtils {

    public static final String PREFERENCE_IS_USER_LOGGED_IN = "IsUserLoggedIn";
    public static final String PREFERENCE_LOGIN_RESPONSE = "LoginResponse";
    public static final String PREFERENCE_TOKEN = "UserToken";
    public static final String PREFERENCE_CITIES = "CitiesResponse";

    public static final String PREFERENCE_COURTTYPERESPONSE = "CourtTypeResponse";
    public static final String PREFERENCE_TEAMLEVELPERESPONSE = "TeamLevelResponse";
    public static final String PREFERENCE_FLOORTYPERESPONSE = "FloorTypeResponse";
    public static final String PREFERENCE_CAPACITYRESPONSE = "CapacitiesResponse";
    public static final String PREFERENCE_EMAIL = "UserEmail";
    public static final String PREFERENCE_DISPLAY_NAME = "UserDisplayName";
    private static final String PREFERENCE_NAME = "SportsVenuePreferences";
    private static SharedPreferencesUtils mSharedPreferencesUtils;
    private static SharedPreferences mSharedPreferences;

    private SharedPreferencesUtils() {

    }

    public static SharedPreferencesUtils getInstance(Context context) {
        if (mSharedPreferencesUtils == null) {
            mSharedPreferencesUtils = new SharedPreferencesUtils();
        }
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        }
        return mSharedPreferencesUtils;
    }

    public void setInt(String key, int value) {
        mSharedPreferences.edit().putInt(key, value).apply();
    }

    public int getInt(String key) {
        return mSharedPreferences.getInt(key, 0);
    }

    public void setString(String key, String value) {
        mSharedPreferences.edit().putString(key, value).apply();
    }

    public String getString(String key) {
        return mSharedPreferences.getString(key, "");
    }

    public void setBoolean(String key, boolean value) {
        mSharedPreferences.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key) {
        return mSharedPreferences.getBoolean(key, false);
    }

    public void clearKey(String key) {
        mSharedPreferences.edit().remove(key).apply();
    }

    public void clearPreferences() {
        mSharedPreferences.edit().clear().apply();
    }
}
