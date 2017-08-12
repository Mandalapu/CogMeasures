package edu.usc.projecttalent.cognitive.thurstone;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

import edu.usc.projecttalent.cognitive.BaseActivity;
import edu.usc.projecttalent.cognitive.R;

/**
 * Change the images in the test.
 */

public class TestRunner extends BaseActivity {

    int[] imageArray = {R.drawable.zero, R.drawable.jacket_one_dark, R.drawable.girl_four_dark, R.drawable.horse_one_dark, R.drawable.pumpkin_four_dark, R.drawable.umbrella_four_dark, R.drawable.chicken_two_dark,
            R.drawable.duck_four_dark, R.drawable.elephant_one, R.drawable.girl_door_one, R.drawable.cake_two, R.drawable.fire_hat_one, R.drawable.baby_bird_one,
            R.drawable.boat_three, R.drawable.ship_four, R.drawable.row_boat_two, R.drawable.clown_three, R.drawable.nest_four, R.drawable.party_hat_three,
            R.drawable.girl_window_one, R.drawable.key_three, R.drawable.butterfly_three, R.drawable.chick_two, R.drawable.house_one, R.drawable.toys_three,
            R.drawable.toy_egg_four, R.drawable.kennel_three, R.drawable.boots_two, R.drawable.box_four};

    // Declare globally
    private int position = -1;
    //This timer will call each of the seconds.
    Timer mTimer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thur_testrunner);

        final ImageView imageChangeReal = (ImageView) findViewById(R.id.imgeChangeReal);

        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                // As timer is not a Main/UI thread need to do all UI task on runOnUiThread
                TestRunner.this.runOnUiThread(() -> {
                    position++;
                    if (position >= imageArray.length) {
                        startActivityForResult(new Intent(getApplicationContext(), TestAnswer.class), 1);
                        mTimer.cancel();
                    } else
                        imageChangeReal.setImageResource(imageArray[position]);
                });
            }
        }, 0, 5000);
    }
}

