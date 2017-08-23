package edu.usc.projecttalent.cognitive.thurstone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.usc.projecttalent.cognitive.R;

public class Practice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        findViewById(R.id.next).setOnClickListener(v -> startActivityForResult(new Intent(this, ExRunner.class), 1));
    }
}
