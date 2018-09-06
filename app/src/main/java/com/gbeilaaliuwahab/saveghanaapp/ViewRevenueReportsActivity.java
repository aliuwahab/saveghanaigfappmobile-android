package com.gbeilaaliuwahab.saveghanaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gbeilaaliuwahab.saveghanaapp.models.ReportedRevenue;

import java.util.ArrayList;

public class ViewRevenueReportsActivity extends AppCompatActivity {
    private RecyclerView mRevenueReportedRecyclerView;
    private RecyclerView.Adapter mRevenueReportedAdapter;
    private RecyclerView.LayoutManager mRevenueReportedLayoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_revenue_reports);

        ArrayList<ReportedRevenue> reportedRevenueArrayList = new ArrayList<>();
        reportedRevenueArrayList.add(new ReportedRevenue(R.drawable.ic_person_outline_black_24dp,"Sule Dintie","GHC 700"));
        reportedRevenueArrayList.add(new ReportedRevenue(R.drawable.ic_person_outline_black_24dp,"Sule Dintie","GHC 900"));
        reportedRevenueArrayList.add(new ReportedRevenue(R.drawable.ic_person_outline_black_24dp,"Sule Dintie","GHC 1400"));


        mRevenueReportedRecyclerView = findViewById(R.id.revenue_reports_recycler_view);
        mRevenueReportedRecyclerView.setHasFixedSize(true);
        mRevenueReportedLayoutManager = new LinearLayoutManager(this);
        mRevenueReportedAdapter = new RevenueReportedAdapter(reportedRevenueArrayList);

        mRevenueReportedRecyclerView.setLayoutManager(mRevenueReportedLayoutManager);
        mRevenueReportedRecyclerView.setAdapter(mRevenueReportedAdapter);


    }





//    public void DataOfReportedRevenue(){
//
//
//
//    }



}
