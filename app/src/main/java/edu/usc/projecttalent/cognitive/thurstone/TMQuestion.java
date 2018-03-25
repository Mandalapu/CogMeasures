package edu.usc.projecttalent.cognitive.thurstone;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.LinkedList;

import edu.usc.projecttalent.cognitive.BR;
import edu.usc.projecttalent.cognitive.QuestionActivity;
import edu.usc.projecttalent.cognitive.R;
import edu.usc.projecttalent.cognitive.Timer;
import edu.usc.projecttalent.cognitive.databinding.ActivityThurAnswerBinding;
import edu.usc.projecttalent.cognitive.holders.TMItem;
import edu.usc.projecttalent.cognitive.model.Answer;
import edu.usc.projecttalent.cognitive.model.Block;
import edu.usc.projecttalent.cognitive.model.Section;
import edu.usc.projecttalent.cognitive.model.Survey;
import edu.usc.projecttalent.cognitive.reasoning.ARInstruction;

/**
 * Thurstone question activity. This activity is used for recording user answers.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class TMQuestion extends QuestionActivity {

    /**
     * View that has been clicked for the answer.
     */
    private View oldView;
    /**
     * binding for each question.
     */
    private ActivityThurAnswerBinding mBinding;

    /**
     * Set a timer and show all questions to the user. The entire thing is one block.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int questionSet = getIntent().getIntExtra("answer",R.array.th_practice);

        //set the name of the section.
        mSection = new Section(getString(questionSet == R.array.th_practice ? R.string.thurs_example : R.string.thurstone_title));
        mContext = this;
        mTimer = Timer.getTimer(3); //timer for 3 minutes.
        prepareFilter();

        Resources res = getResources();
        TypedArray questions = res.obtainTypedArray(questionSet);
        mQueue = new LinkedList<>();
        for (int i = 0; i < questions.length(); i++) { //add all questions.
            mQueue.add(new TMItem(res.obtainTypedArray(questions.getResourceId(i, 0))));
        }

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_thur_answer);
        TableRow options = findViewById(R.id.options);
        setOptionListener(options);
        mBlock = new Block();
        mScore = 0;

        nextQuestion();
        setNextListener(options, questionSet);
    }

    /**
     * Sets the next listener. Checks for answers, calculates scores, updates JSON objects and
     * redirects to appropriate activities.
     * @param options the set of answers
     * @param questionSet the question set ID (practice or real test)
     */
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
            mAnswer.endAnswer(oldView == null ? -99 : options.indexOfChild(oldView) + 1);
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
            createFile("thurstone_", 3);
        };
        (findViewById(R.id.next)).setOnClickListener(listener);
    }

    /**
     * Add the next question to the activity binding and reset timers.
     */
    private void nextQuestion() {
        mBinding.setVariable(BR.item, mQueue.remove());
        mAnswer = new Answer();
        mTimer.startTimer();
        mFtWarn = true;
    }

    /**
     * Set the listener for the answer. Captures user clicks on the options.
     * @param options the set of options.
     */
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

    /**
     * starts the test after all practice items have been answered.
     * @return 0, indicating that the dialog was shown properly.
     */
    private int startTest() {
        Intent intent = new Intent(this, TMRunner.class);
        intent.putExtra("questions", R.array.th_questions);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dialog = builder.setMessage(R.string.real_test_instr)
                .setNeutralButton(R.string.begin, (d, which) -> startActivityForResult(intent, 1))
                .setCancelable(false)
                .create();
        dialog.show();
        TextView textView =  dialog.findViewById(android.R.id.message);
        textView.setTextSize(30);
        return 0;
    }

    /**
     * end this section and move to Abstract reasoning.
     * @return 0, indicating that the dialog was shown properly.
     */
    private int endSection() {
        createFile("thurstone_", 3);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dialog = builder.setMessage(R.string.pressnext)
                .setNeutralButton(R.string.next, (d, which) -> startActivityForResult(new Intent(this, ARInstruction.class), 1))
                .setCancelable(false)
                .create();
        dialog.show();
        TextView textView =  dialog.findViewById(android.R.id.message);
        textView.setTextSize(30);
        return 0;
    }
}
