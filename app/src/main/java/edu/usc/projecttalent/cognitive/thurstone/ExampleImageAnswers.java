package edu.usc.projecttalent.cognitive.thurstone;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import edu.usc.projecttalent.cognitive.R;
import edu.usc.projecttalent.cognitive.model.Answer;
import edu.usc.projecttalent.cognitive.model.Block;
import edu.usc.projecttalent.cognitive.model.Section;
import edu.usc.projecttalent.cognitive.model.Survey;
import edu.usc.projecttalent.cognitive.reasoning.SecAR_Activity;

/**
 * Created by kayigwe on 6/24/17.
 */

public class ExampleImageAnswers extends Activity {

    Section mSection;

    int correct = 0;
    int btnPress = 0;
    String chosen = "n";
    boolean correcti;
    int index = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example_answers);

        final ImageView imageView_one = (ImageView) findViewById(R.id.imageView_one);
        final ImageView imageView_two = (ImageView) findViewById(R.id.imageView_two);
        final ImageView imageView_three = (ImageView) findViewById(R.id.imageView_three);
        final ImageView imageView_four = (ImageView) findViewById(R.id.imageView_four);
        final Button btn = (Button) findViewById(R.id.btnSwitch);

        final Block mBlock;


        //Section mSection;
        mSection = new Section("Thurstone_Example");


        mBlock = new Block(1);
        Answer mAnswer;
        mAnswer = new Answer();
        mAnswer.endAnswer(index, correcti);
        mBlock.addAnswer(mAnswer);


        btn.setEnabled(false);
        imageView_one.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                chosen = "b";
                correcti = false;
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
                correcti = false;
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
                correcti = true;
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
                correcti = false;
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
            Answer mAnswer;

            @Override
            public void onClick(View v) {
                if (chosen == "a"){
                    correct++;
                    correcti = true;
                    mAnswer = new Answer();
                    mAnswer.endAnswer(index, correcti);
                    mBlock.addAnswer(mAnswer);
                }
                else if (chosen == "b"){

                    correcti = false;
                    mAnswer = new Answer();
                    mAnswer.endAnswer(index, correcti);
                    mBlock.addAnswer(mAnswer);
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

                    //The start example test button
                    Button exitBtn = (Button) myView.findViewById(R.id.bntNextExample);
                    exitBtn.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            startActivityForResult(new Intent(getApplicationContext(), SecAR_Activity.class), 1);
                        }
                    });

                    mBuilder.setView(myView);
                    AlertDialog dialog = mBuilder.create();
                    dialog.show();
                    dialog.setCanceledOnTouchOutside(false);

                    mBlock.endBlock(correct);
                    mSection.addBlock(mBlock);
                    mSection.endSection(); //end this section.
                    Survey.getSurvey().addSection(mSection);

                }
                else {
                    AlertDialog.Builder mBuilder= new AlertDialog.Builder(ExampleImageAnswers.this);
                    View myView = getLayoutInflater().inflate(R.layout.begin_real_test, null);

                    //The start example test button
                    Button beginBtn = (Button) myView.findViewById(R.id.beginBtn);
                    beginBtn.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            startActivityForResult(new Intent(getApplicationContext(), TestImageChange.class), 1);
                        }
                    });

                    mBuilder.setView(myView);
                    AlertDialog dialog = mBuilder.create();
                    dialog.show();
                    dialog.setCanceledOnTouchOutside(false);

                    mBlock.endBlock(correct);
                    mSection.addBlock(mBlock);
                    mSection.endSection(); //end this section.
                    Survey.getSurvey().addSection(mSection);

                }
            }
        }

    });


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
