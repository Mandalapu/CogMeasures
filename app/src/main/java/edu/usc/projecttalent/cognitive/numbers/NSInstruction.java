package edu.usc.projecttalent.cognitive.numbers;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.gson.Gson;

import edu.usc.projecttalent.cognitive.R;
import edu.usc.projecttalent.cognitive.databinding.ActivityNsMainBinding;
import edu.usc.projecttalent.cognitive.holders.NSExample;

/**
 * Number section introduction and instructions.
 * This activity shows the example questions.
 * @author Anindya Dutta
 * @version 2.0
 */

public class NSInstruction extends NSBase {

    /**
     * Use data binding to bind a question to this activity. Set up the variables to show the example's
     * answer properly in the next activity.
     * @param savedInstanceState currently nothing is sent in the bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //the value below is sent by NSExAnswer when the ID is 1 in the former activity.
        final boolean showSecond = getIntent().getBooleanExtra("second", false);
        //if the value is true, show second example, else show the first example.
        NSExample mExample = new Gson().fromJson(getString(showSecond ? R.string.ns_example2 : R.string.ns_example1), NSExample.class);

        ActivityNsMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_ns_main);
        //bind the example object to the binding.
        binding.setItem(mExample);

        LinearLayout series = findViewById(R.id.series); //extract the series view.
        answer = findViewById(R.id.answer); //extract the answer view.
        /*
        The answer view needs to be put at the correct location, hence we first remove the view from
        the series and then add it at the correct position. This position is defined by the
        getAnsPosition() method.
         */
        series.removeView(answer);
        series.addView(answer, mExample.getAnsPosition());
        //call numpad setter in super class to initialize.
        setNumPad();

        (findViewById(R.id.next)).setOnClickListener(v -> {
            //intent for the answer class.
            Intent intent = new Intent(this, NSExAnswer.class);
            //add the example so that it can be used in the answer activity.
            intent.putExtra("example", mExample);
            startActivityForResult(intent, 1);
        });
    }
}
