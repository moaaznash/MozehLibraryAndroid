package com.example.moaaznash.mozehlibraryandroid;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.example.moaaznash.userregistrationmozeh.EditSignUpFragment;
import com.example.moaaznash.userregistrationmozeh.MainActivity;
import com.example.moaaznash.userregistrationmozeh.R;
import com.example.moaaznash.userregistrationmozeh.SignInFragment;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

/**
 * Created by moaaznash on 3/21/17.
 */

public class SetAppGlobalVariablesMozeh {


    public static void setVaribales(final Context context, final FragmentManager fragmentManager, final OnProcesResultListenerMozeh onProcesResultListenerMozeh) {
        final ProgressDialog progressDialog = ProgressDialog.show(context, "", "Loading some settings.", true);

        GlobalFunctionsMozeh.checkInternetConnection(context, new GlobalFunctionsMozeh.OnProcesResultListenerMozeh() {
            @Override
            public void onProcessCompleteMozeh(ResultSuccessMozeh resultSuccessMozeh) {
                if (resultSuccessMozeh.Success) {
                    //connection succeed

                    FirebaseAuthProcessMozeh.checkIfSignIn(new FirebaseAuthProcessMozeh.OnProcesResultListenerMozeh() {
                        @Override
                        public void onProcessCompleteMozeh(ResultSuccessMozeh resultSuccessMozeh) {
                            if (resultSuccessMozeh.Success) {
                                //       signed in

                                //Log.d("Mozeh SignIn","Success "+FirebaseAuth.getInstance().getCurrentUser().getUid());
                                UsersFirDBMozeh udb = new UsersFirDBMozeh();

                                udb.getUserInformationById(FirebaseAuth.getInstance().getCurrentUser().getUid(), new OnDataSnapResultListenerMoze() {
                                    @Override
                                    public void onSingleDataSnapShot(UsersFirTblMozeh usersFirTblMozeh) {
                                        UsersFirTblMozeh um = usersFirTblMozeh;

                                        SettingPrametersMozeh.setUserCredintial(context, um);
                                        progressDialog.dismiss();

                                        onProcesResultListenerMozeh.onProcessCompleteMozeh(new ResultSuccessMozeh(true,"Online"));
                                        // getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left).replace(R.id.fr_container, new EditSignUpFragment()).commit();

                                    }

                                    @Override
                                    public void onMultiDataSnapShot(List<UsersFirTblMozeh> usersFirTblMozehs) {

                                    }
                                });


                            } else {
                                //   not signed in
                                Log.d("Mozeh SignIn", "Failed");
                                progressDialog.dismiss();
                                onProcesResultListenerMozeh.onProcessCompleteMozeh(new ResultSuccessMozeh(false,"Online"));
                                //toggleDrawerVisibility(false);
                                // toolbar.setVisibility(View.INVISIBLE);
                                //   setDrawerState(true);

                               // fragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left).replace(R.id.fr_container, new SignInFragment()).commit();

                            }
                        }
                    });

                } else {
                    // no internet

                    //  GlobalFunctionsMozeh.showAlert(, "Check Storage", "Ok");
                    Log.d("MozehIdStorage", "Offline");
                    SettingPrametersMozeh.getUserCredintialsFromStorage(context);

                    // GlobalFunctionsMozeh.showAlert(getApplicationContext(), "Signed Out 2", "Ok");
//Log.d("MozehIdStorage",SettingPrametersMozeh.getUserCredintials().Id);
                    try {

                        if (SettingPrametersMozeh.getUserCredintials().Id != "") {
                            progressDialog.dismiss();
                            onProcesResultListenerMozeh.onProcessCompleteMozeh(new ResultSuccessMozeh(true,"Offline"));
                           // fragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left).replace(R.id.fr_container, new EditSignUpFragment()).commit();

                        } else {
                            progressDialog.dismiss();
                            onProcesResultListenerMozeh.onProcessCompleteMozeh(new ResultSuccessMozeh(false,"Offline"));
                            /*
                            GlobalFunctionsMozeh.showAlert(context, "Sorry we need internt to log you in", "Ok", new GlobalFunctionsMozeh.OnGlobalFunctionsMozehListeners() {
                                @Override
                                public void OnAlertDialogOkPressedMozeh() {
                                    android.os.Process.killProcess(android.os.Process.myPid());
                                    System.exit(1);
                                }
                            });
*/
                        }
                        Log.d("Mozeh Error", "No Error " + SettingPrametersMozeh.getUserCredintials().Id);
                    } catch (Exception e) {
                        Log.d("Mozeh Error", "Error");
                        progressDialog.dismiss();

                        GlobalFunctionsMozeh.showAlert(context, "Sorry we need internt to log you in", "Ok", new GlobalFunctionsMozeh.OnGlobalFunctionsMozehListeners() {
                            @Override
                            public void OnAlertDialogOkPressedMozeh() {
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        });


                    }

                }
            }
        });


    }

    public interface OnProcesResultListenerMozeh{
        public void onProcessCompleteMozeh(ResultSuccessMozeh resultSuccessMozeh);
    }
}
