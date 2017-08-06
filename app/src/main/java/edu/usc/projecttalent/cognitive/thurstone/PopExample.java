package edu.usc.projecttalent.cognitive.thurstone;

import android.os.Bundle;
import android.util.DisplayMetrics;

import edu.usc.projecttalent.cognitive.BaseActivity;
import edu.usc.projecttalent.cognitive.R;

public class PopExample extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popup_example_screen);

        //width and height for the pop up window
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        getWindow().setLayout((int) (dm.widthPixels * .8), (int) (dm.heightPixels * .6));
    }
}
