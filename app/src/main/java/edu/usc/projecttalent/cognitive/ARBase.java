package edu.usc.projecttalent.cognitive;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.databinding.ViewDataBinding;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.gson.Gson;

import java.util.LinkedList;

import edu.usc.projecttalent.cognitive.databinding.ActivityArQuestionBinding;
import edu.usc.projecttalent.cognitive.databinding.ActivitySpQuestionBinding;
import edu.usc.projecttalent.cognitive.model.Answer;
import edu.usc.projecttalent.cognitive.model.Block;
import edu.usc.projecttalent.cognitive.reasoning.ARIntroduction;
import edu.usc.projecttalent.cognitive.holders.ARItem;
import edu.usc.projecttalent.cognitive.reasoning.ARPractice1;
import edu.usc.projecttalent.cognitive.reasoning.ARPractice2;
import edu.usc.projecttalent.cognitive.BR;

/**
 * Common code for Abstract reasoning and Spatial visualization.
 * Created by Anindya Dutta on 8/23/2017.
 */

public abstract class ARBase extends QuestionActivity {

    /**
     * the old view that was saved in the previous click.
     */
    private static View oldView;
    /**
     * binding to all the questions.
     */
    protected static ViewDataBinding mBinding;
    /**
     * reference to the next button.
     */
    protected static Button next;

    /**
     * listener for next button.
     * Handles broadcasts and also handles skipping section logic.
     */
    protected View.OnClickListener nextListener = v ->  {
        if (oldView == null && mFtWarn) {
            mFtWarn = false;
            sendBroadcast(new Intent(Timer.NOANSWER));
            return;
        }
        checkSolution();
        if (oldView != null)
            oldView.setBackground(null);
        oldView = null;
        if (!mQueue.isEmpty()) {
            showNextQuestion();
            return;
        }
        mBlock.endBlock(mScore);
        mSection.addBlock(mBlock);

        if (mSection.getBlockSize() == 1) { //get next block.
            int block = nextSet();
            mBlock = new Block(getBlockId(block));
            TypedArray questions = getResources().obtainTypedArray(block); //all questions of Set X.
            mQueue = new LinkedList<>();
            for (int i = 0; i < questions.length(); i++) {
                mQueue.add(new ARItem(getResources().obtainTypedArray(questions.getResourceId(i, 0)))); //each question. ar_x1 .. ar_x3.
            }
            mScore = 0;
            showNextQuestion();
        } else {
            finishSection();
            boolean isAr = mBinding instanceof ActivityArQuestionBinding;
            createFile(isAr ? "ar_" : "sv_", isAr ? 4 : 0);
        }
    };

    /**
     * show the next question in the queue.
     */
    protected void showNextQuestion() {
        mAnswer = new Answer();
        mBinding.setVariable(BR.item, mQueue.remove());
        mTimer.startTimer();
        mFtWarn = true;
        next.setEnabled(false);
    }

    /**
     * method implemented separately in each subclass.
     * @return
     */
    protected int nextSet() {
        return 0;
    }

    /**
     * check if the solution is correct for this question.
     * also checks whether it is AR or SV and handles appropriately.
     */
    protected void checkSolution() {
        //check if it is AR or SV.
        boolean isAR = mBinding instanceof ActivityArQuestionBinding;
        LinearLayout options = findViewById(R.id.options);
        ARItem question = isAR ? ((ActivityArQuestionBinding) mBinding).getItem(): ((ActivitySpQuestionBinding)mBinding).getItem();
        boolean correct = false;
        if (options.indexOfChild(oldView) == question.getAnsPosition()) {
            mScore++; //correct answer.
            correct = true;
        }
        //add answer to block.
        mAnswer.endAnswer(oldView == null ? -99 : options.indexOfChild(oldView) + (isAR? 1 : 0), correct);
        mBlock.addAnswer(mAnswer);
    }

    /**
     * setting up the options listener for the section.
     * @param isSV false if abstract reasoning, true if spatial visualization.
     * @param example true if the item is an example item.
     */
    protected void setupOptionsListener(boolean isSV, boolean example) {
        View.OnClickListener optionListener = v -> {
            if (v != oldView) {
                v.setPadding(2, 2, 2, 2);
                v.setBackgroundColor(ContextCompat.getColor(this, R.color.black));
                if (oldView != null)
                    oldView.setBackground(null);
                oldView = v;
            }
        };

        LinearLayout options = findViewById(R.id.options);
        for (int i = isSV? 1: 0; i < options.getChildCount(); i++) {
            View v = options.getChildAt(i);
            v = example ? ((ViewGroup)v).getChildAt(0) : v;
            v.setOnClickListener(optionListener);
        }
    }

    /**
     * Gives an option to skip the rest of the examples and show the questions directly.
     * @param item the example item based on which decisions are taken.
     */
    protected void startTask(int item) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(R.string.start_now)
                .setMessage(R.string.start_task)
                .setNegativeButton(R.string.example, (dialog1, which) -> {
                    Intent intent = new Intent(this, item == 1 ? ARPractice1.class : ARPractice2.class);
                    startActivityForResult(intent, 1);
                })
                .setPositiveButton(R.string.start_task_confirm, (dialog2, which) -> {
                    Intent intent = new Intent(this, ARIntroduction.class);
                    startActivityForResult(intent, 1);
                })
                .setCancelable(false).create();
        dialog.show();
    }
}
