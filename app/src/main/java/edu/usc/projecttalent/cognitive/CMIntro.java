package edu.usc.projecttalent.cognitive;

import android.content.Intent;
import android.os.Bundle;

import edu.usc.projecttalent.cognitive.vocab.VSInstruction;

/**
 * Activity that provides general instructions about the Cognitive Measures application.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class CMIntro extends BaseActivity {

    /**
     * Provide basic instructions and move to vocabulary section on clicking Next.
     * @param savedInstanceState nothing is sent to the bundle currently.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cmintro);
        setNext(VSInstruction.class);
        (findViewById(R.id.next)).setOnClickListener(v -> startActivityForResult(new Intent(this, VSInstruction.class), 1));
    }
}
