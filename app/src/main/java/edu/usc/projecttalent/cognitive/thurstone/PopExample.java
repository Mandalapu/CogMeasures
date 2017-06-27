package edu.usc.projecttalent.cognitive.thurstone;

import edu.usc.projecttalent.cognitive.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

/**
 * Created by kayigwe on 6/22/17.
 */

public class PopExample extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popup_example_screen);

        //width and height for the pop up window
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width * .8), (int)(height*.6));
    }
    @Override
    public void onBackPressed() {
    }
}
