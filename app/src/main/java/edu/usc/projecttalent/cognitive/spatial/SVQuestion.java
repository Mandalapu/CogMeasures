package edu.usc.projecttalent.cognitive.spatial;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.Button;

import java.util.LinkedList;

import edu.usc.projecttalent.cognitive.ARBase;
import edu.usc.projecttalent.cognitive.BR;
import edu.usc.projecttalent.cognitive.EndTest;
import edu.usc.projecttalent.cognitive.R;
import edu.usc.projecttalent.cognitive.Timer;
import edu.usc.projecttalent.cognitive.model.Answer;
import edu.usc.projecttalent.cognitive.model.Block;
import edu.usc.projecttalent.cognitive.model.Section;
import edu.usc.projecttalent.cognitive.reasoning.ARItem;

/**
 * Block-adaptive test for Spatial 2D Visualization.
 * Show block 3 first. Based on score, show one of blocks 1, 2, 4 or 5.
 *
 * @author Anindya Dutta
 */

public class SVQuestion extends ARBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mSection = new Section(getString(R.string.sp_section_title));  //make new section.
        mScore = 0; //reset score at the beginning of block.
        mSkipClass = EndTest.class;

        mTimer = Timer.getTimer(3);
        prepareFilter();

        mBlock = new Block(3); //first block is Block 3.
        mFtWarn = true; //for FTU.

        Resources res = getResources();
        TypedArray questions = res.obtainTypedArray(R.array.sp_3); //all questions of Set 3.
        //get questions for set 3.
        mQueue = new LinkedList<>();
        for (int i = 0; i < questions.length(); i++) {
            mQueue.add(new ARItem(res.obtainTypedArray(questions.getResourceId(i, 0)))); //each question. sp_31 .. sp_33.
        }
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_sp_question);
        mBinding.setVariable(BR.item, mQueue.remove());
        mAnswer = new Answer();
        mTimer.startTimer();

        setupOptionsListener();
        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(nextListener);
        next.setEnabled(false);
    }

    @Override
    protected int nextSet() {
        switch (mScore) {
            case 0:
                return R.array.sp_1;
            case 1:
                return R.array.sp_2;
            case 2:
                return R.array.sp_4;
            default:
                return R.array.sp_5;
        }
    }
}
