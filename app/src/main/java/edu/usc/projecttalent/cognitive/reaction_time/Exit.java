package edu.usc.projecttalent.cognitive.reaction_time;

import android.content.Intent;
import android.os.Bundle;

import edu.usc.projecttalent.cognitive.BaseActivity;
import edu.usc.projecttalent.cognitive.R;
import edu.usc.projecttalent.cognitive.spatial.SPpractice_Activity;


/**
 * Exit from Reaction time to Spatial Visualization.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class Exit extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.done);

        (findViewById(R.id.buttonSpace)).setOnClickListener(v ->
                startActivityForResult(new Intent(this, SPpractice_Activity.class), 1));
    }
}
