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

import edu.usc.projecttalent.cognitive.BR;
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

public class TMQuestion extends QuestionActivity {

    private View oldView;
    private ActivityThurAnswerBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int questionSet = getIntent().getIntExtra("answer",R.array.th_practice);

        mSection = new Section(getString(questionSet == R.array.th_practice ? R.string.thurs_example : R.string.thurstone_title));
        mContext = this;
        mTimer = Timer.getTimer(3);
        prepareFilter();

        Resources res = getResources();
        TypedArray questions = res.obtainTypedArray(questionSet);
        mQueue = new LinkedList<>();
        for (int i = 0; i < questions.length(); i++) {
            mQueue.add(new TMItem(res.obtainTypedArray(questions.getResourceId(i, 0))));
        }

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_thur_answer);
        TableRow options = (TableRow) findViewById(R.id.options);
        setOptionListener(options);
        mBlock = new Block(1);
        mScore = 0;

        nextQuestion();
        setNextListener(options, questionSet);
    }

    private void setNextListener(TableRow options, int questionSet) {
        View.OnClickListener listener = v -> {
            if (oldView == null && mFtWarn) {
                mFtWarn = false;
                sendBroadcast(new Intent(Timer.NOANSWER));
                return;
            }
            TMItem question = mBinding.getItem();
            boolean correct = false;
            if (options.indexOfChild(oldView) == question.getAnsPosition()) {
                mScore++; //correct answer.
                correct = true;
            }
            mAnswer.endAnswer(oldView == null ? -99 : options.indexOfChild(oldView) + 1, correct);
            mBlock.addAnswer(mAnswer);
            if (oldView != null)
                oldView.setBackground(null);
            oldView = null;
            if (!mQueue.isEmpty()) {
                nextQuestion();
                return;
            }
            mBlock.endBlock(mScore);
            mSection.addBlock(mBlock);
            if(questionSet == R.array.th_practice) {
                mSection.endSection(); //end this section.
                Survey.getSurvey().addSection(mSection);
                int nextStep = mScore == 0? endSection(): startTest();
                return;
            }
            mSkipClass = ARInstruction.class;
            finishSection();
        };
        (findViewById(R.id.next)).setOnClickListener(listener);
    }

    private void nextQuestion() {
        mBinding.setVariable(BR.item, mQueue.remove());
        mAnswer = new Answer();
        mTimer.startTimer();
        mFtWarn = true;
    }

    private void setOptionListener(TableRow options) {
        Drawable highlight = ContextCompat.getDrawable(this, R.drawable.btn_bg);
        for (int i = 0; i < options.getChildCount(); i++) {
            (options.getChildAt(i)).setOnClickListener(v -> {
                if (v != oldView) {
                    v.setPadding(2, 2, 2, 2);
                    v.setBackground(highlight);
                    if (oldView != null)
                        oldView.setBackground(null);
                    oldView = v;
                }
            });
        }
    }

    private int startTest() {
        Intent intent = new Intent(this, TMRunner.class);
        intent.putExtra("questions", R.array.th_questions);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dialog = builder.setMessage(R.string.real_test_instr)
                .setNeutralButton(R.string.begin, (d, which) -> startActivityForResult(intent, 1))
                .setCancelable(false)
                .create();
        dialog.show();
        return 0;
    }

    private int endSection() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dialog = builder.setMessage(R.string.pressnext)
                .setNeutralButton(R.string.next, (d, which) -> startActivityForResult(new Intent(this, ARInstruction.class), 1))
                .setCancelable(false)
                .create();
        dialog.show();
        return 0;
    }
}