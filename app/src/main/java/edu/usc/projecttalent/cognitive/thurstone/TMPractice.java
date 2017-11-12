package edu.usc.projecttalent.cognitive.thurstone;

import android.os.Bundle;

import edu.usc.projecttalent.cognitive.BaseActivity;
import edu.usc.projecttalent.cognitive.R;

/**
 * Set up instructions for practice examples Thurstone.
 *
 * @author Anindya Dutta
 */

public class TMPractice extends BaseActivity {

    /**
     * starts the thread runner for the practice on clicking next button.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        setNext(TMRunner.class);
    }
}
