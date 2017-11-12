package edu.usc.projecttalent.cognitive.thurstone;

import android.os.Bundle;

import edu.usc.projecttalent.cognitive.BaseActivity;
import edu.usc.projecttalent.cognitive.R;

/**
 * Instructions for the thurstone measure.
 *
 * @author Anindya Dutta
 */
public class TMInstruction extends BaseActivity {

    /**
     * Show the instructions for page 1 and move to show more instructions on next activity.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thur_instr);
        setNext(TMMoreInstr.class);
    }
}
