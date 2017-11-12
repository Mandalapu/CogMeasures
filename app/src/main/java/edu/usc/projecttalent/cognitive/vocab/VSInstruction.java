package edu.usc.projecttalent.cognitive.vocab;

import android.os.Bundle;

import edu.usc.projecttalent.cognitive.BaseActivity;
import edu.usc.projecttalent.cognitive.R;

/**
 * Instructions for the vocabulary section.
 *
 * @author Anindya Dutta
 */
public class VSInstruction extends BaseActivity {

    /**
     * set the instructions and next button to start the section questions.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab_intro);
        setNext(VSQuestion.class);
    }
}
