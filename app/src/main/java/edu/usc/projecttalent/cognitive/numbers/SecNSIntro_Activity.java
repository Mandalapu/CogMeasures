package edu.usc.projecttalent.cognitive.numbers;

import android.content.Intent;
import android.os.Bundle;

import edu.usc.projecttalent.cognitive.BaseActivity;
import edu.usc.projecttalent.cognitive.R;

public class SecNSIntro_Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_nsintro_);
        (findViewById(R.id.next)).setOnClickListener(v -> {
            startActivityForResult(new Intent(this, Set3Item1Activity.class), 1);
        });
    }
}
