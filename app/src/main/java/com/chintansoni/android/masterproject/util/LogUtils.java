package com.chintansoni.android.masterproject.util;

import android.util.Log;

import com.chintansoni.android.masterproject.BuildConfig;

public class LogUtils {

    private static boolean isLoggable() {
        return BuildConfig.DEBUG;
    }

    private static String createTag(Class tag) {
        String tempTag = tag.getSimpleName();
        return tempTag.length() > 23 ? tempTag.substring(0, 22) : tempTag;
    }

    public static void error(Class tag, String message) {
        if (isLoggable()) {
            Log.e(createTag(tag), message);
        }
    }

    public static void warning(Class tag, String message) {
        if (isLoggable()) {
            Log.w(createTag(tag), message);
        }
    }

    public static void information(Class tag, String message) {
        if (isLoggable()) {
            Log.i(createTag(tag), message);
        }
    }

    public static void debug(Class tag, String message) {
        if (isLoggable()) {
            Log.d(createTag(tag), message);
        }
    }

    public static void verbose(Class tag, String message) {
        if (isLoggable()) {
            Log.v(createTag(tag), message);
        }
    }

    public static void wtf(Class tag, String message) {
        if (isLoggable()) {
            Log.wtf(createTag(tag), message);
        }
    }
}