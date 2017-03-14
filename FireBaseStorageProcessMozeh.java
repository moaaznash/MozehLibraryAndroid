package com.example.moaaznash.mozehlibraryandroid;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

/**
 * Created by moaaznash on 3/13/17.
 */

public class FireBaseStorageProcessMozeh {

    public static void uploadPicToFirbaseStorage(byte[] data, String pictureName, String picturDirFirStorage, StorageReference storageReference, final OnFireBaseProcessListenerMozeh onFireBaseProcessListenerMozeh) {

        StorageReference stRef = storageReference.child(picturDirFirStorage + pictureName);
        UploadTask uploadTask = stRef.putBytes(data);


        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Uri downloadUrl = taskSnapshot.getDownloadUrl();
             onFireBaseProcessListenerMozeh.onUploadComplete(downloadUrl.toString());
                //OnFireBaseProcessListenerMoze


            }
        });

    }

    public interface OnFireBaseProcessListenerMozeh{

        public void onUploadComplete(String downloadUrl);
    }
}
