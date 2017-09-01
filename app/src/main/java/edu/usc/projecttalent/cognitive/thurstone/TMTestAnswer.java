package edu.usc.projecttalent.cognitive.thurstone;

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

import edu.usc.projecttalent.cognitive.BR;
import edu.usc.projecttalent.cognitive.Item;
import edu.usc.projecttalent.cognitive.QuestionActivity;
import edu.usc.projecttalent.cognitive.R;
import edu.usc.projecttalent.cognitive.Timer;
import edu.usc.projecttalent.cognitive.databinding.ActivityThurAnswerBinding;
import edu.usc.projecttalent.cognitive.model.Answer;
import edu.usc.projecttalent.cognitive.model.Block;
import edu.usc.projecttalent.cognitive.model.Section;
import edu.usc.projecttalent.cognitive.reasoning.ARInstruction;

/**
 * Test for Thurstone.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class TMTestAnswer extends QuestionActivity {

    private View oldView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSection = new Section(getString(R.string.thurstone_title));
        mSkipClass = ARInstruction.class;
        mContext = this;
        mTimer = Timer.getTimer(3);
        prepareFilter();
        Drawable highlight = ContextCompat.getDrawable(this, R.drawable.btn_bg);

        Resources res = getResources();
        TypedArray questions = res.obtainTypedArray(R.array.th_set);
        mQueue = new LinkedList<>();
        for (int i = 0; i < questions.length(); i++) {
            mQueue.add(new TMItem(res.obtainTypedArray(questions.getResourceId(i, 0))));
        }

        mScore = 0;
        ActivityThurAnswerBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_thur_answer);
        binding.setVariable(BR.item, mQueue.remove());
        Button btn = (Button) findViewById(R.id.next);
        mTimer.startTimer();

        TableRow options = (TableRow) findViewById(R.id.options);
        for (int i = 0; i < options.getChildCount(); i++) {
            (options.getChildAt(i)).setOnClickListener(v -> {
                if(v != oldView) {
                    v.setBackground(highlight);
                    if (oldView != null)
                        oldView.setBackground(null);
                    oldView = v;
                }
            });
        }

        mBlock = new Block(1);
        mAnswer = new Answer();

        btn.setOnClickListener(v -> {
            if(oldView == null && mFtWarn) {
                mFtWarn = false;
                sendBroadcast(new Intent(Timer.NOANSWER));
                return;
            }
            TMItem question = binding.getItem();
            boolean correct1 = false;
            if (options.indexOfChild(oldView) == question.getAnsPosition()) {
                mScore++; //correct answer.
                correct1 = true;
            }
            mAnswer.endAnswer(oldView == null ? -99 : options.indexOfChild(oldView) + 1, correct1); //to shift indices to 1-5.
            mBlock.addAnswer(mAnswer);
            if (oldView != null)
                oldView.setBackground(null);
            oldView = null;
            if (!mQueue.isEmpty()) {
                mAnswer = new Answer();
                binding.setVariable(BR.item, mQueue.remove());
                mFtWarn = true;
                mTimer.startTimer();
            } else {
                mBlock.endBlock(mScore);
                mSection.addBlock(mBlock);
                finishSection();
            }
        });
    }
}
