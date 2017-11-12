package edu.usc.projecttalent.cognitive.thurstone;

import android.os.Bundle;

import edu.usc.projecttalent.cognitive.BaseActivity;
import edu.usc.projecttalent.cognitive.R;

/**
 * More instructions for Thurstone measures.
 * @author Kay, updated by Anindya.
 * @version 1.0
 */

public class TMMoreInstr extends BaseActivity {

    /**
     * Show the instructions for page 2 and set up next button for practice items.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thur_instr2);
        setNext(TMPractice.class);
    }
}
