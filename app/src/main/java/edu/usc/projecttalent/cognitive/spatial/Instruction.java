package edu.usc.projecttalent.cognitive.spatial;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import edu.usc.projecttalent.cognitive.BaseActivity;
import edu.usc.projecttalent.cognitive.R;

/**
 * Spatial introduction. Tell the user about the section and what the questions contain.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class Instruction extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_intro);
        Button button = (Button) findViewById(R.id.next);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(Instruction.this, Sample.class);
            startActivityForResult(intent, 1);
        });
    }
}
