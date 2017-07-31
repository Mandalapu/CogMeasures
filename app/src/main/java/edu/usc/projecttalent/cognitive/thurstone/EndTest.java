package edu.usc.projecttalent.cognitive.thurstone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import edu.usc.projecttalent.cognitive.R;
import edu.usc.projecttalent.cognitive.model.Section;
import edu.usc.projecttalent.cognitive.model.Survey;
import edu.usc.projecttalent.cognitive.reasoning.SecAR_Activity;

/**
 * Created by kayigwe on 6/26/17.
 */

public class EndTest extends Activity {

    Section mSection;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_test);
        (findViewById(R.id.bntNextExample)).setOnClickListener(v -> finishSection());
    }

    private void finishSection() {
        mSection.endSection();
        Survey.getSurvey().addSection(mSection);
        Intent intent = new Intent(this, SecAR_Activity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onBackPressed() {}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        setResult(Activity.RESULT_OK, data);
        super.finish();
    }
}
