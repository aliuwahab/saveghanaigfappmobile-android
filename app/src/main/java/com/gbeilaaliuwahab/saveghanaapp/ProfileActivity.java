package com.gbeilaaliuwahab.saveghanaapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.gbeilaaliuwahab.saveghanaapp.API.JsonAPIRequest;
import com.gbeilaaliuwahab.saveghanaapp.models.RevenueCollectorProfile;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileActivity extends AppCompatActivity {

    private JsonAPIRequest jsonAPIRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiEndPointsConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        jsonAPIRequest = retrofit.create(JsonAPIRequest.class);

        getTaxOfficerProfile(1);



    }




    public void getTaxOfficerProfile(int taxOfficerID){


        Call<RevenueCollectorProfile> call  = jsonAPIRequest.getRevenueCollectorProfile(taxOfficerID);

        call.enqueue(new Callback<RevenueCollectorProfile>() {
            @Override
            public void onResponse(Call<RevenueCollectorProfile> call, Response<RevenueCollectorProfile> response) {

                if (!response.isSuccessful()) {

                    Log.d("ALIU-DEBUG", response.message());
                    return;
                }

                RevenueCollectorProfile revenueCollectorProfile = response.body();

                Log.d("ALIU-DEBUG", revenueCollectorProfile.getFirstName());

                return;


            }

            @Override
            public void onFailure(Call<RevenueCollectorProfile> call, Throwable t) {

                Log.d("ALIU-DEBUG", t.getMessage());

            }


        });

    }






}
