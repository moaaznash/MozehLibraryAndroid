package com.example.moaaznash.mozehlibraryandroid;

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

public static String getAllValues(UsersFirTblMozeh usersFirTblMozeh){
    String s = "";
//    s = "Id: "+usersFirTblMozeh.Id+" - "+"User Name: "+usersFirTblMozeh.UserName+" - "+"FullName: "+usersFirTblMozeh.FullName+" - "+"Phone: "+usersFirTblMozeh.Phone+" - "+"Email: "+usersFirTblMozeh.Email+" - "+"ProfilePicUrl: "+usersFirTblMozeh.ProfilePicUrl+" - Image Name: "+usersFirTblMozeh.ImageName+" - LoginType: "+usersFirTblMozeh.LoginType+" - Notes: "+usersFirTblMozeh.Notes;
    return s;
}
}
