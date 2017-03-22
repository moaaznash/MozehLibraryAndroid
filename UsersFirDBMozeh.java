package com.example.moaaznash.mozehlibraryandroid;

import android.util.Log;
import android.util.StringBuilderPrinter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by moaaznash on 3/9/17.
 */

public class UsersFirDBMozeh {

    private DatabaseReference mDatabase;
    private UsersFirTblMozeh user;
    //private OnDataSnapResultListenerMoze onDataSnapResultListenerMoze;
// ...

    public UsersFirDBMozeh()

    {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        //this.onDataSnapResultListenerMoze = onDataSnapResultListenerMoze;
    }


    public Boolean createOrUpdateUserInformationsFirebaseMozeh(UsersFirTblMozeh usersFirTblMozeh) {

        this.mDatabase.child("UsersInformations").child(usersFirTblMozeh.Id).setValue(usersFirTblMozeh);

        return true;
    }

    public Boolean updateUserInformationsFirebaseByIdMozeh(UsersFirTblMozeh usersFirTblMozeh) {

        this.mDatabase.child("UsersInformations").child(usersFirTblMozeh.Id).setValue(usersFirTblMozeh);

        return true;
    }
    public Boolean removeUserInformationsFirebaseByIdMozeh(UsersFirTblMozeh usersFirTblMozeh) {

        this.mDatabase.child("UsersInformations").child(usersFirTblMozeh.Id).setValue(null);

        return true;
    }
    public void getAllUserInformation(final OnDataSnapResultListenerMoze onDataSnapResultListenerMoze) {

        final List<UsersFirTblMozeh> users = new ArrayList<>();

        this.mDatabase.child("UsersInformations").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                users.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    UsersFirTblMozeh ff = postSnapshot.getValue(UsersFirTblMozeh.class);
                    users.add(ff);

                }
                onDataSnapResultListenerMoze.onMultiDataSnapShot(users);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    public void getUserInformationById(String id, final OnDataSnapResultListenerMoze onDataSnapResultListenerMoze) {
        // final List<UsersFirTblMozeh> users = new ArrayList<>();

        this.mDatabase.child("UsersInformations").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            //UsersFirTblMozeh user = new UsersFirTblMozeh();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UsersFirTblMozeh user = dataSnapshot.getValue(UsersFirTblMozeh.class);

                onDataSnapResultListenerMoze.onSingleDataSnapShot(user);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //return users;
    }


}

