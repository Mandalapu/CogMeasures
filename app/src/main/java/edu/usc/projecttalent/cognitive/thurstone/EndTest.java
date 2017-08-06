package edu.usc.projecttalent.cognitive.thurstone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import edu.usc.projecttalent.cognitive.R;
import edu.usc.projecttalent.cognitive.reasoning.SecAR_Activity;

/**
 * End section for Thurstone
 *
 * @author Kay Igwe
 * @version 1.0
 */

public class EndTest extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_test);
        (findViewById(R.id.bntNextExample)).setOnClickListener(v ->
                startActivityForResult(new Intent(this, SecAR_Activity.class), 1));
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        setResult(Activity.RESULT_OK, data);
        super.finish();
    }
}
