package edu.usc.projecttalent.cognitive.reaction_time;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import edu.usc.projecttalent.cognitive.R;
import edu.usc.projecttalent.cognitive.spatial.SPpractice_Activity;


/**
 * Exit from Reaction time to Spatial Visualization.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class Exit extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.done);

        (findViewById(R.id.buttonSpace)).setOnClickListener(v ->
                startActivityForResult(new Intent(this, SPpractice_Activity.class), 1));
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
