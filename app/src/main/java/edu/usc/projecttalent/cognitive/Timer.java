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

    private static Timer mTimer;
    private AlertDialog mWarningDialog;
    private boolean shown;
    private boolean activated;

    static final String WARNING = "cognitive.timewarning";
    static final String QUIT = "cognitive.quit";
    static final String RESUME = "cognitive.resume";
    public static final String NOANSWER = "cognitive.noanswer";
    public static final String ACTIVENEXT = "cognitive.activenext";

    private static long mMillis;

    private Timer(long millis) {
        super(millis, 1000);
        mMillis = millis;
        shown = false;
        activated = false;
        createDialogs();
    }

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

    public void startTimer() {
        mTimer.cancel();
        shown = false;
        activated = false;
        createDialogs();
        mTimer.start();
    }

    public void stopTimer() {
        if (mTimer != null) {
            mTimer.cancel();
        }
    }

    private void createDialogs() {
        mWarningDialog = new AlertDialog.Builder(mContext)
                .setMessage(R.string.msg2)
                .setNeutralButton(R.string.ok, null)
                .create();
    }
}