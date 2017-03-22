package com.example.moaaznash.mozehlibraryandroid;

import com.example.moaaznash.mozehlibraryandroid.TypesMozeh.FacebookUserTypeMozeh;
import com.example.moaaznash.mozehlibraryandroid.TypesMozeh.GoogleUserTypeMozeh;
import com.example.moaaznash.mozehlibraryandroid.TypesMozeh.TwitterUserTypeMozeh;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by moaaznash on 3/9/17.
 */
@IgnoreExtraProperties
public class UsersFirTblMozeh {

public String Id;


    public String UserName;

    public String FullName;

    public String Phone;

    public String ProfilePicUrl;

    public String Email;

    public String Notes;

    public String LoginType;

    public String ImageName;

    public String FacebookId;

    public String GoogleId;

    public String TwitterId;

/*public String IdFieldString = "Id";
    public String UserNameFieldString = "UserName";
    public String FullNameFieldString = "FullName";
    public String PhoneFieldString = "Phone";
    public String ProfilePicUrlFieldString = "ProfilePicUrl";
    public String EmailFieldString = "Email";
    public String NotesFieldString = "Notes";
    public String LoginTypeFieldString = "LoginType";
    public String ImageNameFieldString = "ImageName";*/

    public UsersFirTblMozeh() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public UsersFirTblMozeh(String id, String userName, String fullName, String phone, String profilePicUrl, String email, String notes, String loginType, String imageName ) {
        this.Id = id;


        this.UserName = userName;

        this.FullName = fullName;

        this.Phone = phone;

        this.ProfilePicUrl = profilePicUrl;

        this.Email = email;

        this.Notes = notes;

        this.LoginType = loginType;

        this.ImageName = imageName;



    }


    public UsersFirTblMozeh(String id, String userName, String fullName, String phone, String profilePicUrl, String email, String notes, String loginType, String imageName,String facebookId,String googleId,String twitterId ) {
        this.Id = id;


        this.UserName = userName;

        this.FullName = fullName;

        this.Phone = phone;

        this.ProfilePicUrl = profilePicUrl;

        this.Email = email;

        this.Notes = notes;

        this.LoginType = loginType;

        this.ImageName = imageName;
this.FacebookId = facebookId;
        this.GoogleId = googleId;
        this.TwitterId = twitterId;


    }

public static String getAllValues(UsersFirTblMozeh usersFirTblMozeh){
    String s = "";
//    s = "Id: "+usersFirTblMozeh.Id+" - "+"User Name: "+usersFirTblMozeh.UserName+" - "+"FullName: "+usersFirTblMozeh.FullName+" - "+"Phone: "+usersFirTblMozeh.Phone+" - "+"Email: "+usersFirTblMozeh.Email+" - "+"ProfilePicUrl: "+usersFirTblMozeh.ProfilePicUrl+" - Image Name: "+usersFirTblMozeh.ImageName+" - LoginType: "+usersFirTblMozeh.LoginType+" - Notes: "+usersFirTblMozeh.Notes;
    return s;
}


public static UsersFirTblMozeh convertFacebookUserTypeMozehtoUserFirTblMozeh(FacebookUserTypeMozeh facebookUserTypeMozeh){

    UsersFirTblMozeh usersFirTblMozeh = new UsersFirTblMozeh();

    usersFirTblMozeh.Email = facebookUserTypeMozeh.Email;
    usersFirTblMozeh.ProfilePicUrl = facebookUserTypeMozeh.ProfilePictureUrl;
    usersFirTblMozeh.Id = FirebaseAuth.getInstance().getCurrentUser().getUid();
    usersFirTblMozeh.LoginType = "FACEBOOK";
    usersFirTblMozeh.FullName = facebookUserTypeMozeh.FirstName +" "+ facebookUserTypeMozeh.LastName;
    usersFirTblMozeh.FacebookId = facebookUserTypeMozeh.Id;
    usersFirTblMozeh.ImageName = GlobalFunctionsMozeh.generateUniqueFileName()+".jpg";
    return  usersFirTblMozeh;
}

    public static UsersFirTblMozeh convertGoogleUserTypeMozehtoUserFirTblMozeh(GoogleUserTypeMozeh googleUserTypeMozeh){

        UsersFirTblMozeh usersFirTblMozeh = new UsersFirTblMozeh();

        usersFirTblMozeh.Email = googleUserTypeMozeh.Email;
        usersFirTblMozeh.ProfilePicUrl = googleUserTypeMozeh.ProfilePictureUrl;
        usersFirTblMozeh.Id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        usersFirTblMozeh.LoginType = "GOOGLE";
        usersFirTblMozeh.FullName = googleUserTypeMozeh.DisplayName;
        usersFirTblMozeh.FacebookId = googleUserTypeMozeh.Id;
        usersFirTblMozeh.ImageName = GlobalFunctionsMozeh.generateUniqueFileName()+".jpg";
        return  usersFirTblMozeh;
    }

    public static UsersFirTblMozeh convertTwitterUserTypeMozehtoUserFirTblMozeh(TwitterUserTypeMozeh twitterUserTypeMozeh){

        UsersFirTblMozeh usersFirTblMozeh = new UsersFirTblMozeh();

        usersFirTblMozeh.Email = twitterUserTypeMozeh.Email;
        usersFirTblMozeh.ProfilePicUrl = twitterUserTypeMozeh.ProfilePictureUrl;
        usersFirTblMozeh.Id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        usersFirTblMozeh.LoginType = "TWITTER";
        usersFirTblMozeh.FullName = twitterUserTypeMozeh.DisplayName;
        usersFirTblMozeh.FacebookId = twitterUserTypeMozeh.Id;
        usersFirTblMozeh.ImageName = GlobalFunctionsMozeh.generateUniqueFileName()+".jpg";
        return  usersFirTblMozeh;
    }

}
