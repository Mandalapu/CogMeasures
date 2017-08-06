package edu.usc.projecttalent.cognitive.thurstone;

import android.content.Intent;
import android.os.Bundle;

import edu.usc.projecttalent.cognitive.BaseActivity;
import edu.usc.projecttalent.cognitive.R;

public class MainActivity_Th extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_thurstone);
        (findViewById(R.id.btnNext)).setOnClickListener(v -> startActivityForResult(new Intent(this, SecondActivity.class), 1));
    }
}
