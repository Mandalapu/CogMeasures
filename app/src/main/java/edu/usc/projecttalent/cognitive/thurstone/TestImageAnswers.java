package edu.usc.projecttalent.cognitive.thurstone;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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
 * Test Image Answers for Thurstone.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class TestImageAnswers extends Activity {

    Section mSection;

    int correct = 0;
    int btnPress = 0;
    String chosen = "n";
    boolean correcti;
    int index = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_answers);

        ImageView imageView_one = (ImageView) findViewById(R.id.imageView_one);
        ImageView imageView_two = (ImageView) findViewById(R.id.imageView_two);
        ImageView imageView_three = (ImageView) findViewById(R.id.imageView_three);
        ImageView imageView_four = (ImageView) findViewById(R.id.imageView_four);
        Button btn = (Button) findViewById(R.id.btnSwitch);

        mSection = new Section("Thurstone");
        Drawable highlight = getResources().getDrawable(R.drawable.highlight);

        Block mBlock = new Block(1);
        Answer mAnswer;
        mAnswer = new Answer();
        mAnswer.endAnswer(index, correcti);
        mBlock.addAnswer(mAnswer);
        btn.setEnabled(false);
        imageView_one.setOnClickListener(v -> {
            index = 1;
            correcti = true;
            btn.setEnabled(true);
            chosen = "a";
            imageView_one.setBackground(highlight);
            imageView_two.setBackground(null);
            imageView_three.setBackground(null);
            imageView_four.setBackground(null);
            imageView_one.setImageResource(R.drawable.jacket_one);
            imageView_two.setImageResource(R.drawable.jacket_two);
            imageView_three.setImageResource(R.drawable.jacket_three);
            imageView_four.setImageResource(R.drawable.jacket_four);

        });

        imageView_two.setOnClickListener(v -> {
            index = 2;
            correcti = false;
            btn.setEnabled(true);
            chosen = "b";
            imageView_two.setBackground(highlight);
            imageView_one.setBackground(null);
            imageView_three.setBackground(null);
            imageView_four.setBackground(null);
            imageView_two.setImageResource(R.drawable.jacket_two);
            imageView_one.setImageResource(R.drawable.jacket_one);
            imageView_three.setImageResource(R.drawable.jacket_three);
            imageView_four.setImageResource(R.drawable.jacket_four);
        });

        imageView_three.setOnClickListener(v -> {
            index = 3;
            correcti = false;
            btn.setEnabled(true);
            chosen = "b";
            imageView_three.setBackground(highlight);
            imageView_two.setBackground(null);
            imageView_one.setBackground(null);
            imageView_four.setBackground(null);
            imageView_three.setImageResource(R.drawable.jacket_three);
            imageView_two.setImageResource(R.drawable.jacket_two);
            imageView_one.setImageResource(R.drawable.jacket_one);
            imageView_four.setImageResource(R.drawable.jacket_four);
        });

        imageView_four.setOnClickListener(v -> {
            index = 4;
            correcti = false;
            btn.setEnabled(true);
            chosen = "b";
            imageView_four.setBackground(highlight);
            imageView_two.setBackground(null);
            imageView_three.setBackground(null);
            imageView_one.setBackground(null);
            imageView_four.setImageResource(R.drawable.jacket_four);
            imageView_two.setImageResource(R.drawable.jacket_two);
            imageView_three.setImageResource(R.drawable.jacket_three);
            imageView_one.setImageResource(R.drawable.jacket_one);
        });

        btn.setOnClickListener(new View.OnClickListener() {
            Answer mAnswer;

            @Override
            public void onClick(View v) {
                if (chosen == "a") {
                    correct++;

                    correcti = true;
                    mAnswer = new Answer();
                    mAnswer.endAnswer(index, correcti);
                    mBlock.addAnswer(mAnswer);
                } else if (chosen == "b") {

                    correcti = false;
                    mAnswer = new Answer();
                    mAnswer.endAnswer(index, correcti);
                    mBlock.addAnswer(mAnswer);
                } else if (chosen == "n") {
                    //pop up don't let person move on
                    btnPress--;
                }
                chosen = "n";
                chosen = "n";
                imageView_one.setBackground(null);
                imageView_two.setBackground(null);
                imageView_three.setBackground(null);
                imageView_four.setBackground(null);
                btnPress++;
                if (btnPress == 1) {
                    imageView_one.setImageResource(R.drawable.girl_one);
                    imageView_two.setImageResource(R.drawable.girl_two);
                    imageView_three.setImageResource(R.drawable.girl_three);
                    imageView_four.setImageResource(R.drawable.girl_four);
                    imageView_one.setOnClickListener(v1 -> {
                        index = 1;
                        chosen = "b";
                        imageView_one.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_two.setOnClickListener(v12 -> {

                        index = 2;
                        chosen = "b";
                        imageView_two.setBackground(highlight);
                        imageView_one.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_three.setOnClickListener(v13 -> {

                        index = 3;
                        chosen = "b";
                        imageView_three.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_one.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_four.setOnClickListener(v14 -> {

                        index = 4;
                        chosen = "a";
                        imageView_four.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_one.setBackground(null);
                    });
                } else if (btnPress == 2) {
                    imageView_one.setImageResource(R.drawable.horse_one);
                    imageView_two.setImageResource(R.drawable.horse_two);
                    imageView_three.setImageResource(R.drawable.horse_three);
                    imageView_four.setImageResource(R.drawable.horse_four);
                    imageView_one.setOnClickListener(v15 -> {
                        chosen = "a";
                        index = 1;

                        imageView_one.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_two.setOnClickListener(v16 -> {
                        chosen = "b";
                        index = 2;

                        imageView_two.setBackground(highlight);
                        imageView_one.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_three.setOnClickListener(v17 -> {
                        chosen = "b";
                        index = 3;

                        imageView_three.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_one.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_four.setOnClickListener(v18 -> {
                        chosen = "b";
                        index = 4;

                        imageView_four.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_one.setBackground(null);
                    });

                } else if (btnPress == 3) {
                    imageView_one.setImageResource(R.drawable.pumpkin_one);
                    imageView_two.setImageResource(R.drawable.pumpkin_two);
                    imageView_three.setImageResource(R.drawable.pumpkin_three);
                    imageView_four.setImageResource(R.drawable.pumpkin_four);
                    imageView_one.setOnClickListener(v19 -> {
                        chosen = "b";
                        index = 1;

                        imageView_one.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_two.setOnClickListener(v110 -> {
                        chosen = "b";
                        index = 2;

                        imageView_two.setBackground(highlight);
                        imageView_one.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_three.setOnClickListener(v111 -> {
                        chosen = "b";
                        index = 3;

                        imageView_three.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_one.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_four.setOnClickListener(v112 -> {
                        chosen = "a";
                        index = 4;

                        imageView_four.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_one.setBackground(null);
                    });

                } else if (btnPress == 4) {
                    imageView_one.setImageResource(R.drawable.umbrella_one);
                    imageView_two.setImageResource(R.drawable.umbrella_two);
                    imageView_three.setImageResource(R.drawable.umbrella_three);
                    imageView_four.setImageResource(R.drawable.umbrella_four);
                    imageView_one.setOnClickListener(v113 -> {
                        chosen = "b";
                        index = 1;

                        imageView_one.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_two.setOnClickListener(v114 -> {
                        chosen = "b";
                        index = 2;

                        imageView_two.setBackground(highlight);
                        imageView_one.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_three.setOnClickListener(v115 -> {
                        chosen = "b";
                        index = 3;

                        imageView_three.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_one.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_four.setOnClickListener(v116 -> {
                        chosen = "a";
                        index = 4;

                        imageView_four.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_one.setBackground(null);
                    });

                } else if (btnPress == 5) {
                    imageView_one.setImageResource(R.drawable.chicken_one);
                    imageView_two.setImageResource(R.drawable.chicken_two);
                    imageView_three.setImageResource(R.drawable.chicken_three);
                    imageView_four.setImageResource(R.drawable.chicken_four);
                    imageView_one.setOnClickListener(v117 -> {
                        chosen = "b";
                        index = 1;

                        imageView_one.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_two.setOnClickListener(v118 -> {
                        chosen = "a";
                        index = 2;

                        imageView_two.setBackground(highlight);
                        imageView_one.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_three.setOnClickListener(v119 -> {
                        chosen = "b";
                        index = 3;

                        imageView_three.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_one.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_four.setOnClickListener(v120 -> {
                        chosen = "b";
                        index = 4;

                        imageView_four.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_one.setBackground(null);
                    });

                } else if (btnPress == 6) {
                    imageView_one.setImageResource(R.drawable.duck_one);
                    imageView_two.setImageResource(R.drawable.duck_two);
                    imageView_three.setImageResource(R.drawable.duck_three);
                    imageView_four.setImageResource(R.drawable.duck_four);
                    imageView_one.setOnClickListener(v121 -> {
                        chosen = "b";
                        index = 1;
                        imageView_one.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_two.setOnClickListener(v122 -> {
                        chosen = "b";
                        index = 2;
                        imageView_two.setBackground(highlight);
                        imageView_one.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_three.setOnClickListener(v123 -> {
                        chosen = "b";
                        index = 3;
                        imageView_three.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_one.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_four.setOnClickListener(v124 -> {
                        chosen = "a";
                        index = 4;
                        imageView_four.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_one.setBackground(null);
                    });

                } else if (btnPress == 7) {
                    imageView_one.setImageResource(R.drawable.elephant_one);
                    imageView_two.setImageResource(R.drawable.elephant_two);
                    imageView_three.setImageResource(R.drawable.elephant_three);
                    imageView_four.setImageResource(R.drawable.elephant_four);
                    imageView_one.setOnClickListener(v125 -> {
                        chosen = "a";
                        index = 1;

                        imageView_one.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_two.setOnClickListener(v126 -> {
                        chosen = "b";
                        index = 2;

                        imageView_two.setBackground(highlight);
                        imageView_one.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_three.setOnClickListener(v127 -> {
                        chosen = "b";
                        index = 3;
                        imageView_three.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_one.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_four.setOnClickListener(v128 -> {
                        chosen = "b";
                        index = 4;

                        imageView_four.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_one.setBackground(null);
                    });
                    if (correct == 0) {
                        AlertDialog.Builder mBuilder = new AlertDialog.Builder(TestImageAnswers.this);
                        View myView = getLayoutInflater().inflate(R.layout.exit_test, null);

                        //The start example test button
                        Button exitBtn = (Button) myView.findViewById(R.id.btnExit);
                        exitBtn.setOnClickListener(v129 -> {
                            startActivityForResult(new Intent(getApplicationContext(), SecAR_Activity.class), 1);
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

                } else if (btnPress == 8) {

                    imageView_one.setImageResource(R.drawable.girl_door_one);
                    imageView_two.setImageResource(R.drawable.girl_door_two);
                    imageView_three.setImageResource(R.drawable.girl_door_three);
                    imageView_four.setImageResource(R.drawable.girl_door_four);
                    imageView_one.setOnClickListener(v130 -> {
                        chosen = "a";
                        index = 1;

                        imageView_one.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_two.setOnClickListener(v131 -> {
                        chosen = "b";
                        index = 2;
                        imageView_one.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_three.setOnClickListener(v132 -> {
                        chosen = "b";
                        index = 3;
                        imageView_two.setBackground(null);
                        imageView_one.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_four.setOnClickListener(v133 -> {
                        chosen = "b";
                        index = 4;

                        imageView_four.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_one.setBackground(null);
                    });

                } else if (btnPress == 9) {
                    imageView_one.setImageResource(R.drawable.cake_one);
                    imageView_two.setImageResource(R.drawable.cake_two);
                    imageView_three.setImageResource(R.drawable.cake_three);
                    imageView_four.setImageResource(R.drawable.cake_four);
                    imageView_one.setOnClickListener(v134 -> {
                        chosen = "b";
                        index = 1;

                        imageView_one.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_two.setOnClickListener(v135 -> {
                        chosen = "a";
                        index = 2;

                        imageView_two.setBackground(highlight);
                        imageView_one.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_three.setOnClickListener(v136 -> {
                        chosen = "b";
                        index = 3;
                        imageView_three.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_one.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_four.setOnClickListener(v137 -> {
                        chosen = "b";
                        index = 4;

                        imageView_four.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_one.setBackground(null);
                    });

                } else if (btnPress == 10) {
                    imageView_one.setImageResource(R.drawable.fire_hat_one);
                    imageView_two.setImageResource(R.drawable.fire_hat_two);
                    imageView_three.setImageResource(R.drawable.fire_hat_three);
                    imageView_four.setImageResource(R.drawable.fire_hat_four);
                    imageView_one.setOnClickListener(v138 -> {
                        chosen = "a";
                        index = 1;

                        imageView_one.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_two.setOnClickListener(v139 -> {
                        chosen = "b";
                        index = 2;

                        imageView_two.setBackground(highlight);
                        imageView_one.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_three.setOnClickListener(v140 -> {
                        chosen = "b";
                        index = 3;

                        imageView_three.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_one.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_four.setOnClickListener(v141 -> {
                        chosen = "b";
                        index = 4;

                        imageView_four.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_one.setBackground(null);
                    });

                } else if (btnPress == 11) {
                    imageView_one.setImageResource(R.drawable.baby_bird_one);
                    imageView_two.setImageResource(R.drawable.baby_bird_two);
                    imageView_three.setImageResource(R.drawable.baby_bird_three);
                    imageView_four.setImageResource(R.drawable.baby_bird_four);
                    imageView_one.setOnClickListener(v142 -> {
                        chosen = "a";
                        index = 1;

                        imageView_one.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_two.setOnClickListener(v143 -> {
                        chosen = "b";
                        index = 2;

                        imageView_two.setBackground(highlight);
                        imageView_one.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_three.setOnClickListener(v144 -> {
                        chosen = "b";
                        index = 3;

                        imageView_three.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_one.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_four.setOnClickListener(v145 -> {
                        chosen = "b";
                        index = 4;

                        imageView_four.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_one.setBackground(null);
                    });

                } else if (btnPress == 12) {
                    imageView_one.setImageResource(R.drawable.boat_one);
                    imageView_two.setImageResource(R.drawable.boat_two);
                    imageView_three.setImageResource(R.drawable.boat_three);
                    imageView_four.setImageResource(R.drawable.boat_four);
                    imageView_one.setOnClickListener(v146 -> {
                        chosen = "b";
                        index = 1;

                        imageView_one.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_two.setOnClickListener(v147 -> {
                        chosen = "b";
                        index = 2;

                        imageView_two.setBackground(highlight);
                        imageView_one.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_three.setOnClickListener(v148 -> {
                        chosen = "a";
                        index = 3;

                        imageView_three.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_one.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_four.setOnClickListener(v149 -> {
                        chosen = "b";
                        index = 4;

                        imageView_four.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_one.setBackground(null);
                    });

                }
                //START HERE WITH CHOSEN @ 13
                else if (btnPress == 13) {
                    imageView_one.setImageResource(R.drawable.ship_one);
                    imageView_two.setImageResource(R.drawable.ship_two);
                    imageView_three.setImageResource(R.drawable.ship_three);
                    imageView_four.setImageResource(R.drawable.ship_four);
                    imageView_one.setOnClickListener(v150 -> {
                        chosen = "b";
                        index = 1;

                        imageView_one.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_two.setOnClickListener(v151 -> {
                        chosen = "b";
                        index = 2;

                        imageView_two.setBackground(highlight);
                        imageView_one.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_three.setOnClickListener(v152 -> {
                        chosen = "b";
                        index = 3;

                        imageView_three.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_one.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_four.setOnClickListener(v153 -> {
                        chosen = "a";
                        index = 4;

                        imageView_four.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_one.setBackground(null);
                    });

                } else if (btnPress == 14) {
                    imageView_one.setImageResource(R.drawable.row_boat_one);
                    imageView_two.setImageResource(R.drawable.row_boat_two);
                    imageView_three.setImageResource(R.drawable.row_boat_three);
                    imageView_four.setImageResource(R.drawable.row_boat_four);
                    imageView_one.setOnClickListener(v154 -> {
                        chosen = "b";
                        index = 1;

                        imageView_one.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_two.setOnClickListener(v155 -> {
                        chosen = "a";
                        index = 2;

                        imageView_two.setBackground(highlight);
                        imageView_one.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_three.setOnClickListener(v156 -> {
                        chosen = "b";
                        index = 3;

                        imageView_three.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_one.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_four.setOnClickListener(v157 -> {
                        chosen = "b";
                        index = 4;

                        imageView_four.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_one.setBackground(null);
                    });

                } else if (btnPress == 15) {
                    imageView_one.setImageResource(R.drawable.clown_one);
                    imageView_two.setImageResource(R.drawable.clown_two);
                    imageView_three.setImageResource(R.drawable.clown_three);
                    imageView_four.setImageResource(R.drawable.clown_four);
                    imageView_one.setOnClickListener(v158 -> {
                        chosen = "b";
                        index = 1;

                        imageView_one.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_two.setOnClickListener(v159 -> {
                        chosen = "b";
                        index = 2;

                        imageView_two.setBackground(highlight);
                        imageView_one.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_three.setOnClickListener(v160 -> {
                        chosen = "a";
                        index = 3;

                        imageView_three.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_one.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_four.setOnClickListener(v161 -> {
                        chosen = "b";
                        index = 4;

                        imageView_four.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_one.setBackground(null);
                    });

                } else if (btnPress == 16) {
                    imageView_one.setImageResource(R.drawable.nest_one);
                    imageView_two.setImageResource(R.drawable.nest_two);
                    imageView_three.setImageResource(R.drawable.nest_three);
                    imageView_four.setImageResource(R.drawable.nest_four);
                    imageView_one.setOnClickListener(v162 -> {
                        chosen = "b";
                        index = 1;

                        imageView_one.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_two.setOnClickListener(v163 -> {
                        chosen = "b";
                        index = 2;

                        imageView_two.setBackground(highlight);
                        imageView_one.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_three.setOnClickListener(v164 -> {
                        chosen = "b";
                        index = 3;

                        imageView_three.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_one.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_four.setOnClickListener(v165 -> {
                        chosen = "a";
                        index = 4;

                        imageView_four.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_one.setBackground(null);
                    });

                } else if (btnPress == 17) {
                    imageView_one.setImageResource(R.drawable.party_hat_one);
                    imageView_two.setImageResource(R.drawable.party_hat_two);
                    imageView_three.setImageResource(R.drawable.party_hat_three);
                    imageView_four.setImageResource(R.drawable.party_hat_four);
                    imageView_one.setOnClickListener(v166 -> {
                        chosen = "b";
                        index = 1;

                        imageView_one.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_two.setOnClickListener(v167 -> {
                        chosen = "b";
                        index = 2;

                        imageView_two.setBackground(highlight);
                        imageView_one.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_three.setOnClickListener(v168 -> {
                        chosen = "a";
                        index = 3;

                        imageView_three.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_one.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_four.setOnClickListener(v169 -> {
                        chosen = "b";
                        index = 4;

                        imageView_four.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_one.setBackground(null);
                    });

                } else if (btnPress == 18) {
                    imageView_one.setImageResource(R.drawable.girl_window_one);
                    imageView_two.setImageResource(R.drawable.girl_window_two);
                    imageView_three.setImageResource(R.drawable.girl_window_three);
                    imageView_four.setImageResource(R.drawable.girl_window_four);
                    imageView_one.setOnClickListener(v170 -> {
                        chosen = "a";
                        index = 1;

                        imageView_one.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_two.setOnClickListener(v171 -> {
                        chosen = "b";
                        index = 2;

                        imageView_two.setBackground(highlight);
                        imageView_one.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_three.setOnClickListener(v172 -> {
                        chosen = "b";
                        index = 3;

                        imageView_three.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_one.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_four.setOnClickListener(v173 -> {
                        chosen = "b";
                        index = 4;

                        imageView_four.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_one.setBackground(null);
                    });

                } else if (btnPress == 19) {
                    imageView_one.setImageResource(R.drawable.key_one);
                    imageView_two.setImageResource(R.drawable.key_two);
                    imageView_three.setImageResource(R.drawable.key_three);
                    imageView_four.setImageResource(R.drawable.key_four);
                    imageView_one.setOnClickListener(v174 -> {
                        chosen = "b";
                        index = 1;

                        imageView_one.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_two.setOnClickListener(v175 -> {
                        chosen = "b";
                        index = 2;

                        imageView_two.setBackground(highlight);
                        imageView_one.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_three.setOnClickListener(v176 -> {
                        chosen = "a";
                        index = 3;

                        imageView_three.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_one.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_four.setOnClickListener(v177 -> {
                        chosen = "b";
                        index = 4;

                        imageView_four.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_one.setBackground(null);
                    });

                } else if (btnPress == 20) {
                    imageView_one.setImageResource(R.drawable.butterfly_one);
                    imageView_two.setImageResource(R.drawable.butterfly_two);
                    imageView_three.setImageResource(R.drawable.butterfly_three);
                    imageView_four.setImageResource(R.drawable.butterfly_four);
                    imageView_one.setOnClickListener(v178 -> {
                        chosen = "b";
                        index = 1;

                        imageView_one.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_two.setOnClickListener(v179 -> {
                        chosen = "b";
                        index = 2;

                        imageView_two.setBackground(highlight);
                        imageView_one.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_three.setOnClickListener(v180 -> {
                        chosen = "a";
                        index = 3;

                        imageView_three.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_one.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_four.setOnClickListener(v181 -> {
                        chosen = "b";
                        index = 4;

                        imageView_four.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_one.setBackground(null);
                    });

                } else if (btnPress == 21) {
                    imageView_one.setImageResource(R.drawable.chick_one);
                    imageView_two.setImageResource(R.drawable.chick_two);
                    imageView_three.setImageResource(R.drawable.chick_three);
                    imageView_four.setImageResource(R.drawable.chick_four);
                    imageView_one.setOnClickListener(v182 -> {
                        chosen = "b";
                        index = 1;

                        imageView_one.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_two.setOnClickListener(v183 -> {
                        chosen = "a";
                        index = 2;

                        imageView_two.setBackground(highlight);
                        imageView_one.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_three.setOnClickListener(v184 -> {
                        chosen = "b";
                        index = 3;

                        imageView_three.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_one.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_four.setOnClickListener(v185 -> {
                        chosen = "b";
                        index = 4;

                        imageView_four.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_one.setBackground(null);
                    });

                } else if (btnPress == 22) {
                    imageView_one.setImageResource(R.drawable.house_one);
                    imageView_two.setImageResource(R.drawable.house_two);
                    imageView_three.setImageResource(R.drawable.house_three);
                    imageView_four.setImageResource(R.drawable.house_four);
                    imageView_one.setOnClickListener(v186 -> {
                        chosen = "a";
                        index = 1;

                        imageView_one.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_two.setOnClickListener(v187 -> {
                        chosen = "b";
                        index = 2;

                        imageView_two.setBackground(highlight);
                        imageView_one.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_three.setOnClickListener(v188 -> {
                        chosen = "b";
                        index = 3;

                        imageView_three.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_one.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_four.setOnClickListener(v189 -> {
                        chosen = "b";
                        index = 4;

                        imageView_four.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_one.setBackground(null);
                    });

                } else if (btnPress == 23) {
                    imageView_one.setImageResource(R.drawable.toys_one);
                    imageView_two.setImageResource(R.drawable.toys_two);
                    imageView_three.setImageResource(R.drawable.toys_three);
                    imageView_four.setImageResource(R.drawable.toys_four);
                    imageView_one.setOnClickListener(v190 -> {
                        chosen = "b";
                        index = 1;

                        imageView_one.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_two.setOnClickListener(v191 -> {
                        chosen = "b";
                        index = 2;

                        imageView_two.setBackground(highlight);
                        imageView_one.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_three.setOnClickListener(v192 -> {
                        chosen = "a";
                        index = 3;

                        imageView_three.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_one.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_four.setOnClickListener(v193 -> {
                        chosen = "b";
                        index = 4;

                        imageView_four.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_one.setBackground(null);
                    });

                } else if (btnPress == 24) {
                    imageView_one.setImageResource(R.drawable.toy_egg_one);
                    imageView_two.setImageResource(R.drawable.toy_egg_two);
                    imageView_three.setImageResource(R.drawable.toy_egg_three);
                    imageView_four.setImageResource(R.drawable.toy_egg_four);
                    imageView_one.setOnClickListener(v194 -> {
                        index = 1;
                        chosen = "b";

                        imageView_one.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_two.setOnClickListener(v195 -> {
                        chosen = "b";
                        index = 2;

                        imageView_two.setBackground(highlight);
                        imageView_one.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_three.setOnClickListener(v196 -> {
                        chosen = "b";
                        index = 3;

                        imageView_three.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_one.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_four.setOnClickListener(v197 -> {
                        chosen = "a";
                        index = 4;

                        imageView_four.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_one.setBackground(null);
                    });

                } else if (btnPress == 25) {
                    imageView_one.setImageResource(R.drawable.kennel_one);
                    imageView_two.setImageResource(R.drawable.kennel_two);
                    imageView_three.setImageResource(R.drawable.kennel_three);
                    imageView_four.setImageResource(R.drawable.kennel_four);
                    imageView_one.setOnClickListener(v198 -> {
                        index = 1;
                        chosen = "b";

                        imageView_one.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_two.setOnClickListener(v199 -> {
                        index = 2;
                        chosen = "b";

                        imageView_two.setBackground(highlight);
                        imageView_one.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_three.setOnClickListener(v1100 -> {
                        index = 3;
                        chosen = "a";

                        imageView_three.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_one.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_four.setOnClickListener(v1101 -> {
                        index = 4;
                        chosen = "b";

                        imageView_four.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_one.setBackground(null);
                    });

                } else if (btnPress == 26) {
                    imageView_one.setImageResource(R.drawable.boots_one);
                    imageView_two.setImageResource(R.drawable.boots_two);
                    imageView_three.setImageResource(R.drawable.boots_three);
                    imageView_four.setImageResource(R.drawable.boots_four);
                    imageView_one.setOnClickListener(v1102 -> {
                        index = 1;
                        chosen = "b";

                        imageView_one.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_two.setOnClickListener(v1103 -> {
                        index = 2;
                        chosen = "a";

                        imageView_two.setBackground(highlight);
                        imageView_one.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_three.setOnClickListener(v1104 -> {
                        index = 3;
                        chosen = "b";

                        imageView_three.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_one.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_four.setOnClickListener(v1105 -> {
                        index = 4;
                        chosen = "b";

                        imageView_four.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_one.setBackground(null);
                    });

                } else if (btnPress == 27) {
                    imageView_one.setImageResource(R.drawable.box_one);
                    imageView_two.setImageResource(R.drawable.box_two);
                    imageView_three.setImageResource(R.drawable.box_three);
                    imageView_four.setImageResource(R.drawable.box_four);
                    imageView_one.setOnClickListener(v1106 -> {
                        index = 1;
                        chosen = "b";

                        imageView_one.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_two.setOnClickListener(v1107 -> {
                        index = 2;
                        chosen = "b";

                        imageView_two.setBackground(highlight);
                        imageView_one.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_three.setOnClickListener(v1108 -> {
                        index = 3;
                        chosen = "b";

                        imageView_three.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_one.setBackground(null);
                        imageView_four.setBackground(null);
                    });

                    imageView_four.setOnClickListener(v1109 -> {
                        index = 4;
                        chosen = "a";

                        imageView_four.setBackground(highlight);
                        imageView_two.setBackground(null);
                        imageView_three.setBackground(null);
                        imageView_one.setBackground(null);
                    });

                } else {
                    if (correct == 0) {
                        AlertDialog.Builder mBuilder = new AlertDialog.Builder(TestImageAnswers.this);
                        View myView = getLayoutInflater().inflate(R.layout.end_test, null);

                        //The start example test button
                        Button exitBtn = (Button) myView.findViewById(R.id.bntNextExample);
                        exitBtn.setOnClickListener(v1110 -> startActivityForResult(new Intent(getApplicationContext(), SecAR_Activity.class), 1));

                        mBuilder.setView(myView);
                        AlertDialog dialog = mBuilder.create();
                        dialog.show();
                        dialog.setCanceledOnTouchOutside(false);

                        mBlock.endBlock(correct);
                        mSection.addBlock(mBlock);
                        mSection.endSection(); //end this section.
                        Survey.getSurvey().addSection(mSection);

                    } else {
                        AlertDialog.Builder mBuilder = new AlertDialog.Builder(TestImageAnswers.this);
                        View myView = getLayoutInflater().inflate(R.layout.exit_test, null);

                        //The start example test button
                        Button exitBtn = (Button) myView.findViewById(R.id.btnExit);
                        exitBtn.setOnClickListener(v1111 -> startActivityForResult(new Intent(getApplicationContext(), SecAR_Activity.class), 1));

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
