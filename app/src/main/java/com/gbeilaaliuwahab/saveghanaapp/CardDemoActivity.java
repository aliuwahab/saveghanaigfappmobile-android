package com.gbeilaaliuwahab.saveghanaapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.gbeilaaliuwahab.saveghanaapp.Helpers.LocalStore;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class CardDemoActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    JsonArray tax_payers;
    String[] titles, details;
    Uri[] images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_demo);

        tax_payers = new LocalStore(CardDemoActivity.this).readTaxPayersAsJsonArray();

        titles = new String[tax_payers.size()];
        details = new String[tax_payers.size()];
        images = new Uri[tax_payers.size()];

        for (int count = 0; count < tax_payers.size(); count++){
            JsonObject obj = tax_payers.get(count).getAsJsonObject();
            titles[count] = obj.get("tax_payer_name").getAsString();
            details[count] = obj.get("tax_payer_description").getAsString();
            try{
             images[count] = Uri.parse(obj.get("created_by").getAsJsonObject().get("user_profile_pic_url").getAsString());

            }
            catch (Exception imageException){
                images[count] = Uri.parse("https://media.licdn.com/dms/image/C5103AQFaSJTLxqY4Zg/" +
                        "profile-displayphoto-shrink_200_200/0?e=1538611200&v=beta&t=A4SqUahkghL5bsB8i5SYIVuNu3OKXpAl9FRBmB-Amos");
            }
        }


       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        recyclerView =
                (RecyclerView) findViewById(R.id.recycler_view);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerAdapter(titles, details, images);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_card_demo, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
