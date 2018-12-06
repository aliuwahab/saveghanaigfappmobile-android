package com.gbeilaaliuwahab.saveghanaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.gbeilaaliuwahab.saveghanaapp.API.JsonAPIRequest;
import com.gbeilaaliuwahab.saveghanaapp.models.RevenueCollectorProfile;
import com.gbeilaaliuwahab.saveghanaapp.models.TaxPayer;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListRegisteredTaxPayersActivity extends AppCompatActivity {
    private TextView textViewTaxPayerName;
    private  JsonAPIRequest jsonAPIRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_registered_tax_payers);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiEndPointsConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonAPIRequest = retrofit.create(JsonAPIRequest.class);

        getListOfTaxPayersUnderRevenueCollectionPoint(1);



    }


    public void getListOfTaxPayersUnderRevenueCollectionPoint(int RevenueCollectionPointID){

        Call<List<TaxPayer>> call = jsonAPIRequest.getTaxPayers(RevenueCollectionPointID);

        call.enqueue(new Callback<List<TaxPayer>>() {
            @Override
            public void onResponse(Call<List<TaxPayer>> call, Response<List<TaxPayer>> response) {

                if (!response.isSuccessful()) {

                    Log.d("RETROFIT_ERROR_CODE_" + response.code(), "CODE");

                    return;
                }

                List<TaxPayer> taxPayers = response.body();

                for (TaxPayer taxPayer: taxPayers) {

                    Log.d("TAX PAYER - ", taxPayer.getTaxPayerContactPersonFullName());
                }

            }

            @Override
            public void onFailure(Call<List<TaxPayer>> call, Throwable t) {

                Log.d("RETROFIT_ERROR", t.getMessage());

            }


        });
    }










}
