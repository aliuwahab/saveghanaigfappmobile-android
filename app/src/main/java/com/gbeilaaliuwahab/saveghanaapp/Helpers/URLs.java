package com.gbeilaaliuwahab.saveghanaapp.Helpers;

public  class URLs {

    public static String BASE_URL  = "http://mmda-igf-tracker-app.herokuapp.com/";
    public static String LOGIN  = BASE_URL+"api/authenticate";
    public static String REGISTER_TAX_PAYER  = BASE_URL+"api/revenue/officers/register/tax/payer";
    public static String GET_DISTRICT_TAX_BRACKETS  = BASE_URL+"api/revenue/officers/district/tax/brackets";
    public static String SEND_MESSAGE  = "http://mmda-igf-tracker-app.herokuapp.com/api/revenue/officers/send/message/to/district/admin";
    public static String GET_TAX_COLLECTOR_PROFILE  = "http://mmda-igf-tracker-app.herokuapp.com/api/revenue/officers/profile/details";
    public static String GET_TAX_PAYERS  = "http://mmda-igf-tracker-app.herokuapp.com/api/revenue/officers/revenue/collector/tax/payers";
//    public static String GET_DISTRICT_TAX_BRACKET  = "http://mmda-igf-tracker-app.herokuapp.com/api/revenue/officers/register/tax" +
//            "/payer?created_by=1&username=hadi&validation_token=WcsOpQ438wUTl6&district_id=1&tax_payer_name=UTV Ghana&" +
//            "tax_payer_contact_person_full_name=Nana Kwame &tax_payer_contact_person_phone_number=0203030303&tax_" +
//            "payer_contact_person_email=kwame@gmail.com&tax_payer_description=Sample Description&tax_payer_location" +
//            "_name=Kantanmanto&tax_payer_location_address=Accra, Shop 10&tax_payer_location_latitude=5.724575&tax_" +
//            "payer_location_longitude=-0.178013&tax_payer_location_revenue_collection_point_id=1&tax_" +
//            "payer_assign_revenue_collector_id=1&tax_bracket_id=1&area_council_id=1&district_id=1&region_id=1&created_user" +
//            "_type=revenue-collector";


}
