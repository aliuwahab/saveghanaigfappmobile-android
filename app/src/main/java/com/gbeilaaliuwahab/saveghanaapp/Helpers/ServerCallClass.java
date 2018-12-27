package com.gbeilaaliuwahab.saveghanaapp.Helpers;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.gbeilaaliuwahab.saveghanaapp.LoginActivity;
import com.gbeilaaliuwahab.saveghanaapp.MainActivity;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class ServerCallClass {
    Context context;

    public ServerCallClass(Context context) {
        this.context = context;
    }

    public JsonObject registerTaxPayers(JsonObject localData, String name,String contact_person,String contact_number,

                                        String contact_email, String description, String location_name, String location_address
                                        , String tax_bracket_id
    ){
        JsonObject serverResults = null;
       GPSLocationData location =  new GPSLocationData(context);

        Ion.with(this.context)
                .load(URLs.REGISTER_TAX_PAYER)
                .setMultipartParameter("username", localData.get("revenue_collector_username").getAsString())
                .setMultipartParameter("tax_bracket_id", tax_bracket_id)
                .setMultipartParameter("validation_token", localData.get("token").getAsString())
                .setMultipartParameter("created_by", localData.get("id").getAsString())
                .setMultipartParameter("district_id", localData.get("district_id").getAsString())
                .setMultipartParameter("tax_payer_name", name)
                .setMultipartParameter("tax_payer_contact_person_full_name", contact_person)
                .setMultipartParameter("tax_payer_contact_person_phone_number", contact_number)
                .setMultipartParameter("tax_payer_contact_person_email", contact_email)
                .setMultipartParameter("tax_payer_description", description)
                .setMultipartParameter("tax_payer_location_name", location_name)
                .setMultipartParameter("tax_payer_location_address", location_address)
                .setMultipartParameter("tax_payer_location_latitude", String.valueOf(location.latitude))
                .setMultipartParameter("tax_payer_location_longitude", String.valueOf(location.longitude))
                .setMultipartParameter("tax_payer_location_revenue_collection_point_id", localData.get("revenue_collection_point_id").getAsString())
                .setMultipartParameter("tax_payer_assign_revenue_collector_id", localData.get("id").getAsString())
                .setMultipartParameter("tax_bracket_id", tax_bracket_id)
                .setMultipartParameter("area_council_id", localData.get("area_council_id").getAsString())
                .setMultipartParameter("region_id", localData.get("region_id").getAsString())
                .setMultipartParameter("created_user_type", "revenue-collector")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        // do stuff with the result or error
                       // Log.e("TAX PAYER", result.toString());

                        if(result != null){
                            Log.e("TAX PAYER", result.toString());
                            Toast.makeText(context, result.toString(), Toast.LENGTH_LONG).show();

//                            if (result.get("status").getAsString().equalsIgnoreCase("Success")){
////                                new LocalStore(LoginActivity.this).saveUserObjectAsString(
////                                        result.get("data").getAsJsonObject().toString());
////                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
//
//
//
//                            }

                        }
                        else {
                            Log.e("TAX PAYER", e.toString());
                        }
                    }
                });
        return serverResults;
    }

    public JsonObject getDistrictTaxBrackets(JsonObject localData){
        JsonObject serverResults = null;

        Ion.with(this.context)
                .load(URLs.GET_DISTRICT_TAX_BRACKETS)
                .setMultipartParameter("username", localData.get("revenue_collector_username").getAsString())
                .setMultipartParameter("validation_token", localData.get("token").getAsString())
                .setMultipartParameter("district_id", localData.get("id").getAsString())

                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        // do stuff with the result or error
                        // Log.e("TAX PAYER", result.toString());

                        if(result != null){
                             Log.e("TAX BRACKETS", result.toString());
                            if (result.get("status").getAsString().equalsIgnoreCase("Success")){

                                new LocalStore(context).saveTaxBracketObjectAsString(result.get("data").getAsJsonObject(

                                ).get("district").getAsJsonObject().toString());
                            }



//                            if (result.get("status").getAsString().equalsIgnoreCase("Success")){
////                                new LocalStore(LoginActivity.this).saveUserObjectAsString(
////                                        result.get("data").getAsJsonObject().toString());
////                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
//
//
//
//                            }

                        }
                        else {
                            Log.e("TAX BRACKETS", e.toString());
                        }
                    }
                });
        return serverResults;
    }


    public JsonObject getTaxCollectorProfile(JsonObject localData){
        JsonObject serverResults = null;

        Ion.with(this.context)
                .load(URLs.GET_TAX_COLLECTOR_PROFILE)
                .setMultipartParameter("username", localData.get("revenue_collector_username").getAsString())
                .setMultipartParameter("validation_token", localData.get("token").getAsString())
                .setMultipartParameter("district_id", localData.get("id").getAsString())
                .setMultipartParameter("revenue_collector_id", localData.get("id").getAsString())

                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        // do stuff with the result or error
                        // Log.e("TAX PAYER", result.toString());

                        if(result != null){
                            Log.e("PROFILE", result.toString());
                            if (result.get("status").getAsString().equalsIgnoreCase("Success")){

                                new LocalStore(context).saveProfileAsString(result.get("data").getAsJsonObject(

                                ).get("user").getAsJsonObject().toString());
                            }



//                            if (result.get("status").getAsString().equalsIgnoreCase("Success")){
////                                new LocalStore(LoginActivity.this).saveUserObjectAsString(
////                                        result.get("data").getAsJsonObject().toString());
////                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
//
//
//
//                            }

                        }
                        else {
                            Log.e("TAX BRACKETS", e.toString());
                        }
                    }
                });
        return serverResults;
    }
    public JsonObject sendMessage(JsonObject localData, String title, String message){
        JsonObject serverResults = null;

        Ion.with(this.context)
                .load(URLs.SEND_MESSAGE)
                .setMultipartParameter("message", message)
                .setMultipartParameter("username", localData.get("revenue_collector_username").getAsString())
                .setMultipartParameter("validation_token", localData.get("token").getAsString())
                .setMultipartParameter("sender_type", "revenue-collector")
                .setMultipartParameter("sender_id", localData.get("id").getAsString())

                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        // do stuff with the result or error
                        // Log.e("TAX PAYER", result.toString());

                        if(result != null){
                            Log.e("MESSAGE SENT", result.toString());
                            if (result.get("status").getAsString().equalsIgnoreCase("Success")){

//                                new LocalStore(context).saveProfileAsString(result.get("data").getAsJsonObject(
//
//                                ).get("user").getAsJsonObject().toString());
                            }



//                            if (result.get("status").getAsString().equalsIgnoreCase("Success")){
////                                new LocalStore(LoginActivity.this).saveUserObjectAsString(
////                                        result.get("data").getAsJsonObject().toString());
////                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
//
//
//
//                            }

                        }
                        else {
                            Log.e("TAX BRACKETS", e.toString());
                        }
                    }
                });
        return serverResults;
    }

    public JsonObject getTaxAllTaxPayersForACollector(JsonObject localData){
        JsonObject serverResults = null;

        Ion.with(this.context)
                .load(URLs.GET_TAX_PAYERS)
                .setMultipartParameter("username", localData.get("revenue_collector_username").getAsString())
                .setMultipartParameter("validation_token", localData.get("token").getAsString())
                .setMultipartParameter("revenue_collector_id", localData.get("id").getAsString())
                .setMultipartParameter("id", localData.get("id").getAsString())

                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        // do stuff with the result or error
                        // Log.e("TAX PAYER", result.toString());

                        if(result != null){
                            Log.e("PAYERS", result.toString());
                            if (result.get("status").getAsString().equalsIgnoreCase("Success")){

                                new LocalStore(context).saveTaxPayersAsString(result.get("data").getAsJsonObject(

                                ).get("revenue_collector").getAsJsonObject().
                                        get("registered_tax_payers").getAsJsonArray().toString());


                            }



//                            if (result.get("status").getAsString().equalsIgnoreCase("Success")){
////                                new LocalStore(LoginActivity.this).saveUserObjectAsString(
////                                        result.get("data").getAsJsonObject().toString());
////                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
//
//
//
//                            }

                        }
                        else {
                            Log.e("TAX BRACKETS", e.toString());
                        }
                    }
                });
        return serverResults;
    }
}
