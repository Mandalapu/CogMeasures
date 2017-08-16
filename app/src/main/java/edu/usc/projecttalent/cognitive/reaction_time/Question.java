package edu.usc.projecttalent.cognitive.reaction_time;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reac_question);

        mSkipClass = Practice.class;
        mSection = new Section(getString(R.string.reaction_time));
        mBlock = new Block(1);

        int[] times_milli = new int[]{2000, 3000, 4000, 5000, 6000, 7000, 8000};

        final Button btnSpace = (Button) findViewById(R.id.buttonSpace);
        final int randomTime = (times_milli[new Random().nextInt(times_milli.length)]);

        final ImageView image = (ImageView) findViewById(R.id.imageView);
        image.setImageResource(R.drawable.cross);

        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, randomTime);
                image.setImageResource(R.drawable.red_circle_large);

                if (image.getDrawable().getConstantState() ==
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.red_circle_large).getConstantState()) {
                    mAnswer = new Answer();
                    start = System.currentTimeMillis();
                }
                btnSpace.setOnClickListener(v -> {
                    image.setImageResource(R.drawable.cross);
                    mAnswer.endAnswer(System.currentTimeMillis() - start);
                    mBlock.addAnswer(mAnswer);
                    counter++;
                    if (counter >= 20) {
                        mSection.addBlock(mBlock);
                        finishSection();
                    }
                });
            }
        }, randomTime);
    }
}
