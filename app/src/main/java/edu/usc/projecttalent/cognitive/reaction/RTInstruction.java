package edu.usc.projecttalent.cognitive.reaction;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.widget.TextView;

import edu.usc.projecttalent.cognitive.BaseActivity;
import edu.usc.projecttalent.cognitive.R;

/**
 * Activity that shows the instructions for Reaction time.
 */
public class RTInstruction extends BaseActivity {

    /**
     * set up the instructions and next button to the practice example for Reaction time.
     * @param savedInstanceState currently null is passed to this activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reac_instr);
        setNext(RTPractice.class);

        String instr = getString(R.string.reaction_instr1);

        SpannableStringBuilder builder = new SpannableStringBuilder();
        int index = instr.indexOf("circle (")+7;

        builder.append(instr.substring(0, index+1)).append(" ");
        builder.setSpan(new ImageSpan(this, R.drawable.instr_red_circle),
                builder.length() - 1, builder.length(), 0);
        builder.append(instr.substring(index + 1));

        ((TextView)findViewById(R.id.rt_instr)).setText(builder);
    }
}
