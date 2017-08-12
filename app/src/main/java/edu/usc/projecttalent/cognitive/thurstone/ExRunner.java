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
 * Item images switcher activity.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class ExRunner extends BaseActivity {

    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thur_exrunner);

        ImageView example = (ImageView) findViewById(R.id.imgeChangeExample);
        TypedArray images = getResources().obtainTypedArray(R.array.thurstone_ex);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    if (position >= images.length()) {
                        startActivityForResult(new Intent(getApplicationContext(), ExAnswer.class), 1);
                        timer.cancel();
                    } else
                        example.setImageResource(images.getResourceId(position++, -1));
                });
            }
        }, 0, 5000);
    }
}

