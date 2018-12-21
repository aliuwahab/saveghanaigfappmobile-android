package com.gbeilaaliuwahab.saveghanaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gbeilaaliuwahab.saveghanaapp.API.JsonAPIRequest;
import com.gbeilaaliuwahab.saveghanaapp.Helpers.LocalStore;
import com.gbeilaaliuwahab.saveghanaapp.Helpers.URLs;
import com.gbeilaaliuwahab.saveghanaapp.models.RevenueCollectorProfile;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    Intent intent;
    private JsonAPIRequest jsonAPIRequest;

    public static final String USERNAME = "com.gbeilaaliuwahab.saveghanaapp.MESSAGE";
//      public static final String USERNAME = "com.gbeilaaliuwahab.saveghanaapp.MESSAGE";
//    public static final String PASSWORD = "com.gbeilaaliuwahab.saveghanaapp.PASSWORD";
    public static final String PASSWORD = "com.gbeilaaliuwahab.saveghanaapp.PASSWORD";


    private Button loginButton;
    private Button loginJoinAgendaButton;
    private Button loginReadMoreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        // intent = new Intent(this, MainActivity.class);



        InnitialiseViewWidgets();
        innitialiseOnClickListeners();
        whenUserClicksLoginButton();
       // startActivity(new Intent(LoginActivity.this, MainActivity.class));

    }


    public void InnitialiseViewWidgets(){
        loginButton = findViewById(R.id.login_button);
        loginReadMoreButton = findViewById(R.id.login_read_more_button);
        loginJoinAgendaButton = findViewById(R.id.login_join_agenda_button);


    }

    public void innitialiseOnClickListeners(){

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                whenUserClicksLoginButton();
                return;
            }
        });

    }
    
    public void whenUserClicksLoginButton(){

        String usernameEntered = "hadi";
        String passwordEntered = "VKAQ2";

        EditText username = (EditText) findViewById(R.id.login_username_edit_text);
        EditText password = (EditText) findViewById(R.id.login_user_password_edit_text);

        if(username != null && !username.equals("")) {
           // username.getText().toString();
            //http://mmda-igf-tracker-app.herokuapp.com/api/authenticate (POST)
//            Username: hadi
//            Password: VKAQ2

            Ion.with(LoginActivity.this)
                    .load(URLs.LOGIN)
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
                                    new LocalStore(LoginActivity.this).saveUserObjectAsString(
                                            result.get("data").getAsJsonObject().toString());
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));



                                }

                            }
                        }
                    });
        }else{
//            StyleableToast.makeText(this, "Hello World!", Toast.LENGTH_LONG, R.style.mytoast).show();
            return;
        }

        if(password != null && !password.equals("")) {
            password.getText().toString();
        }else{
//            StyleableToast.makeText(this, "Hello World!", Toast.LENGTH_LONG, R.style.mytoast).show();
            return;
        }

        authenticateRevenueCollector(usernameEntered, passwordEntered);

        return;
    }



    public void authenticateRevenueCollector(String username, String password){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiEndPointsConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonAPIRequest = retrofit.create(JsonAPIRequest.class);

        Call<RevenueCollectorProfile> call  = jsonAPIRequest.authenticateRevenueCollector(username, password);

        call.enqueue(new Callback<RevenueCollectorProfile>() {
            @Override
            public void onResponse(Call<RevenueCollectorProfile> call, Response<RevenueCollectorProfile> response) {

                if (!response.isSuccessful()) {

                    Log.d("ALIU-DEBUG", response.message());
                    return;
                }

                RevenueCollectorProfile revenueCollectorProfile = response.body();

                Log.d("ALIU-DEBUG", revenueCollectorProfile.getFirstName());
                Log.d("ALIU-DEBUG", revenueCollectorProfile.getLastName());
                Log.d("ALIU-DEBUG", revenueCollectorProfile.getEmail());
                Log.d("ALIU-DEBUG", revenueCollectorProfile.getUserDescription());

                startActivity(intent);

                return;

            }

            @Override
            public void onFailure(Call<RevenueCollectorProfile> call, Throwable t) {

                Log.d("ALIU-DEBUG", t.getMessage());

            }

        });

    }



}
