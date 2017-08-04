package edu.usc.projecttalent.cognitive.reaction_time;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

import edu.usc.projecttalent.cognitive.QuestionTimer;
import edu.usc.projecttalent.cognitive.R;
import edu.usc.projecttalent.cognitive.model.Answer;
import edu.usc.projecttalent.cognitive.model.Block;
import edu.usc.projecttalent.cognitive.model.Section;
import edu.usc.projecttalent.cognitive.model.Survey;

/**
 * Reaction time class.
 * @author Kay (initial), Anindya Dutta (optimization)
 * @version 2.0
 */

public class ReactionTime extends Activity {
    private ImageView imageView;
    private Button btnSpace;
    private int[] times_milli = new int[]{2000, 3000, 4000, 5000, 6000, 7000, 8000};
    private int counter = 0;

    private Section mSection;
    private Block mBlock;
    private Answer mAnswer;
    private long start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_change);

        mSection = new Section(getString(R.string.reaction_time));
        mBlock = new Block(1);

        //prepare timer.
        IntentFilter filter = new IntentFilter();
        filter.addAction(QuestionTimer.WARNING);
        filter.addAction(QuestionTimer.QUIT);
        filter.addAction(QuestionTimer.RESUME);
        registerReceiver(mReceiver, filter);

        btnSpace = (Button) findViewById(R.id.buttonSpace);
        final int randomTime = (times_milli[new Random().nextInt(times_milli.length)]);

        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.cross);

        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, randomTime);
                imageView.setImageResource(R.drawable.red_circle_large);

                if (imageView.getDrawable().getConstantState() ==
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.red_circle_large).getConstantState()) {
                    mAnswer = new Answer();
                    start = System.currentTimeMillis();
                }
                btnSpace.setOnClickListener(v -> {
                    imageView.setImageResource(R.drawable.cross);
                    mAnswer.endAnswer(System.currentTimeMillis() - start);
                    mBlock.addAnswer(mAnswer);
                    counter++;
                    if (counter >= 20) {
                        mSection.addBlock(mBlock);
                        mSection.endSection();
                        finishSection();
                    }
                });
            }
        }, randomTime);
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals(QuestionTimer.QUIT)) {
                mSection.addBlock(mBlock);
                mSection.endSection();
                finishSection();
            } else if (action.equals(QuestionTimer.RESUME)) {
                QuestionTimer.startTimer(getApplicationContext());
            }
        }
    };

    private void finishSection() {
        Survey.getSurvey().addSection(mSection);
        Intent intent = new Intent(this, Exit.class);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onBackPressed() {}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        setResult(Activity.RESULT_OK, data);
        unregisterReceiver(mReceiver);
        super.finish();
    }
}
