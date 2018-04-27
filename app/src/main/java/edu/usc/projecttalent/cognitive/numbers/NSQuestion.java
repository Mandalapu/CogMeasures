package edu.usc.projecttalent.cognitive.numbers;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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

    private List<List<NSItem>> nsList;

    /**
     * Set up the binding for the questions. Show questions with correct skip logic.
     * @param savedInstanceState currently nothing is sent in this bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;
        mSkipClass = TMInstruction.class;
        mSection = new Section(getString(R.string.ns_section_title));
        mScore = 0;
        mTimer = Timer.getTimer(3);
        prepareFilter();

        try (InputStreamReader reader = new InputStreamReader(mContext.getResources().openRawResource(R.raw.number_section))) {
            nsList = new Gson().fromJson(reader, new TypeToken<List<List<NSItem>>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }

        mBlock = new Block();
        mFtWarn = true;

        mQueue = new LinkedList<>();
        mQueue.addAll(nsList.get(2));

        binding = DataBindingUtil.setContentView(this, R.layout.activity_ns_question);
        mAnswer = new Answer();
        NSItem item = (NSItem) mQueue.remove();
        item.setInstr(getResources().getQuantityString(R.plurals.ns_instr, 1));
        binding.setVariable(BR.item, item);

        series = findViewById(R.id.series);
        answer = findViewById(R.id.answer);
        answer2 = findViewById(R.id.answer2);
        series.removeView(answer);
        series.addView(answer, binding.getItem().getAnsPosition());
        setNumPad();

        mTimer.startTimer();
        (findViewById(R.id.next)).setOnClickListener(nextListener);
        (findViewById(R.id.next)).setEnabled(false);
    }

    /**
     * click listener for the next button.
     */
    private View.OnClickListener nextListener = v -> {
            if (answer.getText().toString().equals("") && mFtWarn) {
                mFtWarn = false;
                sendBroadcast(new Intent(Timer.NOANSWER));
                return;
            }

            NSItem curQuestion = binding.getItem();
            if (curQuestion.getAnsPositions() == null) {
                oneOption(curQuestion);
            } else {
                multiOption();
            }

            if (!mQueue.isEmpty()) {
                showNextQuestion();
                return;
            }
            mBlock.endBlock(mScore);
            mSection.addBlock(mBlock);

            if (mSection.getBlockSize() == 1) {
                showNextSet();
                return;
            }
            finishSection();
            createFile("number_", 2);
        };

    /**
     * Retrieve the next question from the queue and show it to the user.
     */
    private void showNextQuestion() {
        NSItem item = (NSItem) mQueue.remove();
        item.setInstr(getResources().getQuantityString(R.plurals.ns_instr,
                item.getAnsPositions() == null ? 1 : 2));
        mAnswer = new Answer();
        binding.setVariable(BR.item, item);
        (findViewById(R.id.next)).setEnabled(false);
        mTimer.startTimer();
        mFtWarn = true;

        NSItem curQuestion = binding.getItem();
        if (curQuestion.getAnsPositions() == null) {
            series.removeView(answer);
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
        mBlock = new Block();
        mQueue.addAll(nsList.get(nextSet()));
        mScore = 0;
        showNextQuestion();
    }

    /**
     * For Q5.3, record both answers in the JSON.
     */
    private void multiOption() {
        int userAns1 = -99;
        int userAns2 = -99;
        try {
            userAns1 = Integer.parseInt(answer.getText().toString());
            userAns2 = Integer.parseInt(answer2.getText().toString());
        } catch (Exception ignored) {}
        //below the answers are hardcoded for 5.3 because there is only one case out of 15 which
        //requires this code.
        if ((userAns1 == 72 && userAns2 == 76) || (userAns1 == 78 && userAns2 == 82)) {
            mScore++;
        }
        mAnswer.endAnswer(userAns1);
        mBlock.addAnswer(mAnswer);
    }

    /**
     * Check for answers if they are correct. Add it to the survey.
     * @param question the question for which processing to be done.
     */
    private void oneOption(NSItem question) {
        int userAns = -99;
        try {
            userAns = Integer.parseInt(answer.getText().toString());
        } catch (Exception ignored) {}
        answer.setText("");
        int ans = question.getOptions()[question.getAnsPosition()];
        if (userAns == ans) {
            mScore++;
        } else if (question.getAnsOptions() != null) {
            int[] answers = question.getAnsOptions();
            Arrays.sort(answers);
            int pos = Arrays.binarySearch(answers, userAns);
            if (pos != -1) {
                mScore++;
            }
        }
        mAnswer.endAnswer(userAns);
        mBlock.addAnswer(mAnswer);
    }

    /**
     * Look for the next block in skip logic. by checking the score.
     * @return the list of questions for the next set.
     */
    private int nextSet() {
        return mScore + (mScore > 1 ? 1 : 0);
    }
}
