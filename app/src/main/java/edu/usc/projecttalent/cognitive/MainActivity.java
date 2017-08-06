package edu.usc.projecttalent.cognitive;

import android.content.Intent;
import android.os.Bundle;

import edu.usc.projecttalent.cognitive.vocab.VocabIntro;

/**
 * Introduction page that welcomes users to Cognitive Measures.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        (findViewById(R.id.next)).setOnClickListener(v -> startActivityForResult(new Intent(this, VocabIntro.class), 1));
    }
}
