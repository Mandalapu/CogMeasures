package edu.usc.projecttalent.cognitive.reaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
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

        mContext = this;
        //if coming from practice activity, this value will be set.
        mTrials = getIntent().getIntExtra("trials", NO_OF_TRIALS);
        mSkipClass = mTrials == 5 ? RTStart.class : SVPractice.class; //where to go in case skipped.
        //set the section name depending on the number of trials.
        mSection = new Section(getString(mTrials == 5? R.string.reaction_practice : R.string.reaction_time));
        mBlock = new Block();

        prepareRTFilter();
        mTimer = Timer.getTimer(0.5); //timer for 30 seconds attached.

        Button space = findViewById(R.id.buttonSpace);
        Random r = new Random();

        final ImageView image = findViewById(R.id.imageView);
        image.setImageResource(R.drawable.cross);

        final Handler handler = new Handler();
        Runnable runnable = () -> {
            image.setImageResource(R.drawable.red_circle_large);
            isRed = true;
            mAnswer = new Answer();
            mTimer.startTimer();
            start = System.currentTimeMillis();
        };

        showNextRed(r, handler, runnable);
        space.setOnTouchListener(getOnTouchListener(space, r, image, handler, runnable));
    }

    private void showNextRed(Random r, Handler handler, Runnable runnable) {
        handler.postDelayed(runnable, 1000 * (r.nextInt(8 - 2) + 2));
    }

    @NonNull
    private View.OnTouchListener getOnTouchListener(Button space, Random r, ImageView image, Handler handler, Runnable runnable) {
        return (v, event) -> {
            v.performClick();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    setColor(space, android.R.color.black, android.R.color.white);
                    processClick(r, image, handler, runnable);
                    break;
                case MotionEvent.ACTION_UP:
                    setColor(space, android.R.color.white, android.R.color.black);
                    break;
            }
            return true;
        };
    }

    private void processClick(Random r, ImageView image, Handler handler, Runnable runnable) {
        if (isRed) {
            countPositive(image);
        } else {
            falseClick();
        }
        mBlock.addAnswer(mAnswer); //add this answer to the block.
        if (counter == mTrials) { //the section has ended now.
            nextSection();
        } else {
            showNextRed(r, handler, runnable);
        }
    }

    private void nextSection() {
        mSection.addBlock(mBlock);
        if(mTrials == NO_OF_TRIALS) {
            finishSection();
            createFile(mTrials == 5 ? "rt_prac_" : "reaction_", 5);
        } else { //practice section has ended.
            mSection.endSection();
            Survey.getSurvey().addSection(mSection);
            startActivityForResult(new Intent(this, mSkipClass), 1);
        }
    }

    private void falseClick() {
        mAnswer = new Answer();
        mAnswer.endAnswer(0);
    }

    private void countPositive(ImageView image) {
        mAnswer.endAnswer(System.currentTimeMillis() - start);
        counter++;
        image.setImageResource(R.drawable.cross);
        isRed = false;
    }

    private void setColor(Button v, int background, int text) {
        v.setBackgroundColor(getResources().getColor(background));
        v.setTextColor(getResources().getColor(text));
    }
}
