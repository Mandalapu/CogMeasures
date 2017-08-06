package edu.usc.projecttalent.cognitive.reaction_time;

import android.content.Intent;
import android.os.Bundle;

import edu.usc.projecttalent.cognitive.BaseActivity;
import edu.usc.projecttalent.cognitive.R;

public class MainActivity_Reac extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_reaction);
        (findViewById(R.id.buttonSpace)).setOnClickListener(v ->
                startActivityForResult(new Intent(this, ReactionTime.class), 1));
    }
}
