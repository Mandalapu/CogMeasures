package edu.usc.projecttalent.cognitive.numbers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import edu.usc.projecttalent.cognitive.BR;
import edu.usc.projecttalent.cognitive.R;
import edu.usc.projecttalent.cognitive.Timer;
import edu.usc.projecttalent.cognitive.databinding.ActivityNsQuestionBinding;
import edu.usc.projecttalent.cognitive.holders.NSItem;
import edu.usc.projecttalent.cognitive.model.Answer;
import edu.usc.projecttalent.cognitive.model.Block;
import edu.usc.projecttalent.cognitive.model.Section;
import edu.usc.projecttalent.cognitive.thurstone.TMInstruction;

/**
 * Block-adaptive test for Number section.
 * Show block 3 first. Based on score, show one of blocks 1, 2, 4 or 5.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class NSQuestion extends NSBase {
    /**
     * the data binding object for the question.
     */
    private ActivityNsQuestionBinding binding;
    /**
     * the series of numbers that needs to be completed.
     */
    private LinearLayout series;

    /**
     * Set up the binding for the questions. Show questions with correct skip logic.
     * @param savedInstanceState currently nothing is sent in this bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this; //set the context.
        mSkipClass = TMInstruction.class; //class to move to if section is skipped.
        mSection = new Section(getString(R.string.ns_section_title));  //make new section.
        mScore = 0; //reset score at the beginning of block.
        mTimer = Timer.getTimer(3); //get a 3 minute timer.
        prepareFilter(); //add filters for broadcast.

        mBlock = new Block();
        mFtWarn = true; //for FTU.

        mQueue = new LinkedList<>();
        mQueue.addAll(new Gson().fromJson(getString(R.string.ns_3), new TypeToken<ArrayList<NSItem>>() {}.getType()));

        binding = DataBindingUtil.setContentView(this, R.layout.activity_ns_question);
        //question has been displayed, so start the answer.
        mAnswer = new Answer();
        NSItem item = (NSItem) mQueue.remove();
        item.setInstr(getResources().getQuantityString(R.plurals.ns_instr, 1)); //to select the one item instruction.
        binding.setVariable(BR.item, item);

        series = findViewById(R.id.series); //get the layout for the series.
        answer = findViewById(R.id.answer); //extract first answer view
        answer2 = findViewById(R.id.answer2); //extract second answer view.

        /*
        The answer view needs to be put at the correct location, hence we first remove the view from
        the series and then add it at the correct position. This position is defined by the
        getAnsPosition() method.
         */
        series.removeView(answer);
        series.addView(answer, binding.getItem().getAnsPosition()); //set the edit box to correct position.
        setNumPad(); //setting up number pad and undo.

        mTimer.startTimer(); //start the timer for first question.
        (findViewById(R.id.next)).setOnClickListener(nextListener);
        (findViewById(R.id.next)).setEnabled(false);
    }

    /**
     * click listener for the next button.
     */
    private View.OnClickListener nextListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //if there is no answer and warning has not been shown yet
            if (answer.getText().toString().equals("") && mFtWarn) {
                mFtWarn = false; //send first-time warning to false.
                /*
                 the above is done first in order to prevent multiple broadcasts if the user does not
                 click the button on the dialog on time.
                 */
                sendBroadcast(new Intent(Timer.NOANSWER));//send broadcast to show the warning.
                return;
            }
            //User has answered, so get the current item.
            NSItem curQuestion = binding.getItem();
            if (curQuestion.getAnsPositions() == null) { //only one option.
                oneOption(curQuestion);
            } else {
                multiOption();
            }

            if (!mQueue.isEmpty()) { //more questions in the same block.
                showNextQuestion();
                return;
            }
            mBlock.endBlock(mScore); //end the block with score.
            mSection.addBlock(mBlock); //add this block to the section.

            if (mSection.getBlockSize() == 1) { //only block 3 has been shown yet. show next block.
                showNextSet();
                return;
            }
            finishSection();
            createFile("number_", 2);
        }
    };

    /**
     * Retrieve the next question from the queue and show it to the user.
     */
    private void showNextQuestion() {
        NSItem item = (NSItem) mQueue.remove();
        item.setInstr(getResources().getQuantityString(R.plurals.ns_instr,
                item.getAnsPositions() == null ? 1 : 2)); //to select the one item instruction.

        mAnswer = new Answer(); //start new answer.
        binding.setVariable(BR.item, item); //add new question.
        (findViewById(R.id.next)).setEnabled(false);
        mTimer.startTimer(); //restart the timer.
        mFtWarn = true; //set first-time warning to true to show warning again.

        NSItem curQuestion = binding.getItem();
        if (curQuestion.getAnsPositions() == null) { //if only one answer option.
            series.removeView(answer); //update position of answer box.
            series.addView(answer, binding.getItem().getAnsPosition());
        } else { //for Q5.3, place the second answer box properly. Hard-coded for now.
            EditText answer = series.findViewById(R.id.answer2);
            series.removeView(answer);
            series.addView(answer, 2);
        }
    }

    /**
     * Show the next set after block 3 is complete.
     */
    private void showNextSet() {
        int block = nextSet(); //find the next block.
        mBlock = new Block(); //create new blcok with ID.
        //retrieve all questions for the block.
        mQueue.addAll(new Gson().fromJson(getString(block), new TypeToken<ArrayList<NSItem>>() {}.getType()));
        mScore = 0; //reset the score.
        showNextQuestion(); //show the first question of new block.
    }

    /**
     * For Q5.3, record both answers in the JSON.
     */
    private void multiOption() {
        //This section is for Sec 5 Q 3.
        int userAns1 = -99;
        int userAns2 = -99;
        try {
            userAns1 = Integer.parseInt(answer.getText().toString());
            userAns2 = Integer.parseInt(answer2.getText().toString());
        } catch (Exception ignored) {
            //if user has not entered any answer then the above will throw exception.
            //We do not need to do anything as they are still initialized to -99.
        }
        //below the answers are hardcoded for 5.3 because there is only one case out of 15 which
        //requires this code.
        if ((userAns1 == 72 && userAns2 == 76) || (userAns1 == 78 && userAns2 == 82)) {
            mScore++;
        }
        mAnswer.endAnswer(userAns1); //add the answer.
        mBlock.addAnswer(mAnswer); //if it is correct.
    }

    /**
     * Check for answers if they are correct. Add it to the survey.
     * @param question the question for which processing to be done.
     */
    private void oneOption(NSItem question) {
        int userAns = -99; //invalid. user did not select an answer;
        try {
            userAns = Integer.parseInt(answer.getText().toString());
        } catch (Exception ignored) {
            //if user has not answered, ignore and add -99 to the JSON.
        }
        answer.setText(""); //reset the field.
        int ans = question.getOptions()[question.getAnsPosition()]; //actual answer.
        if (userAns == ans) { //correct.
            mScore++;
        } else if (question.getAnsOptions() != null) { //if more than one answer.
            int[] answers = question.getAnsOptions(); //get all answers.
            Arrays.sort(answers); //sort all possible answers.
            //binary search to see if any answer matches user's answer.
            int pos = Arrays.binarySearch(answers, userAns);
            if (pos != -1) { //answer found
                mScore++;
            }
        }
        mAnswer.endAnswer(userAns); //end answer.
        mBlock.addAnswer(mAnswer); //add answer to block.
    }

    /**
     * Look for the next block in skip logic. by checking the score.
     * @return the list of questions for the next set.
     */
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
}
