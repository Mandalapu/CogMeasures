package edu.usc.projecttalent.cognitive;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    protected static Context mContext;
    protected Timer mTimer;
    protected boolean mFtWarn;

    protected BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Timer.QUIT)) {
                finishSection(); //go to end of section.
            } else if (action.equals(Timer.RESUME)) { //reset timer for the same question.
                mTimer.startTimer();
            } else if(action.equals(Timer.NOANSWER)) {
                showNoAnswerDialog();
            } else if(action.equals(Timer.ACTIVENEXT)) {
                (findViewById(R.id.next)).setEnabled(true);
            }
        }
    };

    private void showNoAnswerDialog() {
        AlertDialog ansDialog = new AlertDialog.Builder(this)
                .setMessage(R.string.msg3)
                .setNeutralButton(R.string.ok, null)
                .create();
        ansDialog.show();
    }


    @Override
    public void onBackPressed() {
    }

    protected void finishSection() {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        setResult(Activity.RESULT_OK, data);
        super.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver(mReceiver);
        } catch (Exception e) {
        }
    }

    protected void prepareFilter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Timer.WARNING);
        filter.addAction(Timer.QUIT);
        filter.addAction(Timer.RESUME);
        filter.addAction(Timer.NOANSWER);
        filter.addAction(Timer.ACTIVENEXT);
        registerReceiver(mReceiver, filter);
    }

    protected int getBlockId(int set) {
        switch (set) {
            case R.string.ns_1:
            case R.array.sp_1:
            case R.array.ar_1:
            case R.string.vocab1:
                return 1;
            case R.string.ns_2:
            case R.array.sp_2:
            case R.array.ar_2:
            case R.string.vocab2:
                return 2;
            case R.string.ns_4:
            case R.array.sp_4:
            case R.array.ar_4:
            case R.string.vocab4:
                return 4;
            default:
                return 5;
        }
    }
}
