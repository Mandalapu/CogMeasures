package edu.usc.projecttalent.cognitive.vocab;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import edu.usc.projecttalent.cognitive.FinishActivity;
import edu.usc.projecttalent.cognitive.QuestionTimer;
import edu.usc.projecttalent.cognitive.R;
import edu.usc.projecttalent.cognitive.databinding.ActivityVocabBinding;
import edu.usc.projecttalent.cognitive.model.Answer;
import edu.usc.projecttalent.cognitive.model.Block;
import edu.usc.projecttalent.cognitive.model.Section;
import edu.usc.projecttalent.cognitive.model.Survey;

public class VO31_Activity extends AppCompatActivity {
    Context mContext;
    int mScore;
    boolean mFtWarn; //first time warning for no selection.

    Section mSection;
    Answer mAnswer;
    Block mBlock;
    Queue<VocabItem> mQueue;
    ArrayList<VocabItem> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab);

        mContext = this;
        mSection = new Section(getString(R.string.vocabulary)); //make new section.
        mScore = 0; //reset score at the beginning of block.
        mFtWarn = true; //FTU logic.

        //prepare timer.
        IntentFilter filter = new IntentFilter();
        filter.addAction(QuestionTimer.WARNING);
        filter.addAction(QuestionTimer.QUIT);
        filter.addAction(QuestionTimer.RESUME);
        registerReceiver(mReceiver, filter);

        mBlock = new Block(3); //first block is Block 3.

        final Type question = new TypeToken<ArrayList<VocabItem>>(){}.getType();
        mList = new Gson().fromJson(getString(R.string.vocab3), question);
        mQueue = new LinkedList<>();
        mQueue.addAll(mList); //Add all questions of Block 3 to queue.

        final ActivityVocabBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_vocab);
        mAnswer = new Answer(); //Create new answer object. Start-time is saved.
        binding.setItem(mQueue.remove()); //show first question.
        QuestionTimer.startTimer(mContext); //start timer for first question.

        final RadioGroup options = (RadioGroup) findViewById(R.id.options);

        Button button = (Button) findViewById(R.id.next);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(options.getCheckedRadioButtonId() == -1 && mFtWarn) {
                    mFtWarn = false;
                    sendBroadcast(new Intent(QuestionTimer.NOANSWER));
                } else {
                    int answer = binding.getItem().answer; //Retrieve correct answer.
                    RadioButton checked = (RadioButton) options.findViewById(options.getCheckedRadioButtonId());
                    int index = options.indexOfChild(checked); //Retrieve user answer.
                    options.clearCheck(); //Clear radio button for next question.
                    if (answer == index)
                        mScore++; //if answer is correct, update score.
                    mAnswer.endAnswer(index, answer); //Record answer and end time.
                    mBlock.addAnswer(mAnswer); //Add answer to block.
                    if (!mQueue.isEmpty()) { //Other questions from this block left.
                        mAnswer = new Answer();
                        binding.setItem(mQueue.remove());
                        QuestionTimer.startTimer(mContext);
                        mFtWarn = true;
                    } else { // a block has ended. End this block and prepare for new block.
                        mBlock.endBlock(mScore); //end block.
                        mSection.addBlock(mBlock); //add this block to the vocabulary section.

                        if (mSection.getBlockSize() == 1) { //only block 3 has been shown yet. show new block.
                            int block = nextSet(); //find next set based on score.
                            mBlock = new Block(getBlockId(block)); //create new block.
                            mList = new Gson().fromJson(getString(block), question); //get new questions.
                            mQueue.addAll(mList);
                            mScore = 0; //reset the score for the new block.
                            binding.setItem(mQueue.remove());
                            QuestionTimer.startTimer(mContext);
                            mFtWarn = true;
                        } else { //both blocks have been shown. proceed to next section.
                            finishSection();
                        }
                    }
                }
            }

            private int nextSet() {
                switch (mScore) {
                    case 0: return R.string.vocab1;
                    case 1: return R.string.vocab2;
                    case 2: return R.string.vocab4;
                    default: return R.string.vocab5;
                }
            }

            private int getBlockId(int set) {
                switch(set) {
                    case R.string.vocab1: return 1;
                    case R.string.vocab2: return 2;
                    case R.string.vocab4: return 4;
                    default: return 5;
                }
            }
        });
    }

    private void finishSection() {
        mSection.endSection(); //end this section.
        Survey.getSurvey().addSection(mSection); //add vocab section to survey.
        Intent intent = new Intent(mContext, FinishActivity.class);
        intent.putExtra(FinishActivity.SECTION, R.string.switch_ns);
        startActivityForResult(intent, 1);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        setResult(Activity.RESULT_OK, data);
        unregisterReceiver(mReceiver);
        finish();
    }

    @Override
    public void onBackPressed() {}
}
