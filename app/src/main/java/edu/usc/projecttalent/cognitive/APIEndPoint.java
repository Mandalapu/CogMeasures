package edu.usc.projecttalent.cognitive;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by anind on 7/10/2017.
 */

public interface APIEndPoint {

    @POST("add.php")
    @FormUrlEncoded
    Call<Void> getData(@Field("value") String value);

}
