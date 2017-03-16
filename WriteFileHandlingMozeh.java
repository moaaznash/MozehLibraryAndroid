package com.example.moaaznash.mozehlibraryandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Random;

import static com.example.moaaznash.userregistrationmozeh.R.id.imageView;

/**
 * Created by moaaznash on 3/13/17.
 */

public class WriteFileHandlingMozeh {

    public static String getAppDirectoryRoot(){

        return Environment.getExternalStorageDirectory().toString();



    }
    public static Bitmap loadImageFromInternalStorage(String fullPath){
        Bitmap bb = null;
        File imageFile = new  File(fullPath);
        if(imageFile.exists()){
            // ImageView imageView= (ImageView) findViewById(R.id.imageviewTest);
         bb = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
        }
        return bb;
    }


    public static void saveImageToInternalStorage(Bitmap finalBitmap, String fileName,OnDifferentActivitiesResultListenerMozeh onDifferentActivitiesResultListenerMozeh) {
 String dirPath =WriteFileHandlingMozeh.getAppDirectoryRoot() + SettingPrametersMozeh.getImageSavingDirectory();
        File myDir = new File(dirPath);
        myDir.mkdirs();
        Log.d("MozehDir",myDir.getAbsolutePath()+"/"+fileName);
        File file = new File(myDir, "/"+fileName);
        if (file.exists()) file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
           // resultSuccessMozeh.Success = true;
           // resultSuccessMozeh.Message = "success";
           // resultSuccessMozeh = new ResultSuccessMozeh(true,"success");
onDifferentActivitiesResultListenerMozeh.onProcessCompleteMozeh(new ResultSuccessMozeh(true,"success"));

        } catch (Exception e) {
            onDifferentActivitiesResultListenerMozeh.onProcessCompleteMozeh(new ResultSuccessMozeh(false,"failed"));

            e.printStackTrace();
        }
    }


    public static byte[] getBytesDataFromImageView(ImageView imageView){

        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache();
        final Bitmap bitmap = imageView.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        return data;
    }
   public static void getTheImage(final String fullPath, String profilePicUrl, Context cont,final OnBitmapsProcessListenerMozeh onBitmapsProcessListenerMozeh){
       Bitmap bb = null;
/*
       final String folder = "/user_reg_mozeh1";
       String dirPath = WriteFileHandlingMozeh.getAppDirectoryRoot() + folder;
       final String fileName = GlobalFunctionsMozeh.generateUniqueFileName()+".jpg";

      */

    String ful =   getAppDirectoryRoot() + SettingPrametersMozeh.getImageSavingDirectory()+"/"+fullPath;

       File file = new File(ful);
       if (file.exists()){
Log.d("MozehGetTheImage","Internal "+ful);
          bb = BitmapFactory.decodeFile(file.getAbsolutePath());
           onBitmapsProcessListenerMozeh.OnBitmapFinishloading(bb);

       }else {
          // Glide
           Log.d("MozehGetTheImage","Download");
           Glide.with(cont)
                   .load(profilePicUrl)
                   .asBitmap()
                   .into(new SimpleTarget<Bitmap>() {
                       @Override
                       public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                       //    imageView.setImageBitmap(resource);
                         //  onBitmapsProcessListenerMozeh.OnBitmapFinishloading(resource);


                           onBitmapsProcessListenerMozeh.OnBitmapFinishloading(resource);
                           //String dir = SettingPrametersMozeh.getImageSavingDirectory();
                           String fileName = fullPath;

                      saveImageToInternalStorage(resource,  fileName, new OnDifferentActivitiesResultListenerMozeh() {


                          @Override
                          public void onProcessCompleteMozeh(ResultSuccessMozeh resultSuccessMozeh) {

                          }
                      });
                       }
                   });




       }



   }


    public interface OnBitmapsProcessListenerMozeh{

        public void OnBitmapFinishloading(Bitmap bitmap);
    }

    public interface OnDifferentActivitiesResultListenerMozeh{

        //public void   OnAlertDialogOkPressedMozeh();
        public void onProcessCompleteMozeh(ResultSuccessMozeh resultSuccessMozeh);
    }


}
