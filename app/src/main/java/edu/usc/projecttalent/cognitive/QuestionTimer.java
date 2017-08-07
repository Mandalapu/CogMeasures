package edu.usc.projecttalent.cognitive;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.CountDownTimer;
import android.util.Log;

/**
 * Timer set for each question. Warning show up on one minute. Quit on two minutes.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class QuestionTimer extends CountDownTimer {

    private static QuestionTimer mTimer;
    private Context mContext;
    private AlertDialog mWarningDialog;
    private boolean shown;

    static final String WARNING = "cognitive.timewarning";
    static final String QUIT = "cognitive.quit";
    static final String RESUME = "cognitive.resume";
    public static final String NOANSWER = "cognitive.noanswer";

    private static long time = 3;

    private QuestionTimer(long minutes) {
        super(minutes * 60 * 1000, 1000);
        time = minutes;
        shown = false;
        IntentFilter filter = new IntentFilter();
        filter.addAction(NOANSWER);
        mContext = BaseActivity.mContext;
        mContext.registerReceiver(mReceiver, filter);
        createDialogs();
    }

    @Override
    public void onTick(long millisUntilFinished) {
        if (millisUntilFinished <= 60 * 1000 && !shown) {
            mWarningDialog.show();
            shown = true;
            mContext.sendBroadcast(new Intent(WARNING));
        }
    }

    @Override
    public void onFinish() {
        try {
            mContext.unregisterReceiver(mReceiver);
        } catch (Exception e) {
        }
        if (mWarningDialog.isShowing())
            mWarningDialog.dismiss();

        AlertDialog quitDialog = new AlertDialog.Builder(mContext)
                .setMessage(R.string.quit_resume)
                .setNegativeButton(R.string.quit, (dialog, which) -> mContext.sendBroadcast(new Intent(QUIT)))
                .setPositiveButton(R.string.resume, (dialog, which) -> mContext.sendBroadcast(new Intent(RESUME)))
                .setCancelable(false).create();
        quitDialog.show();
    }

    public static QuestionTimer getTimer(long minutes) {
        if(mTimer == null) {
            mTimer = new QuestionTimer(minutes);
        } else if(minutes != time) {
            mTimer.cancel();
            mTimer = new QuestionTimer(minutes);
        }
        return mTimer;
    }

    public void startTimer() {
        mTimer.cancel();
        shown = false;
        mTimer.start();
    }

    public void stopTimer() {
        if (mTimer != null) {
            mTimer.cancel();
            try {
                mContext.unregisterReceiver(mReceiver);
            } catch (Exception e) {
                Log.e("anindya", "Receiver not registered.");
            }
        }
    }

    private void createDialogs() {
        mWarningDialog = new AlertDialog.Builder(mContext)
                .setMessage(R.string.msg2)
                .setNeutralButton(R.string.ok, null)
                .create();


    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(NOANSWER)) {
                AlertDialog ansDialog = new AlertDialog.Builder(mContext)
                        .setMessage(R.string.msg3)
                        .setNeutralButton(R.string.ok, null)
                        .create();
                ansDialog.show();
            }
        }
    };
}