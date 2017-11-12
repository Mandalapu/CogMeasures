package edu.usc.projecttalent.cognitive;

import android.content.Intent;
import android.os.Bundle;

import com.google.gson.Gson;

import edu.usc.projecttalent.cognitive.model.Survey;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Last activity of the application. Wraps up the JSON data and sends it to the server.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class EndTest extends BaseActivity {

    /**
     * The key to be used for the JSON data.
     */
    private static final String JSON = "json";
    /**
     * The URL used where the data is uploaded during pilot session.
     */
    private static final String BASE_URL = "http://projecttalent.usc.edu/pilot/";

    /**
     * Ends the test. Collect all the data, create a Retrofit instance and send this data to the server.
     * Also send this data back through the activities to the beginning of this application.
     * @param savedInstanceState nothing is sent to this bundle currently.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endtest);

        (findViewById(R.id.finish)).setOnClickListener(v -> {
            Survey survey = Survey.getSurvey();
            survey.endSurvey();
            String result = new Gson().toJson(survey);
            //add retrofit code.
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            APIEndPoint service = retrofit.create(APIEndPoint.class);
            Call<Void> call = service.getData(result);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                }
            });


            Intent intent = new Intent();
            intent.putExtra(JSON, result);
            setResult(RESULT_OK, intent);
            Timer.getTimer(3).stopTimer(); //to prevent new on-ticks after activity closes.
            finish();
        });
    }
}
