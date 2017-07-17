package edu.usc.projecttalent.cognitive.thurstone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

import edu.usc.projecttalent.cognitive.R;

/**
 * Created by kayigwe on 6/24/17.
 */

public class TestImageChange extends Activity {

    int[] imageArray = {R.drawable.zero, R.mipmap.jacket_one_dark, R.mipmap.girl_four_dark, R.mipmap.horse_one_dark, R.mipmap.pumpkin_four_dark, R.mipmap.umbrella_four_dark, R.mipmap.chicken_two_dark,
                        R.mipmap.duck_four_dark, R.mipmap.elephant_one, R.mipmap.girl_door_one, R.mipmap.cake_two, R.mipmap.fire_hat_one, R.mipmap.baby_bird_one,
                        R.mipmap.boat_three, R.mipmap.ship_four, R.mipmap.row_boat_two, R.mipmap.clown_three, R.mipmap.nest_four, R.mipmap.party_hat_three,
                        R.mipmap.girl_window_one, R.mipmap.key_three, R.mipmap.butterfly_three, R.mipmap.chick_two, R.mipmap.house_one, R.mipmap.toys_three,
                        R.mipmap.toy_egg_four, R.mipmap.kennel_three, R.mipmap.boots_two, R.mipmap.box_four};

    // Declare globally
    private int position = -1;
    //This timer will call each of the seconds.
    Timer mTimer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.real_test_image_change);

        final ImageView imageChangeReal = (ImageView) findViewById(R.id.imgeChangeReal);

        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                // As timer is not a Main/UI thread need to do all UI task on runOnUiThread
                TestImageChange.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // increase your position so new image will show
                        position++;
                        // check whether position increased to length then set it to 0
                        if (position >= imageArray.length){
                            startActivity(new Intent(getApplicationContext(), TestImageAnswers.class));
                            mTimer.cancel();}
                        else
                            imageChangeReal.setImageResource(imageArray[position]);
                    }
                });
            }
        }, 0, 5000);
    }
    @Override
    public void onBackPressed() {
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                setResult(Activity.RESULT_OK, data);
                super.finish();
            }
        }
    }
    }

