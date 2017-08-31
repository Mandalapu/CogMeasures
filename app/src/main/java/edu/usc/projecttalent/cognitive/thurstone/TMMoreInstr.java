package edu.usc.projecttalent.cognitive.thurstone;

import android.os.Bundle;

import edu.usc.projecttalent.cognitive.BaseActivity;
import edu.usc.projecttalent.cognitive.R;

/**
 * @author Kay, updated by Anindya.
 * @version 1.0
 */

public class TMMoreInstr extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thur_instr2);
        setNext(TMPractice.class);
    }
}
