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
 * Created by kayigwe on 6/24/17.
 */

public class ExampleImageAnswers extends Activity {

    int correct = 0;
    int btnPress = 0;
    String chosen = "n";
    boolean correcti;
    int index = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example_answers);

        ImageView img1 = (ImageView) findViewById(R.id.imageView_one),
                  img2 = (ImageView) findViewById(R.id.imageView_two),
                  img3 = (ImageView) findViewById(R.id.imageView_three),
                  img4 = (ImageView) findViewById(R.id.imageView_four);
        Button btn = (Button) findViewById(R.id.btnSwitch);

        Section mSection = new Section("Thurstone Example");
        Block mBlock = new Block(1);
        btn.setEnabled(false);

        img1.setOnClickListener(v -> {
            chosen = "b";
            correcti = false;
            btn.setEnabled(true);
            Drawable highlight = getResources().getDrawable(R.drawable.highlight);
            img1.setBackground(highlight);
            img2.setBackground(null);
            img3.setBackground(null);
            img4.setBackground(null);
            img1.setImageResource(R.drawable.cop_one);
            img2.setImageResource(R.drawable.cop_two);
            img3.setImageResource(R.drawable.cop_three);
            img4.setImageResource(R.drawable.cop_four);
        });

        img2.setOnClickListener(v -> {
            chosen = "b";
            correcti = false;
            btn.setEnabled(true);
            Drawable highlight = getResources().getDrawable(R.drawable.highlight);
            img2.setBackground(highlight);
            img1.setBackground(null);
            img3.setBackground(null);
            img4.setBackground(null);
            img1.setImageResource(R.drawable.cop_one);
            img2.setImageResource(R.drawable.cop_two);
            img3.setImageResource(R.drawable.cop_three);
            img4.setImageResource(R.drawable.cop_four);
        });

        img3.setOnClickListener(v -> {
            chosen = "a";
            correcti = true;
            btn.setEnabled(true);
            Drawable highlight = getResources().getDrawable(R.drawable.highlight);
            img3.setBackground(highlight);
            img2.setBackground(null);
            img1.setBackground(null);
            img4.setBackground(null);
            img1.setImageResource(R.drawable.cop_one);
            img2.setImageResource(R.drawable.cop_two);
            img3.setImageResource(R.drawable.cop_three);
            img4.setImageResource(R.drawable.cop_four);
        });

        img4.setOnClickListener(v -> {
            chosen = "b";
            correcti = false;
            btn.setEnabled(true);
            Drawable highlight = getResources().getDrawable(R.drawable.highlight);
            img4.setBackground(highlight);
            img2.setBackground(null);
            img3.setBackground(null);
            img1.setBackground(null);
            img1.setImageResource(R.drawable.cop_one);
            img2.setImageResource(R.drawable.cop_two);
            img3.setImageResource(R.drawable.cop_three);
            img4.setImageResource(R.drawable.cop_four);
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
                img1.setBackground(null);
                img2.setBackground(null);
                img3.setBackground(null);
                img4.setBackground(null);

                btnPress++;
                switch(btnPress) {
                    case 1:
                        img1.setImageResource(R.drawable.dog_one);
                        img2.setImageResource(R.drawable.dog_two);
                        img3.setImageResource(R.drawable.dog_three);
                        img4.setImageResource(R.drawable.dog_four);
                        img1.setOnClickListener(v122 -> {
                            chosen = "a";
                            Drawable highlight = getResources().getDrawable(R.drawable.highlight);
                            img1.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img2.setOnClickListener(v1 -> {
                            chosen = "b";
                            Drawable highlight = getResources().getDrawable(R.drawable.highlight);
                            img2.setBackground(highlight);
                            img1.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img3.setOnClickListener(v12 -> {
                            chosen = "b";
                            Drawable highlight = getResources().getDrawable(R.drawable.highlight);
                            img3.setBackground(highlight);
                            img2.setBackground(null);
                            img1.setBackground(null);
                            img4.setBackground(null);
                        });

                        img4.setOnClickListener(v13 -> {
                            chosen = "b";
                            Drawable highlight = getResources().getDrawable(R.drawable.highlight);
                            img4.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img1.setBackground(null);
                        });
                        break;

                    case 2:
                        img1.setImageResource(R.drawable.hat_one);
                        img2.setImageResource(R.drawable.hat_two);
                        img3.setImageResource(R.drawable.hat_three);
                        img4.setImageResource(R.drawable.hat_four);
                        img1.setOnClickListener(v14 -> {
                            chosen = "a";
                            Drawable highlight = getResources().getDrawable(R.drawable.highlight);
                            img1.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img2.setOnClickListener(v15 -> {
                            chosen = "b";
                            Drawable highlight = getResources().getDrawable(R.drawable.highlight);
                            img2.setBackground(highlight);
                            img1.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img3.setOnClickListener(v16 -> {
                            chosen = "b";
                            Drawable highlight = getResources().getDrawable(R.drawable.highlight);
                            img3.setBackground(highlight);
                            img2.setBackground(null);
                            img1.setBackground(null);
                            img4.setBackground(null);
                        });

                        img4.setOnClickListener(v17 -> {
                            chosen = "b";
                            Drawable highlight = getResources().getDrawable(R.drawable.highlight);
                            img4.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img1.setBackground(null);
                        });
                        break;

                    case 3:
                        img1.setImageResource(R.drawable.table_one);
                        img2.setImageResource(R.drawable.table_two);
                        img3.setImageResource(R.drawable.table_three);
                        img4.setImageResource(R.drawable.table_four);
                        img1.setOnClickListener(v18 -> {
                            chosen = "b";
                            Drawable highlight = getResources().getDrawable(R.drawable.highlight);
                            img1.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img2.setOnClickListener(v19 -> {
                            chosen = "b";
                            Drawable highlight = getResources().getDrawable(R.drawable.highlight);
                            img2.setBackground(highlight);
                            img1.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img3.setOnClickListener(v110 -> {
                            chosen = "a";
                            Drawable highlight = getResources().getDrawable(R.drawable.highlight);
                            img3.setBackground(highlight);
                            img2.setBackground(null);
                            img1.setBackground(null);
                            img4.setBackground(null);
                        });

                        img4.setOnClickListener(v111 -> {
                            chosen = "b";
                            Drawable highlight = getResources().getDrawable(R.drawable.highlight);
                            img4.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img1.setBackground(null);
                        });
                        break;

                    case 4:
                        img1.setImageResource(R.drawable.truck_one);
                        img2.setImageResource(R.drawable.truck_two);
                        img3.setImageResource(R.drawable.truck_three);
                        img4.setImageResource(R.drawable.truck_four);
                        img1.setOnClickListener(v112 -> {
                            chosen = "b";
                            Drawable highlight = getResources().getDrawable(R.drawable.highlight);
                            img1.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img2.setOnClickListener(v113 -> {
                            chosen = "b";
                            Drawable highlight = getResources().getDrawable(R.drawable.highlight);
                            img2.setBackground(highlight);
                            img1.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img3.setOnClickListener(v114 -> {
                            chosen = "a";
                            Drawable highlight = getResources().getDrawable(R.drawable.highlight);
                            img3.setBackground(highlight);
                            img2.setBackground(null);
                            img1.setBackground(null);
                            img4.setBackground(null);
                        });

                        img4.setOnClickListener(v115 -> {
                            chosen = "b";
                            Drawable highlight = getResources().getDrawable(R.drawable.highlight);
                            img4.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img1.setBackground(null);
                        });
                        break;

                    case 5:
                        img1.setImageResource(R.drawable.window_one);
                        img2.setImageResource(R.drawable.window_two);
                        img3.setImageResource(R.drawable.window_three);
                        img4.setImageResource(R.drawable.window_four);
                        img1.setOnClickListener(v116 -> {
                            chosen = "b";
                            Drawable highlight = getResources().getDrawable(R.drawable.highlight);
                            img1.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img2.setOnClickListener(v117 -> {
                            chosen = "a";
                            Drawable highlight = getResources().getDrawable(R.drawable.highlight);
                            img2.setBackground(highlight);
                            img1.setBackground(null);
                            img3.setBackground(null);
                            img4.setBackground(null);
                        });

                        img3.setOnClickListener(v118 -> {
                            chosen = "b";
                            Drawable highlight = getResources().getDrawable(R.drawable.highlight);
                            img3.setBackground(highlight);
                            img2.setBackground(null);
                            img1.setBackground(null);
                            img4.setBackground(null);
                        });

                        img4.setOnClickListener(v119 -> {
                            chosen = "b";
                            Drawable highlight = getResources().getDrawable(R.drawable.highlight);
                            img4.setBackground(highlight);
                            img2.setBackground(null);
                            img3.setBackground(null);
                            img1.setBackground(null);
                        });
                        break;

                    default:
                        AlertDialog.Builder mBuilder = new AlertDialog.Builder(ExampleImageAnswers.this);
                        View myView;
                        if (correct == 0) {
                            myView = getLayoutInflater().inflate(R.layout.end_test, null);
                            Button exitBtn = (Button) myView.findViewById(R.id.bntNextExample);
                            exitBtn.setOnClickListener(v120 -> startActivityForResult(new Intent(getApplicationContext(), SecAR_Activity.class), 1));
                        } else {
                            myView = getLayoutInflater().inflate(R.layout.begin_real_test, null);
                            Button beginBtn = (Button) myView.findViewById(R.id.beginBtn);
                            beginBtn.setOnClickListener(v121 -> startActivityForResult(new Intent(getApplicationContext(), TestImageChange.class), 1));
                        }

                        mBuilder.setView(myView);
                        AlertDialog dialog = mBuilder.create();
                        dialog.show();
                        dialog.setCanceledOnTouchOutside(false);

                        mBlock.endBlock(correct);
                        mSection.addBlock(mBlock);
                        mSection.endSection(); //end this section.
                        Survey.getSurvey().addSection(mSection);
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
