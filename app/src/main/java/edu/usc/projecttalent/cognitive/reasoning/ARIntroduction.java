package edu.usc.projecttalent.cognitive.reasoning;

import android.content.Intent;
import android.os.Bundle;

import edu.usc.projecttalent.cognitive.BaseActivity;
import edu.usc.projecttalent.cognitive.R;

/**
 * ARIntroduction to the abstract reasoning section.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class ARIntroduction extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar_intro);
        (findViewById(R.id.next)).setOnClickListener(v -> startActivityForResult(new Intent(this, ARQuestion.class), 1));
    }
}
