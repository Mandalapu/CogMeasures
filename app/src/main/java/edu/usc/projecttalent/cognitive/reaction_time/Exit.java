package edu.usc.projecttalent.cognitive.reaction_time;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import edu.usc.projecttalent.cognitive.R;
import edu.usc.projecttalent.cognitive.model.Block;
import edu.usc.projecttalent.cognitive.model.Section;
import edu.usc.projecttalent.cognitive.model.Survey;
import edu.usc.projecttalent.cognitive.spatial.SPpractice_Activity;


/**
 * Created by kayigwe on 6/26/17.
 */

public class Exit extends Activity {

    Section mSection;
    Block mBlock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.done);

        mSection = new Section("Thurstone");
        mBlock = new Block(1);
        final int correct = 0;

        (findViewById(R.id.buttonSpace)).setOnClickListener(v -> {
            mBlock.endBlock(correct);
            mSection.addBlock(mBlock);
            Survey.getSurvey().addSection(mSection);
            finishSection();
        });
    }

    private void finishSection() {
        mSection.endSection();
        Survey.getSurvey().addSection(mSection);
        startActivityForResult(new Intent(this, SPpractice_Activity.class), 1);
    }

    @Override
    public void onBackPressed() {}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                setResult(Activity.RESULT_OK, data);
                super.finish();
            }
        }
    }
}
