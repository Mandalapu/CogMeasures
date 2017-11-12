package edu.usc.projecttalent.cognitive.reaction;

import android.content.Intent;
import android.os.Bundle;

import edu.usc.projecttalent.cognitive.BaseActivity;
import edu.usc.projecttalent.cognitive.R;

/**
 * Set up the practice environment for the reaction time.
 * 5 chances of getting used to the format of the test is given to the user.
 */
public class RTPractice extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reac_practice);

        (findViewById(R.id.next)).setOnClickListener(v -> {
            Intent intent = new Intent(this, RTQuestion.class);
            //number of trials that the user can perform in the section.
            intent.putExtra("trials", 5);
            startActivityForResult(intent, 1);
        });
    }
}
