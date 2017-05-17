package edu.usc.cognitive.vocab;

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

import edu.usc.cognitive.FinishActivity;
import edu.usc.cognitive.MyGlobalVariables;
import edu.usc.cognitive.R;

public class VO23_Activity extends AppCompatActivity {
    long ms;
    boolean click = false;
    boolean empty=false;
    int count=0;
    RadioGroup radioGroup;
    RadioButton radioButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vo23_);

        Date start = new Date();
        String s = MyGlobalVariables.getTime();
        //s+="sec_vo_start:"+start.toString()+";";
        s+="vo23_start:"+start.toString()+";";
        MyGlobalVariables.setTime(s);

        ////This function can be used. ///////
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
                Intent intent = new Intent(VO23_Activity.this, FinishActivity.class);
                startActivityForResult(intent,1);
            }
        });

        /////The baove code can be used /////


        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                click = true;count++;
                radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
                int selectedId = radioGroup.getCheckedRadioButtonId();
                String s = MyGlobalVariables.getData();
                int p;
                if (s.contains("vo23:")){
                    p=s.indexOf("vo23:");
                    s = s.substring(0,p);
                }
                if (s.contains("vo23_score:")){
                    p=s.indexOf("vo23_score:");
                    s = s.substring(0,p);
                }
                if (s.contains("vo23_ans:")){
                    p=s.indexOf("vo23_ans:");
                    s = s.substring(0,p);
                }

                if (selectedId == -1)
                {
                    s+="vo23:0"+";";
                    MyGlobalVariables.q31 = 0;
                    s+="vo23_score:"+Integer.toString(MyGlobalVariables.qx3)+";";
                    s+="vo23_ans:2;";

                    TextView tv = (TextView) findViewById(R.id.message);
                    String t = getResources().getString(R.string.msg3);
                    tv.setText(t);
                    tv.setVisibility(View.VISIBLE);
                    empty=true;
                }
                else
                {
                    radioButton = (RadioButton)findViewById(selectedId);
                    int idx = radioGroup.indexOfChild(radioButton);


//                    String string,u;
//                    string = radioButton.getI
//                    u = string.substring(string.length() - 1));

                    if(idx==2)
                        MyGlobalVariables.qx3=1;
                    s+="vo23:"+Integer.toString(idx)+";";
                    s+="vo23_score:"+Integer.toString(MyGlobalVariables.qx3)+";";
                    s+="vo23_ans:2;";
                    // MyGlobalVariables g = new MyGlobalVariables();
                    empty=false;
                }
                MyGlobalVariables.setData(s);
                if((click && count>=2)){
                    click = false;
                    TextView tv = (TextView) findViewById(R.id.message);
                    tv.setVisibility(View.INVISIBLE);
                    recordEndTime();
                    Intent intent=null;
                    //create_textfile();
                    //intent = new Intent(VO23_Activity.this, SecNS_Activity.class);
                    intent = new Intent(VO23_Activity.this, FinishActivity.class);
                    startActivityForResult(intent,1);
                }


            }
        });
    }


    /////This function can be used /////
    public void runCountDownTimer()
    {

        new CountDownTimer(120000, 1000) {

            public void onTick(long millisUntilFinished) {
                ms=millisUntilFinished;
                if( !click &&  millisUntilFinished <= 60000 ){
                    //We should display a new message and arrange everything for just one click to enter the next activity.
                    TextView tv = (TextView) findViewById(R.id.message);
                    String t = getResources().getString(R.string.msg2);
                    tv.setText(t);
                    tv.setVisibility(View.VISIBLE);
                    count++;
                }
                else if(click && !empty)
                {
                    click = false;
                    TextView tv = (TextView) findViewById(R.id.message);
                    tv.setVisibility(View.INVISIBLE);
                    recordEndTime();
                    Intent intent = new Intent(VO23_Activity.this, FinishActivity.class);
                    startActivityForResult(intent,1);
                }
            }
            public void onFinish() {
                if(!click){
                    //On finishing the 120 seconds, take the user to Quit/Resume activity.
                    TextView tv = (TextView) findViewById(R.id.message);
                    tv.setVisibility(View.INVISIBLE);
                    RelativeLayout questionArea = (RelativeLayout) findViewById(R.id.main_layout);
                    questionArea.setVisibility(View.INVISIBLE);
                    RelativeLayout quitResumeArea = (RelativeLayout) findViewById(R.id.quit_resume_layout);
                    quitResumeArea.setVisibility(View.VISIBLE);
                    //todo clarfy about this count value.
                    //count++;
                }
                else
                {
                    recordEndTime();
                    Intent intent = new Intent(VO23_Activity.this, FinishActivity.class);
                    startActivityForResult(intent,1);
                }
            }
        }.start();
        return;
    }

    @Override
    public void onBackPressed() {
        //do nothing.
    }

    private void recordEndTime()
    {
        Date end = new Date();
        String s = MyGlobalVariables.getTime();
        s+="vo23_end:"+end.toString()+";";
        s+="sec_vo_end:"+end.toString()+";";
        String t=MyGlobalVariables.getData();
        t+=s;MyGlobalVariables.setData(t);
        MyGlobalVariables.setTime("");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        Log.d("VO23",  "onActivityResult - VO23_Activity");
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.

                // Do something with the contact here (bigger example below)
                setResult(Activity.RESULT_OK, data);
                super.finish();
            }
        }
    }

}
