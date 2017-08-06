package edu.usc.projecttalent.cognitive.thurstone;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;

import java.util.LinkedList;
import java.util.Queue;

import edu.usc.projecttalent.cognitive.R;
import edu.usc.projecttalent.cognitive.databinding.ExampleAnswersBinding;
import edu.usc.projecttalent.cognitive.model.Answer;
import edu.usc.projecttalent.cognitive.model.Block;
import edu.usc.projecttalent.cognitive.model.Section;
import edu.usc.projecttalent.cognitive.model.Survey;
import edu.usc.projecttalent.cognitive.reasoning.SecAR_Activity;

/**
 * Test for Thurstone.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class TestImageAnswers extends Activity {

    private Answer mAnswer;
    private View oldView;
    private int score;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Section section = new Section(getString(R.string.thurstone_title));
        Drawable highlight = ContextCompat.getDrawable(this, R.drawable.highlight);

        Resources res = getResources();
        TypedArray questions = res.obtainTypedArray(R.array.th_set);
        Queue<THItem> mQueue = new LinkedList<>();
        for (int i = 0; i < questions.length(); i++) {
            mQueue.add(new THItem(res.obtainTypedArray(questions.getResourceId(i, 0))));
        }

        ExampleAnswersBinding binding = DataBindingUtil.setContentView(this, R.layout.example_answers);
        binding.setItem(mQueue.remove());
        Button btn = (Button) findViewById(R.id.next);
        btn.setEnabled(false);

        TableRow options = (TableRow) findViewById(R.id.options);
        for (int i = 0; i < options.getChildCount(); i++) {
            (options.getChildAt(i)).setOnClickListener(v -> {
                v.setBackground(highlight);
                if (oldView != null)
                    oldView.setBackground(null);
                oldView = v;
                btn.setEnabled(true);
            });
        }

        Block block = new Block(1);
        mAnswer = new Answer();

        btn.setOnClickListener(v -> {
            if (oldView != null) {
                THItem question = binding.getItem();
                boolean correct1 = false;
                if (options.indexOfChild(oldView) == question.getAnsOption()) {
                    score++; //correct answer.
                    correct1 = true;
                }
                mAnswer.endAnswer(oldView == null ? -99 : options.indexOfChild(oldView) + 1, correct1); //to shift indices to 1-5.
                block.addAnswer(mAnswer);
                if (oldView != null)
                    oldView.setBackground(null);
                oldView = null;
                if (!mQueue.isEmpty()) {
                    mAnswer = new Answer();
                    binding.setItem(mQueue.remove());
                } else {
                    block.endBlock(score);
                    section.addBlock(block);
                    section.endSection(); //end this section.
                    Survey.getSurvey().addSection(section);

                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    View view;
                    Button button;

                    if (score == 0) {
                        view = getLayoutInflater().inflate(R.layout.end_test, null);
                        button = (Button) view.findViewById(R.id.bntNextExample);

                    } else {
                        view = getLayoutInflater().inflate(R.layout.exit_test, null);
                        button = (Button) view.findViewById(R.id.btnExit);
                    }

                    button.setOnClickListener(v1111 -> startActivityForResult(new Intent(this, SecAR_Activity.class), 1));

                    builder.setView(view);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    dialog.setCanceledOnTouchOutside(false);
                }
            }
        });
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
