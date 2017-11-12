package edu.usc.projecttalent.cognitive.reasoning;

import android.os.Bundle;

import edu.usc.projecttalent.cognitive.BaseActivity;
import edu.usc.projecttalent.cognitive.R;

/**
 * ARIntroduction to the abstract reasoning section.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class ARIntroduction extends BaseActivity {

    /**
     * set up the next button to go to the Question class.
     * @param savedInstanceState null
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar_intro);
        setNext(ARQuestion.class);
    }
}
