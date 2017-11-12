package edu.usc.projecttalent.cognitive.reasoning;

import android.os.Bundle;

import edu.usc.projecttalent.cognitive.ARBase;
import edu.usc.projecttalent.cognitive.R;

/**
 * Instructions 2 for Abstract reasoning section.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class ARInstruction2 extends ARBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar_instr);
        (findViewById(R.id.next)).setOnClickListener(v -> startTask(1));
    }
}
