package edu.usc.projecttalent.cognitive.vocab;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

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
    private List<List<VSItem>> vsList;

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

        try (InputStreamReader reader = new InputStreamReader(mContext.getResources().openRawResource(R.raw.vocabulary))) {
            vsList = new Gson().fromJson(reader, new TypeToken<List<List<VSItem>>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }

        mBlock = new Block();
        mQueue = new LinkedList<>();
        mQueue.addAll(vsList.get(2));

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_vocab);
        showNextQuestion();
        RadioGroup options = findViewById(R.id.options);
        findViewById(R.id.next).setOnClickListener(getListener(options));
    }

    @NonNull
    private View.OnClickListener getListener(RadioGroup options) {
        return v -> {
            if (options.getCheckedRadioButtonId() == -1 && mFtWarn) {
                mFtWarn = false;
                sendBroadcast(new Intent(Timer.NOANSWER));
            } else {
                processQuestion(options);
            }
        };
    }

    private void processQuestion(RadioGroup options) {
        int index = options.indexOfChild(options.findViewById(options.getCheckedRadioButtonId()));
        options.clearCheck();
        addAnsToBlock(mBinding.getItem().getAnsPosition(), index);
        if (!mQueue.isEmpty()) {
            showNextQuestion();
        } else {
            showNextSectionIfAvailable();
        }
    }

    private void addAnsToBlock(int answer, int index) {
        if (answer == index) {
            mScore++;
        }
        mAnswer.endAnswer(index + 1); //to shift indices to 1-5.
        mBlock.addAnswer(mAnswer);
    }

    private void showNextSectionIfAvailable() {
        mBlock.endBlock(mScore);
        mSection.addBlock(mBlock);
        if (mSection.getBlockSize() == 1) {
            getNextSection();
        } else {
            finishSection();
            createFile("vocab_", 1);
        }
    }

    private void getNextSection() {
        mBlock = new Block();
        mQueue.addAll(vsList.get(nextSet()));
        mScore = 0;
        showNextQuestion();
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
        return mScore < 2 ? mScore : mScore + 1;
    }
}
