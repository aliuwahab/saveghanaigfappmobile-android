package com.gbeilaaliuwahab.saveghanaapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gbeilaaliuwahab.saveghanaapp.models.ReportedRevenue;

import java.util.ArrayList;

public class RevenueReportedAdapter extends RecyclerView.Adapter<RevenueReportedAdapter.RevenueReportedViewHolder> {
    private ArrayList<ReportedRevenue> mReportedRevenueArrayList;


    public static class RevenueReportedViewHolder extends RecyclerView.ViewHolder{

        public ImageView mRevenueReporterImageView;
        public TextView mRevenueReporterNameTextView;
        public TextView mRevenueReportedAmountTextView;



        public RevenueReportedViewHolder(View itemView) {
            super(itemView);
            mRevenueReporterImageView = itemView.findViewById(R.id.revenue_reporter_image_view);
            mRevenueReporterNameTextView = itemView.findViewById(R.id.revenue_collector_name_text_view);
            mRevenueReportedAmountTextView = itemView.findViewById(R.id.revenue_amount_reported_text_view);

        }

    }


    public RevenueReportedAdapter(ArrayList<ReportedRevenue> reportedRevenueArrayList){
        mReportedRevenueArrayList = reportedRevenueArrayList;
    }



    @NonNull
    @Override
    public RevenueReportedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.revenue_report_item,parent,false);
        RevenueReportedViewHolder revenueReportedViewHolder = new RevenueReportedViewHolder(view);
        return revenueReportedViewHolder;



    }

    @Override
    public void onBindViewHolder(@NonNull RevenueReportedViewHolder holder, int position) {
        ReportedRevenue currentReportedRevenue =  mReportedRevenueArrayList.get(position);

        holder.mRevenueReporterImageView.setImageResource(currentReportedRevenue.getmRevenueCollectorImage());
        holder.mRevenueReportedAmountTextView.setText(currentReportedRevenue.getmAmountReported());
        holder.mRevenueReporterNameTextView.setText(currentReportedRevenue.getmRevenueCollectorName());


    }

    @Override
    public int getItemCount() {
        return mReportedRevenueArrayList.size();
    }


}
