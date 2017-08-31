package edu.usc.projecttalent.cognitive.reasoning;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.Button;

import java.util.LinkedList;

import edu.usc.projecttalent.cognitive.ARBase;
import edu.usc.projecttalent.cognitive.BR;
import edu.usc.projecttalent.cognitive.R;
import edu.usc.projecttalent.cognitive.Timer;
import edu.usc.projecttalent.cognitive.model.Answer;
import edu.usc.projecttalent.cognitive.model.Block;
import edu.usc.projecttalent.cognitive.model.Section;
import edu.usc.projecttalent.cognitive.reaction.RTInstruction;

/**
 * Block-adaptive test for Abstract Reasoning.
 * Show block 3 first. Based on score, show one of blocks 1, 2, 4 or 5.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class ARQuestion extends ARBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;
        mSkipClass = RTInstruction.class;
        mSection = new Section(getString(R.string.ar_section_title));  //make new section.
        mScore = 0; //reset score at the beginning of block.
        mTimer = Timer.getTimer(3);
        prepareFilter();

        mBlock = new Block(3); //first block is Block 3.

        final Resources res = getResources();
        TypedArray questions = res.obtainTypedArray(R.array.ar_3);
        mQueue = new LinkedList<>();
        for (int i = 0; i < questions.length(); i++) {
            mQueue.add(new ARItem(res.obtainTypedArray(questions.getResourceId(i, 0))));
        }

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_ar_question);
        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(nextListener);
        setupOptionsListener(false);

        showNextQuestion();
        next.setEnabled(false);
    }

    @Override
    protected int nextSet() {
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
}
