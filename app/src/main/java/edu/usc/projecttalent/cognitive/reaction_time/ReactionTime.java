package edu.usc.projecttalent.cognitive.reaction_time;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

import edu.usc.projecttalent.cognitive.FinishActivity;
import edu.usc.projecttalent.cognitive.QuestionTimer;
import edu.usc.projecttalent.cognitive.R;
import edu.usc.projecttalent.cognitive.model.*;

/**
 * Created by kayigwe on 6/25/17.
 */

public class ReactionTime extends Activity {
    ImageView imageView;
    Button btnSpace;
    private int[] times_milli = new int[]{2000, 3000, 4000, 5000, 6000, 7000, 8000};
    int counter = 0;

    Context mContext;
    Section mSection;
    Block mBlock;
    Answer mAnswer;
    long start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_change);

        mSection = new Section(getString(R.string.reaction_time));
        mBlock = new Block(1);
        //QuestionTimer.startTimer(mContext);

        //prepare timer.
        IntentFilter filter = new IntentFilter();
        filter.addAction(QuestionTimer.WARNING);
        filter.addAction(QuestionTimer.QUIT);
        filter.addAction(QuestionTimer.RESUME);
        registerReceiver(mReceiver, filter);

        mContext = this;

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

                if (imageView.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.red_circle_large).getConstantState()) {
                    mAnswer = new Answer();
                    start = System.currentTimeMillis();
                }
                btnSpace.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        imageView.setImageResource(R.drawable.cross);
                        mAnswer.endAnswer(System.currentTimeMillis() - start);
                        mBlock.addAnswer(mAnswer);
                        counter++;
                        if (counter >= 4) {
                            mSection.addBlock(mBlock);
                            mSection.endSection(); //end this section.
                            //Survey.getSurvey().addSection(mSection);
                            //startActivity(new Intent(getApplicationContext(), Exit.class));
                            finishSection();
                        }
                    }
                });
            }
        }, randomTime);
    }

    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals(QuestionTimer.QUIT)) {
                mSection.addBlock(mBlock);
                mSection.endSection(); //end this section.
                //Survey.getSurvey().addSection(mSection);
                finishSection(); //go to end of section.
            } else if (action.equals(QuestionTimer.RESUME)) { //reset timer for the same question.
                QuestionTimer.startTimer(mContext);
            }
        }
    };

    private void finishSection() {
        Survey.getSurvey().addSection(mSection); //add V2D section to survey.
        Intent intent = new Intent(mContext, Exit.class);
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
