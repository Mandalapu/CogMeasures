package edu.usc.projecttalent.cognitive.thurstone;

import edu.usc.projecttalent.cognitive.FinishActivity;
import edu.usc.projecttalent.cognitive.R;
import edu.usc.projecttalent.cognitive.model.Section;
import edu.usc.projecttalent.cognitive.model.Survey;
import edu.usc.projecttalent.cognitive.reasoning.ARIntro_Activity;
import edu.usc.projecttalent.cognitive.reasoning.SecAR_Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

/**
 * Created by kayigwe on 6/26/17.
 */

public class EndTest extends Activity {

    Button btn;
    Section mSection;
    Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_test);

        btn = (Button) findViewById(R.id.bntNextExample);
        MyOnClickListener myOnClickListener = new MyOnClickListener();
        btn.setOnClickListener(myOnClickListener);


    }


    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            finishSection();
        }
    }

    private void finishSection() {
        mSection.endSection(); //end this section.
        Survey.getSurvey().addSection(mSection); //add V2D section to survey.
        Intent intent = new Intent(getApplicationContext(), SecAR_Activity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onBackPressed() {}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        setResult(Activity.RESULT_OK, data);
        //unregisterReceiver(mReceiver);
        super.finish();
    }
}
