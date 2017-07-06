package edu.usc.projecttalent.cognitive.thurstone;

import edu.usc.projecttalent.cognitive.R;
import edu.usc.projecttalent.cognitive.model.Answer;
import edu.usc.projecttalent.cognitive.model.Block;
import edu.usc.projecttalent.cognitive.model.Section;
import edu.usc.projecttalent.cognitive.reasoning.ARIntro_Activity;
import edu.usc.projecttalent.cognitive.reasoning.SecAR_Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.drawable.Drawable;
import edu.usc.projecttalent.cognitive.model.Answer;
import edu.usc.projecttalent.cognitive.model.Block;
import edu.usc.projecttalent.cognitive.model.Section;
import edu.usc.projecttalent.cognitive.model.Survey;

/**
 * Created by kayigwe on 6/24/17.
 */

public class TestImageAnswers extends Activity {

    ImageView imageView_one;
    ImageView imageView_two;
    ImageView image_three;
    ImageView imageView_four;
    Section mSection;

    Button btn;
    int correct = 0;
    int btnPress = 0;
    String chosen = "n";
    boolean correcti;
    int index = 0;

    /*
    Array of correct answers
    int[] imageArray = {R.drawable.zero, R.mipmap.jacket_one, R.mipmap.girl_four, R.mipmap.horse_one, R.mipmap.pumpkin_four, R.mipmap.umbrella_four, R.mipmap.chicken_two,
    R.mipmap.duck_four, R.mipmap.elephant_one, R.mipmap.girl_door_one, R.mipmap.cake_two, R.mipmap.fire_hat_one, R.mipmap.baby_bird_one,
    R.mipmap.boat_three, R.mipmap.ship_four, R.mipmap.row_boat_two, R.mipmap.clown_three, R.mipmap.nest_four, R.mipmap.party_hat_three,
    R.mipmap.girl_window_one, R.mipmap.key_three, R.mipmap.butterfly_three, R.mipmap.chick_two, R.mipmap.house_one, R.mipmap.toys_three,
    R.mipmap.toy_egg_four, R.mipmap.kennel_three, R.mipmap.boots_two, R.mipmap.box_four};*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_answers);

        final ImageView imageView_one = (ImageView) findViewById(R.id.imageView_one);
        final ImageView imageView_two = (ImageView) findViewById(R.id.imageView_two);
        final ImageView imageView_three = (ImageView) findViewById(R.id.imageView_three);
        final ImageView imageView_four = (ImageView) findViewById(R.id.imageView_four);
        Button btn = (Button) findViewById(R.id.btnSwitch);


        final Block mBlock;


        //Section mSection;
        mSection = new Section("Thurstone");

        //QuestionTimer.startTimer(mContext);

        /*mAnswer = new Answer();
        mAnswer.endAnswer(index, correct);
        mBlock.addAnswer(mAnswer);
        28 TIMES*/

        /*mBlock.endBlock(mScore);
        mSection.addBlock(mBlock);
        mSection.endSection(); //end this section.
        Survey.getSurvey().addSection(mSection); //add vocab section to survey.*/

        mBlock = new Block(1);
        Answer mAnswer;
        mAnswer = new Answer();
        mAnswer.endAnswer(index, correcti);
        mBlock.addAnswer(mAnswer);
        imageView_one.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                index = 1;
                Drawable highlight = getResources().getDrawable( R.drawable.highlight);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    chosen = "a";
                    imageView_one.setBackground(highlight);
                    imageView_two.setBackground(null);
                    imageView_three.setBackground(null);
                    imageView_four.setBackground(null);
                }else{
                    imageView_one.setBackgroundDrawable(highlight);
                }
                imageView_one.setImageResource(R.mipmap.jacket_one);
                imageView_two.setImageResource(R.mipmap.jacket_two);
                imageView_three.setImageResource(R.mipmap.jacket_three);
                imageView_four.setImageResource(R.mipmap.jacket_four);

            }
        });

        imageView_two.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                index = 2;
                Drawable highlight = getResources().getDrawable( R.drawable.highlight);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    chosen = "b";
                    imageView_two.setBackground(highlight);
                    imageView_one.setBackground(null);
                    imageView_three.setBackground(null);
                    imageView_four.setBackground(null);
                }else{
                    imageView_two.setBackgroundDrawable(highlight);
                }
                imageView_two.setImageResource(R.mipmap.jacket_two);
                imageView_one.setImageResource(R.mipmap.jacket_one);
                imageView_three.setImageResource(R.mipmap.jacket_three);
                imageView_four.setImageResource(R.mipmap.jacket_four);
            }
        });

        imageView_three.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                index = 3;
                Drawable highlight = getResources().getDrawable( R.drawable.highlight);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    chosen = "b";
                    imageView_three.setBackground(highlight);
                    imageView_two.setBackground(null);
                    imageView_one.setBackground(null);
                    imageView_four.setBackground(null);
                }else{
                    imageView_three.setBackgroundDrawable(highlight);
                }
                imageView_three.setImageResource(R.mipmap.jacket_three);
                imageView_two.setImageResource(R.mipmap.jacket_two);
                imageView_one.setImageResource(R.mipmap.jacket_one);
                imageView_four.setImageResource(R.mipmap.jacket_four);
            }
        });

        imageView_four.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                index = 4;
                Drawable highlight = getResources().getDrawable( R.drawable.highlight);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    chosen = "b";
                    imageView_four.setBackground(highlight);
                    imageView_two.setBackground(null);
                    imageView_three.setBackground(null);
                    imageView_one.setBackground(null);
                }else{
                    imageView_four.setBackgroundDrawable(highlight);
                }
                imageView_four.setImageResource(R.mipmap.jacket_four);
                imageView_two.setImageResource(R.mipmap.jacket_two);
                imageView_three.setImageResource(R.mipmap.jacket_three);
                imageView_one.setImageResource(R.mipmap.jacket_one);
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
                        imageView_one.setImageResource(R.mipmap.girl_one);
                        imageView_two.setImageResource(R.mipmap.girl_two);
                        imageView_three.setImageResource(R.mipmap.girl_three);
                        imageView_four.setImageResource(R.mipmap.girl_four);
                        imageView_one.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Drawable highlight = getResources().getDrawable( R.drawable.highlight);
                                index = 1;
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                    chosen = "b";
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
                                Drawable highlight = getResources().getDrawable( R.drawable.highlight);
                                index = 2;
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                    chosen = "b";
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
                                Drawable highlight = getResources().getDrawable( R.drawable.highlight);
                                index = 3;
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                    chosen = "b";
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
                                Drawable highlight = getResources().getDrawable( R.drawable.highlight);
                                index = 4;
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                    chosen = "a";
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

                    else if (btnPress == 2) {
                        imageView_one.setImageResource(R.mipmap.horse_one);
                        imageView_two.setImageResource(R.mipmap.horse_two);
                        imageView_three.setImageResource(R.mipmap.horse_three);
                        imageView_four.setImageResource(R.mipmap.horse_four);
                    imageView_one.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosen = "a";
                            index = 1;
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
                            index = 2;
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
                            index = 3;
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
                            index = 4;
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
                    imageView_one.setImageResource(R.mipmap.pumpkin_one);
                    imageView_two.setImageResource(R.mipmap.pumpkin_two);
                    imageView_three.setImageResource(R.mipmap.pumpkin_three);
                    imageView_four.setImageResource(R.mipmap.pumpkin_four);
                    imageView_one.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosen = "b";
                            index = 1;
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
                            index = 2;
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
                            index = 3;
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
                            chosen = "a";
                            index = 4;
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
                    imageView_one.setImageResource(R.mipmap.umbrella_one);
                    imageView_two.setImageResource(R.mipmap.umbrella_two);
                    imageView_three.setImageResource(R.mipmap.umbrella_three);
                    imageView_four.setImageResource(R.mipmap.umbrella_four);
                    imageView_one.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosen = "b";
                            index = 1;
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
                            index = 2;
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
                            index = 3;
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
                            chosen = "a";
                            index = 4;
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
                    imageView_one.setImageResource(R.mipmap.chicken_one);
                    imageView_two.setImageResource(R.mipmap.chicken_two);
                    imageView_three.setImageResource(R.mipmap.chicken_three);
                    imageView_four.setImageResource(R.mipmap.chicken_four);
                    imageView_one.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosen = "b";
                            index = 1;
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
                            index = 2;
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
                            index = 3;
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
                            index = 4;
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
                else if (btnPress == 6) {
                    imageView_one.setImageResource(R.mipmap.duck_one);
                    imageView_two.setImageResource(R.mipmap.duck_two);
                    imageView_three.setImageResource(R.mipmap.duck_three);
                    imageView_four.setImageResource(R.mipmap.duck_four);
                    imageView_one.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosen = "b";
                            index = 1;
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
                            index = 2;
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
                            index = 3;
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
                            chosen = "a";
                            index = 4;
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
                else if (btnPress == 7) {
                    imageView_one.setImageResource(R.mipmap.elephant_one);
                    imageView_two.setImageResource(R.mipmap.elephant_two);
                    imageView_three.setImageResource(R.mipmap.elephant_three);
                    imageView_four.setImageResource(R.mipmap.elephant_four);
                    imageView_one.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosen = "a";
                            index = 1;
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
                            index = 2;
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
                            index = 3;
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
                            index = 4;
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
                    if (correct == 0) {
                        AlertDialog.Builder mBuilder= new AlertDialog.Builder(TestImageAnswers.this);
                        View myView = getLayoutInflater().inflate(R.layout.exit_test, null);

                        //The start example test button
                        Button exitBtn = (Button) myView.findViewById(R.id.btnExit);
                        exitBtn.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v) {

                                //change to different activity here
                                startActivity(new Intent(getApplicationContext(), SecAR_Activity.class));
                            }
                        });

                        mBuilder.setView(myView);
                        AlertDialog dialog = mBuilder.create();
                        dialog.show();
                        dialog.setCanceledOnTouchOutside(false);
                    }

                }
                else if (btnPress == 8) {

                    imageView_one.setImageResource(R.mipmap.girl_door_one);
                    imageView_two.setImageResource(R.mipmap.girl_door_two);
                    imageView_three.setImageResource(R.mipmap.girl_door_three);
                    imageView_four.setImageResource(R.mipmap.girl_door_four);
                    imageView_one.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosen = "a";
                            index = 1;
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
                            index = 2;
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
                            index = 3;
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
                            index = 4;
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
                else if (btnPress == 9) {
                    imageView_one.setImageResource(R.mipmap.cake_one);
                    imageView_two.setImageResource(R.mipmap.cake_two);
                    imageView_three.setImageResource(R.mipmap.cake_three);
                    imageView_four.setImageResource(R.mipmap.cake_four);
                    imageView_one.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosen = "b";
                            index = 1;
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
                            index = 2;
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
                            index = 3;
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
                            index = 4;
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
                else if (btnPress == 10) {
                    imageView_one.setImageResource(R.mipmap.fire_hat_one);
                    imageView_two.setImageResource(R.mipmap.fire_hat_two);
                    imageView_three.setImageResource(R.mipmap.fire_hat_three);
                    imageView_four.setImageResource(R.mipmap.fire_hat_four);
                    imageView_one.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosen = "a";
                            index = 1;
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
                            index = 2;
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
                            index = 3;
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
                            index = 4;
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
                else if (btnPress == 11) {
                    imageView_one.setImageResource(R.mipmap.baby_bird_one);
                    imageView_two.setImageResource(R.mipmap.baby_bird_two);
                    imageView_three.setImageResource(R.mipmap.baby_bird_three);
                    imageView_four.setImageResource(R.mipmap.baby_bird_four);
                    imageView_one.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosen = "a";
                            index = 1;
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
                            index = 2;
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
                            index = 3;
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
                            index = 4;
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
                else if (btnPress == 12) {
                    imageView_one.setImageResource(R.mipmap.boat_one);
                    imageView_two.setImageResource(R.mipmap.boat_two);
                    imageView_three.setImageResource(R.mipmap.boat_three);
                    imageView_four.setImageResource(R.mipmap.boat_four);
                    imageView_one.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosen = "b";
                            index = 1;
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
                            index = 2;
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
                            index = 3;
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
                            index = 4;
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
                //START HERE WITH CHOSEN @ 13
                else if (btnPress == 13) {
                    imageView_one.setImageResource(R.mipmap.ship_one);
                    imageView_two.setImageResource(R.mipmap.ship_two);
                    imageView_three.setImageResource(R.mipmap.ship_three);
                    imageView_four.setImageResource(R.mipmap.ship_four);
                    imageView_one.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosen = "b";
                            index = 1;
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
                            index = 2;
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
                            index = 3;
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
                            chosen = "a";
                            index = 4;
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
                else if (btnPress == 14) {
                    imageView_one.setImageResource(R.mipmap.row_boat_one);
                    imageView_two.setImageResource(R.mipmap.row_boat_two);
                    imageView_three.setImageResource(R.mipmap.row_boat_three);
                    imageView_four.setImageResource(R.mipmap.row_boat_four);
                    imageView_one.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosen = "b";
                            index = 1;
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
                            index = 2;
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
                            index = 3;
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
                            index = 4;
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
                else if (btnPress == 15) {
                    imageView_one.setImageResource(R.mipmap.clown_one);
                    imageView_two.setImageResource(R.mipmap.clown_two);
                    imageView_three.setImageResource(R.mipmap.clown_three);
                    imageView_four.setImageResource(R.mipmap.clown_four);
                    imageView_one.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosen = "b";
                            index = 1;
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
                            index = 2;
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
                            index = 3;
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
                            index = 4;
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
                else if (btnPress == 16) {
                    imageView_one.setImageResource(R.mipmap.nest_one);
                    imageView_two.setImageResource(R.mipmap.nest_two);
                    imageView_three.setImageResource(R.mipmap.nest_three);
                    imageView_four.setImageResource(R.mipmap.nest_four);
                    imageView_one.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosen = "b";
                            index = 1;
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
                            index = 2;
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
                            index = 3;
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
                            chosen = "a";
                            index = 4;
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
                else if (btnPress == 17) {
                    imageView_one.setImageResource(R.mipmap.party_hat_one);
                    imageView_two.setImageResource(R.mipmap.party_hat_two);
                    imageView_three.setImageResource(R.mipmap.party_hat_three);
                    imageView_four.setImageResource(R.mipmap.party_hat_four);
                    imageView_one.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosen = "b";
                            index = 1;
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
                            index = 2;
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
                            index = 3;
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
                            index = 4;
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
                else if (btnPress == 18) {
                    imageView_one.setImageResource(R.mipmap.girl_window_one);
                    imageView_two.setImageResource(R.mipmap.girl_window_two);
                    imageView_three.setImageResource(R.mipmap.girl_window_three);
                    imageView_four.setImageResource(R.mipmap.girl_window_four);
                    imageView_one.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosen = "a";
                            index = 1;
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
                            index = 2;
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
                            index = 3;
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
                            index = 4;
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
                else if (btnPress == 19) {
                    imageView_one.setImageResource(R.mipmap.key_one);
                    imageView_two.setImageResource(R.mipmap.key_two);
                    imageView_three.setImageResource(R.mipmap.key_three);
                    imageView_four.setImageResource(R.mipmap.key_four);
                    imageView_one.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosen = "b";
                            index = 1;
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
                            index = 2;
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
                            index = 3;
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
                            index = 4;
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
                else if (btnPress == 20) {
                    imageView_one.setImageResource(R.mipmap.butterfly_one);
                    imageView_two.setImageResource(R.mipmap.butterfly_two);
                    imageView_three.setImageResource(R.mipmap.butterfly_three);
                    imageView_four.setImageResource(R.mipmap.butterfly_four);
                    imageView_one.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosen = "b";
                            index = 1;
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
                            index = 2;
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
                            index = 3;
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
                            index = 4;
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
                else if (btnPress == 21) {
                    imageView_one.setImageResource(R.mipmap.chick_one);
                    imageView_two.setImageResource(R.mipmap.chick_two);
                    imageView_three.setImageResource(R.mipmap.chick_three);
                    imageView_four.setImageResource(R.mipmap.chick_four);
                    imageView_one.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosen = "b";
                            index = 1;
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
                            index = 2;
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
                            index = 3;
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
                            index = 4;
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
                else if (btnPress == 22) {
                    imageView_one.setImageResource(R.mipmap.house_one);
                    imageView_two.setImageResource(R.mipmap.house_two);
                    imageView_three.setImageResource(R.mipmap.house_three);
                    imageView_four.setImageResource(R.mipmap.house_four);
                    imageView_one.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosen = "a";
                            index = 1;
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
                            index = 2;
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
                            index = 3;
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
                            index = 4;
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
                else if (btnPress == 23) {
                    imageView_one.setImageResource(R.mipmap.toys_one);
                    imageView_two.setImageResource(R.mipmap.toys_two);
                    imageView_three.setImageResource(R.mipmap.toys_three);
                    imageView_four.setImageResource(R.mipmap.toys_four);
                    imageView_one.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosen = "b";
                            index = 1;
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
                            index = 2;
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
                            index = 3;
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
                            index = 4;
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
                else if (btnPress == 24) {
                    imageView_one.setImageResource(R.mipmap.toy_egg_one);
                    imageView_two.setImageResource(R.mipmap.toy_egg_two);
                    imageView_three.setImageResource(R.mipmap.toy_egg_three);
                    imageView_four.setImageResource(R.mipmap.toy_egg_four);
                    imageView_one.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            index = 1;
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
                            index = 2;
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
                            index = 3;
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
                            chosen = "a";
                            index = 4;
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
                else if (btnPress == 25) {
                    imageView_one.setImageResource(R.mipmap.kennel_one);
                    imageView_two.setImageResource(R.mipmap.kennel_two);
                    imageView_three.setImageResource(R.mipmap.kennel_three);
                    imageView_four.setImageResource(R.mipmap.kennel_four);
                    imageView_one.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            index = 1;
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
                            index = 2;
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
                            index = 3;
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
                            index = 4;
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
                else if (btnPress == 26) {
                    imageView_one.setImageResource(R.mipmap.boots_one);
                    imageView_two.setImageResource(R.mipmap.boots_two);
                    imageView_three.setImageResource(R.mipmap.boots_three);
                    imageView_four.setImageResource(R.mipmap.boots_four);
                    imageView_one.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            index = 1;
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
                            index = 2;
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
                            index = 3;
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
                            index = 4;
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
                else if (btnPress == 27) {
                    imageView_one.setImageResource(R.mipmap.box_one);
                    imageView_two.setImageResource(R.mipmap.box_two);
                    imageView_three.setImageResource(R.mipmap.box_three);
                    imageView_four.setImageResource(R.mipmap.box_four);
                    imageView_one.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            index = 1;
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
                            index = 2;
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
                            index = 3;
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
                            index = 4;
                            chosen = "a";
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
                        AlertDialog.Builder mBuilder= new AlertDialog.Builder(TestImageAnswers.this);
                        View myView = getLayoutInflater().inflate(R.layout.end_test, null);
                        TextView top = (TextView) myView.findViewById(R.id.textNone);
                        TextView explanationText = (TextView) myView.findViewById(R.id.textViewExplanation);

                        //The start example test button
                        Button exitBtn = (Button) myView.findViewById(R.id.bntNextExample);
                        exitBtn.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v) {
                                //startActivity(new Intent(getApplicationContext(), ExampleImageChange.class));
                                startActivity(new Intent(getApplicationContext(), SecAR_Activity.class));
                            }
                        });

                        mBuilder.setView(myView);
                        AlertDialog dialog = mBuilder.create();
                        dialog.show();
                        dialog.setCanceledOnTouchOutside(false);

                    }
                    else {
                        AlertDialog.Builder mBuilder= new AlertDialog.Builder(TestImageAnswers.this);
                        View myView = getLayoutInflater().inflate(R.layout.exit_test, null);

                        //The start example test button
                        Button exitBtn = (Button) myView.findViewById(R.id.btnExit);
                        exitBtn.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v) {
                                //startActivity(new Intent(getApplicationContext(), TestImageChange.class));
                                startActivity(new Intent(getApplicationContext(), SecAR_Activity.class));
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
}
