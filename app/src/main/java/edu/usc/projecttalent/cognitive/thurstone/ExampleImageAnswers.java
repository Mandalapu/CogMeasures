package edu.usc.projecttalent.cognitive.thurstone;

import edu.usc.projecttalent.cognitive.R;
import edu.usc.projecttalent.cognitive.reasoning.ARIntro_Activity;
import edu.usc.projecttalent.cognitive.reasoning.SecAR_Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by kayigwe on 6/24/17.
 */

public class ExampleImageAnswers extends Activity {

    ImageView imageView_one;
    ImageView imageView_two;
    ImageView image_three;
    ImageView imageView_four;
    Button btn;
    int correct = 0;
    int btnPress = 0;
    String chosen = "n";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example_answers);

        final ImageView imageView_one = (ImageView) findViewById(R.id.imageView_one);
        final ImageView imageView_two = (ImageView) findViewById(R.id.imageView_two);
        final ImageView imageView_three = (ImageView) findViewById(R.id.imageView_three);
        final ImageView imageView_four = (ImageView) findViewById(R.id.imageView_four);
        final Button btn = (Button) findViewById(R.id.btnSwitch);
        btn.setEnabled(false);
        imageView_one.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                chosen = "b";
                btn.setEnabled(true);
                Drawable highlight = getResources().getDrawable( R.drawable.highlight);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    imageView_one.setBackground(highlight);
                    imageView_two.setBackground(null);
                    imageView_three.setBackground(null);
                    imageView_four.setBackground(null);
                }else{
                    imageView_one.setBackgroundDrawable(highlight);
                }
                imageView_one.setImageResource(R.drawable.cop_one);
                imageView_two.setImageResource(R.drawable.cop_two);
                imageView_three.setImageResource(R.drawable.cop_three);
                imageView_four.setImageResource(R.drawable.cop_four);
            }
        });

        imageView_two.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                chosen = "b";
                btn.setEnabled(true);
                Drawable highlight = getResources().getDrawable( R.drawable.highlight);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    imageView_two.setBackground(highlight);
                    imageView_one.setBackground(null);
                    imageView_three.setBackground(null);
                    imageView_four.setBackground(null);
                }else{
                    imageView_two.setBackgroundDrawable(highlight);
                }
                imageView_one.setImageResource(R.drawable.cop_one);
                imageView_two.setImageResource(R.drawable.cop_two);
                imageView_three.setImageResource(R.drawable.cop_three);
                imageView_four.setImageResource(R.drawable.cop_four);
            }
        });

        imageView_three.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                chosen = "a";
                btn.setEnabled(true);
                Drawable highlight = getResources().getDrawable( R.drawable.highlight);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    imageView_three.setBackground(highlight);
                    imageView_two.setBackground(null);
                    imageView_one.setBackground(null);
                    imageView_four.setBackground(null);
                }else{
                    imageView_three.setBackgroundDrawable(highlight);
                }
                imageView_one.setImageResource(R.drawable.cop_one);
                imageView_two.setImageResource(R.drawable.cop_two);
                imageView_three.setImageResource(R.drawable.cop_three);
                imageView_four.setImageResource(R.drawable.cop_four);
            }
        });

        imageView_four.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                chosen = "b";
                btn.setEnabled(true);
                Drawable highlight = getResources().getDrawable( R.drawable.highlight);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    imageView_four.setBackground(highlight);
                    imageView_two.setBackground(null);
                    imageView_three.setBackground(null);
                    imageView_one.setBackground(null);
                }else{
                    imageView_four.setBackgroundDrawable(highlight);
                }
                imageView_one.setImageResource(R.drawable.cop_one);
                imageView_two.setImageResource(R.drawable.cop_two);
                imageView_three.setImageResource(R.drawable.cop_three);
                imageView_four.setImageResource(R.drawable.cop_four);
            }
        });


        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (chosen == "a"){
                    correct++;
                }
                else if (chosen == "n"){
                    //pop up don't let person move on
                    btnPress --;
                }
                chosen = "n";
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    chosen = "n";
                    imageView_one.setBackground(null);
                    imageView_two.setBackground(null);
                    imageView_three.setBackground(null);
                    imageView_four.setBackground(null);
                }else{
                    chosen = "n";
                    imageView_one.setBackgroundDrawable(null);
                    imageView_two.setBackgroundDrawable(null);
                    imageView_three.setBackgroundDrawable(null);
                    imageView_four.setBackgroundDrawable(null);
                }

                btnPress++;
                if (btnPress == 1) {
                    imageView_one.setImageResource(R.drawable.dog_one);
                    imageView_two.setImageResource(R.drawable.dog_two);
                    imageView_three.setImageResource(R.drawable.dog_three);
                    imageView_four.setImageResource(R.drawable.dog_four);
                    imageView_one.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosen = "a";
                            Drawable highlight = getResources().getDrawable( R.drawable.highlight);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                imageView_one.setBackground(highlight);
                                imageView_two.setBackground(null);
                                imageView_three.setBackground(null);
                                imageView_four.setBackground(null);
                            }else{
                                imageView_one.setBackgroundDrawable(highlight);
                            }
                        }
                    });

                    imageView_two.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosen = "b";
                            Drawable highlight = getResources().getDrawable( R.drawable.highlight);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                imageView_two.setBackground(highlight);
                                imageView_one.setBackground(null);
                                imageView_three.setBackground(null);
                                imageView_four.setBackground(null);
                            }else{
                                imageView_two.setBackgroundDrawable(highlight);
                            }
                        }
                    });

                    imageView_three.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosen = "b";
                            Drawable highlight = getResources().getDrawable( R.drawable.highlight);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                imageView_three.setBackground(highlight);
                                imageView_two.setBackground(null);
                                imageView_one.setBackground(null);
                                imageView_four.setBackground(null);
                            }else{
                                imageView_three.setBackgroundDrawable(highlight);
                            }
                        }
                    });

                    imageView_four.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosen = "b";
                            Drawable highlight = getResources().getDrawable( R.drawable.highlight);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                imageView_four.setBackground(highlight);
                                imageView_two.setBackground(null);
                                imageView_three.setBackground(null);
                                imageView_one.setBackground(null);
                            }else{
                                imageView_four.setBackgroundDrawable(highlight);
                            }
                        }
                    });}

                else if (btnPress == 2) {
                    imageView_one.setImageResource(R.drawable.hat_one);
                    imageView_two.setImageResource(R.drawable.hat_two);
                    imageView_three.setImageResource(R.drawable.hat_three);
                    imageView_four.setImageResource(R.drawable.hat_four);
                    imageView_one.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosen = "a";
                            Drawable highlight = getResources().getDrawable( R.drawable.highlight);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                imageView_one.setBackground(highlight);
                                imageView_two.setBackground(null);
                                imageView_three.setBackground(null);
                                imageView_four.setBackground(null);
                            }else{
                                imageView_one.setBackgroundDrawable(highlight);
                            }
                        }
                    });

                    imageView_two.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosen = "b";
                            Drawable highlight = getResources().getDrawable( R.drawable.highlight);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                imageView_two.setBackground(highlight);
                                imageView_one.setBackground(null);
                                imageView_three.setBackground(null);
                                imageView_four.setBackground(null);
                            }else{
                                imageView_two.setBackgroundDrawable(highlight);
                            }
                        }
                    });

                    imageView_three.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosen = "b";
                            Drawable highlight = getResources().getDrawable( R.drawable.highlight);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                imageView_three.setBackground(highlight);
                                imageView_two.setBackground(null);
                                imageView_one.setBackground(null);
                                imageView_four.setBackground(null);
                            }else{
                                imageView_three.setBackgroundDrawable(highlight);
                            }
                        }
                    });

                    imageView_four.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosen = "b";
                            Drawable highlight = getResources().getDrawable( R.drawable.highlight);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                imageView_four.setBackground(highlight);
                                imageView_two.setBackground(null);
                                imageView_three.setBackground(null);
                                imageView_one.setBackground(null);
                            }else{
                                imageView_four.setBackgroundDrawable(highlight);
                            }
                        }
                    });

                }
                else if (btnPress == 3) {
                    imageView_one.setImageResource(R.drawable.table_one);
                    imageView_two.setImageResource(R.drawable.table_two);
                    imageView_three.setImageResource(R.drawable.table_three);
                    imageView_four.setImageResource(R.drawable.table_four);
                    imageView_one.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosen = "b";
                            Drawable highlight = getResources().getDrawable( R.drawable.highlight);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                imageView_one.setBackground(highlight);
                                imageView_two.setBackground(null);
                                imageView_three.setBackground(null);
                                imageView_four.setBackground(null);
                            }else{
                                imageView_one.setBackgroundDrawable(highlight);
                            }
                        }
                    });

                    imageView_two.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosen = "b";
                            Drawable highlight = getResources().getDrawable( R.drawable.highlight);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                imageView_two.setBackground(highlight);
                                imageView_one.setBackground(null);
                                imageView_three.setBackground(null);
                                imageView_four.setBackground(null);
                            }else{
                                imageView_two.setBackgroundDrawable(highlight);
                            }
                        }
                    });

                    imageView_three.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosen = "a";
                            Drawable highlight = getResources().getDrawable( R.drawable.highlight);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                imageView_three.setBackground(highlight);
                                imageView_two.setBackground(null);
                                imageView_one.setBackground(null);
                                imageView_four.setBackground(null);
                            }else{
                                imageView_three.setBackgroundDrawable(highlight);
                            }
                        }
                    });

                    imageView_four.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosen = "b";
                            Drawable highlight = getResources().getDrawable( R.drawable.highlight);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                imageView_four.setBackground(highlight);
                                imageView_two.setBackground(null);
                                imageView_three.setBackground(null);
                                imageView_one.setBackground(null);
                            }else{
                                imageView_four.setBackgroundDrawable(highlight);
                            }
                        }
                    });

                }
                else if (btnPress == 4) {
                    imageView_one.setImageResource(R.drawable.truck_one);
                    imageView_two.setImageResource(R.drawable.truck_two);
                    imageView_three.setImageResource(R.drawable.truck_three);
                    imageView_four.setImageResource(R.drawable.truck_four);
                    imageView_one.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosen = "b";
                            Drawable highlight = getResources().getDrawable( R.drawable.highlight);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                imageView_one.setBackground(highlight);
                                imageView_two.setBackground(null);
                                imageView_three.setBackground(null);
                                imageView_four.setBackground(null);
                            }else{
                                imageView_one.setBackgroundDrawable(highlight);
                            }
                        }
                    });

                    imageView_two.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosen = "b";
                            Drawable highlight = getResources().getDrawable( R.drawable.highlight);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                imageView_two.setBackground(highlight);
                                imageView_one.setBackground(null);
                                imageView_three.setBackground(null);
                                imageView_four.setBackground(null);
                            }else{
                                imageView_two.setBackgroundDrawable(highlight);
                            }
                        }
                    });

                    imageView_three.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosen = "a";
                            Drawable highlight = getResources().getDrawable( R.drawable.highlight);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                imageView_three.setBackground(highlight);
                                imageView_two.setBackground(null);
                                imageView_one.setBackground(null);
                                imageView_four.setBackground(null);
                            }else{
                                imageView_three.setBackgroundDrawable(highlight);
                            }
                        }
                    });

                    imageView_four.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosen = "b";
                            Drawable highlight = getResources().getDrawable( R.drawable.highlight);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                imageView_four.setBackground(highlight);
                                imageView_two.setBackground(null);
                                imageView_three.setBackground(null);
                                imageView_one.setBackground(null);
                            }else{
                                imageView_four.setBackgroundDrawable(highlight);
                            }
                        }
                    });

                }
                else if (btnPress == 5) {
                    imageView_one.setImageResource(R.drawable.window_one);
                    imageView_two.setImageResource(R.drawable.window_two);
                    imageView_three.setImageResource(R.drawable.window_three);
                    imageView_four.setImageResource(R.drawable.window_four);
                    imageView_one.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosen = "b";
                            Drawable highlight = getResources().getDrawable( R.drawable.highlight);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                imageView_one.setBackground(highlight);
                                imageView_two.setBackground(null);
                                imageView_three.setBackground(null);
                                imageView_four.setBackground(null);
                            }else{
                                imageView_one.setBackgroundDrawable(highlight);
                            }
                        }
                    });

                    imageView_two.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosen = "a";
                            Drawable highlight = getResources().getDrawable( R.drawable.highlight);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                imageView_two.setBackground(highlight);
                                imageView_one.setBackground(null);
                                imageView_three.setBackground(null);
                                imageView_four.setBackground(null);
                            }else{
                                imageView_two.setBackgroundDrawable(highlight);
                            }
                        }
                    });

                    imageView_three.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosen = "b";
                            Drawable highlight = getResources().getDrawable( R.drawable.highlight);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                imageView_three.setBackground(highlight);
                                imageView_two.setBackground(null);
                                imageView_one.setBackground(null);
                                imageView_four.setBackground(null);
                            }else{
                                imageView_three.setBackgroundDrawable(highlight);
                            }
                        }
                    });

                    imageView_four.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosen = "b";
                            Drawable highlight = getResources().getDrawable( R.drawable.highlight);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                imageView_four.setBackground(highlight);
                                imageView_two.setBackground(null);
                                imageView_three.setBackground(null);
                                imageView_one.setBackground(null);
                            }else{
                                imageView_four.setBackgroundDrawable(highlight);
                            }
                        }
                    });

                }
                else {
                if (correct == 0){
                    AlertDialog.Builder mBuilder= new AlertDialog.Builder(ExampleImageAnswers.this);
                    View myView = getLayoutInflater().inflate(R.layout.end_test, null);
                    TextView top = (TextView) myView.findViewById(R.id.textNone);
                    TextView explanationText = (TextView) myView.findViewById(R.id.textViewExplanation);

                    //The start example test button
                    Button exitBtn = (Button) myView.findViewById(R.id.bntNextExample);
                    exitBtn.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            //startActivity(new Intent(getApplicationContext(), EndTest.class));
                            startActivity(new Intent(getApplicationContext(), SecAR_Activity.class));
                        }
                    });

                    mBuilder.setView(myView);
                    AlertDialog dialog = mBuilder.create();
                    dialog.show();
                    dialog.setCanceledOnTouchOutside(false);

                }
                else {
                    AlertDialog.Builder mBuilder= new AlertDialog.Builder(ExampleImageAnswers.this);
                    View myView = getLayoutInflater().inflate(R.layout.begin_real_test, null);

                    //The start example test button
                    Button beginBtn = (Button) myView.findViewById(R.id.beginBtn);
                    beginBtn.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(getApplicationContext(), TestImageChange.class));
                        }
                    });

                    mBuilder.setView(myView);
                    AlertDialog dialog = mBuilder.create();
                    dialog.show();
                    dialog.setCanceledOnTouchOutside(false);

                }
            }
        }

    });


    }
    @Override
    public void onBackPressed() {
    }
}
