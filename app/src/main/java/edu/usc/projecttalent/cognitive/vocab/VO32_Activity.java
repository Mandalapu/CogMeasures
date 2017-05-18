package edu.usc.projecttalent.cognitive.vocab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Date;

import edu.usc.projecttalent.cognitive.FinishActivity;
import edu.usc.projecttalent.cognitive.MyGlobalVariables;
import edu.usc.projecttalent.cognitive.R;

public class VO32_Activity extends AppCompatActivity {
    long ms;
    boolean click = false;
    boolean empty = false;
    int count = 0;
    RadioGroup radioGroup;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vo32_);

        Date start = new Date();
        String s = MyGlobalVariables.getTime();
        s += "vo32_start:" + start.toString() + ";";
        MyGlobalVariables.setTime(s);

        runCountDownTimer();
        Button resumeButton = (Button) findViewById(R.id.resume);
        resumeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RelativeLayout questionArea = (RelativeLayout) findViewById(R.id.main_layout);
                questionArea.setVisibility(View.VISIBLE);
                RelativeLayout quitResumeArea = (RelativeLayout) findViewById(R.id.quit_resume_layout);
                quitResumeArea.setVisibility(View.INVISIBLE);
                runCountDownTimer();
            }
        });

        Button quitButton = (Button) findViewById(R.id.quit);
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recordEndTime();
                Intent intent = new Intent(VO32_Activity.this, FinishActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                click = true;
                count++;
                radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
                int selectedId = radioGroup.getCheckedRadioButtonId();
                String s = MyGlobalVariables.getData();
                int p;
                if (s.contains("vo32:")) {
                    p = s.indexOf("vo32:");
                    s = s.substring(0, p);
                }
                if (s.contains("vo32_score:")) {
                    p = s.indexOf("vo32_score:");
                    s = s.substring(0, p);
                }
                if (s.contains("vo32_ans:")) {
                    p = s.indexOf("vo32_ans:");
                    s = s.substring(0, p);
                }

                if (selectedId == -1) {
                    s += "vo32:0" + ";";
                    MyGlobalVariables.q31 = 0;
                    s += "vo32_score:" + Integer.toString(MyGlobalVariables.q32) + ";";
                    s += "vo32_ans:4;";

                    TextView tv = (TextView) findViewById(R.id.message);
                    String t = getResources().getString(R.string.msg3);
                    tv.setText(t);
                    tv.setVisibility(View.VISIBLE);
                    empty = true;
                } else {
                    radioButton = (RadioButton) findViewById(selectedId);
                    int idx = radioGroup.indexOfChild(radioButton);
                    if (idx == 4)
                        MyGlobalVariables.q32 = 1;
                    s += "vo32:" + Integer.toString(idx) + ";";
                    s += "vo32_score:" + Integer.toString(MyGlobalVariables.q32) + ";";
                    s += "vo32_ans:4;";
                    empty = false;
                }
                MyGlobalVariables.setData(s);
                if ((click && count >= 2)) {
                    click = false;
                    TextView tv = (TextView) findViewById(R.id.message);
                    tv.setVisibility(View.INVISIBLE);
                    recordEndTime();
                    Intent intent = new Intent(VO32_Activity.this, VO33_Activity.class);
                    startActivityForResult(intent, 1);
                }
            }
        });
    }

    public void runCountDownTimer() {

        new CountDownTimer(120000, 1000) {

            public void onTick(long millisUntilFinished) {
                ms = millisUntilFinished;
                if (!click && millisUntilFinished <= 60000) {
                    //We should display a new message and arrange everything for just one click to enter the next activity.
                    TextView tv = (TextView) findViewById(R.id.message);
                    String t = getResources().getString(R.string.msg2);
                    tv.setText(t);
                    tv.setVisibility(View.VISIBLE);
                    count++;
                } else if (click && !empty) {
                    click = false;
                    TextView tv = (TextView) findViewById(R.id.message);
                    tv.setVisibility(View.INVISIBLE);
                    recordEndTime();
                    Intent intent = new Intent(VO32_Activity.this, VO33_Activity.class);
                    startActivityForResult(intent, 1);
                }
            }

            public void onFinish() {
                if (!click) {
                    //On finishing the 120 seconds, take the user to Quit/Resume activity.
                    TextView tv = (TextView) findViewById(R.id.message);
                    tv.setVisibility(View.INVISIBLE);
                    RelativeLayout questionArea = (RelativeLayout) findViewById(R.id.main_layout);
                    questionArea.setVisibility(View.INVISIBLE);
                    RelativeLayout quitResumeArea = (RelativeLayout) findViewById(R.id.quit_resume_layout);
                    quitResumeArea.setVisibility(View.VISIBLE);
                } else {
                    recordEndTime();
                    Intent intent = new Intent(VO32_Activity.this, VO33_Activity.class);
                    startActivityForResult(intent, 1);
                }
            }
        }.start();
        return;
    }

    @Override
    public void onBackPressed() {}

    private void recordEndTime() {
        Date end = new Date();
        String s = MyGlobalVariables.getTime();
        s += "vo32_end:" + end.toString() + ";";
        MyGlobalVariables.setTime(s);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                setResult(Activity.RESULT_OK, data);
                super.finish();
            }
        }
    }
}
