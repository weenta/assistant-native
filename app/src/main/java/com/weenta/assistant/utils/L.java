package com.weenta.assistant.utils;

import android.util.Log;

/**
 * log 封装
 */
public class L {
    public static final String TAG = "assistant_l";

    // L.i
    public static void i(String msg) {
        if(Config.IS_DEBUG) Log.i(TAG,msg);
    }

    // L.e
    public static void e(String msg) {
        if(Config.IS_DEBUG) Log.e(TAG,msg);
    }

    // L.w
    public static void w(String msg) {
        if(Config.IS_DEBUG) Log.w(TAG,msg);
    }
}
