package edu.usc.projecttalent.cognitive.thurstone;

import edu.usc.projecttalent.cognitive.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by kayigwe on 6/7/17.
 */

public class SecondActivity extends Activity {

    private TextView txtView;
    private Button btn;
    private ImageView imageView_one;
    private ImageView imageView_two;
    private ImageView imageView_three;
    private ImageView imageView_four;
    private ImageView imageViewExample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_second_page);

        btn = (Button) findViewById(R.id.btnNext);
        MyOnClickListener myOnClickListener = new MyOnClickListener();
        btn.setOnClickListener(myOnClickListener);
        imageView_one = (ImageView) findViewById(R.id.truck_one);
        imageView_two = (ImageView) findViewById(R.id.truck_two);
        imageView_three = (ImageView) findViewById(R.id.truck_three);
        imageView_four = (ImageView) findViewById(R.id.truck_four);

        txtView = (TextView) findViewById(R.id.textView2);
    }


    private class MyOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            txtView.setText("Now click on the picture you have seen.");
            btn.setVisibility(View.INVISIBLE);

            MyImageOnClickListenerWrong myImageOnClickListenerWrong = new MyImageOnClickListenerWrong();
            MyImageOnClickListenerCorrect myImageOnClickListenerCorrect = new MyImageOnClickListenerCorrect();

            imageView_one.setOnClickListener(myImageOnClickListenerWrong);
            imageView_two.setOnClickListener(myImageOnClickListenerWrong);
            imageView_three.setOnClickListener(myImageOnClickListenerCorrect);
            imageView_four.setOnClickListener(myImageOnClickListenerWrong);

        }

        private class MyImageOnClickListenerWrong implements View.OnClickListener {
            @Override
            public void onClick(View v) {
               //image 1,2,4
                AlertDialog.Builder mBuilder= new AlertDialog.Builder(SecondActivity.this);
                View myView = getLayoutInflater().inflate(R.layout.popup_wrong_examples, null);
                imageViewExample = (ImageView) myView.findViewById(R.id.imageViewExample);
                TextView greatText = (TextView) myView.findViewById(R.id.textViewGreat);
                TextView explanationText = (TextView) myView.findViewById(R.id.textViewExplanation);

                //The start example test button
                Button nextBtn = (Button) myView.findViewById(R.id.bntNextExample);
                nextBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), ExampleImageChange.class));
                    }
                });

                mBuilder.setView(myView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
                dialog.setCanceledOnTouchOutside(false);
            }
        }

        private class MyImageOnClickListenerCorrect implements View.OnClickListener{
            @Override
            public void onClick(View v) {
                //image 3
                //startActivity(new Intent(SecondActivity.this,PopExample.class));
                AlertDialog.Builder mBuilder= new AlertDialog.Builder(SecondActivity.this);
                View myView = getLayoutInflater().inflate(R.layout.popup_correctwrong_examples, null);
                TextView greatText = (TextView) myView.findViewById(R.id.textViewGreat);
                TextView explanationText = (TextView) myView.findViewById(R.id.textViewExplanation);

                //The start example test button
                Button nextBtn = (Button) myView.findViewById(R.id.bntNextExample);
                nextBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), ExampleImageChange.class));
                    }
                });

                mBuilder.setView(myView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
                dialog.setCanceledOnTouchOutside(false);
            }
        }
    }
    @Override
    public void onBackPressed() {
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
