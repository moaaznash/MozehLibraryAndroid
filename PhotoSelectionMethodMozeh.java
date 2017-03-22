package com.example.moaaznash.mozehlibraryandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.moaaznash.mozehlibraryandroid.TypesMozeh.ImageTypeMozeh;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;

/**
 * Created by moaaznash on 3/20/17.
 * This class needs imageView on the fragment or the activity and define onActivityResult() on the fragment or the activity
 *
 * 1-
 * PhotoSelectionMethodMozeh photoSelectionMethodMozeh = new PhotoSelectionMethodMozeh(getActivity());
 *
 * 2- Parameteres: Activity _activity , Boolean _uploadToFireStorage , Boolean _saveOnLocalDisk
 * PhotoSelectionMethodMozeh photoSelectionMethodMozeh = new PhotoSelectionMethodMozeh(getActivity(),true,true);
 *
 * 3- Parameteres: Activity _activity , Boolean _uploadToFireStorage , Boolean _saveOnLocalDisk, String _outSourceImageLink
 * PhotoSelectionMethodMozeh photoSelectionMethodMozeh = new PhotoSelectionMethodMozeh(getActivity(),true,true,"http://www.photo.com");

  Usage

 public class anything {

 public anything() {
 photoSelectionMethodMozeh = new PhotoSelectionMethodMozeh(getActivity());
 imageView = (ImageView) view.findViewById(R.id.imageView);
 Button but;
 but.setOnClickListener(new View.OnClickListener() {
@Override public void onClick(View v) {
photoSelectionMethodMozeh.startPhotoLibs(imageView);
}
});
 }

 @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
 super.onActivityResult(requestCode, resultCode, data);


 photoSelectionMethodMozeh.onActivityResult(requestCode, resultCode, data, new PhotoSelectionMethodMozeh.OnActivityListenerMozeh() {
 @Override public void onPickPhotoComplete(String downloadUrl, String fileName) {
 // Log.d("MozehTest1",downloadUrl+" - "+fileName);
 usersFirTblMozeh.ProfilePicUrl = downloadUrl;
 usersFirTblMozeh.ImageName = fileName;
 }
 });


 }
 * }
 */

public class PhotoSelectionMethodMozeh {
    ImageView imageView;
    private Bitmap bitmap;

    Boolean downloadComplete = false;
    Boolean saveComplete = false;

    Boolean uploadToFireStorage = true;
    Boolean saveOnLocalDisk = true;

    String downloadUrlLink = "";
    String fileNameGlob = "";
    String outSourceImageLink = "";
    Activity activity;
    Context context;
    ImageTypeMozeh defaultImage;
    StorageReference storageRef = FirebaseStorage.getInstance().getReference();

    public PhotoSelectionMethodMozeh(Activity _activiy, Context _context, ImageTypeMozeh _defaultImage, ImageView _imageView){
        this.activity = _activiy;
        this.defaultImage = _defaultImage;
        this.imageView = _imageView;
setImageViewPicture(_context,_defaultImage);


    }
    public PhotoSelectionMethodMozeh(Activity _activiy, Context _context, ImageTypeMozeh _defaultImage ,Boolean _uploadToFireStorage,Boolean _saveOnLocalDisk, ImageView _imageView){
        this.activity = _activiy;
this.uploadToFireStorage = _uploadToFireStorage;
        this.saveOnLocalDisk = _saveOnLocalDisk;
        this.defaultImage = _defaultImage;
        this.imageView = _imageView;
        setImageViewPicture(_context,_defaultImage);
    }
    public PhotoSelectionMethodMozeh(Activity _activiy, Context _context, ImageTypeMozeh _defaultImage,Boolean _uploadToFireStorage,Boolean _saveOnLocalDisk,String _outSourceImageLink, ImageView _imageView){
        this.activity = _activiy;
        this.uploadToFireStorage = _uploadToFireStorage;
        this.saveOnLocalDisk = _saveOnLocalDisk;
        this.outSourceImageLink = _outSourceImageLink;
        this.defaultImage = _defaultImage;
        this.imageView = _imageView;
        setImageViewPicture(_context,_defaultImage);
    }


    public void setImageViewPicture(Context _context, ImageTypeMozeh _defaultImage){
Log.d("MozehPointer",_defaultImage.ImageUrl+"sss");

        if(defaultImage.ImageName != "" || defaultImage.ImageUrl != "") {
    WriteFileHandlingMozeh.getTheImage(_defaultImage.ImageName, _defaultImage.ImageUrl, _context, new WriteFileHandlingMozeh.OnBitmapsProcessListenerMozeh() {
        @Override
        public void OnBitmapFinishloading(Bitmap bitmap) {
          //  Log.d("MozehPointer",bitmap+"sss");

            imageView.setImageBitmap(bitmap);
        }
    });
}
    }

    public void startPhotoLibs(ImageView _image_view) {


        imageView = _image_view;
        // Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        // getIntent.setType("image/*");

        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        // Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
        //chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});


        activity.startActivityForResult(pickIntent, 7764);
    }

    public void startCamera() {

        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        activity.startActivityForResult(cameraIntent, 2413);

    }




    private void pictureProcess(final PhotoSelectionMethodMozeh.OnActivityListenerMozeh onActivityListenerMozeh) {
        final String fileName = GlobalFunctionsMozeh.generateUniqueFileName() + ".jpg";


        byte[] data11 = WriteFileHandlingMozeh.getBytesDataFromImageView(imageView);

        if(uploadToFireStorage) {
            FireBaseStorageProcessMozeh.uploadPicToFirbaseStorage(data11, fileName, "", storageRef, new FireBaseStorageProcessMozeh.OnFireBaseProcessListenerMozeh() {
                @Override
                public void onUploadComplete(String downloadUrl) {
                    Log.d("MozehDownloadurl", downloadUrl);
                    downloadComplete = true;
                    downloadUrlLink = downloadUrl;
                    if (downloadComplete == true && saveComplete == true) {

                        onActivityListenerMozeh.onPickPhotoComplete(downloadUrlLink, fileNameGlob);
                    }
                    //usersFirTblMozeh.ProfilePicUrl = downloadUrl;
                }
            });
        }else{
            downloadComplete = true;
            downloadUrlLink = outSourceImageLink;
            if (downloadComplete == true && saveComplete == true) {

                onActivityListenerMozeh.onPickPhotoComplete(downloadUrlLink, fileNameGlob);
            }
        }

        if(saveOnLocalDisk) {
            final String folder = SettingPrametersMozeh.getImageSavingDirectory();
            // String dirPath = WriteFileHandlingMozeh.getAppDirectoryRoot() + folder;


            WriteFileHandlingMozeh.saveImageToInternalStorage(bitmap, fileName, new WriteFileHandlingMozeh.OnDifferentActivitiesResultListenerMozeh() {
                @Override
                public void onProcessCompleteMozeh(ResultSuccessMozeh resultSuccessMozeh) {
                    if (resultSuccessMozeh.Success) {


                        saveComplete = true;
                        fileNameGlob = fileName;
                        if (downloadComplete == true && saveComplete == true) {

                            onActivityListenerMozeh.onPickPhotoComplete(downloadUrlLink, fileNameGlob);
                        }
                        // GlobalFunctionsMozeh.showAlert(getContext(),"Success","Ok");
                        // usersFirTblMozeh.ImageName = fileName;
                        //  Log.d("MozehImageName", usersFirTblMozeh.ImageName);

                    }
                }
            });
        }else{
            saveComplete = true;
            fileNameGlob = "";
            if (downloadComplete == true && saveComplete == true) {

                onActivityListenerMozeh.onPickPhotoComplete(downloadUrlLink, fileNameGlob);
            }

        }
    }




    public void onActivityResult(int requestCode, int resultCode, Intent data, final PhotoSelectionMethodMozeh.OnActivityListenerMozeh onActivityListenerMozeh) {
        //super.onActivityResult(requestCode, resultCode, data);



        InputStream stream = null;


        if (requestCode == 7764 && resultCode == RESULT_OK) {
            try {
                if (bitmap != null) {
                    bitmap.recycle();
                }
                stream = activity.getApplicationContext().getContentResolver().openInputStream(data.getData());
                bitmap = BitmapFactory.decodeStream(stream);

                imageView.setImageBitmap(bitmap);

                pictureProcess(new PhotoSelectionMethodMozeh.OnActivityListenerMozeh() {
                    @Override
                    public void onPickPhotoComplete(String downloadUrl, String fileName) {
                        onActivityListenerMozeh.onPickPhotoComplete(downloadUrl,fileName);
                    }
                });

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (stream != null)
                    try {
                        stream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }

        } else if (requestCode == 2413 && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);

            pictureProcess(new PhotoSelectionMethodMozeh.OnActivityListenerMozeh() {
                @Override
                public void onPickPhotoComplete(String downloadUrl, String fileName) {
                    onActivityListenerMozeh.onPickPhotoComplete(downloadUrl,fileName);
                }
            });
        }


    }

    public interface OnActivityListenerMozeh{

        public void onPickPhotoComplete(String downloadUrl,String fileName);


    }

}
