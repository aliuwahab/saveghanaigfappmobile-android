package com.gbeilaaliuwahab.saveghanaapp.API;

import com.gbeilaaliuwahab.saveghanaapp.models.RevenueCollectorProfile;
import com.gbeilaaliuwahab.saveghanaapp.models.TaxPayer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JsonAPIRequest {

    @FormUrlEncoded
    @POST("api/revenue/officers/authenticate")
    Call<RevenueCollectorProfile>authenticateRevenueCollector(@Field("username") String username, @Field("password") String password);

    @GET("api/revenue/officers/profile/{id}/details")
    Call<RevenueCollectorProfile> getRevenueCollectorProfile(@Path("id") int taxCollectorID);

    @GET("district/{district_id}/tax/brackets")
    Call<List<TaxPayer>> getTaxBrackets(@Path("district_id") int DistrictID);

    @GET("tax/revenue/collection/point/{revenue_collection_point_id}/payers")
    Call<List<TaxPayer>> getTaxPayers(@Path("revenue_collection_point_id") int RevenueCollectionPointID);

    @POST("register/tax/payer")
    Call<TaxPayer>createANewTaxPayer(@Body TaxPayer taxPayer);








}
