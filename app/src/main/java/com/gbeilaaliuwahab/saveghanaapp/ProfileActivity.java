package com.gbeilaaliuwahab.saveghanaapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gbeilaaliuwahab.saveghanaapp.API.JsonAPIRequest;
import com.gbeilaaliuwahab.saveghanaapp.Helpers.LocalStore;
import com.gbeilaaliuwahab.saveghanaapp.models.RevenueCollectorProfile;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileActivity extends AppCompatActivity {

    private JsonAPIRequest jsonAPIRequest;
    TextView fullName, phoneNumber, description,location, recentRevenueReported, totalRevenueReported;
    Button viewPayers;
    CircleImageView profileImage;
   // String fullNameString, phoneNumberString, descriptionString,locationString;

    LocalStore localStore ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        fullName = (TextView) findViewById(R.id.profile_full_name);
        phoneNumber = (TextView) findViewById(R.id.profile_phone_number);
        description = (TextView) findViewById(R.id.profile_description);
        location = (TextView) findViewById(R.id.profile_location);

        viewPayers = (Button) findViewById(R.id.view_tax_payers);

        viewPayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, TaxCollectorsListDisplay.class));
            }
        });

        setProfile();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiEndPointsConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        jsonAPIRequest = retrofit.create(JsonAPIRequest.class);

        getTaxOfficerProfile(1);



    }




    public void getTaxOfficerProfile(int taxOfficerID){

//        JsonObject bracketJson = localStore.readProfileAsJson();

    }


    public void setProfile(){
        JsonObject profileObject = new LocalStore(ProfileActivity.this).readProfileAsJson();
        fullName.setText(profileObject.get("revenue_collector_first_name").getAsString()
                + " "
                +profileObject.get("revenue_collector_last_name").getAsString() );
        phoneNumber.setText(profileObject.get("revenue_collector_phone_number").getAsString());
        description.setText(profileObject.get("revenue_collector_description").getAsString());

        try{
            String loc = (profileObject.get("revenue_collector_current_location").getAsString() == null )  ? "N/A" : profileObject.get("revenue_collector_current_location").getAsString();
            location.setText(loc);
        }
        catch (Exception excep){
            location.setText("Location Not Displayed");
        }

    }




}
