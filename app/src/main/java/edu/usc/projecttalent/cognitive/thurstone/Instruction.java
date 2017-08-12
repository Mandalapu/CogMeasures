package edu.usc.projecttalent.cognitive.thurstone;

import android.content.Intent;
import android.os.Bundle;

import edu.usc.projecttalent.cognitive.BaseActivity;
import edu.usc.projecttalent.cognitive.R;

public class Instruction extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thur_instr);
        (findViewById(R.id.next)).setOnClickListener(v -> startActivityForResult(new Intent(this, MoreInstr.class), 1));
    }
}
