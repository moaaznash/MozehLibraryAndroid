package com.example.moaaznash.mozehlibraryandroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
//import android.content;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.moaaznash.userregistrationmozeh.MainActivity;
import com.example.moaaznash.userregistrationmozeh.R;
import com.example.moaaznash.userregistrationmozeh.SignUpFragment;

import java.lang.reflect.Field;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.Random;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

/**
 * Created by moaaznash on 3/12/17.
 */

public class GlobalFunctionsMozeh  {

    public static Boolean validateEmail(String testStr) {
        //  let emailFormat = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}"
        //let emailPredicate = NSPredicate(format:"SELF MATCHES %@", emailFormat)
        //  return emailPredicate.evaluate(with: testStr)

        return !TextUtils.isEmpty(testStr) && android.util.Patterns.EMAIL_ADDRESS.matcher(testStr).matches();

    }


    public static ResultSuccessMozeh validateEmailSignUpForm(String fullName, String email, String password, String confirmPassword, String profilePicUrl, String imageName) {
        ResultSuccessMozeh res = new ResultSuccessMozeh(true, "");
        String message = "";
        Boolean valid = validateEmail(email);

        if (Objects.equals(fullName, "")){
            message = "يرجى ملأ حقل الأسم";
            res = new ResultSuccessMozeh(false, message);
            return res;

        }
        if (valid) {

        } else {
            //   completeHandler(true,"بريد الكتروني غير صحيح يرجى اعادة المحاولة")
            message = "بريد الكتروني غير صحيح يرجى اعادة المحاولة";

            res = new ResultSuccessMozeh(false, message);
            return res;
        }

        if (Objects.equals(email, "")) {

            message = "يرجى ملأ حقل البريد الالكتروني";
            res = new ResultSuccessMozeh(false, message);
            return res;
        }

        if (!Objects.equals(password, "") && !Objects.equals(confirmPassword, "")) {

            if(Objects.equals(password, confirmPassword)) {

            } else {
                message = "كلمة السر وتأكيد كلمة السر غير متطابقة يرجى المحاولة مجدداً";
               // message = password + confirmPassword;
                res = new ResultSuccessMozeh(false, message);
                return res;
            }
        } else {
            message = "يرجى التأكد من ملأ كلمة السر وتأكيد كلمة السر للمتابعة";
            res = new ResultSuccessMozeh(false, message);
            return res;
        }
        int passeln = password.length();


        if (passeln < 6) {
            message = "كلمة السر يجب ان تكون اكثر من ٦ حروف وأرقام";
            res = new ResultSuccessMozeh(false, message);
            return res;
        }

        if (profilePicUrl == null ) {

            message = "يرجى ملأ اختيار صورة";
            res = new ResultSuccessMozeh(false, message);
            return res;
        }

        if (imageName == null) {

            message = "يرجى ملأ اختيار صورة";
            res = new ResultSuccessMozeh(false, message);
            return res;
        }
        return res;

    }


    public static String printClassFields(Context context) {
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        result.append( context.getClass().getName() );
        result.append( " Object {" );
        result.append(newLine);

        //determine fields declared in this class only (no fields of superclass)
        Field[] fields = context.getClass().getDeclaredFields();

        //print field names paired with their values
        for ( Field field : fields  ) {
            result.append("  ");
            try {
                result.append( field.getName() );
                result.append(": ");
                //requires access to private field:
                result.append( field.get(context) );
            } catch ( IllegalAccessException ex ) {
                System.out.println(ex);
            }
            result.append(newLine);
        }
        result.append("}");

        return result.toString();
    }

    public static String getTextSplitByChar(String splitCharacter,String text,Boolean begain_end){

        String str = text;
        String newstr = "";

        if (null != str && str.length() > 0 )
        {
            int endIndex = str.lastIndexOf(splitCharacter);
            if (endIndex != -1)
            {

              if(begain_end) {
                   newstr = str.substring(0, endIndex); // not forgot to put check if(endIndex != -1)
              }else {
                  newstr = str.substring(endIndex + 1);
              }
              //  Log.d("Mozeh", newstr1);
            }
        }
        return newstr;

    }
public static void showAlert(Context cont,String message, String okButton){

    AlertDialog.Builder builder = new AlertDialog.Builder(cont);
    builder.setTitle("");
    builder.setMessage(message);
    builder.setPositiveButton(okButton, new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int id) {
            // You don't have to do anything here if you just want it dismissed when clicked
        }
    });
    builder.create();
    builder.show();

}

    public static void showAlert(Context cont, String message, String okButton, final OnGlobalFunctionsMozehListeners onGlobalFunctionsMozehListeners){

        AlertDialog.Builder builder = new AlertDialog.Builder(cont);
        builder.setTitle("");
        builder.setMessage(message);
        builder.setPositiveButton(okButton, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // You don't have to do anything here if you just want it dismissed when clicked
onGlobalFunctionsMozehListeners.OnAlertDialogOkPressedMozeh();
            }
        });
        builder.create();
        builder.show();

    }

    public static String getCurrentDateAsLongInt(){

        // get the supported ids for GMT-08:00 (Pacific Standard Time)
        String[] ids = TimeZone.getAvailableIDs(-8 * 60 * 60 * 1000);
        // if no ids were returned, something is wrong. get out.
        if (ids.length == 0)
            System.exit(0);

        // begin output
        System.out.println("Current Time");

        // create a Pacific Standard Time time zone
        SimpleTimeZone pdt = new SimpleTimeZone(-8 * 60 * 60 * 1000, ids[0]);

        // set up rules for Daylight Saving Time
        pdt.setStartRule(Calendar.APRIL, 1, Calendar.SUNDAY, 2 * 60 * 60 * 1000);
        pdt.setEndRule(Calendar.OCTOBER, -1, Calendar.SUNDAY, 2 * 60 * 60 * 1000);

        // create a GregorianCalendar with the Pacific Daylight time zone
        // and the current date and time
        Calendar calendar = new GregorianCalendar(pdt);
        Date trialTime = new Date();
        calendar.setTime(trialTime);

        String date = String.valueOf(calendar.get(Calendar.YEAR)) + String.valueOf(calendar.get(Calendar.MONTH))+String.valueOf(calendar.get(Calendar.DAY_OF_MONTH))+String.valueOf(calendar.get(Calendar.HOUR))+String.valueOf(calendar.get(Calendar.MINUTE))+String.valueOf(calendar.get(Calendar.SECOND))+String.valueOf(calendar.get(Calendar.MILLISECOND));

Log.d("MozehDate",String.valueOf(date));
        return String.valueOf(date);
    }
public static String generateUniqueFileName(){
  return generateRanomNumber(11,99) + getCurrentDateAsLongInt();


}
    //private static final String ALLOWED_CHARACTERS ="0123456789qwertyuiopasdfghjklzxcvbnm";

    public static String generateRandomString(final int sizeOfRandomString)
    {
        String allowed_char ="0123456789qwertyuiopasdfghjklzxcvbnm";
        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(sizeOfRandomString);
        for(int i=0;i<sizeOfRandomString;++i)
            sb.append(allowed_char.charAt(random.nextInt(allowed_char.length())));
        return sb.toString();
    }

    public static  String generateRanomNumber(int min,int max){

        Random r = new Random();
        int i1 = r.nextInt(max - min + 1);

        return String.valueOf(i1);
    }


    public interface OnGlobalFunctionsMozehListeners{

        public void   OnAlertDialogOkPressedMozeh();
       // public void onProcessCompleteMozeh(ResultSuccessMozeh resultSuccessMozeh);
    }

    /**
     * Mozeh
     * Need Permission
     * <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

     * @return
     */
    public static void checkInternetConnection(Context cont, final OnProcesResultListenerMozeh onProcesResultListenerMozeh) {
      //  onProcesResultListenerMozeh.onProcessCompleteMozeh(new ResultSuccessMozeh(true,""));
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(cont);
        String url ="http://www.google.com";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                      Log.d("Mozeh","Response is: "+ response.substring(0,500));
                   onProcesResultListenerMozeh.onProcessCompleteMozeh(new ResultSuccessMozeh(true,""));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.d("Mozeh","That didn't work!");
                onProcesResultListenerMozeh.onProcessCompleteMozeh(new ResultSuccessMozeh(false,""));

            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

public interface OnProcesResultListenerMozeh{
    public void onProcessCompleteMozeh(ResultSuccessMozeh resultSuccessMozeh);
}


    public static void checkIfSignedIn(FragmentManager fragmentManager) {
        try {
            if (SettingPrametersMozeh.getUserCredintials().Id != "") {
                // Log.d("MozehCheckIfSignedIn", SettingPrametersMozeh.getUserCredintials().Id);


               // GlobalFunctionsMozeh.showAlert(fragmentActivity.getApplicationContext(), "Signed In", "Ok");
            } else {

               fragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left).replace(R.id.fr_container, new SignUpFragment()).commit();


               // GlobalFunctionsMozeh.showAlert(fragmentActivity.getApplicationContext(), "Signed Out", "Ok");
            }

        }catch (Exception e){

            fragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left).replace(R.id.fr_container, new SignUpFragment()).commit();

          //  GlobalFunctionsMozeh.showAlert(fragmentActivity.getApplicationContext(), "Signed Out", "Ok");

        }
    }

/*
    private void checkIfSignedInActivity(Activity activity) {
        try {
            if (SettingPrametersMozeh.getUserCredintials().Id != "") {
                // Log.d("MozehCheckIfSignedIn", SettingPrametersMozeh.getUserCredintials().Id);


                GlobalFunctionsMozeh.showAlert(activity.getApplicationContext(), "Signed In", "Ok");
            } else {


                MainActivity.this.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left).replace(R.id.fr_container, new SignUpFragment()).commit();


                GlobalFunctionsMozeh.showAlert(activity.getApplicationContext(), "Signed Out", "Ok");
            }

        }catch (Exception e){

            activity.this.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left).replace(R.id.fr_container, new SignUpFragment()).commit();

            GlobalFunctionsMozeh.showAlert(activity.getApplicationContext(), "Signed Out", "Ok");

        }
    }
*/

}
