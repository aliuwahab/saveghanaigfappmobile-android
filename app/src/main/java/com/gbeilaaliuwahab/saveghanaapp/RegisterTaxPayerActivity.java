package com.gbeilaaliuwahab.saveghanaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.gbeilaaliuwahab.saveghanaapp.Helpers.LocalStore;
import com.gbeilaaliuwahab.saveghanaapp.Helpers.ServerCallClass;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mmstq.progressbargifdialog.ProgressBarGIFDialog;

public class RegisterTaxPayerActivity extends AppCompatActivity {
    EditText fullName, contact_person, registerContactNumber,registerContactEmail,
              description, locationName, address;

    Spinner taxBrackets;

    String fullNameString, taxBracketsString, contact_personString, registerContactNumberString,registerContactEmailString,
            descriptionString, locationNameString, addressString;

    CheckBox confirm;

    Button register;

    Boolean edit;

    ProgressBarGIFDialog.Builder progressBarGIFDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_tax_payer);
        edit = false;

        try {
            Log.e("BRACKET ID", getTaxBracketId("Road Toll"));

        } catch
                (Exception cc){
            Log.e("BRACKET ID", "7HG");
        }



        fullName = (EditText) findViewById(R.id.register_full_name);
//        taxBrackets = (EditText) findViewById(R.id.register_tax_bracket);
        taxBrackets = (Spinner) findViewById(R.id.register_tax_bracket);
        contact_person = (EditText) findViewById(R.id.register_contact_person);
        registerContactNumber = (EditText) findViewById(R.id.register_contact_number);
        registerContactEmail = (EditText) findViewById(R.id.register_contact_email);
        description = (EditText) findViewById(R.id.register_description);
        locationName = (EditText) findViewById(R.id.register_location_name);
        address = (EditText) findViewById(R.id.register_address);
        confirm = (CheckBox) findViewById(R.id.confirm_revenue_entried_checkbox);
        register = (Button) findViewById(R.id.submit_revenue_button);

        try {
            tryToPopulateWidgetsFroEdits();
            edit = true;
            new LocalStore(RegisterTaxPayerActivity.this).deleteObjectToEdit();
        }
        catch (Exception editException){

        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit == true){
                    Toast.makeText(RegisterTaxPayerActivity.this, "Edit", Toast.LENGTH_LONG).show();
                }
                else {
                    submitData();
                }

            }
        });



        prepareTaxBracketDropDown();


    }


    public void prepareTaxBracketDropDown() {
        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.register_tax_bracket);
        //create a list of items for the spinner.
        String[] brackets = new String[]{"1", "2", "three"};

        JsonArray taxBracketsObjects =  new LocalStore(RegisterTaxPayerActivity.this).readBracketsObjectAsJson().get("tax_brackets").getAsJsonArray();
        Log.e("ID",brackets.toString());

        for(int i = 0; i < taxBracketsObjects.size(); i++){
            Log.e("ID",taxBracketsObjects.get(i).getAsJsonObject().toString());
            taxBracketsObjects.get(i).getAsJsonObject().get("bracket_name").getAsString();
        }

        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, brackets);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
    }



    private void tryToPopulateWidgetsFroEdits() {
       JsonObject object = new LocalStore(RegisterTaxPayerActivity.this).readPayToEditAsJson();
       fullName.setText(object.get("tax_payer_contact_person_full_name").getAsString());
       contact_person.setText(object.get("tax_payer_contact_person_full_name").getAsString());
       registerContactNumber.setText(object.get("tax_payer_contact_person_phone_number").getAsString());
       registerContactEmail.setText(object.get("tax_payer_contact_person_email").getAsString());
       description.setText(object.get("tax_payer_description").getAsString());
       locationName.setText(object.get("tax_payer_location_name").getAsString());
       address.setText(object.get("tax_payer_location_address").getAsString());
    }


    public void initialiseRetrofit(){


    }



    public void submitData(){
        if(confirm.isChecked()){
            progressBarGIFDialog= new ProgressBarGIFDialog.Builder(RegisterTaxPayerActivity.this);

            progressBarGIFDialog.setCancelable(false)

                    .setTitleColor(R.color.colorPrimary)
                    // Set Title Color (int only)

                    .setLoadingGifID(R.drawable.loading) // Set Loading Gif

                    .setDoneGifID(R.drawable.done) // Set Done Gif

                    .setDoneTitle("Registration completed") // Set Done Title

                    .setLoadingTitle("Registering user ") // Set Loading Title

                    .build();



            fullNameString = fullName.getText().toString();
            contact_personString = contact_person.getText().toString();
            registerContactNumberString = registerContactNumber.getText().toString();
            registerContactEmailString = registerContactEmail.getText().toString();
            descriptionString = description.getText().toString();
            locationNameString = locationName.getText().toString();
            addressString = address.getText().toString();
            JsonObject collectorDetails = new LocalStore(RegisterTaxPayerActivity.this)
                    .readUserObjectAsJson();

            String bracket_ID = getTaxBracketId(taxBrackets.getSelectedItem().toString());

            new ServerCallClass(RegisterTaxPayerActivity.this).registerTaxPayers(collectorDetails, fullNameString, contact_personString,
                    registerContactNumberString, registerContactEmailString,descriptionString, locationNameString, addressString, bracket_ID );

            progressBarGIFDialog.clear();
            startActivity(new Intent(RegisterTaxPayerActivity.this, MainActivity.class));
            finish();
        }
        else {
            Toast.makeText(RegisterTaxPayerActivity.this, "Please Confirm All Inputs and click the checkbox below",
                    Toast.LENGTH_LONG).show();
        }
    }


    public String getTaxBracketId(String taxBracketsString){
        String result = "-1";

       JsonArray brackets =  new LocalStore(RegisterTaxPayerActivity.this).readBracketsObjectAsJson().get("tax_brackets").getAsJsonArray();
       Log.e("ID",brackets.toString());

       for(int i = 0; i < brackets.size(); i++){
           Log.e("ID",brackets.get(i).getAsJsonObject().toString());

           if(brackets.get(i).getAsJsonObject().get("bracket_name").getAsString() .equalsIgnoreCase(taxBracketsString)){

               return brackets.get(i).getAsJsonObject().get("id").getAsString();
           }
       }

       return result;
    }
}
