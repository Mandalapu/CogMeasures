package edu.usc.projecttalent.cognitive.reasoning;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.LinkedList;
import java.util.Queue;

import edu.usc.projecttalent.cognitive.BaseActivity;
import edu.usc.projecttalent.cognitive.QuestionTimer;
import edu.usc.projecttalent.cognitive.R;
import edu.usc.projecttalent.cognitive.databinding.ActivityAr31Binding;
import edu.usc.projecttalent.cognitive.model.Answer;
import edu.usc.projecttalent.cognitive.model.Block;
import edu.usc.projecttalent.cognitive.model.Section;
import edu.usc.projecttalent.cognitive.model.Survey;
import edu.usc.projecttalent.cognitive.reaction_time.MainActivity_Reac;

/**
 * Block-adaptive test for Abstract Reasoning.
 * Show block 3 first. Based on score, show one of blocks 1, 2, 4 or 5.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class AR31_Activity extends BaseActivity {

    private int mScore;
    private Section mSection;
    private Block mBlock;
    private boolean mFtWarn;
    private Queue<ARExample> mQueue;
    private Answer mAnswer;
    private View oldView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;
        mSection = new Section(getString(R.string.ar_section_title));  //make new section.
        mScore = 0; //reset score at the beginning of block.

        //prepare timer.
        IntentFilter filter = new IntentFilter();
        filter.addAction(QuestionTimer.WARNING);
        filter.addAction(QuestionTimer.QUIT);
        filter.addAction(QuestionTimer.RESUME);
        registerReceiver(mReceiver, filter);

        mBlock = new Block(3); //first block is Block 3.
        mFtWarn = true; //for FTU.

        final Resources res = getResources();
        TypedArray questions = res.obtainTypedArray(R.array.ar_3); //all questions of Set 3.
        //get questions for set 3.
        mQueue = new LinkedList<>();
        for (int i = 0; i < questions.length(); i++) {
            mQueue.add(new ARExample(res.obtainTypedArray(questions.getResourceId(i, 0)))); //each question. ar_31 .. ar_33.
        }

        ActivityAr31Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_ar31_);
        binding.setItem(mQueue.remove());
        mAnswer = new Answer();
        QuestionTimer.startTimer(mContext);

        LinearLayout options = (LinearLayout) findViewById(R.id.options);
        for (int i = 0; i < options.getChildCount(); i++) {
            (options.getChildAt(i)).setOnClickListener(v -> {
                v.setPadding(2, 2, 2, 2);
                v.setBackgroundColor(ContextCompat.getColor(this, R.color.black));
                if (oldView != null)
                    oldView.setBackground(null);
                oldView = v;
            });
        }

        Button next = (Button) findViewById(R.id.next);
        next.setOnClickListener(v -> {
            if (oldView == null && mFtWarn) {
                mFtWarn = false;
                sendBroadcast(new Intent(QuestionTimer.NOANSWER));
            } else {
                ARExample question = binding.getItem();
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
                    QuestionTimer.startTimer(mContext);
                    mFtWarn = true;
                } else {
                    mBlock.endBlock(mScore);
                    mSection.addBlock(mBlock);
                    if (mSection.getBlockSize() == 1) { //get next block.
                        int block = nextSet();
                        mBlock = new Block(getBlockId(block));
                        TypedArray questions1 = res.obtainTypedArray(block); //all questions of Set X.
                        mQueue = new LinkedList<>();
                        for (int i = 0; i < questions1.length(); i++) {
                            mQueue.add(new ARExample(res.obtainTypedArray(questions1.getResourceId(i, 0)))); //each question. ar_x1 .. ar_x3.
                        }
                        mScore = 0;
                        binding.setItem(mQueue.remove());
                        QuestionTimer.startTimer(mContext);
                        mFtWarn = true;
                    } else {
                        finishSection();
                    }
                }
            }
        });
    }

    private int getBlockId(int block) {
        switch (block) {
            case R.array.ar_1:
                return 1;
            case R.array.ar_2:
                return 2;
            case R.array.ar_4:
                return 4;
            default:
                return 5;
        }
    }

    private int nextSet() {
        switch (mScore) {
            case 0:
                return R.array.ar_1;
            case 1:
                return R.array.ar_2;
            case 2:
                return R.array.ar_4;
            default:
                return R.array.ar_5;
        }
    }

    @Override
    protected void finishSection() {
        mSection.endSection(); //end this section.
        Survey.getSurvey().addSection(mSection); //add AR section to survey.
        startActivityForResult(new Intent(mContext, MainActivity_Reac.class), 1);
    }
}
