package edu.usc.projecttalent.cognitive.numbers;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;

import edu.usc.projecttalent.cognitive.BaseActivity;
import edu.usc.projecttalent.cognitive.R;
import edu.usc.projecttalent.cognitive.databinding.ActivityNsExansBinding;
import edu.usc.projecttalent.cognitive.holders.NSExample;

/**
 * Number section example 1 answer.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class NSExAnswer extends BaseActivity {

    /**
     * Use data binding to set an example object to this activity.
     * @param savedInstanceState nothing is sent in this bundle currently
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //example object that was sent to this activity.
        NSExample example = (NSExample) getIntent().getExtras().get("example");
        ActivityNsExansBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_ns_exans);
        //set the example object to this binding.
        binding.setItem(example);

        LinearLayout series = findViewById(R.id.series); //extract the series view.
        EditText answer = findViewById(R.id.answer); //extract the answer view.
        /*
        The answer view needs to be put at the correct location, hence we first remove the view from
        the series and then add it at the correct position. This position is defined by the
        getAnsPosition() method.
         */
        series.removeView(answer);
        series.addView(answer, example.getAnsPosition());

        (findViewById(R.id.next)).setOnClickListener(v -> {
            //intent for the next activity.
            Intent intent;
            if (example.getId() == 1) {
                //if the ID is one, we need to show another example, therefore go back to the instructions class.
                intent = new Intent(this, NSInstruction.class);
                //add an indicator for the instructions to send second example.
                intent.putExtra("second", true);
            } else {
                //if the ID is 2, both examples have been shown. Proceed to the introduction activity.
                intent = new Intent(this, NSIntroduction.class);
            }
            startActivityForResult(intent, 1);
        });
    }
}
