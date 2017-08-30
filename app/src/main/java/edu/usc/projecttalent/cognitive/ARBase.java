package edu.usc.projecttalent.cognitive;

import android.content.Intent;
import android.content.res.TypedArray;
import android.databinding.ViewDataBinding;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.LinkedList;
import java.util.Queue;

import edu.usc.projecttalent.cognitive.databinding.ActivityArQuestionBinding;
import edu.usc.projecttalent.cognitive.databinding.ActivitySpQuestionBinding;
import edu.usc.projecttalent.cognitive.model.Answer;
import edu.usc.projecttalent.cognitive.model.Block;
import edu.usc.projecttalent.cognitive.reasoning.ARItem;

/**
 * Common code for Abstract reasoning and Spatial visualization.
 * Created by anind on 8/23/2017.
 */

public abstract class ARBase extends QuestionActivity {

    protected Queue<ARItem> mQueue;
    protected static View oldView;
    protected ViewDataBinding mBinding;
    protected Button next;

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
            mAnswer = new Answer();
            mBinding.setVariable(BR.item, mQueue.remove());
            mTimer.startTimer();
            mFtWarn = true;
            next.setEnabled(false);
            return;
        }
        mBlock.endBlock(mScore);
        mSection.addBlock(mBlock);
        if (mSection.getBlockSize() == 1) { //get next block.
            int block = nextSet();
            mBlock = new Block(getBlockId(block));
            TypedArray questions1 = getResources().obtainTypedArray(block); //all questions of Set X.
            mQueue = new LinkedList<>();
            for (int i = 0; i < questions1.length(); i++) {
                mQueue.add(new ARItem(getResources().obtainTypedArray(questions1.getResourceId(i, 0)))); //each question. ar_x1 .. ar_x3.
            }
            mScore = 0;
            mBinding.setVariable(BR.item, mQueue.remove());
            mTimer.startTimer();
            mFtWarn = true;
        } else {
            finishSection();
        }
    };

    protected abstract int nextSet();

    protected void checkSolution() {
        LinearLayout options = (LinearLayout) findViewById(R.id.options);
        ARItem question = (mBinding instanceof ActivityArQuestionBinding) ? ((ActivityArQuestionBinding) mBinding).getItem():
                ((ActivitySpQuestionBinding)mBinding).getItem();
        boolean correct = false;
        if (options.indexOfChild(oldView) == question.getAnsOption()) {
            mScore++; //correct answer.
            correct = true;
        }
        mAnswer.endAnswer(oldView == null ? -99 : options.indexOfChild(oldView) + 1, correct); //to shift indices from 1-5.
        mBlock.addAnswer(mAnswer);
    }

    protected void setupOptionsListener() {
        View.OnClickListener optionListener = v -> {
            if (v != oldView) {
                v.setPadding(2, 2, 2, 2);
                v.setBackgroundColor(ContextCompat.getColor(this, R.color.black));
                if (oldView != null)
                    oldView.setBackground(null);
                oldView = v;
            }
        };

        LinearLayout options = (LinearLayout) findViewById(R.id.options);
        for (int i = 0; i < options.getChildCount(); i++) {
            (options.getChildAt(i)).setOnClickListener(optionListener);
        }
    }
}
