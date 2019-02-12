package com.gbeilaaliuwahab.saveghanaapp.models;

import com.google.gson.JsonObject;

public class TaxPayer {

    private String taxPayerID;
    private String taxPayerName;
    private String taxPayerContactPersonFullName;
    private String taxPayerContactPersonPhoneNumber;
    private String taxPayerLocationName;
    private String taxPayerLocationAddress;
    private int image;

    public TaxPayer(String taxPayerID, String taxPayerName,
                    String taxPayerContactPersonPhoneNumber
                    ) {
        this.taxPayerID = taxPayerID;
        this.taxPayerName = taxPayerName;
        this.taxPayerContactPersonPhoneNumber = taxPayerContactPersonPhoneNumber;

    }

    public String getTaxPayerID() {
        return taxPayerID;
    }

    public String getTaxPayerName() {
        return this.taxPayerName;
    }

    public String getTaxPayerContactPersonFullName() {
        return this.taxPayerContactPersonFullName;
    }

    public String getTaxPayerContactPersonPhoneNumber() {
        return taxPayerContactPersonPhoneNumber;
    }

    public String getTaxPayerLocationName() {
        return taxPayerLocationName;
    }

    public String getTaxPayerLocationAddress() {
        return taxPayerLocationAddress;
    }

    public JsonObject getInfoObject(){

        return new JsonObject();
    }







}
