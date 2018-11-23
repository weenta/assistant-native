package com.weenta.assistant.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * util
 */
public class Util {
    /**
     * toast
     * @param context
     * @param msg
     */
    public static void toast(Context context,String msg) {
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
}
