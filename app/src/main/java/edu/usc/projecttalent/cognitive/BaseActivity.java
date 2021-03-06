package edu.usc.projecttalent.cognitive;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import edu.usc.projecttalent.cognitive.model.Survey;
import edu.usc.projecttalent.cognitive.util.Fileutils;

/**
 * Base activity. This class is extended by all activities in the project.
 * It contains common code that applies to all activities.
 *
 * @author Anindya Dutta
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * reference to the current Activity context.
     */
    protected static Context mContext;
    /**
     * the timer that is used across all questions in the survey.
     */
    protected Timer mTimer;
    /**
     * first-time warning for not answered. Shown only once.
     */
    protected boolean mFtWarn;

    /**
     * Broadcast receiver.
     * This receiver receives intents for quitting the section, resuming the section at the end of 3
     * minutes. It also receives intents when no answer has been selected, and to activate the
     * next button after a period of 5 seconds to prevent constant clicking of the button by the user.
     */
    protected BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case Timer.QUIT:
                    finishSection();
                    break;
                case Timer.RESUME:
                    mTimer.startTimer();
                    break;
                case Timer.NOANSWER:
                    showNoAnswerDialog();
                    break;
                case Timer.ACTIVENEXT:
                    (findViewById(R.id.next)).setEnabled(true);
                    break;
            }
        }
    };

    /**
     * The dialog that is displayed when the user does not complete the question and clicks Next to
     * proceed to the next question.
     */
    private void showNoAnswerDialog() {
        AlertDialog ansDialog = new AlertDialog.Builder(this)
                .setMessage(R.string.msg3)
                .setNeutralButton(R.string.ok, null)
                .create();
        ansDialog.show();
        TextView textView =  ansDialog.findViewById(android.R.id.message);
        textView.setTextSize(30);
    }

    /**
     * Override the back press for all activities as we do not allow the user to go back on any activity.
     */
    @Override
    public void onBackPressed() {
    }

    /**
     * A dummy method overriden by all subclasses to implement specific finish section logic.
     */
    protected void finishSection() {
    }

    /**
     * Back-propagate the JSON data from the finish activity to the main activity so that it can
     * be sent to droid survey to collect the JSON data accumulated during the entire survey.
     * @param requestCode 1, the code sent throughout the application
     * @param resultCode RESULT_OK
     * @param data the intent with the JSON data.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        setResult(Activity.RESULT_OK, data);
        super.finish();
    }

    /**
     * Unregister all receivers to prevent leaks in case the application is destroyed due to any issue.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver(mReceiver);
        } catch (Exception ignored) {
        }
    }

    /**
     * Prepare broadcast filters for all activities except reaction time.
     */
    protected void prepareFilter() {
        IntentFilter filter = new IntentFilter();
        final List<String> actions = Arrays.asList(Timer.WARNING, Timer.QUIT, Timer.RESUME, Timer.NOANSWER, Timer.ACTIVENEXT);
        for(String action: actions)
            filter.addAction(action);
        registerReceiver(mReceiver, filter);
    }

    /**
     * Prepare broadcast filters for reaction time activities. Does not include the WARNING,
     * NOANSWER and ACTIVENEXT filters used in the prepareRTFilter.
     */
    protected void prepareRTFilter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Timer.QUIT);
        filter.addAction(Timer.RESUME);
        registerReceiver(mReceiver, filter);
    }

    /**
     * Set the functionality of clicking the next button to move to next class.
     * @param nextClass the class which needs to be opened to clicking next button.
     */
    protected void setNext(Class nextClass) {
        (findViewById(R.id.next)).setOnClickListener(v -> startActivityForResult(new Intent(this, nextClass), 1));
    }

    protected void createFile(String fileName, int moduleNumber) {
        SharedPreferences preferences = getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("module", moduleNumber);

        editor.putString("data", fileName.startsWith("survey") ? "reset" : new Gson().toJson(Survey.getSurvey()));
        editor.apply();
        new Fileutils().writeFile(fileName, new Gson().toJson(Survey.getSurvey()));
    }
}
