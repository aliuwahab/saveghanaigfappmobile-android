package com.gbeilaaliuwahab.saveghanaapp.models;

import com.google.gson.JsonObject;

/**
 * Created by Comrade-Hadi on 7/1/2014.
 */
public class TaxPayerItem {
    private int image;
    private String itemname;
    private String gender;
    private String subjectOrClass;
    private JsonObject infoOBject;

    Class destinationClass;

    public TaxPayerItem(int image, String itemname, String gender, String subjectOrClass, JsonObject infoOBject) {
        this.image = image;
        this.itemname = itemname;
        this.gender = gender ;
        this.subjectOrClass = subjectOrClass;
        this.infoOBject = infoOBject;

    }


    public void setInfoOBject(JsonObject infoOBject) {
        this.infoOBject = infoOBject;
    }

    public JsonObject getInfoOBject() {
        return infoOBject;
    }

    public int getImage() {
        return image;
    }

    public String getItemname() {
        return itemname;
    }

    public String getGender() {
        return gender;
    }

    public String getSubjectOrClass() {
        return subjectOrClass;
    }

    public Class getDestinationClass() {
        return destinationClass;
    }
    public void setImage(int image) {
        this.image = image;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }
}
