package edu.usc.projecttalent.cognitive.numbers;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import edu.usc.projecttalent.cognitive.BaseActivity;
import edu.usc.projecttalent.cognitive.R;
import edu.usc.projecttalent.cognitive.databinding.ActivityNsExansBinding;

/**
 * Number section example 1 answer.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class ExAnswer extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Example example = (Example) getIntent().getExtras().get("example");
        ActivityNsExansBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_ns_exans);
        binding.setItem(example);

        LinearLayout series = (LinearLayout) findViewById(R.id.series);
        final EditText answer = (EditText) findViewById(R.id.answer);
        series.removeView(answer);
        series.addView(answer, example.getAnsPosition());

        Button button = (Button) findViewById(R.id.next);
        button.setOnClickListener(v -> {
            Intent intent;
            if (example.getId() == 1) {
                intent = new Intent(this, Instruction.class);
                intent.putExtra("second", true);
            } else {
                intent = new Intent(this, Introduction.class);
            }
            startActivityForResult(intent, 1);
        });
    }
}
