package edu.usc.projecttalent.cognitive;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import java.util.Queue;

import edu.usc.projecttalent.cognitive.holders.Item;
import edu.usc.projecttalent.cognitive.model.Answer;
import edu.usc.projecttalent.cognitive.model.Block;
import edu.usc.projecttalent.cognitive.model.Section;
import edu.usc.projecttalent.cognitive.model.Survey;

/**
 * An abstract class that is extended by all activities that show questions.
 *
 * @author Anindya Dutta
 */
public abstract class QuestionActivity extends BaseActivity {

    /**
     * The section object corresponding to this section. For example, number section, AR etc.
     */
    protected Section mSection;
    /**
     * Encapsulates the various blocks used during a test.
     */
    protected Block mBlock;
    /**
     * Encapsulates the answers recorded in each block.
     */
    protected Answer mAnswer;
    /**
     * The score keeper for each block.
     */
    protected int mScore;
    /**
     * The class to skip to if the user quits this section.
     */
    protected static Class mSkipClass;
    /**
     * A queue that stores all the questions for a block from which questions are pulled and bound
     * to the activity at runtime.
     */
    protected static Queue<Item> mQueue;

    /**
     * Implemented by each individual activity.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Called when a section needs to be ended, either automatically or by user intervention.
     * Adds this section to the survey and then moves to the next section based on the skip class.
     */
    @Override
    protected void finishSection() {
        mSection.endSection(); //end this section.
        Survey.getSurvey().addSection(mSection); //add AR section to survey.
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setMessage(R.string.pressnext)
                .setNeutralButton(R.string.next, (d, which) -> startActivityForResult(new Intent(this, mSkipClass), 1))
                .setCancelable(false)
                .create();
        dialog.show();
    }
}
