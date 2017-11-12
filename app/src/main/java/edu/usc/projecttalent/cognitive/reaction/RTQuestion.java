package edu.usc.projecttalent.cognitive.reaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

import edu.usc.projecttalent.cognitive.QuestionActivity;
import edu.usc.projecttalent.cognitive.R;
import edu.usc.projecttalent.cognitive.Timer;
import edu.usc.projecttalent.cognitive.model.Answer;
import edu.usc.projecttalent.cognitive.model.Block;
import edu.usc.projecttalent.cognitive.model.Section;
import edu.usc.projecttalent.cognitive.model.Survey;
import edu.usc.projecttalent.cognitive.spatial.SVPractice;

/**
 * This activity implements the reaction time. The user gets a number of trials to click the space
 * bar when the red dot appears.
 * @author Kay (initial), Anindya Dutta (optimization)
 * @version 2.0
 */

public class RTQuestion extends QuestionActivity {
    /**
     * counter checks how many times the user has already clicked the space button.
     */
    private int counter = 0;
    /**
     * to calculate delay in pressing space once stimulus is shown.
     */
    private long start;
    /**
     * number of trials for this test.
     */
    private int mTrials;
    /**
     * default trials if not practice activity.
     */
    public static final int NO_OF_TRIALS = 20;
    /**
     * denotes when stimulus is being shown.
     */
    private boolean isRed = false;

    /**
     * set up the timer and functionality of the reaction time.
     * @param savedInstanceState currently nothing is sent to this activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reac_question);

        mContext = this; //current context
        //if coming from practice activity, this value will be set.
        mTrials = getIntent().getIntExtra("trials", NO_OF_TRIALS);
        mSkipClass = mTrials == 5 ? RTStart.class : SVPractice.class; //where to go in case skipped.
        //set the section name depending on the number of trials.
        mSection = new Section(getString(mTrials == 5? R.string.reaction_practice : R.string.reaction_time));
        //start a new block. By default, there is only one block in RT. So block no. is 1.
        mBlock = new Block(1);

        //prepare filters for reaction time.
        prepareRTFilter();
        mTimer = Timer.getTimer(0.5); //timer for 30 seconds attached.

        Button space = findViewById(R.id.buttonSpace);
        Random r = new Random();
        int low = 2, high = 8; //between 2 and 8 seconds.

        final ImageView image = findViewById(R.id.imageView);
        image.setImageResource(R.drawable.cross);

        final Handler handler = new Handler();
        //runnable to show the red circle.
        Runnable runnable = () -> {
            image.setImageResource(R.drawable.red_circle_large);
            isRed = true;
            mAnswer = new Answer();
            mTimer.startTimer();
            start = System.currentTimeMillis();
        };

        //display this runnable randomly.
        handler.postDelayed(runnable, 1000 * (r.nextInt(high - low) + low));

        space.setOnTouchListener((v, event) -> {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                //once clicked, show the cross sign.
                setColor(space, android.R.color.black, android.R.color.white);

                if (isRed) { //if stimulus was shown, correct click.
                    //add the time in milliseconds delay.
                    mAnswer.endAnswer(System.currentTimeMillis() - start, true);
                    counter++; //update counter
                    image.setImageResource(R.drawable.cross); //show the cross.
                    isRed = false; //re-init the stimulus flag.
                } else { //false click
                    mAnswer = new Answer();
                    mAnswer.endAnswer(0, false); //add false for dalse click.
                }
                mBlock.addAnswer(mAnswer); //add this answer to the block.
                if (counter == mTrials) { //the section has ended now.
                    mSection.addBlock(mBlock); //add this section to the block.
                    if(mTrials == NO_OF_TRIALS) { //actual question section has ended.
                        finishSection();
                    } else { //practice section has ended.
                        mSection.endSection(); //end this section.
                        Survey.getSurvey().addSection(mSection);
                        startActivityForResult(new Intent(this, mSkipClass), 1);
                    }
                } else {
                    //set to show stimulus randomly.
                    handler.postDelayed(runnable, 1000 * (r.nextInt(high - low) + low));
                }
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                //set color of space button to show space was clicked.
                setColor(space, android.R.color.white, android.R.color.black);
            }
            return true;
        });

    }

    /**
     * set the color of button.
     * @param v the view for which color needs to be changed.
     * @param background new background color.
     * @param text new text color.
     */
    private void setColor(Button v, int background, int text) {
        v.setBackgroundColor(getResources().getColor(background));
        v.setTextColor(getResources().getColor(text));
    }
}
