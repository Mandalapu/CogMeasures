package edu.usc.projecttalent.cognitive;

import android.content.Intent;
import android.os.Bundle;

import edu.usc.projecttalent.cognitive.vocab.VSInstruction;

/**
 * NSIntroduction page that welcomes users to Cognitive Measures.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class CMIntro extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cmintro);
        setNext(VSInstruction.class);
        (findViewById(R.id.next)).setOnClickListener(v -> startActivityForResult(new Intent(this, VSInstruction.class), 1));
    }
}
