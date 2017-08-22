package edu.usc.projecttalent.cognitive.reaction_time;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

import edu.usc.projecttalent.cognitive.QuestionActivity;
import edu.usc.projecttalent.cognitive.R;
import edu.usc.projecttalent.cognitive.model.Answer;
import edu.usc.projecttalent.cognitive.model.Block;
import edu.usc.projecttalent.cognitive.model.Section;
import edu.usc.projecttalent.cognitive.spatial.Practice;

/**
 * Reaction time class.
 *
 * @author Kay (initial), Anindya Dutta (optimization)
 * @version 2.0
 */

public class Question extends QuestionActivity {
    private int counter = 0;
    private long start;
    private static final int NO_OF_TRIALS = 20;
    private boolean isRed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reac_question);

        mSkipClass = Practice.class;
        mSection = new Section(getString(R.string.reaction_time));
        mBlock = new Block(1);

        Button space = (Button) findViewById(R.id.buttonSpace);
        Random r = new Random();
        int low = 2, high = 8; //between 2 and 8 seconds.

        final ImageView image = (ImageView) findViewById(R.id.imageView);
        image.setImageResource(R.drawable.cross);

        final Handler handler = new Handler();

        Runnable runnable = () -> {
            image.setImageResource(R.drawable.red_circle_large);
            isRed = true;
            mAnswer = new Answer();
            start = System.currentTimeMillis();
        };

        handler.postDelayed(runnable, 1000 * (r.nextInt(high - low) + low));

        space.setOnTouchListener((v, event) -> {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                setColor(space, android.R.color.black, android.R.color.white);

                if (isRed) {
                    mAnswer.endAnswer(System.currentTimeMillis() - start, true);
                    counter++;
                    image.setImageResource(R.drawable.cross);
                    isRed = false;
                } else {
                    mAnswer = new Answer();
                    mAnswer.endAnswer(0, false);
                }
                mBlock.addAnswer(mAnswer);
                if (counter == NO_OF_TRIALS) {
                    mSection.addBlock(mBlock);
                    finishSection();
                } else {
                    handler.postDelayed(runnable, 1000 * (r.nextInt(high - low) + low));
                }
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                setColor(space, android.R.color.white, android.R.color.black);
            }
            return true;
        });

    }

    private void setColor(Button v, int background, int text) {
        v.setBackgroundColor(getResources().getColor(background));
        v.setTextColor(getResources().getColor(text));
    }
}
