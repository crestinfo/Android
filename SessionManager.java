package com.Hector.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Hector on 8/17/17.
 */

public class SessionManager {

    public static final String USERNAME = "USERNAME";


    public static void setSession(Context context, String Key, String Value) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(Key, Value);
        editor.commit();
    }

    public static String getSession(Context context, String Key) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        return settings.getString(Key, "");
    }
}
