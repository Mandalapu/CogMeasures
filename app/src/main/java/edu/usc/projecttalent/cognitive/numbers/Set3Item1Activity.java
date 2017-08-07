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
import edu.usc.projecttalent.cognitive.databinding.ActivityAr31Binding;
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
    private int mScore;
    private Section mSection;
    private Block mBlock;
    private boolean mFtWarn;

    private ArrayList<NSQuestion> mList;
    private Queue<NSQuestion> mQueue;
    private Answer mAnswer;

    private EditText answer, answer2;
    private ActivitySet3Item1Binding binding;
    private LinearLayout series;
    private Type question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;
        mSection = new Section(getString(R.string.ns_section_title));  //make new section.
        mScore = 0; //reset score at the beginning of block.
        mTimer = QuestionTimer.getTimer(3);
        prepareFilter();

        mBlock = new Block(3); //first block is Block 3.
        mFtWarn = true; //for FTU.

        question = new TypeToken<ArrayList<NSQuestion>>() {
        }.getType();
        mList = new Gson().fromJson(getString(R.string.ns_3), question);
        mQueue = new LinkedList<>();
        mQueue.addAll(mList);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_set3_item1);
        mAnswer = new Answer();
        NSQuestion item = mQueue.remove();
        if (item.getAnsPositions() == null)
            item.setInstr(getResources().getQuantityString(R.plurals.ns_instr, 1)); //to select the one item instruction.
        binding.setItem(item);

        series = (LinearLayout) findViewById(R.id.series);
        answer = (EditText) findViewById(R.id.answer);
        answer2 = (EditText) findViewById(R.id.answer2);

        series.removeView(answer);
        series.addView(answer, binding.getItem().getAnsPosition()); //set the edit box to correct position.
        setNumPad(); //setting up number pad and undo.

        mTimer.startTimer(); //start the timer for first question.
        (findViewById(R.id.next)).setOnClickListener(nextListener);
    }

    private View.OnClickListener nextListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (answer.getText().toString().equals("") && mFtWarn) {
                mFtWarn = false;
                sendBroadcast(new Intent(QuestionTimer.NOANSWER));
                return;
            }

            NSQuestion curQuestion = binding.getItem();
            if (curQuestion.getAnsPositions() == null) { //only one option.
                oneOption(curQuestion);
            } else {
                multiOption();
            }

            if (!mQueue.isEmpty()) { //more questions in the same block.
                showNextQuestion();
                return;
            }

            mBlock.endBlock(mScore);
            mSection.addBlock(mBlock);

            if (mSection.getBlockSize() == 1) { //only block 3 has been shown yet. show next block.
                showNextSet(question);
            } else {
                finishSection();
            }
        }
    };

    private void showNextQuestion() {
        mAnswer = new Answer();
        NSQuestion item = mQueue.remove();
        item.setInstr(getResources().getQuantityString(R.plurals.ns_instr,
                item.getAnsPositions() == null ? 1 : 2)); //to select the one item instruction.
        binding.setItem(item); //add new question.
        mTimer.startTimer();
        mFtWarn = true;
        NSQuestion curQuestion = binding.getItem();
        if (curQuestion.getAnsPositions() == null) {
            series.removeView(answer); //update position of answer box.
            series.addView(answer, binding.getItem().getAnsPosition());
        } else {
            EditText answer = (EditText) series.findViewById(R.id.answer2);
            series.removeView(answer);
            series.addView(answer, 2);
        }
    }

    private void showNextSet(Type question) {
        int block = nextSet();
        mBlock = new Block(getBlockId(block));
        mList = new Gson().fromJson(getString(block), question); //get new questions.
        mQueue.addAll(mList);
        mScore = 0; //reset the score for the new block.
        NSQuestion item1 = mQueue.remove();
        item1.setInstr(getResources().getQuantityString(R.plurals.ns_instr, item1.getAnsPositions() == null ? 1 : 2)); //to select the one item instruction.
        binding.setItem(item1);
        mTimer.startTimer();
        mFtWarn = true;
        series.removeView(answer); //update position of answer box.
        series.addView(answer, binding.getItem().getAnsPosition());
    }

    private void multiOption() {
        //This section is for Sec 5 Q 3.
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
        mAnswer.endAnswer(userAns1, correct);
        mBlock.addAnswer(mAnswer);
    }

    private void oneOption(NSQuestion question) {
        int userAns = -99; //invalid. user did not select an answer;
        try {
            userAns = Integer.parseInt(answer.getText().toString());
        } catch (Exception ignored) {
        }
        answer.setText("");
        int ans = question.getOptions()[question.getAnsPosition()];
        boolean correct = false;
        if (userAns == ans) {
            mScore++; //correct answer.
        } else if (question.getAnsOptions() != null) {
            int[] answers = question.getAnsOptions();
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
    }

    private void setNumPad() {
        LinearLayout numPad = (LinearLayout) findViewById(R.id.numpad);
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
