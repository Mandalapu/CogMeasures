package edu.usc.projecttalent.cognitive.vocab;

import android.content.Intent;
import android.os.Bundle;

import edu.usc.projecttalent.cognitive.BaseActivity;
import edu.usc.projecttalent.cognitive.R;

public class VocabIntro extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab_intro);

        (findViewById(R.id.next)).setOnClickListener(v ->
                startActivityForResult(new Intent(this, VO31_Activity.class), 1));
    }
}
