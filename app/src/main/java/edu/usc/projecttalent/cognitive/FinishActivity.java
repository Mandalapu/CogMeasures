package edu.usc.projecttalent.cognitive;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import edu.usc.projecttalent.cognitive.model.Survey;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FinishActivity extends AppCompatActivity {

    public static final String JSON = "json";
    public static final String BASE_URL = "http://projecttalent.usc.edu/pilot/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        Button button = (Button) findViewById(R.id.finish);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Survey survey = Survey.getSurvey();
                survey.endSurvey();
                Intent intent = new Intent();
                String result = new Gson().toJson(survey);
                //add retrofit code.
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                APIEndPoint service = retrofit.create(APIEndPoint.class);
                Call<Void> call =  service.getData(result);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Log.e("anindya", response.message());
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.e("anindya", t.getMessage());
                    }
                });
                for(int i=0; i<result.length(); i+=500) {
                    Log.e("anindya", result.substring(i, Math.min(i+500, result.length())));
                }

                intent.putExtra(JSON, result);
                setResult(RESULT_OK, intent);
                QuestionTimer.stopTimer(); //to prevent new on-ticks after activity closes.
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                setResult(Activity.RESULT_OK, data);
                super.finish();
            }
        }
    }
}
