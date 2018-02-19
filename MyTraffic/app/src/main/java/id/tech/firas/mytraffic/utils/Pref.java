package id.tech.firas.mytraffic.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by Firas Luthfi on 2/18/2018.
 */

public class Pref {

    // LogCat tag
    private static String TAG = Pref.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;

    SharedPreferences.Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "id.tech.firas.mytraffic.preferences";

    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";

    public Pref(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn) {

        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);

        // commit changes
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    public void set_userdata(String key_session, String value_session){
        editor.putString(key_session, value_session);
        editor.commit();
    }
    public void set_userdata(String key_session, Long value_session){
        editor.putLong(key_session, value_session);
        editor.commit();
    }
    public void set_userdata(String key_session, boolean value_session){
        editor.putBoolean(key_session, value_session);
        editor.commit();
    }

    public String userdata(String key_session){
        return pref.getString(key_session, null);
    }
    public Long userdata_long(String key_session){
        return pref.getLong(key_session, 0);
    }
    public boolean isUserdata(String key_session){
        return pref.getBoolean(key_session, false);
    }

}