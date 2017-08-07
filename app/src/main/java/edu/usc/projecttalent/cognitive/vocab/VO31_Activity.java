package edu.usc.projecttalent.cognitive.vocab;

import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import edu.usc.projecttalent.cognitive.BaseActivity;
import edu.usc.projecttalent.cognitive.QuestionTimer;
import edu.usc.projecttalent.cognitive.R;
import edu.usc.projecttalent.cognitive.databinding.ActivityVocabBinding;
import edu.usc.projecttalent.cognitive.model.Answer;
import edu.usc.projecttalent.cognitive.model.Block;
import edu.usc.projecttalent.cognitive.model.Section;
import edu.usc.projecttalent.cognitive.model.Survey;
import edu.usc.projecttalent.cognitive.numbers.SecNS_Activity;

/**
 * Block-adaptive test for Vocabulary.
 * Show block 3 first. Based on score, show one of blocks 1, 2, 4 or 5.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class VO31_Activity extends BaseActivity {
    private int mScore;
    private boolean mFtWarn; //first time warning for no selection.

    private Section mSection;
    private Answer mAnswer;
    private Block mBlock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab);

        mSection = new Section(getString(R.string.vocabulary));
        mScore = 0;
        mFtWarn = true;

        mContext = this;
        mTimer = QuestionTimer.getTimer(2);
        prepareFilter();

        mBlock = new Block(3);
        Type question = new TypeToken<ArrayList<VocabItem>>() {
        }.getType();
        Queue<VocabItem> mQueue = new LinkedList<>();
        mQueue.addAll(new Gson().fromJson(getString(R.string.vocab3), question));

        ActivityVocabBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_vocab);
        mAnswer = new Answer();
        binding.setItem(mQueue.remove());
        mTimer.startTimer();

        RadioGroup options = (RadioGroup) findViewById(R.id.options);

        Button button = (Button) findViewById(R.id.next);
        button.setOnClickListener(v -> {
            if (options.getCheckedRadioButtonId() == -1 && mFtWarn) {
                mFtWarn = false;
                sendBroadcast(new Intent(QuestionTimer.NOANSWER));
                return;
            }
            int answer = binding.getItem().getAnswer();
            RadioButton checked = (RadioButton) options.findViewById(options.getCheckedRadioButtonId());
            int index = options.indexOfChild(checked);
            options.clearCheck();
            boolean correct = false;
            if (answer == index) {
                mScore++;
                correct = true;
            }
            mAnswer.endAnswer(index + 1, correct); //to shift indices to 1-5.
            mBlock.addAnswer(mAnswer);
            if (!mQueue.isEmpty()) {
                mAnswer = new Answer();
                binding.setItem(mQueue.remove());
                mTimer.startTimer();
                mFtWarn = true;
                return;
            }
            mBlock.endBlock(mScore);
            mSection.addBlock(mBlock);

            if (mSection.getBlockSize() == 1) {
                int block = nextSet();
                mBlock = new Block(getBlockId(block));
                mQueue.addAll(new Gson().fromJson(getString(block), question));
                mScore = 0;
                binding.setItem(mQueue.remove());
                mTimer.startTimer();
                mFtWarn = true;
            } else {
                finishSection();
            }
        });
    }

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

    private int getBlockId(int set) {
        switch (set) {
            case R.string.vocab1:
                return 1;
            case R.string.vocab2:
                return 2;
            case R.string.vocab4:
                return 4;
            default:
                return 5;
        }
    }

    @Override
    protected void finishSection() {
        mSection.endSection();
        Survey.getSurvey().addSection(mSection);
        startActivityForResult(new Intent(this, SecNS_Activity.class), 1);
    }
}
