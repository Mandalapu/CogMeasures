package edu.usc.projecttalent.cognitive.thurstone;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

import edu.usc.projecttalent.cognitive.R;

/**
 * Example images switcher activity.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class ExampleImageChange extends Activity {

    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_change_example);

        ImageView example = (ImageView) findViewById(R.id.imgeChangeExample);
        TypedArray images = getResources().obtainTypedArray(R.array.thurstone_ex);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    if (position >= images.length()) {
                        startActivityForResult(new Intent(getApplicationContext(), ExampleImageAnswers.class), 1);
                        timer.cancel();
                    } else
                        example.setImageResource(images.getResourceId(position++, -1));
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

