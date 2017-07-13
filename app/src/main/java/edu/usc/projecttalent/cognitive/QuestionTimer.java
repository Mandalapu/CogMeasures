package edu.usc.projecttalent.cognitive;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.CountDownTimer;
import android.util.Log;

/**
 * Created by anind on 5/18/2017.
 */

public class QuestionTimer extends CountDownTimer {

    private static QuestionTimer mTimer;
    private static Context mContext;
    private static AlertDialog mWarningDialog;
    private static AlertDialog mQuitDialog;
    private static AlertDialog mAnsDialog;
    private static boolean shown;

    public static final String WARNING = "cognitive.timewarning";
    public static final String QUIT = "cognitive.quit";
    public static final String RESUME = "cognitive.resume";
    public static final String NOANSWER = "cognitive.noanswer";

    static long time = 3;


    private QuestionTimer(long millisInFuture, long countDownInterval, Context context) {
        super(millisInFuture, countDownInterval);
        mContext = context;
        shown = false;
        IntentFilter filter = new IntentFilter();
        filter.addAction(NOANSWER);
        mContext.registerReceiver(mReceiver, filter);
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
            Log.e("anindya", "Receiver is not registered.");
        }
        if (mWarningDialog.isShowing())
            mWarningDialog.dismiss();
        mQuitDialog.show();
    }

    public static void startTimer(Context context) {
        startTimer(context, 3);
    }

    public static void startTimer(Context context, long minutes) {
        if (mTimer == null || time != minutes) {
            time = minutes;
            if (mTimer != null)
                mTimer.cancel();
            mTimer = new QuestionTimer(time * 60 * 1000, 1000, context); //time = 2 for vocab, 3 for others
        }
        mContext = context;
        createDialogs();
        mTimer.cancel();
        shown = false;
        mTimer.start();
    }

    public static void stopTimer() {
        if (mTimer != null) {
            mTimer.cancel();
            try {
                mContext.unregisterReceiver(mReceiver);
            } catch (Exception e) {
                Log.e("anindya", "Receiver not registered.");
            }
        }
    }

    private static void createDialogs() {
        mWarningDialog = new AlertDialog.Builder(mContext)
                .setMessage(R.string.msg2)
                .setNeutralButton(R.string.ok, null)
                .create();
        mQuitDialog = new AlertDialog.Builder(mContext)
                .setMessage(R.string.quit_resume)
                .setNegativeButton(R.string.quit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mContext.sendBroadcast(new Intent(QUIT));
                    }
                })
                .setPositiveButton(R.string.resume, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mContext.sendBroadcast(new Intent(RESUME));
                    }
                })
                .setCancelable(false).create();
        mAnsDialog = new AlertDialog.Builder(mContext)
                .setMessage(R.string.msg3)
                .setNeutralButton(R.string.ok, null)
                .create();
    }

    static BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(NOANSWER)) {
                mAnsDialog.show();
            }
        }
    };
}
