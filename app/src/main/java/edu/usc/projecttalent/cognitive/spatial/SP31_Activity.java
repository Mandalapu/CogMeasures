package edu.usc.projecttalent.cognitive.spatial;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.LinkedList;
import java.util.Queue;

import edu.usc.projecttalent.cognitive.FinishActivity;
import edu.usc.projecttalent.cognitive.QuestionTimer;
import edu.usc.projecttalent.cognitive.R;
import edu.usc.projecttalent.cognitive.databinding.ActivitySp31Binding;
import edu.usc.projecttalent.cognitive.model.Answer;
import edu.usc.projecttalent.cognitive.model.Block;
import edu.usc.projecttalent.cognitive.model.Section;
import edu.usc.projecttalent.cognitive.model.Survey;
import edu.usc.projecttalent.cognitive.reasoning.ARExample;

public class SP31_Activity extends Activity {

    int mScore;
    Section mSection;
    Context mContext;
    Block mBlock;
    boolean mFtWarn;
    Queue<ARExample> mQueue;
    Answer mAnswer;
    View oldView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mContext = this;
        mSection = new Section(getString(R.string.sp_section_title));  //make new section.
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
        TypedArray questions = res.obtainTypedArray(R.array.sp_3); //all questions of Set 3.
        //get questions for set 3.
        mQueue = new LinkedList<>();
        for(int i=0; i<questions.length(); i++) {
            mQueue.add(new ARExample(res.obtainTypedArray(questions.getResourceId(i, 0)))); //each question. sp_31 .. sp_33.
        }

        final ActivitySp31Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_sp31_);
        binding.setItem(mQueue.remove());
        mAnswer = new Answer();
        QuestionTimer.startTimer(mContext);

        final LinearLayout options = (LinearLayout) findViewById(R.id.options);
        for(int i=1; i<options.getChildCount(); i++) {
            (options.getChildAt(i)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.setPadding(2, 2, 2, 2);
                    v.setBackgroundColor(getResources().getColor(R.color.black));
                    if (oldView != null)
                        oldView.setBackground(null);
                    oldView = v;
                }
            });
        }

       Button next = (Button) findViewById(R.id.next);
       next.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v) {
               if (oldView == null && mFtWarn) {
                   mFtWarn = false;
                   sendBroadcast(new Intent(QuestionTimer.NOANSWER));
               } else {
                   ARExample question = binding.getItem();
                   boolean correct = false;
                   if (options.indexOfChild(oldView) == question.ansOption) {
                       mScore++; //correct answer.
                       correct = true;
                   }
                   mAnswer.endAnswer(oldView == null ? -99 : options.indexOfChild(oldView), correct);
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
                           TypedArray questions = res.obtainTypedArray(block); //all questions of Set X.
                           mQueue = new LinkedList<>();
                           for (int i = 0; i < questions.length(); i++) {
                               mQueue.add(new ARExample(res.obtainTypedArray(questions.getResourceId(i, 0)))); //each question. sp_x1 .. sp_x3.
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
           }

           private int getBlockId(int block) {
               switch (block) {
                   case R.array.sp_1: return 1;
                   case R.array.sp_2: return 2;
                   case R.array.sp_4: return 4;
                   default: return 5;
               }
           }

           private int nextSet() {
               switch (mScore) {
                   case 0: return R.array.sp_1;
                   case 1: return R.array.sp_2;
                   case 2: return R.array.sp_4;
                   default: return R.array.sp_5;
               }
           }
       });
	}

    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals(QuestionTimer.QUIT)) {
                finishSection(); //go to end of section.
            } else if (action.equals(QuestionTimer.RESUME)) { //reset timer for the same question.
                QuestionTimer.startTimer(mContext);
            }
        }
    };

    private void finishSection() {
        mSection.endSection(); //end this section.
        Survey.getSurvey().addSection(mSection); //add V2D section to survey.
        Intent intent = new Intent(mContext, FinishActivity.class);
        startActivityForResult(intent, 1);
    }

	@Override
	public void onBackPressed() {}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        setResult(Activity.RESULT_OK, data);
        unregisterReceiver(mReceiver);
        super.finish();
    }

}
