package com.utiset.muffin.data;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ife on 03/06/16.
 */
public class AuthPrefs {
    public static final String PREFS_NAME = "AuthPrefsFile";
    private Context ctx;
    private SharedPreferences settings;
    public AuthPrefs(Context _ctx)
    {
        ctx = _ctx;

    }
    private boolean initPrefs()
    {

        if (ctx == null)
            return false;
        settings = ctx.getSharedPreferences(PREFS_NAME, 0);

        return true;
    }

    public boolean setToken(String _token) {
        if (initPrefs()) {
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("token", _token);
            editor.commit();
            return true;
        }
        return false;
    }

    public String getToken()
    {
        if (initPrefs())
        {
            return settings.getString("token","");
        }
        return "";
    }

}
