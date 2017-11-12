package edu.usc.projecttalent.cognitive.reaction;

import android.os.Bundle;

import edu.usc.projecttalent.cognitive.BaseActivity;
import edu.usc.projecttalent.cognitive.R;

/**
 * Activity that shows the instructions for Reaction time.
 */
public class RTInstruction extends BaseActivity {

    /**
     * set up the instructions and next button to the practice example for Reaction time.
     * @param savedInstanceState currently null is passed to this activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reac_instr);
        setNext(RTPractice.class);
    }
}
