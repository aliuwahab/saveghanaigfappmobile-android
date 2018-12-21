package com.gbeilaaliuwahab.saveghanaapp.Helpers;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

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

    public JsonObject registerTaxPayers(String username,
                                        String validation_token,
                                        String created_by,
                                        String district_id,
                                        String tax_payer_name,
                                        String  tax_payer_contact_person_full_name,
                                        String tax_payer_contact_person_phone_number,
                                        String tax_payer_contact_person_email,
                                        String tax_payer_description,
                                        String tax_payer_location_name,
                                        String tax_payer_location_address,
                                        String tax_payer_location_latitude,
                                        String tax_payer_location_longitude,
                                        String tax_payer_location_revenue_collection_point_id,
                                        String tax_payer_assign_revenue_collector_id,
                                        String tax_bracket_id,
                                        String area_council_id,
                                        String region_id,
                                        String created_user_type){
        JsonObject serverResults = null;

        Ion.with(this.context)
                .load(URLs.REGISTER_TAX_PAYER)
                .setMultipartParameter("revenue_collector_username", "hadi")
                .setMultipartParameter("password", "VKAQ2")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        // do stuff with the result or error
                        Log.e("DATA", result.toString());

                        if(result != null){

                            if (result.get("status").getAsString().equalsIgnoreCase("Success")){
//                                new LocalStore(LoginActivity.this).saveUserObjectAsString(
//                                        result.get("data").getAsJsonObject().toString());
//                                startActivity(new Intent(LoginActivity.this, MainActivity.class));



                            }

                        }
                    }
                });
        return serverResults;
    }
}
