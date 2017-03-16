package com.example.moaaznash.mozehlibraryandroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.StringDef;
import android.support.v4.util.ArrayMap;

import com.example.moaaznash.userregistrationmozeh.R;
import com.google.gson.Gson;
import com.google.gson.internal.ObjectConstructor;

/**
 * Created by moaaznash on 3/15/17.
 */

public class SharedPreferenceMozeh {

    /**
     *
     * @param cont this
     * @param key
     * @param val
     */

   /*
    public static void setSharedPreferenceString(Context cont, String key, String val){
        SharedPreferences settings = cont.getSharedPreferences(cont.getResources().getString(R.string.shared_preference_mozeh), 0);
        //getSharedPreferenceString("sdf",0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key,val);
        editor.commit();
    }

    public static String getSharedPreferenceString(Context cont,String key){

        SharedPreferences settings = cont.getSharedPreferences(cont.getResources().getString(R.string.shared_preference_mozeh), 0);
        return settings.getString(key,null);

    }
*/



    public static void setSharedPreferenceObject(Context cont, SettingPrametersMozeh settingPrametersMozeh, String key){
        SharedPreferences settings = cont.getSharedPreferences(cont.getResources().getString(R.string.shared_preference_mozeh), 0);


        SharedPreferences.Editor prefsEditor = settings.edit();
        Gson gson = new Gson();
        String json = gson.toJson(settingPrametersMozeh);
        prefsEditor.putString(key, json);
        prefsEditor.commit();
    }

    public static SettingPrametersMozeh getSharedPreferenceObject(Context cont,String key){

        SharedPreferences settings = cont.getSharedPreferences(cont.getResources().getString(R.string.shared_preference_mozeh), 0);
        Gson gson = new Gson();
        String json = settings.getString(key, "");
        SettingPrametersMozeh settingPrametersMozeh;
        settingPrametersMozeh =   gson.fromJson(json,SettingPrametersMozeh.class);

        return settingPrametersMozeh;

    }
}
