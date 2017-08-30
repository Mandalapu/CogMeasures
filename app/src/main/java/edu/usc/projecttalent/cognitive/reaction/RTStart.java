package edu.usc.projecttalent.cognitive.reaction;

import android.content.Intent;
import android.os.Bundle;

import edu.usc.projecttalent.cognitive.BaseActivity;
import edu.usc.projecttalent.cognitive.R;

public class RTStart extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rtstart);

        (findViewById(R.id.next)).setOnClickListener(v ->
            startActivityForResult(new Intent(this, RTQuestion.class), 1));
    }
}
