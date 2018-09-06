package com.gbeilaaliuwahab.saveghanaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

public class LoginActivity extends AppCompatActivity {

    public static final String USERNAME = "com.gbeilaaliuwahab.saveghanaapp.MESSAGE";
    public static final String PASSWORD = "com.gbeilaaliuwahab.saveghanaapp.PASSWORD";


    private Button loginButton;
    private Button loginJoinAgendaButton;
    private Button loginReadMoreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

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

        Intent intent = new Intent(this, MainActivity.class);

        EditText username = (EditText) findViewById(R.id.login_username_edit_text);
        EditText password = (EditText) findViewById(R.id.login_user_password_edit_text);

        String usernameEntered = username.getText().toString();
        String passwordEntered = password.getText().toString();

        intent.putExtra(USERNAME, usernameEntered);
        intent.putExtra(PASSWORD, passwordEntered);

        startActivity(intent);

//        StyleableToast.makeText(this, "Hello World!", Toast.LENGTH_LONG, R.style.mytoast).show();






    }



}
