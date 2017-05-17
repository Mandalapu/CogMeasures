package edu.usc.cognitive.reasoning;



import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import edu.usc.cognitive.FinishActivity;
import edu.usc.cognitive.ImageDecoder;
import edu.usc.cognitive.MyGlobalVariables;
import edu.usc.cognitive.R;

public class AR33_Activity extends Activity {
	boolean[] selected={false,false,false,false,false};
	boolean click = false; int count=0;long ms;boolean empty=false, exit = false;
    int count1=0;
	ImageView img1,img2,img3,img4,img5;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_ar33_);
		Date start = new Date();
		String s = MyGlobalVariables.getTime();
		s+="ar33_start:"+start.toString()+";";
		MyGlobalVariables.setTime(s);
		ImageView myImage = (ImageView) findViewById(R.id.question_image);
	    myImage.setImageBitmap(ImageDecoder.decodeImage(getResources(),R.drawable.ar_13_main,myImage.getLayoutParams().width,myImage.getLayoutParams().height));

	    myImage = (ImageView) findViewById(R.id.imageView1);
	    myImage.setImageBitmap(ImageDecoder.decodeImage(getResources(),R.drawable.ar_13_1,myImage.getLayoutParams().width,myImage.getLayoutParams().height));

	    myImage = (ImageView) findViewById(R.id.imageView2);
	    myImage.setImageBitmap(ImageDecoder.decodeImage(getResources(),R.drawable.ar_13_2,myImage.getLayoutParams().width,myImage.getLayoutParams().height));

	    myImage = (ImageView) findViewById(R.id.imageView3);
	    myImage.setImageBitmap(ImageDecoder.decodeImage(getResources(),R.drawable.ar_13_3,myImage.getLayoutParams().width,myImage.getLayoutParams().height));

	    myImage = (ImageView) findViewById(R.id.imageView4);
	    myImage.setImageBitmap(ImageDecoder.decodeImage(getResources(),R.drawable.ar_13_4,myImage.getLayoutParams().width,myImage.getLayoutParams().height));

	    myImage = (ImageView) findViewById(R.id.imageView5);
	    myImage.setImageBitmap(ImageDecoder.decodeImage(getResources(),R.drawable.ar_13_5,myImage.getLayoutParams().width,myImage.getLayoutParams().height));

		////This function can be used. ///////
        timeOutUser();
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
				Intent intent = new Intent(AR33_Activity.this, FinishActivity.class);
				startActivityForResult(intent,1);
			}
		});

		//THE ABOVE CODE CAN BE USED ////



		img1 = (ImageView) findViewById(R.id.imageView1);
			img2 = (ImageView) findViewById(R.id.imageView2);
			img3 = (ImageView) findViewById(R.id.imageView3);
			img4 = (ImageView) findViewById(R.id.imageView4);
			img5 = (ImageView) findViewById(R.id.imageView5);
			img1.setPadding(1, 1, 1, 1);
			img2.setPadding(1, 1, 1, 1);
			img3.setPadding(1, 1, 1, 1);
			img4.setPadding(1, 1, 1, 1);
			img5.setPadding(1, 1, 1, 1);
	        img1.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	                // Perform action on click
	            	selected[0]=true;
	            	selected[1]=false;
	            	selected[2]=false;
	            	selected[3]=false;
	            	selected[4]=false;
	            	img1.setBackgroundColor(Color.parseColor("#000000"));
	            	img2.setBackgroundColor(Color.parseColor("#FFFFFF"));
	            	img3.setBackgroundColor(Color.parseColor("#FFFFFF"));
	            	img4.setBackgroundColor(Color.parseColor("#FFFFFF"));
	            	img5.setBackgroundColor(Color.parseColor("#FFFFFF"));
	            }
	        });
	        img2.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	                // Perform action on click
	            	selected[0]=false;
	            	selected[1]=true;
	            	selected[2]=false;
	            	selected[3]=false;
	            	selected[4]=false;
	            	img1.setBackgroundColor(Color.parseColor("#FFFFFF"));
	            	img2.setBackgroundColor(Color.parseColor("#000000"));
	            	img3.setBackgroundColor(Color.parseColor("#FFFFFF"));
	            	img4.setBackgroundColor(Color.parseColor("#FFFFFF"));
	            	img5.setBackgroundColor(Color.parseColor("#FFFFFF"));
	            }
	        });
	        img3.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	                // Perform action on click
	            	selected[0]=false;
	            	selected[1]=false;
	            	selected[2]=true;
	            	selected[3]=false;
	            	selected[4]=false;
	            	img1.setBackgroundColor(Color.parseColor("#FFFFFF"));
	            	img2.setBackgroundColor(Color.parseColor("#FFFFFF"));
	            	img3.setBackgroundColor(Color.parseColor("#000000"));
	            	img4.setBackgroundColor(Color.parseColor("#FFFFFF"));
	            	img5.setBackgroundColor(Color.parseColor("#FFFFFF"));
	            }
	        });
	        img4.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	                // Perform action on click
	            	selected[0]=false;
	            	selected[1]=false;
	            	selected[2]=false;
	            	selected[3]=true;
	            	selected[4]=false;
	            	img1.setBackgroundColor(Color.parseColor("#FFFFFF"));
	            	img2.setBackgroundColor(Color.parseColor("#FFFFFF"));
	            	img3.setBackgroundColor(Color.parseColor("#FFFFFF"));
	            	img4.setBackgroundColor(Color.parseColor("#000000"));
	            	img5.setBackgroundColor(Color.parseColor("#FFFFFF"));
	            }
	        });
	        img5.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	                // Perform action on click
	            	selected[0]=false;
	            	selected[1]=false;
	            	selected[2]=false;
	            	selected[3]=false;
	            	selected[4]=true;
	            	img1.setBackgroundColor(Color.parseColor("#FFFFFF"));
	            	img2.setBackgroundColor(Color.parseColor("#FFFFFF"));
	            	img3.setBackgroundColor(Color.parseColor("#FFFFFF"));
	            	img4.setBackgroundColor(Color.parseColor("#FFFFFF"));
	            	img5.setBackgroundColor(Color.parseColor("#000000"));
	            }
	        });
      Button button = (Button) findViewById(R.id.button3);
      button.setOnClickListener(new View.OnClickListener() {
          public void onClick(View v) {
              // Perform action on click
          	click = true;count++;
          	String s = MyGlobalVariables.getData();
      		if (s.contains("ar33:")){
      			int p=s.indexOf("ar33:");
      			s = s.substring(0,p);
      		}
          	if (selected[0] || selected[1] || selected[2] || selected[3] || selected[4])
          	{
          		int u=0;
          		if(selected[0])u=1;
          		else if(selected[1])u=2;
          		else if(selected[2])u=3;
          		else if(selected[3])u=4;
          		else if(selected[4])u=5;
          		s+="ar33:"+Integer.toString(u)+";";
          	}
          	else
          	{
          		s+="ar33:0"+";";

              		TextView tv = (TextView) findViewById(R.id.message);
              		String t = getResources().getString(R.string.msg3);
         				 tv.setText(t);
         				 tv.setVisibility(View.VISIBLE);
         				 empty=true;
          	}

          	MyGlobalVariables.setData(s);
          	if((click && count>=2)){
          		click = false;
				recordScores();
        		TextView tv = (TextView) findViewById(R.id.message);
        		tv.setVisibility(View.INVISIBLE);
				recordEndTime();
        		takeToNextActivity();

          	}
          }
      });
	}

	/////This function can be used /////
	public void runCountDownTimer()
	{

		click = false;
		count = 0;
		new CountDownTimer(18000, 1000) {

			public void onTick(long millisUntilFinished) {
				ms=millisUntilFinished;
				if( !click &&  millisUntilFinished <= 12000 ){
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
					recordScores();
					recordEndTime();
					takeToNextActivity();
				}
			}
			public void onFinish() {
				if(!click){
					//On finishing the 180 seconds, take the user to Quit/Resume activity.
					TextView tv = (TextView) findViewById(R.id.message);
					tv.setVisibility(View.INVISIBLE);
					RelativeLayout questionArea = (RelativeLayout) findViewById(R.id.main_layout);
					questionArea.setVisibility(View.INVISIBLE);
					RelativeLayout quitResumeArea = (RelativeLayout) findViewById(R.id.quit_resume_layout);
					quitResumeArea.setVisibility(View.VISIBLE);
				}
			}
		}.start();
		return;
	}

    /////This function can be used /////
    public void timeOutUser()
    {

        //Actual time should be for 2 hrs. Need to change the seconds.
        new CountDownTimer(25000, 1000) {

            @Override
            public void onTick(long l) {

            }

            public void onFinish() {
                if(!click & !exit ){
                    //On finishing the 180 seconds, take the user to Quit/Resume activity.
                    recordEndTime();
                    Intent intent = new Intent(AR33_Activity.this, ARIntro_Activity.class);
                    startActivityForResult(intent,1);
                }
            }
        }.start();
        return;
    }



	private void takeToNextActivity()
	{
		Intent intent=null;
		if(count1==0)
			intent = new Intent(AR33_Activity.this, AR11_Activity.class);
		else if(count1==1)
			intent = new Intent(AR33_Activity.this, AR21_Activity.class);
		else if(count1==2)
			intent = new Intent(AR33_Activity.this, AR41_Activity.class);
		else if(count1==3)
			intent = new Intent(AR33_Activity.this, AR51_Activity.class);
		startActivityForResult(intent,1);
	}

	private void recordEndTime()
	{
        exit = true;
		Date end = new Date();
		String s = MyGlobalVariables.getTime();
		s+="ar33_end:"+end.toString()+";";
		String t=MyGlobalVariables.getData();
		t+=s;MyGlobalVariables.setData(t);
		MyGlobalVariables.setTime("");
	}

	private void recordScores()
	{
		String s = MyGlobalVariables.getData();
		String ans= s.substring(s.indexOf("ar31:"));
		String[] l = new String[3];
		l = ans.split(";");count1=0;
		s=s.substring(0,s.indexOf("ar31:"));
		for(int i = 0;i<3;i++){
			if(i==0 && l[i].substring(l[i].indexOf(':')+1).equals("3")) {count1++;s+="ar31_score:1;";s+="ar31_ans:3;";}
			else if(i==1 && l[i].substring(l[i].indexOf(':')+1).equals("2")) {count1++;s+="ar32_score:1;";s+="ar32_ans:2;";}
			else if(i==2 && l[i].substring(l[i].indexOf(':')+1).equals("4")) {count1++;s+="ar33_score:1;";s+="ar33_ans:4;";}
			else
			{
				if(i==0){s+="ar31_score:0;";s+="ar31_ans:"+l[i].substring(l[i].indexOf(':')+1)+";";}
				else if(i==1){s+="ar32_score:0;";s+="ar32_ans:"+l[i].substring(l[i].indexOf(':')+1)+";";}
				else if(i==2){s+="ar33_score:0;";s+="ar33_ans:"+l[i].substring(l[i].indexOf(':')+1)+";";}
			}
		}
		MyGlobalVariables.setData(s);
	}

	@Override
	public void onBackPressed()
	{

	   //thats it
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Check which request we're responding to
		Log.d("AR33",  "onActivityResult - AR33");
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