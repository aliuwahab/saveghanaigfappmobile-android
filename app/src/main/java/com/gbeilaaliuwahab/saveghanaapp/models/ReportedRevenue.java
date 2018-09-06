package com.gbeilaaliuwahab.saveghanaapp.models;

public class ReportedRevenue {
    private int mRevenueCollectorImage;
    private String mRevenueCollectorName;
    private String mAmountReported;

    public ReportedRevenue(int revenueCollectorProfilePicUrl, String revenueCollectorName, String revenueAmount){
        mRevenueCollectorImage = revenueCollectorProfilePicUrl;
        mRevenueCollectorName = revenueCollectorName;
        mAmountReported = revenueAmount;
    }


    public int getmRevenueCollectorImage() {
        return mRevenueCollectorImage;
    }

    public String getmRevenueCollectorName() {
        return mRevenueCollectorName;
    }

    public String getmAmountReported() {
        return mAmountReported;
    }



}
