package edu.usc.projecttalent.cognitive;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import edu.usc.projecttalent.cognitive.model.Survey;

public class FinishActivity extends AppCompatActivity {

    public static final String JSON = "json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        Button button = (Button) findViewById(R.id.finish);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //log here to print
                Survey survey = Survey.getSurvey();
                survey.endSurvey();
                Intent intent = new Intent();
                intent.putExtra(JSON, new Gson().toJson(survey));
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
