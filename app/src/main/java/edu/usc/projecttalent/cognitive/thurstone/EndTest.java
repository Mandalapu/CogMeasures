package edu.usc.projecttalent.cognitive.thurstone;

import edu.usc.projecttalent.cognitive.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by kayigwe on 6/26/17.
 */

public class EndTest extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_test);
    }
    @Override
    public void onBackPressed() {
    }
}
