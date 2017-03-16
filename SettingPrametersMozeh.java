package com.example.moaaznash.mozehlibraryandroid;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v4.util.ArrayMap;

import com.example.moaaznash.userregistrationmozeh.R;
import com.google.gson.Gson;

/**
 * Created by moaaznash on 3/14/17.
 */

public class SettingPrametersMozeh {


    //public static Context context;
    //private static ArrayMap<String, Object> ARGS = new ArrayMap<>();

    private static String userCredintials;

    private static String imageSavingDirectory = "/user_reg_mozeh1";

    private static UsersFirTblMozeh usersFirTblMozeh;


    //private static Boolean claseIsInitized = false;

    public SettingPrametersMozeh(UsersFirTblMozeh _usersFirTblMozeh) {

       // claseIsInitized = true;

        //imageSavingDirectory = Resources.getSystem().getString(R.string.image_saving_directory_mozeh);
        //userCredintials = Resources.getSystem().getString(R.string.user_credintial_key_name_mozeh);

        usersFirTblMozeh = _usersFirTblMozeh;


      //  SettingPrametersMozeh newS = new SettingPrametersMozeh(_usersFirTblMozeh)

     //   ARGS.put(userCredintials, _usersFirTblMozeh);
        //ARGS.put("IMAGE_SAVING_DIRECTORY", imageSavingDirectory);

    }
    public static void setUserCredintial(Context cont,UsersFirTblMozeh _usersFirTblMozeh) {

       usersFirTblMozeh = _usersFirTblMozeh;

        setSharedPreferenceUserCredintial(cont,_usersFirTblMozeh);
        //  ARGS.put(userCredintials, _usersFirTblMozeh);
        // return;
    }
    public static void removeUserCredintial(Context cont){
        UsersFirTblMozeh oo = new UsersFirTblMozeh("","","","","","","","","");
        setUserCredintial(cont,oo);
    }

    public static UsersFirTblMozeh getUserCredintials() {
        //UsersFirTblMozeh uu = (com.example.moaaznash.mozehlibraryandroid.UsersFirTblMozeh) ARGS.get(userCredintials);



        return usersFirTblMozeh;
    }

    public static UsersFirTblMozeh getUserCredintialsFromStorage(Context cont) {
        //UsersFirTblMozeh uu = (com.example.moaaznash.mozehlibraryandroid.UsersFirTblMozeh) ARGS.get(userCredintials);

            usersFirTblMozeh = getSharedPreferenceUserCredintial(cont);




        return usersFirTblMozeh;
    }

    public static String getImageSavingDirectory() {
        //  imageSavingDirectory = cont.getResources().getString();
        //imageSavingDirectory = _imageSavingDirectory;
        return imageSavingDirectory;
    }



    private static void setSharedPreferenceUserCredintial(Context cont, UsersFirTblMozeh usersFirTblMozeh){
        SharedPreferences settings = cont.getSharedPreferences(cont.getResources().getString(R.string.shared_preference_mozeh), 0);


        SharedPreferences.Editor prefsEditor = settings.edit();
        Gson gson = new Gson();
        String json = gson.toJson(usersFirTblMozeh);
        prefsEditor.putString("USERT_CREDINTIAL", json);
        prefsEditor.commit();
    }

    private static UsersFirTblMozeh getSharedPreferenceUserCredintial(Context cont){

        SharedPreferences settings = cont.getSharedPreferences(cont.getResources().getString(R.string.shared_preference_mozeh), 0);
        Gson gson = new Gson();
        String json = settings.getString("USERT_CREDINTIAL", "");
       // SettingPrametersMozeh settingPrametersMozeh;
       UsersFirTblMozeh usersFirTblMozeh =   gson.fromJson(json,UsersFirTblMozeh.class);

        return usersFirTblMozeh;

    }


}
