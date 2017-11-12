package edu.usc.projecttalent.cognitive.reaction;

import android.os.Bundle;

import edu.usc.projecttalent.cognitive.BaseActivity;
import edu.usc.projecttalent.cognitive.R;

/**
 * Activity which starts the main test for the reaction time section.
 */
public class RTStart extends BaseActivity {

    /**
     * Set the next button to start the test.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rtstart);
        setNext(RTQuestion.class);
    }
}
