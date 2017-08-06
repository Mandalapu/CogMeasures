package edu.usc.projecttalent.cognitive.numbers;

import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;

import edu.usc.projecttalent.cognitive.BaseActivity;
import edu.usc.projecttalent.cognitive.QuestionTimer;
import edu.usc.projecttalent.cognitive.R;
import edu.usc.projecttalent.cognitive.databinding.ActivitySet3Item1Binding;
import edu.usc.projecttalent.cognitive.model.Answer;
import edu.usc.projecttalent.cognitive.model.Block;
import edu.usc.projecttalent.cognitive.model.Section;
import edu.usc.projecttalent.cognitive.model.Survey;
import edu.usc.projecttalent.cognitive.thurstone.MainActivity_Th;

/**
 * Block-adaptive test for Number section.
 * Show block 3 first. Based on score, show one of blocks 1, 2, 4 or 5.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class Set3Item1Activity extends BaseActivity {
    int mScore;
    Section mSection;
    Block mBlock;
    boolean mFtWarn;

    ArrayList<NSQuestion> mList;
    Queue<NSQuestion> mQueue;
    Answer mAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;
        mSection = new Section(getString(R.string.ns_section_title));  //make new section.
        mScore = 0; //reset score at the beginning of block.

        //prepare timer.
        IntentFilter filter = new IntentFilter();
        filter.addAction(QuestionTimer.WARNING);
        filter.addAction(QuestionTimer.QUIT);
        filter.addAction(QuestionTimer.RESUME);
        registerReceiver(mReceiver, filter);

        mBlock = new Block(3); //first block is Block 3.
        mFtWarn = true; //for FTU.

        final Type question = new TypeToken<ArrayList<NSQuestion>>() {
        }.getType();
        mList = new Gson().fromJson(getString(R.string.ns_3), question);
        mQueue = new LinkedList<>();
        mQueue.addAll(mList);

        final ActivitySet3Item1Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_set3_item1);
        NSQuestion item = mQueue.remove();
        if (item.getAnsPositions() == null)
            item.setInstr(getResources().getQuantityString(R.plurals.ns_instr, 1)); //to select the one item instruction.
        binding.setItem(item);

        mAnswer = new Answer();

        final LinearLayout series = (LinearLayout) findViewById(R.id.series);
        LinearLayout numPad = (LinearLayout) findViewById(R.id.numpad);
        final EditText answer = (EditText) findViewById(R.id.answer),
                answer2 = (EditText) findViewById(R.id.answer2);

        series.removeView(answer);
        series.addView(answer, binding.getItem().getAnsPosition()); //set the edit box to correct position.

        //setting up number pad and undo.
        View.OnClickListener listener = v -> {
            if (answer2.hasFocus())
                answer2.append(((Button) v).getText());
            else
                answer.append(((Button) v).getText()); //extra code for Set 5 Q3.
        };
        for (int i = 0; i < numPad.getChildCount(); i++) {
            ((Button) (numPad.getChildAt(i))).setText(String.format(Locale.getDefault(), "%d", i));
            (numPad.getChildAt(i)).setOnClickListener(listener);
        }

        (findViewById(R.id.undo)).setOnClickListener(v -> {
            EditText hasFocus = answer2.hasFocus() ? answer2 : answer;
            int length = hasFocus.length();
            if (length > 0)
                hasFocus.getText().delete(length - 1, length);
        });

        QuestionTimer.startTimer(mContext); //start the timer for first question.

        Button button = (Button) findViewById(R.id.next);
        button.setOnClickListener(v -> {
            if (answer.getText().toString().equals("") && mFtWarn) {
                mFtWarn = false;
                sendBroadcast(new Intent(QuestionTimer.NOANSWER));
            } else {
                NSQuestion curQuestion = binding.getItem();
                if (curQuestion.getAnsPositions() == null) { //only one option.
                    int userAns = -99; //invalid. user did not select an answer;
                    try {
                        userAns = Integer.parseInt(answer.getText().toString());
                    } catch (Exception ignored) {
                    }
                    answer.setText("");
                    int ans = curQuestion.getOptions()[curQuestion.getAnsPosition()];
                    boolean correct = false;
                    if (userAns == ans) {
                        mScore++; //correct answer.
                    } else if (curQuestion.getAnsOptions() != null) {
                        int[] answers = curQuestion.getAnsOptions();
                        for (int answer1 : answers) {
                            if (userAns == answer1) { //if there are multiple answers to the same question.
                                mScore++;
                                correct = true;
                                break;
                            }
                        }
                    }
                    mAnswer.endAnswer(userAns, correct); //end answer.
                    mBlock.addAnswer(mAnswer); //add answer to block.
                } else {
                    //This section is for Sec 5 Q 3.
                    //Hardcoding this section because there is only 1 out of 15 such questions.
                    //Generalizing this would involve lots of complicated code.
                    int userAns1 = -99;
                    int userAns2 = -99;
                    try {
                        userAns1 = Integer.parseInt(answer.getText().toString());
                        userAns2 = Integer.parseInt(answer2.getText().toString());
                    } catch (Exception ignored) {
                    }
                    boolean correct = false;
                    if ((userAns1 == 72 && userAns2 == 76) || (userAns1 == 78 && userAns2 == 82)) {
                        correct = true;
                        mScore++;
                    }
                    mAnswer.endAnswer(userAns1, correct); //TODO: // FIXME: 6/3/2017
                    mBlock.addAnswer(mAnswer);
                }

                if (!mQueue.isEmpty()) { //more questions in the same block.
                    mAnswer = new Answer();
                    NSQuestion item1 = mQueue.remove();
                    item1.setInstr(getResources().getQuantityString(R.plurals.ns_instr, item1.getAnsPositions() == null ? 1 : 2)); //to select the one item instruction.
                    binding.setItem(item1); //add new question.
                    QuestionTimer.startTimer(mContext);
                    mFtWarn = true;
                    curQuestion = binding.getItem();
                    if (curQuestion.getAnsPositions() == null) {
                        series.removeView(answer); //update position of answer box.
                        series.addView(answer, binding.getItem().getAnsPosition());
                    } else {
                        EditText answer21 = (EditText) series.findViewById(R.id.answer2);
                        series.removeView(answer21);
                        series.addView(answer21, 2);
                    }
                } else { //a block has ended. end this block and prepare for new block.
                    mBlock.endBlock(mScore);
                    mSection.addBlock(mBlock);

                    if (mSection.getBlockSize() == 1) { //only block 3 has been shown yet. show next block.
                        int block = nextSet();
                        mBlock = new Block(getBlockId(block));
                        mList = new Gson().fromJson(getString(block), question); //get new questions.
                        mQueue.addAll(mList);
                        mScore = 0; //reset the score for the new block.
                        NSQuestion item1 = mQueue.remove();
                        item1.setInstr(getResources().getQuantityString(R.plurals.ns_instr, item1.getAnsPositions() == null ? 1 : 2)); //to select the one item instruction.
                        binding.setItem(item1);
                        QuestionTimer.startTimer(mContext);
                        mFtWarn = true;
                        series.removeView(answer); //update position of answer box.
                        series.addView(answer, binding.getItem().getAnsPosition());
                    } else {
                        finishSection();
                    }
                }
            }
        });
    }

    private int getBlockId(int set) {
        switch (set) {
            case R.string.ns_1:
                return 1;
            case R.string.ns_2:
                return 2;
            case R.string.ns_4:
                return 4;
            default:
                return 5;
        }
    }

    private int nextSet() {
        switch (mScore) {
            case 0:
                return R.string.ns_1;
            case 1:
                return R.string.ns_2;
            case 2:
                return R.string.ns_4;
            default:
                return R.string.ns_5;
        }
    }

    @Override
    protected void finishSection() {
        mSection.endSection(); //end this section.
        Survey.getSurvey().addSection(mSection); //add number section to survey.
        startActivityForResult(new Intent(mContext, MainActivity_Th.class), 1);
    }
}
