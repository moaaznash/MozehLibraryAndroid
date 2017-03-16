package com.example.moaaznash.mozehlibraryandroid;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.moaaznash.userregistrationmozeh.R;
import com.google.gson.Gson;

/**
 * Created by moaaznash on 3/15/17.
 */

public class AppSettingsMozeh {

private static SettingPrametersMozeh settingPrametersMozeh;




    public static void setSettingPrametersMozeh(SettingPrametersMozeh _settingPrametersMozeh){

        settingPrametersMozeh = _settingPrametersMozeh;
    }

    public static SettingPrametersMozeh getSettingPrametersMozeh(){

        return settingPrametersMozeh;
    }
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
