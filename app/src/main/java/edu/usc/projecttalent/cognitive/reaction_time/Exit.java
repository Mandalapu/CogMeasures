package edu.usc.projecttalent.cognitive.reaction_time;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import edu.usc.projecttalent.cognitive.R;
import edu.usc.projecttalent.cognitive.model.Answer;
import edu.usc.projecttalent.cognitive.model.Block;
import edu.usc.projecttalent.cognitive.model.Section;
import edu.usc.projecttalent.cognitive.model.Survey;
import edu.usc.projecttalent.cognitive.spatial.SPpractice_Activity;


/**
 * Created by kayigwe on 6/26/17.
 */

public class Exit extends Activity {

    Button spacebar;
    //Context mContext;
    Section mSection;
    Block mBlock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.done);


        //Section mSection;
        mSection = new Section("Thurstone");
        mBlock = new Block(1);
        Answer mAnswer;
        mAnswer = new Answer();
        final int correct = 0;


        spacebar = (Button) findViewById(R.id.buttonSpace);

        spacebar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getApplicationContext(), SPpractice_Activity.class));
                mBlock.endBlock(correct);
                mSection.addBlock(mBlock);
                mSection.endSection(); //end this section.
                Survey.getSurvey().addSection(mSection);
                finishSection();
                //finish();
            }
        });
    }

    private void finishSection() {
        mSection.endSection(); //end this section.
        Survey.getSurvey().addSection(mSection); //add V2D section to survey.
        Intent intent = new Intent(getApplicationContext(), SPpractice_Activity.class);
        //startActivity(new Intent(getApplicationContext(), SPpractice_Activity.class));
        startActivityForResult(intent, 1);
       // finish();
    }

    @Override
    public void onBackPressed() {
    }

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
