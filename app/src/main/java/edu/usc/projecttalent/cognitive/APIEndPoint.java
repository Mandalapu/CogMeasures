package edu.usc.projecttalent.cognitive;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by anind on 7/10/2017.
 */

public interface APIEndPoint {

    @GET("add.php")
    Call<Void> getData(@Query("value") String value);

}
