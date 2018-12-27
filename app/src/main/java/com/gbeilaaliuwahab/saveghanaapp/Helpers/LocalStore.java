package com.gbeilaaliuwahab.saveghanaapp.Helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class LocalStore {
    Context context;
    SharedPreferences userPreference ;


    public LocalStore(Context context) {
        this.context = context;
    }



    public void saveUserObjectAsString(String userObjectString){


        userPreference = context.getSharedPreferences("USER",0);
        SharedPreferences.Editor userPreferenceEditor = userPreference.edit();
        userPreferenceEditor.putString("user_string", userObjectString);
        userPreferenceEditor.commit();
    }

    public JsonObject readUserObjectAsJson(){
        JsonObject userOBject = null;

        userPreference = context.getSharedPreferences("USER",0);
        String userString = userPreference.getString("user_string",null);
        if(userString != null){
            userOBject = (JsonObject) new JsonParser().parse(userString);
        }



        return userOBject;
    }



    public void saveTaxBracketObjectAsString(String taxBracketObjectString){


        userPreference = context.getSharedPreferences("BRACKETS",0);
        SharedPreferences.Editor userPreferenceEditor = userPreference.edit();
        userPreferenceEditor.putString("brackets_string", taxBracketObjectString);
        userPreferenceEditor.commit();
    }


    public JsonObject readBracketsObjectAsJson(){
        JsonObject userObject = null;

        userPreference = context.getSharedPreferences("BRACKETS",0);
        String userString = userPreference.getString("brackets_string",null);
        if(userString != null){
            userObject = (JsonObject) new JsonParser().parse(userString);
        }



        return userObject;
    }



    public void saveProfileAsString(String taxBracketObjectString){

        Log.e("PROFILE AT SAVE", taxBracketObjectString );
        userPreference = context.getSharedPreferences("PROFILE",0);
        SharedPreferences.Editor userPreferenceEditor = userPreference.edit();
        userPreferenceEditor.putString("profile_string", taxBracketObjectString);
        userPreferenceEditor.commit();
    }


    public JsonObject readProfileAsJson(){
        JsonObject userObject = null;

        userPreference = context.getSharedPreferences("PROFILE",0);
        String userString = userPreference.getString("profile_string",null);
        if(userString != null){
            userObject = (JsonObject) new JsonParser().parse(userString);
            Log.e("PROFILE OBJ 1 AT READ", userObject.toString() );
        }


       // Log.e("PROFILE OBJ 2 AT READ", userObject.toString() );

        return userObject;
    }



    public void saveTaxPayersAsString(String taxBracketObjectString){

        Log.e("PAYERS", taxBracketObjectString);
        userPreference = context.getSharedPreferences("PAYERS",0);
        SharedPreferences.Editor userPreferenceEditor = userPreference.edit();
        userPreferenceEditor.putString("payers_string", taxBracketObjectString);
        userPreferenceEditor.commit();
    }


    public JsonArray readTaxPayersAsJsonArray(){
        JsonArray userObject = null;

        userPreference = context.getSharedPreferences("PAYERS",0);
        String userString = userPreference.getString("payers_string",null);
        if(userString != null){
            userObject = (JsonArray) new JsonParser().parse(userString);
            Log.e("PROFILE OBJ 1 AT READ", userObject.toString() );
        }


        // Log.e("PROFILE OBJ 2 AT READ", userObject.toString() );

        return userObject;
    }


    public void objectToEdit(int position){

        JsonObject object = readTaxPayersAsJsonArray().get(position).getAsJsonObject();

        userPreference = context.getSharedPreferences("EDIT",0);
        SharedPreferences.Editor userPreferenceEditor = userPreference.edit();
        userPreferenceEditor.putString("edit_string", object.toString());
        userPreferenceEditor.commit();

        Log.e("EDIT", object.toString());

    }

    public JsonObject readPayToEditAsJson(){
        JsonObject userObject = null;

        userPreference = context.getSharedPreferences("EDIT",0);
        String userString = userPreference.getString("edit_string",null);
        if(userString != null){
            userObject = (JsonObject) new JsonParser().parse(userString);
            Log.e("EDIT 1 AT READ", userObject.toString() );
        }


        // Log.e("PROFILE OBJ 2 AT READ", userObject.toString() );

        return userObject;
    }


    public void deleteObjectToEdit(){


        userPreference = context.getSharedPreferences("EDIT",0);
        SharedPreferences.Editor userPreferenceEditor = userPreference.edit();
        userPreferenceEditor.putString("edit_string", null);
        userPreferenceEditor.commit();



    }
}
