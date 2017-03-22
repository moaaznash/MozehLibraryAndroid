package com.example.moaaznash.mozehlibraryandroid.TypesMozeh;

/**
 * Created by moaaznash on 3/21/17.
 */

public class TwitterUserTypeMozeh {
    public    String Id = "";
    public String Email = "";
    public String ProfilePictureUrl = "";
    public String DisplayName = "";



    public  TwitterUserTypeMozeh(){}


    public TwitterUserTypeMozeh(String id, String email, String profilePictureUrl, String displayName){
        this.Id = id;
        this.Email = email;
        this.ProfilePictureUrl = profilePictureUrl;
        this.DisplayName = displayName;


    }
}
