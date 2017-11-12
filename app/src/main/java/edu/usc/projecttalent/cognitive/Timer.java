package edu.usc.projecttalent.cognitive;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.CountDownTimer;

import static edu.usc.projecttalent.cognitive.BaseActivity.mContext;

/**
 * Timer set for each question. Warning show up on one minute. Quit on two minutes.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class Timer extends CountDownTimer {

    /**
     * Singleton timer instance for the application.
     */
    private static Timer mTimer;
    /**
     * Warning dialog shown at the half-time for a question.
     */
    private AlertDialog mWarningDialog;
    /**
     * a check for if the warning has been shown once to prevent multiple displays.
     */
    private boolean shown;
    /**
     * check if next button has been activated for this question.
     */
    private boolean activated;
    /**
     * Intent for warning dialog.
     */
    static final String WARNING = "cognitive.timewarning";
    /**
     * Intent for quit section.
     */
    static final String QUIT = "cognitive.quit";
    /**
     * Intent to resume a section.
     */
    static final String RESUME = "cognitive.resume";
    /**
     * Intent when no answer has been selected by a user and next has been clicked.
     */
    public static final String NOANSWER = "cognitive.noanswer";
    /**
     * Intent when the next button is activated and responsive to user clicks.
     */
    public static final String ACTIVENEXT = "cognitive.activenext";
    /**
     * Time allotted to the timer.
     */
    private static long mMillis;

    /**
     * Constructor for the timer.
     * @param millis time alloted to the timer.
     */
    private Timer(long millis) {
        super(millis, 1000);
        mMillis = millis;
        shown = false;
        activated = false;
        createDialogs();
    }

    /**
     * Override the onTick to display various dialogs based on context.
     * @param millisUntilFinished time left on the timer.
     */
    @Override
    public void onTick(long millisUntilFinished) {
        if(millisUntilFinished <= mMillis - 5000 && !activated) {
            mContext.sendBroadcast(new Intent(ACTIVENEXT));
            activated = true;
        }
        if (millisUntilFinished <= 60 * 1000 && !shown) {
            if(mMillis > 0.5 * 60 * 1000)
                mWarningDialog.show();
            shown = true;
            mContext.sendBroadcast(new Intent(WARNING));
        }
    }

    /**
     * Called when the timer expires to show the Quit/Resume dialog.
     */
    @Override
    public void onFinish() {
        if (mWarningDialog.isShowing())
            mWarningDialog.dismiss();

        AlertDialog quitDialog = new AlertDialog.Builder(mContext)
                .setMessage(R.string.quit_resume)
                .setNegativeButton(R.string.quit, (dialog, which) -> mContext.sendBroadcast(new Intent(QUIT)))
                .setPositiveButton(R.string.resume, (dialog, which) -> mContext.sendBroadcast(new Intent(RESUME)))
                .setCancelable(false).create();
        quitDialog.show();
    }

    /**
     * Get instance method for singleton timer.
     * @param minutes minutes set on this timer.
     * @return the timer instance.
     */
    public static Timer getTimer(double minutes) {
        long millis = (long) (minutes * 60 * 1000);

        if(mTimer == null) {
            mTimer = new Timer(millis);
        } else if(millis != mMillis) {
            mTimer.cancel();
            mTimer = new Timer(millis);
        }
        return mTimer;
    }

    /**
     * Starts the timer.
     */
    public void startTimer() {
        mTimer.cancel();
        shown = false;
        activated = false;
        createDialogs();
        mTimer.start();
    }

    /**
     * Stops the timer.
     */
    public void stopTimer() {
        if (mTimer != null) {
            mTimer.cancel();
        }
    }

    /**
     * Creates a warning dialog when Nno answer has been responded with yet.
     */
    private void createDialogs() {
        mWarningDialog = new AlertDialog.Builder(mContext)
                .setMessage(R.string.msg2)
                .setNeutralButton(R.string.ok, null)
                .create();
    }
}