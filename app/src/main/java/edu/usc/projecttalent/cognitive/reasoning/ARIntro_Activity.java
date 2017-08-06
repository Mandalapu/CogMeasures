package edu.usc.projecttalent.cognitive.reasoning;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import edu.usc.projecttalent.cognitive.R;

/**
 * Introduction to the abstract reasoning section.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class ARIntro_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arintro_);
        (findViewById(R.id.next)).setOnClickListener(v -> {
            startActivityForResult(new Intent(this, AR31_Activity.class), 1);
        });
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

    @Override
    public void onBackPressed() {
    }
}
