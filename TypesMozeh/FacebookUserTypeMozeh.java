package com.example.moaaznash.mozehlibraryandroid.TypesMozeh;


/**
 * Created by moaaznash on 3/21/17.
 */

public class FacebookUserTypeMozeh {

  public    String Id = "";
   public String Email = "";
   public String ProfilePictureUrl = "";
    public String FirstName = "";
    public String LastName = "";


    public  FacebookUserTypeMozeh(){}


    public FacebookUserTypeMozeh(String id, String email, String profilePictureUrl,String firstName,String lastName){
        this.Id = id;
        this.Email = email;
        this.ProfilePictureUrl = profilePictureUrl;
        this.FirstName =firstName;
        this.LastName = lastName;

    }



}
