package edu.usc.projecttalent.cognitive;

import android.app.AlertDialog;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import java.util.Queue;

import edu.usc.projecttalent.cognitive.model.Answer;
import edu.usc.projecttalent.cognitive.model.Block;
import edu.usc.projecttalent.cognitive.model.Section;
import edu.usc.projecttalent.cognitive.model.Survey;

public abstract class QuestionActivity extends BaseActivity {

    protected Section mSection;
    protected Block mBlock;
    protected Answer mAnswer;
    protected Class mSkipClass;
    protected int mScore;
    protected Queue<Item> mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void finishSection() {
        mSection.endSection(); //end this section.
        Survey.getSurvey().addSection(mSection); //add AR section to survey.
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setMessage(R.string.pressnext)
                .setNeutralButton(R.string.next, (d, which) -> startActivityForResult(new Intent(this, mSkipClass), 1))
                .setCancelable(false)
                .create();
        dialog.show();
    }
}
