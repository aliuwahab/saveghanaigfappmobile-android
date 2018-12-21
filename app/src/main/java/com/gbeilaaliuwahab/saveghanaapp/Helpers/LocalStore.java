package com.gbeilaaliuwahab.saveghanaapp.Helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class LocalStore {
    Context context;
    SharedPreferences userPreference = context.getSharedPreferences("USER",0);


    public LocalStore(Context context) {
        this.context = context;
    }



    public void saveUserObjectAsString(String userObjectString){


        SharedPreferences.Editor userPreferenceEditor = userPreference.edit();
        userPreferenceEditor.putString("user_string", userObjectString);
        userPreferenceEditor.commit();
    }

    public JsonObject readUserObjectAsJson(){
        JsonObject userOBject = null;
        String userString = userPreference.getString("user_string",null);
        if(userString != null){
            userOBject = (JsonObject) new JsonParser().parse(userString);
        }



        return userOBject;
    }
}
