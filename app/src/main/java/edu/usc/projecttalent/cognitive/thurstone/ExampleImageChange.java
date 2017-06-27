package edu.usc.projecttalent.cognitive.thurstone;

import edu.usc.projecttalent.cognitive.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;


import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by kayigwe on 6/24/17.
 */

public class ExampleImageChange extends Activity {

    int[] imageArray = {R.drawable.zero, R.drawable.cop, R.drawable.dog, R.drawable.hat, R.drawable.table, R.drawable.truck, R.drawable.window};

    // Declare globally
    private int position = -1;
    //This timer will call each of the seconds.
    Timer mTimer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_change_example);

        final ImageView imageChangeExample = (ImageView) findViewById(R.id.imgeChangeExample);

        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                // As timer is not a Main/UI thread need to do all UI task on runOnUiThread
                ExampleImageChange.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // increase your position so new image will show
                        position++;
                        // check whether position increased to length then set it to 0
                        // so it will show images in circuler
                        if (position >= imageArray.length){
                            startActivity(new Intent(getApplicationContext(), ExampleImageAnswers.class));
                            mTimer.cancel();}
                        else
                            imageChangeExample.setImageResource(imageArray[position]);
                    }
                });
            }
        }, 0, 5000);
    }
    @Override
    public void onBackPressed() {
    }
    }

