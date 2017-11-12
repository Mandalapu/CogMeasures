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
 * Thurstone images switcher activity.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class TMRunner extends BaseActivity {

    /**
     * the item that is being currently shown.
     */
    private int position = 0;
    /**
     * Timer for the thurstone runner, timed 5 seconds.
     */
    Timer mTimer = new Timer();

    /**
     * Shows a set of images, equally interval-ed at 5 seconds to the user.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thur_exrunner);

        ImageView example = findViewById(R.id.image);
        int questions = getIntent().getIntExtra("questions", R.array.thurstone_ex);
        TypedArray images = getResources().obtainTypedArray(questions);

        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    if (position >= images.length()) {
                        Intent intent = new Intent(getApplicationContext(), TMQuestion.class);
                        intent.putExtra("answer", questions == R.array.thurstone_ex ? R.array.th_practice : R.array.th_set);
                        startActivityForResult(intent, 1);
                        mTimer.cancel();
                    } else
                        example.setImageResource(images.getResourceId(position++, -1));
                });
            }
        }, 0, 5000);
    }
}

