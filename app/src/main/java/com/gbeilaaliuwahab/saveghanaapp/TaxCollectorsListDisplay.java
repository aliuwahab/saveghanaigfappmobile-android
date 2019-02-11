package com.gbeilaaliuwahab.saveghanaapp;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gbeilaaliuwahab.saveghanaapp.Helpers.LocalStore;
import com.gbeilaaliuwahab.saveghanaapp.models.TaxPayer;
import com.gbeilaaliuwahab.saveghanaapp.models.TaxPayer;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;



public class TaxCollectorsListDisplay extends ListActivity {
    MyArrayAdapter adapter;
    JsonArray tax_payers;

    List<TaxPayer> weeklyList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tax_payers = new LocalStore(TaxCollectorsListDisplay.this).readTaxPayersAsJsonArray();

        displayTeacherObjectsByName();
        adapter = new MyArrayAdapter();
        setListAdapter(adapter);
    }

    
    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    public int getImage(String gender){
        int male = R.drawable.teacher_icon;
        int female = R.drawable.female_teacher;
        int icon = (gender.equalsIgnoreCase("male")) ? male : female;

        return icon;

    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
//        JsonObject submitOBject = weeklyList.get(position).getInfoOBject();
//        Intent intent = new Intent(TaxCollectorsListDisplay.this, MainActivity.class);
//        intent.putExtra("data", submitOBject.toString());
//        startActivity(intent);
        this.finish();
    }


    public void displayTeacherObjectsByName(){
        weeklyList = new ArrayList<TaxPayer>();
//        SharedPreferences details = getSharedPreferences("teachers", 0);
//        String teacherString = details.getString("data", null);
       // if(teacherString !=null && teacherString !="[]"){
           // JsonArray teacherObject = (JsonArray) new JsonParser().parse(teacherString);
            for (int i = 0; i < tax_payers.size(); i++){

                final JsonObject eachTeacher = tax_payers.get(i).getAsJsonObject();



                int image_to_use = getImage(eachTeacher.get("tax_payer_location_name").getAsString());
//                weeklyList.add(new TaxPayer(image_to_use, eachTeacher.get("first_name").getAsString() +" "+ eachTeacher.get("last_name").getAsString(),
//                       " "+ eachTeacher.get("gender").getAsString()," "+ eachTeacher.get("staff_number").getAsString(), eachTeacher));

                weeklyList.add(new TaxPayer(eachTeacher.get("id").getAsString(),
                        eachTeacher.get("tax_payer_contact_person_full_name").getAsString(),
                        eachTeacher.get("tax_payer_contact_person_phone_number").getAsString()));
            }


    }



    private class MyArrayAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return weeklyList.size();
        }

        @Override
        public TaxPayer getItem(int position) {
            return weeklyList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder itemHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_menu_row_layout, parent, false);

                itemHolder = new ViewHolder();
                itemHolder.menuParent = (RelativeLayout) convertView.findViewById(R.id.menuParent);
                itemHolder.titleView = (TextView) convertView.findViewById(R.id.menuLabel);
                itemHolder.subTextView = (TextView) convertView.findViewById(R.id.menuLabelChild);

                itemHolder.menuImage = (ImageView) convertView.findViewById(R.id.menuImage);
                itemHolder.menuImage.setVisibility(View.VISIBLE);

                convertView.setTag(itemHolder);
            }else{
                itemHolder = (ViewHolder) convertView.getTag();
            }


            itemHolder.titleView.setText(weeklyList.get(position).getTaxPayerName());
            itemHolder.menuImage.setImageResource(R.drawable.teacher_icon);

            itemHolder.subTextView.setText(  String.format("Type : %s ",
                    "Tax Payer"));
                        itemHolder.subTextView.setVisibility(View.VISIBLE);

//            switch (position){
//                case 0 :
//                    itemHolder.titleView.setText(weeklyList.get(position).getItemname());
//                    if (Constants.week != null && Constants.term != null && Constants.year != null) {
//                        itemHolder.subTextView.setText(  String.format("Week : %s | Term : %s | Year : %s | Days : %s", Constants.week,
//                                Constants.term, Constants.year, Constants.num_school_days_in_session));
//                        itemHolder.subTextView.setVisibility(View.VISIBLE);
//                    }
//                    break;
//
//                default:
//                    itemHolder.titleView.setText(weeklyList.get(position).getItemname());
//                    itemHolder.menuImage.setImageResource(weeklyList.get(position).getImage());
//                    break;
//            }

            return convertView;
        }

        class ViewHolder{
            TextView titleView;
            TextView subTextView;
            ImageView menuImage;
            RelativeLayout menuParent;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


              //  return true;



        return super.onOptionsItemSelected(item);
    }



}
