package edu.usc.projecttalent.cognitive.thurstone;

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

import edu.usc.projecttalent.cognitive.QuestionActivity;
import edu.usc.projecttalent.cognitive.R;
import edu.usc.projecttalent.cognitive.Timer;
import edu.usc.projecttalent.cognitive.databinding.ActivityThurAnswerBinding;
import edu.usc.projecttalent.cognitive.model.Answer;
import edu.usc.projecttalent.cognitive.model.Block;
import edu.usc.projecttalent.cognitive.model.Section;
import edu.usc.projecttalent.cognitive.model.Survey;
import edu.usc.projecttalent.cognitive.reasoning.ARInstruction;

/**
 * Thurstone example activity.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class TMExAnswer extends QuestionActivity {

    private View oldView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSection = new Section(getString(R.string.thurs_example));
        mBlock = new Block(1);
        mFtWarn = true;
        mContext = this;
        mTimer = Timer.getTimer(3);
        prepareFilter();
        Drawable highlight = ContextCompat.getDrawable(this, R.drawable.btn_bg);

        Resources res = getResources();
        TypedArray questions = res.obtainTypedArray(R.array.th_practice);
        Queue<TMItem> mQueue = new LinkedList<>();
        for (int i = 0; i < questions.length(); i++) {
            mQueue.add(new TMItem(res.obtainTypedArray(questions.getResourceId(i, 0))));
        }

        ActivityThurAnswerBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_thur_answer);
        binding.setItem(mQueue.remove());
        mTimer.startTimer();
        Button btn = (Button) findViewById(R.id.next);

        TableRow options = (TableRow) findViewById(R.id.options);
        for (int i = 0; i < options.getChildCount(); i++) {
            (options.getChildAt(i)).setOnClickListener(v -> {
                if (v != oldView) {
                    v.setBackground(highlight);
                    if (oldView != null)
                        oldView.setBackground(null);
                    oldView = v;
                    btn.setEnabled(true);
                }
            });
        }

        mAnswer = new Answer();

        View.OnClickListener listener = v -> {
            if(oldView == null && mFtWarn) {
                mFtWarn = false;
                sendBroadcast(new Intent(Timer.NOANSWER));
                return;
            }
            TMItem question = binding.getItem();
            boolean correct = false;
            if (options.indexOfChild(oldView) == question.getAnsOption()) {
                mScore++; //correct answer.
                correct = true;
            }
            mAnswer.endAnswer(oldView == null ? -99 : options.indexOfChild(oldView) + 1, correct); //to shift indices from 1-5.
            mBlock.addAnswer(mAnswer);
            if (oldView != null)
                oldView.setBackground(null);
            oldView = null;
            if (!mQueue.isEmpty()) {
                mAnswer = new Answer();
                binding.setItem(mQueue.remove());
                mTimer.startTimer();
                mFtWarn = true;
            } else {
                mBlock.endBlock(mScore);
                mSection.addBlock(mBlock);
                mSection.endSection(); //end this section.
                Survey.getSurvey().addSection(mSection);
                if (mScore == 0) {
                    endSection();
                } else {
                    startTest();
                }
            }
        };

        btn.setOnClickListener(listener);
    }

    private void startTest() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dialog = builder.setMessage(R.string.real_test_instr)
                .setNeutralButton(R.string.begin, (d, which) -> startActivityForResult(new Intent(this, TMTestRunner.class), 1))
                .setCancelable(false)
                .create();
        dialog.show();
    }

    private void endSection() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dialog = builder.setMessage(R.string.pressnext)
                .setNeutralButton(R.string.next, (d, which) -> startActivityForResult(new Intent(this, ARInstruction.class), 1))
                .setCancelable(false)
                .create();
        dialog.show();
    }
}
