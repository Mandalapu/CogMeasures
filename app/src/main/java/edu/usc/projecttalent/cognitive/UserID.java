package edu.usc.projecttalent.cognitive;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import edu.usc.projecttalent.cognitive.model.Survey;

/**
 * Takes the user ID as input and stores it for the survey.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class UserID extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_id);

        (findViewById(R.id.next)).setOnClickListener(v -> {
            Survey.getSurvey().setUser(Integer.parseInt(((EditText) findViewById(R.id.uid))
                    .getText().toString()));
            startActivityForResult(new Intent(this, MainActivity.class), 1);
        });
    }

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
