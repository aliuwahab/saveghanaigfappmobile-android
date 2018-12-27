package com.gbeilaaliuwahab.saveghanaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.gbeilaaliuwahab.saveghanaapp.Helpers.LocalStore;
import com.gbeilaaliuwahab.saveghanaapp.Helpers.ServerCallClass;
import com.google.gson.JsonObject;

import maes.tech.intentanim.CustomIntent;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//    private CardView addRevenue, viewRevenueReports,sendMessage,viewProfile,registerTaxPayer;
    private CardView sendMessage,viewProfile,registerTaxPayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerTaxPayer = (CardView) findViewById(R.id.register_a_tax_payer_card) ;
        registerTaxPayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "Register", Toast.LENGTH_LONG).show();

            }
        });

        receiveMessagesPassedToThisIntent();
        associateCardViewsWithClassProperties();


//        JsonObject ibjecti = new LocalStore(MainActivity.this).readUserObjectAsJson();
//        new ServerCallClass(MainActivity.this).sendMessage(ibjecti, "Hello", "A MESSAGE FROM HADI'S APPPP");
    }



    @Override
    public void onClick(View v) {

        Intent cardViewIntent;

        switch (v.getId()) {
//            case R.id.add_revenue_card:
//                cardViewIntent = new Intent(this, ReportRevenueActivity.class);
//                startActivity(cardViewIntent);
//                intentAnimation();
//                break;
//
//            case R.id.view_revenue_card:
//                cardViewIntent = new Intent(this, ViewRevenueReportsActivity.class);
//                startActivity(cardViewIntent);
//                intentAnimation();
//                break;

            case R.id.send_message_card:
                cardViewIntent = new Intent(this, MessageActivity.class);
                startActivity(cardViewIntent);
                intentAnimation();
                break;

            case R.id.view_profile_card:
                cardViewIntent = new Intent(this, ProfileActivity.class);
                startActivity(cardViewIntent);
                intentAnimation();
                break;

            case R.id.register_a_tax_payer_card:
                cardViewIntent = new Intent(this, RegisterTaxPayerActivity.class);
                startActivity(cardViewIntent);
                intentAnimation();

            default:
                break;
        }

    }




    public void associateCardViewsWithClassProperties(){

//        associate class properties with view fields
//        addRevenue = (CardView) findViewById(R.id.add_revenue_card);
//        viewRevenueReports = (CardView) findViewById(R.id.view_revenue_card);



        sendMessage = (CardView) findViewById(R.id.send_message_card);
        viewProfile = (CardView) findViewById(R.id.view_profile_card);
        registerTaxPayer = (CardView) findViewById(R.id.register_a_tax_payer_card);

        //Set on click listeners to cardviews class properties
//        addRevenue.setOnClickListener(this);
//        viewRevenueReports.setOnClickListener(this);


        sendMessage.setOnClickListener(this);
        viewProfile.setOnClickListener(this);
        registerTaxPayer.setOnClickListener(this);

    }



    public void intentAnimation(){
        CustomIntent.customType(MainActivity.this, "bottom-to-up");

//        POSSIBLE ANIMATION TYPES
//        *left-to-right
//        *right-to-left
//        *bottom-to-up
//        *up-to-bottom
//        *fadein-to-fadeout
//         *rotateout-to-rotatein

    }



    public void receiveMessagesPassedToThisIntent(){

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String username = intent.getStringExtra(LoginActivity.USERNAME);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
           // startActivity(new Intent(MainActivity.this, CardDemoActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
