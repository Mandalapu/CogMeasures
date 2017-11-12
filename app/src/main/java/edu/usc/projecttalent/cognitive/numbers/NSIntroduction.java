package edu.usc.projecttalent.cognitive.numbers;

import android.os.Bundle;

import edu.usc.projecttalent.cognitive.BaseActivity;
import edu.usc.projecttalent.cognitive.R;

/**
 * This activity displays the instructions for the Number section. No user interaction required except
 * clicking the Next button after reading all the instructions and the introduction.
 */
public class NSIntroduction extends BaseActivity {

    /**
     * Set up the layout. Program the Next button to go to "NSQuestion".
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_nsintro);
        //set the next button to NSQuestion.
        setNext(NSQuestion.class);
    }
}
