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

    private static long time = 3;

    private Timer(long minutes) {
        super(minutes * 60 * 1000, 1000);
        time = minutes;
        shown = false;
        activated = false;
        createDialogs();
    }

    @Override
    public void onTick(long millisUntilFinished) {
        if(millisUntilFinished <= (time * 60 - 5) * 1000 && !activated) {
            mContext.sendBroadcast(new Intent(ACTIVENEXT));
            activated = true;
        }
        if (millisUntilFinished <= 60 * 1000 && !shown) {
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

    public static Timer getTimer(long minutes) {
        if(mTimer == null) {
            mTimer = new Timer(minutes);
        } else if(minutes != time) {
            mTimer.cancel();
            mTimer = new Timer(minutes);
        }
        return mTimer;
    }

    public void startTimer() {
        mTimer.cancel();
        shown = false;
        activated = false;
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