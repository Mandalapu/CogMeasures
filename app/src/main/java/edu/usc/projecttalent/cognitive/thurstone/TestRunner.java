package edu.usc.projecttalent.cognitive.thurstone;

import android.content.Intent;
import android.content.res.TypedArray;
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

    private int position = -1;
    Timer mTimer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thur_testrunner);

        final ImageView imageChangeReal = (ImageView) findViewById(R.id.imgeChangeReal);
        TypedArray image = getResources().obtainTypedArray(R.array.th_questions);

        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                // As timer is not a Main/UI thread need to do all UI task on runOnUiThread
                TestRunner.this.runOnUiThread(() -> {
                    position++;
                    if (position >= image.length()) {
                        startActivityForResult(new Intent(getApplicationContext(), TestAnswer.class), 1);
                        mTimer.cancel();
                    } else
                        imageChangeReal.setImageResource(image.getResourceId(position, 0));
                });
            }
        }, 0, 5000);
    }
}
