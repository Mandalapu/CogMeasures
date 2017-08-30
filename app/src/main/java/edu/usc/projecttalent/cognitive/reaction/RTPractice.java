package edu.usc.projecttalent.cognitive.reaction;

import android.content.Intent;
import android.os.Bundle;

import edu.usc.projecttalent.cognitive.BaseActivity;
import edu.usc.projecttalent.cognitive.R;

public class RTPractice extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reac_practice);

        (findViewById(R.id.next)).setOnClickListener(v -> {
            Intent intent = new Intent(this, RTQuestion.class);
            intent.putExtra("trials", 5);
            startActivityForResult(intent, 1);
        });
    }
}
