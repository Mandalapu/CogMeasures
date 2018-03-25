package edu.usc.projecttalent.cognitive.vocab;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;

import edu.usc.projecttalent.cognitive.BR;
import edu.usc.projecttalent.cognitive.QuestionActivity;
import edu.usc.projecttalent.cognitive.R;
import edu.usc.projecttalent.cognitive.Timer;
import edu.usc.projecttalent.cognitive.databinding.ActivityVocabBinding;
import edu.usc.projecttalent.cognitive.holders.VSItem;
import edu.usc.projecttalent.cognitive.model.Answer;
import edu.usc.projecttalent.cognitive.model.Block;
import edu.usc.projecttalent.cognitive.model.Section;
import edu.usc.projecttalent.cognitive.numbers.NSInstruction;

/**
 * Block-adaptive test for Vocabulary.
 * Show block 3 first. Based on score, show one of blocks 1, 2, 4 or 5.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class VSQuestion extends QuestionActivity {

    /**
     * binding for all the questions.
     */
    private ActivityVocabBinding mBinding;

    /**
     * Sets up all the questions, timers, filters and checks for correctness of answers.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab);

        mSkipClass = NSInstruction.class;
        mSection = new Section(getString(R.string.vocabulary));
        mScore = 0;

        mContext = this;
        mTimer = Timer.getTimer(2);
        prepareFilter();

        mBlock = new Block();
        Type question = new TypeToken<ArrayList<VSItem>>() {}.getType();
        mQueue = new LinkedList<>();
        mQueue.addAll(new Gson().fromJson(getString(R.string.vocab3), question));

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_vocab);
        RadioGroup options = findViewById(R.id.options);
        showNextQuestion();

        findViewById(R.id.next).setOnClickListener(v -> {
            if (options.getCheckedRadioButtonId() == -1 && mFtWarn) {
                mFtWarn = false;
                sendBroadcast(new Intent(Timer.NOANSWER));
                return;
            }

            int answer = mBinding.getItem().getAnsPosition();
            RadioButton checked = options.findViewById(options.getCheckedRadioButtonId());
            int index = options.indexOfChild(checked);
            options.clearCheck();

            if (answer == index) {
                mScore++;
            }
            mAnswer.endAnswer(index + 1); //to shift indices to 1-5.
            mBlock.addAnswer(mAnswer);

            if (!mQueue.isEmpty()) {
                showNextQuestion();
                return;
            }
            mBlock.endBlock(mScore);
            mSection.addBlock(mBlock);

            if (mSection.getBlockSize() == 1) {
                int block = nextSet();
                mBlock = new Block();
                mQueue.addAll(new Gson().fromJson(getString(block), question));
                mScore = 0;
                showNextQuestion();
                return;
            }

            finishSection();
            createFile("vocab_", 1);
        });
    }

    /**
     * Show the next question in the queue.
     */
    private void showNextQuestion() {
        mAnswer = new Answer();
        mBinding.setVariable(BR.item, mQueue.remove());
        mTimer.startTimer();
        mFtWarn = true;
    }

    /**
     * Find the next set of questions that should be displayed based on the score.
     * @return the next set of questions.
     */
    private int nextSet() {
        switch (mScore) {
            case 0:
                return R.string.vocab1;
            case 1:
                return R.string.vocab2;
            case 2:
                return R.string.vocab4;
            default:
                return R.string.vocab5;
        }
    }
}
