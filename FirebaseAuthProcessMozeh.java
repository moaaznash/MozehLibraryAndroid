package com.example.moaaznash.mozehlibraryandroid;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.Toast;

import com.example.moaaznash.userregistrationmozeh.EditSignUpFragment;
import com.example.moaaznash.userregistrationmozeh.R;
import com.example.moaaznash.userregistrationmozeh.SignInFragment;
import com.example.moaaznash.userregistrationmozeh.SignUpFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by moaaznash on 3/15/17.
 */

public class FirebaseAuthProcessMozeh {


    private static FirebaseAuth mAuth = FirebaseAuth.getInstance();;

    private static FirebaseAuth.AuthStateListener mAuthListener;


    public static void checkIfSignIn1(OnProcesResultListenerMozeh onProcesResultListenerMozeh){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
//user signed in
            onProcesResultListenerMozeh.onProcessCompleteMozeh(new ResultSuccessMozeh(true,""));
        }else{
         //user not Signed in
            onProcesResultListenerMozeh.onProcessCompleteMozeh(new ResultSuccessMozeh(false,""));
        }


    }

    public static void checkIfSignIn(final Context cont, final FragmentManager fragmentManager) {
        mAuth = FirebaseAuth.getInstance();


        GlobalFunctionsMozeh.checkInternetConnection(cont, new GlobalFunctionsMozeh.OnProcesResultListenerMozeh() {
            @Override
            public void onProcessCompleteMozeh(ResultSuccessMozeh resultSuccessMozeh) {
                if (resultSuccessMozeh.Success) {

                    mAuthListener = new FirebaseAuth.AuthStateListener() {
                        @Override
                        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            if (user != null) {
                                // User is signed in
                                //final UsersFirTblMozeh um;
                                UsersFirDBMozeh udb = new UsersFirDBMozeh();

                                udb.getUserInformationById(user.getUid(), new OnDataSnapResultListenerMoze() {
                                    @Override
                                    public void onSingleDataSnapShot(UsersFirTblMozeh usersFirTblMozeh) {
                                        UsersFirTblMozeh um = usersFirTblMozeh;

                                        SettingPrametersMozeh.setUserCredintial(cont, um);
                                        fragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left).replace(R.id.fr_container, new EditSignUpFragment()).commit();

                                    }

                                    @Override
                                    public void onMultiDataSnapShot(List<UsersFirTblMozeh> usersFirTblMozehs) {

                                    }
                                });

                                //UsersFirTblMozeh um = new UsersFirTblMozeh(user.getUid(), "", "", "", "", "", "", "", "");

                                // GlobalFunctionsMozeh.checkIfSignedIn(fragmentManager);
                                GlobalFunctionsMozeh.showAlert(cont, "Signed In", "Ok");

                                Log.d("MozehFire", "onAuthStateChanged:signed_in:" + user.getUid());
                            } else {
                                // GlobalFunctionsMozeh.checkIfSignedIn(fragmentManager);
                                GlobalFunctionsMozeh.showAlert(cont, "Signed Out 1", "Ok");
                                fragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left).replace(R.id.fr_container, new SignInFragment()).commit();

                                // User is signed out
                                Log.d("MozehFire", "onAuthStateChanged:signed_out");
                            }
                            // ...
                        }
                    };
                    mAuth.addAuthStateListener(mAuthListener);
                    // Log.d("Mozeh","Success");
                } else {
                    GlobalFunctionsMozeh.showAlert(cont, "Check Storage", "Ok");
                    SettingPrametersMozeh.getUserCredintialsFromStorage(cont);
                    GlobalFunctionsMozeh.showAlert(cont, "Signed Out 2", "Ok");
                    fragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left).replace(R.id.fr_container, new EditSignUpFragment()).commit();

                    try {
                        if (SettingPrametersMozeh.getUserCredintials().Id != "") {


                        }

                    } catch (Exception e) {
                        fragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left).replace(R.id.fr_container, new SignInFragment()).commit();


                    }

                    Log.d("Mozeh", "Faled");
                }
            }
        });


    }


    public static void signIn(Activity activity, String email, String password, final OnProcesResultListenerMozeh onProcesResultListenerMozeh) {
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Mozeh", "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w("Mozeh", "signInWithEmail:failed", task.getException());
                            //  Toast.makeText(EmailPasswordActivity.this, R.string.auth_failed,
                            //Toast.LENGTH_SHORT).show();
                            onProcesResultListenerMozeh.onProcessCompleteMozeh(new ResultSuccessMozeh(false,""));
                        } else {
                            onProcesResultListenerMozeh.onProcessCompleteMozeh(new ResultSuccessMozeh(true,""));


                        }

                        // ...
                    }
                });


    }

    public interface OnProcesResultListenerMozeh {
        public void onProcessCompleteMozeh(ResultSuccessMozeh resultSuccessMozeh);
    }

    public static void updateFirebaseUser(String displayName, String profilePicture) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(displayName)
                .setPhotoUri(Uri.parse(profilePicture))
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("MozehUpdate User", "User profile updated.");
                        }
                    }
                });
    }

    public static void updateEmail(Activity activity, final String email,final String newEmail, String password, final OnProcesResultListenerMozeh onProcesResultListenerMozeh) {
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Mozeh", "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w("Mozeh", "signInWithEmail:failed", task.getException());
                            //  Toast.makeText(EmailPasswordActivity.this, R.string.auth_failed,
                            //Toast.LENGTH_SHORT).show();
                            //onProcesResultListenerMozeh.onProcessCompleteMozeh(new ResultSuccessMozeh(false,""));
                        } else {
                            //onProcesResultListenerMozeh.onProcessCompleteMozeh(new ResultSuccessMozeh(true,""));
                            user.updateEmail(newEmail)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.d("Mozeh", "User email address updated.");
                                            onProcesResultListenerMozeh.onProcessCompleteMozeh(new ResultSuccessMozeh(true,""));
                                            }else{
                                                onProcesResultListenerMozeh.onProcessCompleteMozeh(new ResultSuccessMozeh(false,""));


                                            }
                                        }
                                    });

                        }

                        // ...
                    }
                });


    }



    public static void updatePassword(Activity activity, final String email, final String oldPassword, final String newPassword, final OnProcesResultListenerMozeh onProcesResultListenerMozeh) {
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        mAuth.signInWithEmailAndPassword(email, oldPassword)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Mozeh", "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w("Mozeh", "signInWithEmail:failed", task.getException());
                            //  Toast.makeText(EmailPasswordActivity.this, R.string.auth_failed,
                            //Toast.LENGTH_SHORT).show();
                            //onProcesResultListenerMozeh.onProcessCompleteMozeh(new ResultSuccessMozeh(false,""));
                        } else {
                            //onProcesResultListenerMozeh.onProcessCompleteMozeh(new ResultSuccessMozeh(true,""));
                            user.updatePassword(newPassword)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.d("Mozeh", "User Password updated.");
                                                onProcesResultListenerMozeh.onProcessCompleteMozeh(new ResultSuccessMozeh(true,""));
                                            }else{
                                                onProcesResultListenerMozeh.onProcessCompleteMozeh(new ResultSuccessMozeh(false,""));


                                            }
                                        }
                                    });

                        }

                        // ...
                    }
                });


    }

}
