package edu.usc.projecttalent.cognitive.reaction_time;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.usc.projecttalent.cognitive.R;

/**
 * Created by kayigwe on 6/26/17.
 */

public class Exit extends Activity {

    Button spacebar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.done);

        spacebar = (Button) findViewById(R.id.buttonSpace);

        spacebar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getApplicationContext(), ReactionTime.class));
                //finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
    }
}
