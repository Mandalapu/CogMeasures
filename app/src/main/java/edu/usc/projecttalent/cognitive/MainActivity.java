package edu.usc.projecttalent.cognitive;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import edu.usc.projecttalent.cognitive.model.Survey;
import edu.usc.projecttalent.cognitive.vocab.VSInstruction;

/**
 * Takes the user ID as input and stores it for the survey.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        (findViewById(R.id.next)).setOnClickListener(v -> {
            Survey.getSurvey().setUser(Integer.parseInt(((EditText) findViewById(R.id.uid))
                    .getText().toString()));
            startActivityForResult(new Intent(this, VSInstruction.class), 1);
        });
    }
}
