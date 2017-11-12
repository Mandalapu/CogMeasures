package edu.usc.projecttalent.cognitive.spatial;

import android.os.Bundle;

import edu.usc.projecttalent.cognitive.BaseActivity;
import edu.usc.projecttalent.cognitive.R;

/**
 * Spatial introduction. Tell the user about the section and what the questions contain.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class SVInstruction extends BaseActivity {

    /**
     * Contains instructions for the section. Redirects to the sample questions.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_intro);
        setNext(SVSample.class);
    }
}
