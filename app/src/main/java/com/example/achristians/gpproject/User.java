package com.example.achristians.gpproject;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;


//Changing this file so Git will decide that my version is up to date

@IgnoreExtraProperties
public class User {

    public static void MockUser(){
        loggedIn = new User("TestUser",new HashMap<String, String>(), new HashMap<String, String>());
        loggedIn.setCurrent_UID("UserIdentifier");
    }

    private static User loggedIn;

    public static User getUser(){
        if (loggedIn == null){
            loggedIn = new User("", new HashMap<String, String>(), new HashMap<String, String>());
            loggedIn.setCurrent_UID("");
        }
        return loggedIn;
    }
    public static void setUser(User u){ loggedIn = u;}

    public User(){
        //Default no-args constructor is required for firebase RT DB usage.
    }

    public User(String Identifier, HashMap<String, String> Courses_Completed, HashMap<String, String> Courses_Registered){
        this.Identifier = Identifier;
        this.Courses_Completed = Courses_Completed;
        this.Courses_Registered = Courses_Registered;
    }


    private String UID;
    private String Identifier;
    private HashMap<String, String> Courses_Completed;
    private HashMap<String, String> Courses_Registered;

    public HashMap<String, String> getCompleted() { return loggedIn.Courses_Completed; }
    public void setCourses_Completed(HashMap<String, String> CC){  if(CC != null) loggedIn.Courses_Completed = CC; }

    public HashMap<String, String> getRegistered() { return loggedIn.Courses_Registered; }
    public void setCourses_Registered(HashMap<String, String> CR){ if(CR != null) loggedIn.Courses_Registered = CR;}

    public String getCurrent_Identifier() {
        return loggedIn.Identifier;
    }
    public void setCurrent_Identifier(String id){ if(id != null) loggedIn.Identifier = id; }

    public String getCurrent_UID() { return loggedIn.UID; }
    public void setCurrent_UID(String uid){ if(uid != null) loggedIn.UID = uid; }

    public static void deleteUserInfo(){
        if(loggedIn != null && loggedIn.getCurrent_UID() != null && loggedIn.getCurrent_UID().compareTo("") != 0) {
            firebaseDB.dbInterface.getRootDataReference().child("Users").child(User.getUser().getCurrent_UID()).removeValue();
        }
    }
}
