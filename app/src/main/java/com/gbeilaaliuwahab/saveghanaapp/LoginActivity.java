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
import com.gbeilaaliuwahab.saveghanaapp.models.RevenueCollectorProfile;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    Intent intent;
    private JsonAPIRequest jsonAPIRequest;

    public static final String USERNAME = "com.gbeilaaliuwahab.saveghanaapp.MESSAGE";
    public static final String PASSWORD = "com.gbeilaaliuwahab.saveghanaapp.PASSWORD";


    private Button loginButton;
    private Button loginJoinAgendaButton;
    private Button loginReadMoreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

         intent = new Intent(this, MainActivity.class);



        InnitialiseViewWidgets();
        innitialiseOnClickListeners();

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

        String usernameEntered = "";
        String passwordEntered = "";

        EditText username = (EditText) findViewById(R.id.login_username_edit_text);
        EditText password = (EditText) findViewById(R.id.login_user_password_edit_text);

        if(username != null && !username.equals("")) {
            username.getText().toString();
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
