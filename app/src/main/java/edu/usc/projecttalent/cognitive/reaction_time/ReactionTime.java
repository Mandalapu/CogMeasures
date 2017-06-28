package edu.usc.projecttalent.cognitive.reaction_time;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.content.Intent;
import edu.usc.projecttalent.cognitive.R;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.os.Handler;
import java.lang.Runnable;

/**
 * Created by kayigwe on 6/25/17.
 */

public class ReactionTime extends Activity {
    ImageView imageView;
    Button btnSpace;
    private int[] times_milli = new int[]{2000, 3000, 4000, 5000, 6000, 7000, 8000};
    int counter = 0;
    long nowTime;
    long redTime;
    long elapsedTime;
    List<Long> myTimes = new ArrayList<Long>();

    public String path = Environment.getExternalStorageDirectory().getAbsolutePath()+ "/aaReactionTime";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_change);

        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.cross);

      //  LayoutParams params = (LayoutParams) imageView.getLayoutParams();
      //  params.width = 120;
      //  imageView.setLayoutParams(params);
        btnSpace = (Button) findViewById(R.id.buttonSpace);

        int idx = new Random().nextInt(times_milli.length);
        final int random_Time = (times_milli[idx]);

        File dir = new File(path);
        dir.mkdirs();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run(){
                handler.postDelayed(this, random_Time);
                imageView.setImageResource(R.drawable.red_circle_large);
                if (imageView.getDrawable().getConstantState() == getResources().getDrawable( R.drawable.red_circle_large).getConstantState()){
                    redTime = System.currentTimeMillis();
                }
                btnSpace.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        imageView.setImageResource(R.drawable.cross);
                        nowTime = System.currentTimeMillis();
                        elapsedTime = redTime - nowTime;
                        myTimes.add(elapsedTime);
                        counter++;
                        if (counter >= 20){
                            //Below saves to a file on the device. To allow this function, un-mute the commented section below
                           /* File file = new File (path + "/savedFile.txt");
                            try {
                                FileWriter writer = new FileWriter(file);
                                writer.append((CharSequence) myTimes);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }*/
                            startActivity(new Intent(getApplicationContext(), Exit.class));

                        }
                    }
                });
            }
        }, random_Time);
    }
    @Override
    public void onBackPressed() {
    }





}
