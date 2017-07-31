package edu.usc.projecttalent.cognitive;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * API Endpoint for Retrofit interface.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

interface APIEndPoint {

    @POST("add.php")
    @FormUrlEncoded
    Call<Void> getData(@Field("value") String value);

}
