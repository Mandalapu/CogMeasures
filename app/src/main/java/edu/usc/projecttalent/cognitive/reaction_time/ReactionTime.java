package edu.usc.projecttalent.cognitive.reaction_time;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

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

    Section mSection;
    Block mBlock;
    Answer mAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_change);

        mSection = new Section(getString(R.string.reaction_time));
        mBlock = new Block(1);

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
                }
                btnSpace.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        imageView.setImageResource(R.drawable.cross);
                        mAnswer.endAnswer(0, true);
                        mBlock.addAnswer(mAnswer);
                        counter++;
                        if (counter >= 20) {
                            mSection.addBlock(mBlock);
                            Survey.getSurvey().addSection(mSection);
                            startActivity(new Intent(getApplicationContext(), Exit.class));
                        }
                    }
                });
            }
        }, randomTime);
    }

    @Override
    public void onBackPressed() {}
}
