package edu.usc.projecttalent.cognitive.reaction_time;

import android.content.Intent;
import android.os.Bundle;

import edu.usc.projecttalent.cognitive.BaseActivity;
import edu.usc.projecttalent.cognitive.R;

public class RTInstruction extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reac_instr);
        (findViewById(R.id.buttonSpace)).setOnClickListener(v ->
                startActivityForResult(new Intent(this, RTQuestion.class), 1));
    }
}
