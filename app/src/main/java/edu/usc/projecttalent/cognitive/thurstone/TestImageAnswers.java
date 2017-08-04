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

        ImageView img1 = (ImageView) findViewById(R.id.imageView_one),
                  img2 = (ImageView) findViewById(R.id.imageView_two),
                  img3 = (ImageView) findViewById(R.id.imageView_three),
                  img4 = (ImageView) findViewById(R.id.imageView_four);
        Button btn = (Button) findViewById(R.id.btnSwitch);

        mSection = new Section("Thurstone");
        Drawable highlight = getResources().getDrawable(R.drawable.highlight);

        Block mBlock = new Block(1);
        btn.setEnabled(false);
        img1.setOnClickListener(v -> {
            index = 1;
            correcti = true;
            btn.setEnabled(true);
            chosen = "a";
            img1.setBackground(highlight);
            img2.setBackground(null);
            img3.setBackground(null);
            img4.setBackground(null);
            img1.setImageResource(R.drawable.jacket_one);
            img2.setImageResource(R.drawable.jacket_two);
            img3.setImageResource(R.drawable.jacket_three);
            img4.setImageResource(R.drawable.jacket_four);

        });

        img2.setOnClickListener(v -> {
            index = 2;
            correcti = false;
            btn.setEnabled(true);
            chosen = "b";
            img2.setBackground(highlight);
            img1.setBackground(null);
            img3.setBackground(null);
            img4.setBackground(null);
            img2.setImageResource(R.drawable.jacket_two);
            img1.setImageResource(R.drawable.jacket_one);
            img3.setImageResource(R.drawable.jacket_three);
            img4.setImageResource(R.drawable.jacket_four);
        });

        img3.setOnClickListener(v -> {
            index = 3;
            correcti = false;
            btn.setEnabled(true);
            chosen = "b";
            img3.setBackground(highlight);
            img2.setBackground(null);
            img1.setBackground(null);
            img4.setBackground(null);
            img3.setImageResource(R.drawable.jacket_three);
            img2.setImageResource(R.drawable.jacket_two);
            img1.setImageResource(R.drawable.jacket_one);
            img4.setImageResource(R.drawable.jacket_four);
        });

        img4.setOnClickListener(v -> {
            index = 4;
            correcti = false;
            btn.setEnabled(true);
            chosen = "b";
            img4.setBackground(highlight);
            img2.setBackground(null);
            img3.setBackground(null);
            img1.setBackground(null);
            img4.setImageResource(R.drawable.jacket_four);
            img2.setImageResource(R.drawable.jacket_two);
            img3.setImageResource(R.drawable.jacket_three);
            img1.setImageResource(R.drawable.jacket_one);
        });

        btn.setOnClickListener(new View.OnClickListener() {
            Answer mAnswer;

            @Override
            public void onClick(View v) {
                if (chosen.equals("a")) {
                    correct++;

                    correcti = true;
                    mAnswer = new Answer();
                    mAnswer.endAnswer(index, correcti);
                    mBlock.addAnswer(mAnswer);
                } else if (chosen.equals("b")) {

                    correcti = false;
                    mAnswer = new Answer();
                    mAnswer.endAnswer(index, correcti);
                    mBlock.addAnswer(mAnswer);
                } else if (chosen.equals("n")) {
                    //pop up don't let person move on
                    btnPress--;
                }
                chosen = "n";
                chosen = "n";
                img1.setBackground(null);
                img2.setBackground(null);
                img3.setBackground(null);
                img4.setBackground(null);
                btnPress++;

                switch (btnPress) {
                    case 1:
                        img1.setImageResource(R.drawable.girl_one);
                        img2.setImageResource(R.drawable.girl_two);
                        img3.setImageResource(R.drawable.girl_three);
                        img4.setImageResource(R.drawable.girl_four);
                        img1.setOnClickListener(v1 -> {
                            index = 1;
                            chosen = "b";
                            img1.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img2.setOnClickListener(v12 -> {

                            index = 2;
                            chosen = "b";
                            img2.setBackground(highlight);
                            img1.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img3.setOnClickListener(v13 -> {

                            index = 3;
                            chosen = "b";
                            img3.setBackground(highlight);
                            img2.setBackground(null);
                            img1.setBackground(null);
                            img4.setBackground(null);
                        });

                        img4.setOnClickListener(v14 -> {

                            index = 4;
                            chosen = "a";
                            img4.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img1.setBackground(null);
                        });
                        break;

                    case 2:
                        img1.setImageResource(R.drawable.horse_one);
                        img2.setImageResource(R.drawable.horse_two);
                        img3.setImageResource(R.drawable.horse_three);
                        img4.setImageResource(R.drawable.horse_four);
                        img1.setOnClickListener(v15 -> {
                            chosen = "a";
                            index = 1;

                            img1.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img2.setOnClickListener(v16 -> {
                            chosen = "b";
                            index = 2;

                            img2.setBackground(highlight);
                            img1.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img3.setOnClickListener(v17 -> {
                            chosen = "b";
                            index = 3;

                            img3.setBackground(highlight);
                            img2.setBackground(null);
                            img1.setBackground(null);
                            img4.setBackground(null);
                        });

                        img4.setOnClickListener(v18 -> {
                            chosen = "b";
                            index = 4;

                            img4.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img1.setBackground(null);
                        });
                        break;

                    case 3:
                        img1.setImageResource(R.drawable.pumpkin_one);
                        img2.setImageResource(R.drawable.pumpkin_two);
                        img3.setImageResource(R.drawable.pumpkin_three);
                        img4.setImageResource(R.drawable.pumpkin_four);
                        img1.setOnClickListener(v19 -> {
                            chosen = "b";
                            index = 1;

                            img1.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img2.setOnClickListener(v110 -> {
                            chosen = "b";
                            index = 2;

                            img2.setBackground(highlight);
                            img1.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img3.setOnClickListener(v111 -> {
                            chosen = "b";
                            index = 3;

                            img3.setBackground(highlight);
                            img2.setBackground(null);
                            img1.setBackground(null);
                            img4.setBackground(null);
                        });

                        img4.setOnClickListener(v112 -> {
                            chosen = "a";
                            index = 4;

                            img4.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img1.setBackground(null);
                        });
                        break;

                    case 4:
                        img1.setImageResource(R.drawable.umbrella_one);
                        img2.setImageResource(R.drawable.umbrella_two);
                        img3.setImageResource(R.drawable.umbrella_three);
                        img4.setImageResource(R.drawable.umbrella_four);
                        img1.setOnClickListener(v113 -> {
                            chosen = "b";
                            index = 1;

                            img1.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img2.setOnClickListener(v114 -> {
                            chosen = "b";
                            index = 2;

                            img2.setBackground(highlight);
                            img1.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img3.setOnClickListener(v115 -> {
                            chosen = "b";
                            index = 3;

                            img3.setBackground(highlight);
                            img2.setBackground(null);
                            img1.setBackground(null);
                            img4.setBackground(null);
                        });

                        img4.setOnClickListener(v116 -> {
                            chosen = "a";
                            index = 4;

                            img4.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img1.setBackground(null);
                        });
                        break;

                    case 5:
                        img1.setImageResource(R.drawable.chicken_one);
                        img2.setImageResource(R.drawable.chicken_two);
                        img3.setImageResource(R.drawable.chicken_three);
                        img4.setImageResource(R.drawable.chicken_four);
                        img1.setOnClickListener(v117 -> {
                            chosen = "b";
                            index = 1;

                            img1.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img2.setOnClickListener(v118 -> {
                            chosen = "a";
                            index = 2;

                            img2.setBackground(highlight);
                            img1.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img3.setOnClickListener(v119 -> {
                            chosen = "b";
                            index = 3;

                            img3.setBackground(highlight);
                            img2.setBackground(null);
                            img1.setBackground(null);
                            img4.setBackground(null);
                        });

                        img4.setOnClickListener(v120 -> {
                            chosen = "b";
                            index = 4;

                            img4.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img1.setBackground(null);
                        });
                        break;

                    case 6:
                        img1.setImageResource(R.drawable.duck_one);
                        img2.setImageResource(R.drawable.duck_two);
                        img3.setImageResource(R.drawable.duck_three);
                        img4.setImageResource(R.drawable.duck_four);
                        img1.setOnClickListener(v121 -> {
                            chosen = "b";
                            index = 1;
                            img1.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img2.setOnClickListener(v122 -> {
                            chosen = "b";
                            index = 2;
                            img2.setBackground(highlight);
                            img1.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img3.setOnClickListener(v123 -> {
                            chosen = "b";
                            index = 3;
                            img3.setBackground(highlight);
                            img2.setBackground(null);
                            img1.setBackground(null);
                            img4.setBackground(null);
                        });

                        img4.setOnClickListener(v124 -> {
                            chosen = "a";
                            index = 4;
                            img4.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img1.setBackground(null);
                        });
                        break;

                    case 7:
                        img1.setImageResource(R.drawable.elephant_one);
                        img2.setImageResource(R.drawable.elephant_two);
                        img3.setImageResource(R.drawable.elephant_three);
                        img4.setImageResource(R.drawable.elephant_four);
                        img1.setOnClickListener(v125 -> {
                            chosen = "a";
                            index = 1;

                            img1.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img2.setOnClickListener(v126 -> {
                            chosen = "b";
                            index = 2;

                            img2.setBackground(highlight);
                            img1.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img3.setOnClickListener(v127 -> {
                            chosen = "b";
                            index = 3;
                            img3.setBackground(highlight);
                            img2.setBackground(null);
                            img1.setBackground(null);
                            img4.setBackground(null);
                        });

                        img4.setOnClickListener(v128 -> {
                            chosen = "b";
                            index = 4;

                            img4.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img1.setBackground(null);
                        });
                        if (correct == 0) {
                            AlertDialog.Builder mBuilder = new AlertDialog.Builder(TestImageAnswers.this);
                            View myView = getLayoutInflater().inflate(R.layout.exit_test, null);

                            //The start example test button
                            Button exitBtn = (Button) myView.findViewById(R.id.btnExit);
                            exitBtn.setOnClickListener(v129 ->
                                    startActivityForResult(new Intent(getApplicationContext(), SecAR_Activity.class), 1));

                            mBuilder.setView(myView);
                            AlertDialog dialog = mBuilder.create();
                            dialog.show();
                            dialog.setCanceledOnTouchOutside(false);

                            mBlock.endBlock(correct);
                            mSection.addBlock(mBlock);
                            mSection.endSection(); //end this section.
                            Survey.getSurvey().addSection(mSection);
                        }
                        break;

                    case 8:
                        img1.setImageResource(R.drawable.girl_door_one);
                        img2.setImageResource(R.drawable.girl_door_two);
                        img3.setImageResource(R.drawable.girl_door_three);
                        img4.setImageResource(R.drawable.girl_door_four);
                        img1.setOnClickListener(v130 -> {
                            chosen = "a";
                            index = 1;

                            img1.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img2.setOnClickListener(v131 -> {
                            chosen = "b";
                            index = 2;
                            img1.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img3.setOnClickListener(v132 -> {
                            chosen = "b";
                            index = 3;
                            img2.setBackground(null);
                            img1.setBackground(null);
                            img4.setBackground(null);
                        });

                        img4.setOnClickListener(v133 -> {
                            chosen = "b";
                            index = 4;

                            img4.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img1.setBackground(null);
                        });
                        break;

                    case 9:
                        img1.setImageResource(R.drawable.cake_one);
                        img2.setImageResource(R.drawable.cake_two);
                        img3.setImageResource(R.drawable.cake_three);
                        img4.setImageResource(R.drawable.cake_four);
                        img1.setOnClickListener(v134 -> {
                            chosen = "b";
                            index = 1;

                            img1.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img2.setOnClickListener(v135 -> {
                            chosen = "a";
                            index = 2;

                            img2.setBackground(highlight);
                            img1.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img3.setOnClickListener(v136 -> {
                            chosen = "b";
                            index = 3;
                            img3.setBackground(highlight);
                            img2.setBackground(null);
                            img1.setBackground(null);
                            img4.setBackground(null);
                        });

                        img4.setOnClickListener(v137 -> {
                            chosen = "b";
                            index = 4;

                            img4.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img1.setBackground(null);
                        });
                        break;

                    case 10:
                        img1.setImageResource(R.drawable.fire_hat_one);
                        img2.setImageResource(R.drawable.fire_hat_two);
                        img3.setImageResource(R.drawable.fire_hat_three);
                        img4.setImageResource(R.drawable.fire_hat_four);
                        img1.setOnClickListener(v138 -> {
                            chosen = "a";
                            index = 1;

                            img1.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img2.setOnClickListener(v139 -> {
                            chosen = "b";
                            index = 2;

                            img2.setBackground(highlight);
                            img1.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img3.setOnClickListener(v140 -> {
                            chosen = "b";
                            index = 3;

                            img3.setBackground(highlight);
                            img2.setBackground(null);
                            img1.setBackground(null);
                            img4.setBackground(null);
                        });

                        img4.setOnClickListener(v141 -> {
                            chosen = "b";
                            index = 4;

                            img4.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img1.setBackground(null);
                        });
                        break;

                    case 11:
                        img1.setImageResource(R.drawable.baby_bird_one);
                        img2.setImageResource(R.drawable.baby_bird_two);
                        img3.setImageResource(R.drawable.baby_bird_three);
                        img4.setImageResource(R.drawable.baby_bird_four);
                        img1.setOnClickListener(v142 -> {
                            chosen = "a";
                            index = 1;

                            img1.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img2.setOnClickListener(v143 -> {
                            chosen = "b";
                            index = 2;

                            img2.setBackground(highlight);
                            img1.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img3.setOnClickListener(v144 -> {
                            chosen = "b";
                            index = 3;

                            img3.setBackground(highlight);
                            img2.setBackground(null);
                            img1.setBackground(null);
                            img4.setBackground(null);
                        });

                        img4.setOnClickListener(v145 -> {
                            chosen = "b";
                            index = 4;

                            img4.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img1.setBackground(null);
                        });
                        break;

                    case 12:
                        img1.setImageResource(R.drawable.boat_one);
                        img2.setImageResource(R.drawable.boat_two);
                        img3.setImageResource(R.drawable.boat_three);
                        img4.setImageResource(R.drawable.boat_four);
                        img1.setOnClickListener(v146 -> {
                            chosen = "b";
                            index = 1;

                            img1.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img2.setOnClickListener(v147 -> {
                            chosen = "b";
                            index = 2;

                            img2.setBackground(highlight);
                            img1.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img3.setOnClickListener(v148 -> {
                            chosen = "a";
                            index = 3;

                            img3.setBackground(highlight);
                            img2.setBackground(null);
                            img1.setBackground(null);
                            img4.setBackground(null);
                        });

                        img4.setOnClickListener(v149 -> {
                            chosen = "b";
                            index = 4;

                            img4.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img1.setBackground(null);
                        });
                        break;

                    case 13:
                        img1.setImageResource(R.drawable.ship_one);
                        img2.setImageResource(R.drawable.ship_two);
                        img3.setImageResource(R.drawable.ship_three);
                        img4.setImageResource(R.drawable.ship_four);
                        img1.setOnClickListener(v150 -> {
                            chosen = "b";
                            index = 1;

                            img1.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img2.setOnClickListener(v151 -> {
                            chosen = "b";
                            index = 2;

                            img2.setBackground(highlight);
                            img1.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img3.setOnClickListener(v152 -> {
                            chosen = "b";
                            index = 3;

                            img3.setBackground(highlight);
                            img2.setBackground(null);
                            img1.setBackground(null);
                            img4.setBackground(null);
                        });

                        img4.setOnClickListener(v153 -> {
                            chosen = "a";
                            index = 4;

                            img4.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img1.setBackground(null);
                        });
                        break;

                    case 14:
                        img1.setImageResource(R.drawable.row_boat_one);
                        img2.setImageResource(R.drawable.row_boat_two);
                        img3.setImageResource(R.drawable.row_boat_three);
                        img4.setImageResource(R.drawable.row_boat_four);
                        img1.setOnClickListener(v154 -> {
                            chosen = "b";
                            index = 1;

                            img1.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img2.setOnClickListener(v155 -> {
                            chosen = "a";
                            index = 2;

                            img2.setBackground(highlight);
                            img1.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img3.setOnClickListener(v156 -> {
                            chosen = "b";
                            index = 3;

                            img3.setBackground(highlight);
                            img2.setBackground(null);
                            img1.setBackground(null);
                            img4.setBackground(null);
                        });

                        img4.setOnClickListener(v157 -> {
                            chosen = "b";
                            index = 4;

                            img4.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img1.setBackground(null);
                        });
                        break;

                    case 15:
                        img1.setImageResource(R.drawable.clown_one);
                        img2.setImageResource(R.drawable.clown_two);
                        img3.setImageResource(R.drawable.clown_three);
                        img4.setImageResource(R.drawable.clown_four);
                        img1.setOnClickListener(v158 -> {
                            chosen = "b";
                            index = 1;

                            img1.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img2.setOnClickListener(v159 -> {
                            chosen = "b";
                            index = 2;

                            img2.setBackground(highlight);
                            img1.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img3.setOnClickListener(v160 -> {
                            chosen = "a";
                            index = 3;

                            img3.setBackground(highlight);
                            img2.setBackground(null);
                            img1.setBackground(null);
                            img4.setBackground(null);
                        });

                        img4.setOnClickListener(v161 -> {
                            chosen = "b";
                            index = 4;

                            img4.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img1.setBackground(null);
                        });
                        break;

                    case 16:
                        img1.setImageResource(R.drawable.nest_one);
                        img2.setImageResource(R.drawable.nest_two);
                        img3.setImageResource(R.drawable.nest_three);
                        img4.setImageResource(R.drawable.nest_four);
                        img1.setOnClickListener(v162 -> {
                            chosen = "b";
                            index = 1;

                            img1.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img2.setOnClickListener(v163 -> {
                            chosen = "b";
                            index = 2;

                            img2.setBackground(highlight);
                            img1.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img3.setOnClickListener(v164 -> {
                            chosen = "b";
                            index = 3;

                            img3.setBackground(highlight);
                            img2.setBackground(null);
                            img1.setBackground(null);
                            img4.setBackground(null);
                        });

                        img4.setOnClickListener(v165 -> {
                            chosen = "a";
                            index = 4;

                            img4.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img1.setBackground(null);
                        });
                        break;

                    case 17:
                        img1.setImageResource(R.drawable.party_hat_one);
                        img2.setImageResource(R.drawable.party_hat_two);
                        img3.setImageResource(R.drawable.party_hat_three);
                        img4.setImageResource(R.drawable.party_hat_four);
                        img1.setOnClickListener(v166 -> {
                            chosen = "b";
                            index = 1;

                            img1.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img2.setOnClickListener(v167 -> {
                            chosen = "b";
                            index = 2;

                            img2.setBackground(highlight);
                            img1.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img3.setOnClickListener(v168 -> {
                            chosen = "a";
                            index = 3;

                            img3.setBackground(highlight);
                            img2.setBackground(null);
                            img1.setBackground(null);
                            img4.setBackground(null);
                        });

                        img4.setOnClickListener(v169 -> {
                            chosen = "b";
                            index = 4;

                            img4.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img1.setBackground(null);
                        });
                        break;

                    case 18:
                        img1.setImageResource(R.drawable.girl_window_one);
                        img2.setImageResource(R.drawable.girl_window_two);
                        img3.setImageResource(R.drawable.girl_window_three);
                        img4.setImageResource(R.drawable.girl_window_four);
                        img1.setOnClickListener(v170 -> {
                            chosen = "a";
                            index = 1;

                            img1.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img2.setOnClickListener(v171 -> {
                            chosen = "b";
                            index = 2;

                            img2.setBackground(highlight);
                            img1.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img3.setOnClickListener(v172 -> {
                            chosen = "b";
                            index = 3;

                            img3.setBackground(highlight);
                            img2.setBackground(null);
                            img1.setBackground(null);
                            img4.setBackground(null);
                        });

                        img4.setOnClickListener(v173 -> {
                            chosen = "b";
                            index = 4;

                            img4.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img1.setBackground(null);
                        });
                        break;

                    case 19:
                        img1.setImageResource(R.drawable.key_one);
                        img2.setImageResource(R.drawable.key_two);
                        img3.setImageResource(R.drawable.key_three);
                        img4.setImageResource(R.drawable.key_four);
                        img1.setOnClickListener(v174 -> {
                            chosen = "b";
                            index = 1;

                            img1.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img2.setOnClickListener(v175 -> {
                            chosen = "b";
                            index = 2;

                            img2.setBackground(highlight);
                            img1.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img3.setOnClickListener(v176 -> {
                            chosen = "a";
                            index = 3;

                            img3.setBackground(highlight);
                            img2.setBackground(null);
                            img1.setBackground(null);
                            img4.setBackground(null);
                        });

                        img4.setOnClickListener(v177 -> {
                            chosen = "b";
                            index = 4;

                            img4.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img1.setBackground(null);
                        });
                        break;

                    case 20:
                        img1.setImageResource(R.drawable.butterfly_one);
                        img2.setImageResource(R.drawable.butterfly_two);
                        img3.setImageResource(R.drawable.butterfly_three);
                        img4.setImageResource(R.drawable.butterfly_four);
                        img1.setOnClickListener(v178 -> {
                            chosen = "b";
                            index = 1;

                            img1.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img2.setOnClickListener(v179 -> {
                            chosen = "b";
                            index = 2;

                            img2.setBackground(highlight);
                            img1.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img3.setOnClickListener(v180 -> {
                            chosen = "a";
                            index = 3;

                            img3.setBackground(highlight);
                            img2.setBackground(null);
                            img1.setBackground(null);
                            img4.setBackground(null);
                        });

                        img4.setOnClickListener(v181 -> {
                            chosen = "b";
                            index = 4;

                            img4.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img1.setBackground(null);
                        });
                        break;

                    case 21:
                        img1.setImageResource(R.drawable.chick_one);
                        img2.setImageResource(R.drawable.chick_two);
                        img3.setImageResource(R.drawable.chick_three);
                        img4.setImageResource(R.drawable.chick_four);
                        img1.setOnClickListener(v182 -> {
                            chosen = "b";
                            index = 1;

                            img1.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img2.setOnClickListener(v183 -> {
                            chosen = "a";
                            index = 2;

                            img2.setBackground(highlight);
                            img1.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img3.setOnClickListener(v184 -> {
                            chosen = "b";
                            index = 3;

                            img3.setBackground(highlight);
                            img2.setBackground(null);
                            img1.setBackground(null);
                            img4.setBackground(null);
                        });

                        img4.setOnClickListener(v185 -> {
                            chosen = "b";
                            index = 4;

                            img4.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img1.setBackground(null);
                        });
                        break;

                    case 22:
                        img1.setImageResource(R.drawable.house_one);
                        img2.setImageResource(R.drawable.house_two);
                        img3.setImageResource(R.drawable.house_three);
                        img4.setImageResource(R.drawable.house_four);
                        img1.setOnClickListener(v186 -> {
                            chosen = "a";
                            index = 1;

                            img1.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img2.setOnClickListener(v187 -> {
                            chosen = "b";
                            index = 2;

                            img2.setBackground(highlight);
                            img1.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img3.setOnClickListener(v188 -> {
                            chosen = "b";
                            index = 3;

                            img3.setBackground(highlight);
                            img2.setBackground(null);
                            img1.setBackground(null);
                            img4.setBackground(null);
                        });

                        img4.setOnClickListener(v189 -> {
                            chosen = "b";
                            index = 4;

                            img4.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img1.setBackground(null);
                        });
                        break;

                    case 23:
                        img1.setImageResource(R.drawable.toys_one);
                        img2.setImageResource(R.drawable.toys_two);
                        img3.setImageResource(R.drawable.toys_three);
                        img4.setImageResource(R.drawable.toys_four);
                        img1.setOnClickListener(v190 -> {
                            chosen = "b";
                            index = 1;

                            img1.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img2.setOnClickListener(v191 -> {
                            chosen = "b";
                            index = 2;

                            img2.setBackground(highlight);
                            img1.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img3.setOnClickListener(v192 -> {
                            chosen = "a";
                            index = 3;

                            img3.setBackground(highlight);
                            img2.setBackground(null);
                            img1.setBackground(null);
                            img4.setBackground(null);
                        });

                        img4.setOnClickListener(v193 -> {
                            chosen = "b";
                            index = 4;

                            img4.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img1.setBackground(null);
                        });
                        break;

                    case 24:
                        img1.setImageResource(R.drawable.toy_egg_one);
                        img2.setImageResource(R.drawable.toy_egg_two);
                        img3.setImageResource(R.drawable.toy_egg_three);
                        img4.setImageResource(R.drawable.toy_egg_four);
                        img1.setOnClickListener(v194 -> {
                            index = 1;
                            chosen = "b";

                            img1.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img2.setOnClickListener(v195 -> {
                            chosen = "b";
                            index = 2;

                            img2.setBackground(highlight);
                            img1.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img3.setOnClickListener(v196 -> {
                            chosen = "b";
                            index = 3;

                            img3.setBackground(highlight);
                            img2.setBackground(null);
                            img1.setBackground(null);
                            img4.setBackground(null);
                        });

                        img4.setOnClickListener(v197 -> {
                            chosen = "a";
                            index = 4;

                            img4.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img1.setBackground(null);
                        });
                        break;

                    case 25:
                        img1.setImageResource(R.drawable.kennel_one);
                        img2.setImageResource(R.drawable.kennel_two);
                        img3.setImageResource(R.drawable.kennel_three);
                        img4.setImageResource(R.drawable.kennel_four);
                        img1.setOnClickListener(v198 -> {
                            index = 1;
                            chosen = "b";

                            img1.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img2.setOnClickListener(v199 -> {
                            index = 2;
                            chosen = "b";

                            img2.setBackground(highlight);
                            img1.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img3.setOnClickListener(v1100 -> {
                            index = 3;
                            chosen = "a";

                            img3.setBackground(highlight);
                            img2.setBackground(null);
                            img1.setBackground(null);
                            img4.setBackground(null);
                        });

                        img4.setOnClickListener(v1101 -> {
                            index = 4;
                            chosen = "b";

                            img4.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img1.setBackground(null);
                        });
                        break;

                    case 26:
                        img1.setImageResource(R.drawable.boots_one);
                        img2.setImageResource(R.drawable.boots_two);
                        img3.setImageResource(R.drawable.boots_three);
                        img4.setImageResource(R.drawable.boots_four);
                        img1.setOnClickListener(v1102 -> {
                            index = 1;
                            chosen = "b";

                            img1.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img2.setOnClickListener(v1103 -> {
                            index = 2;
                            chosen = "a";

                            img2.setBackground(highlight);
                            img1.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img3.setOnClickListener(v1104 -> {
                            index = 3;
                            chosen = "b";

                            img3.setBackground(highlight);
                            img2.setBackground(null);
                            img1.setBackground(null);
                            img4.setBackground(null);
                        });

                        img4.setOnClickListener(v1105 -> {
                            index = 4;
                            chosen = "b";

                            img4.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img1.setBackground(null);
                        });
                        break;

                    case 27:
                        img1.setImageResource(R.drawable.box_one);
                        img2.setImageResource(R.drawable.box_two);
                        img3.setImageResource(R.drawable.box_three);
                        img4.setImageResource(R.drawable.box_four);
                        img1.setOnClickListener(v1106 -> {
                            index = 1;
                            chosen = "b";

                            img1.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img2.setOnClickListener(v1107 -> {
                            index = 2;
                            chosen = "b";

                            img2.setBackground(highlight);
                            img1.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img3.setOnClickListener(v1108 -> {
                            index = 3;
                            chosen = "b";

                            img3.setBackground(highlight);
                            img2.setBackground(null);
                            img1.setBackground(null);
                            img4.setBackground(null);
                        });

                        img4.setOnClickListener(v1109 -> {
                            index = 4;
                            chosen = "a";

                            img4.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img1.setBackground(null);
                        });
                        break;

                    default:
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
                        break;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {}

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
