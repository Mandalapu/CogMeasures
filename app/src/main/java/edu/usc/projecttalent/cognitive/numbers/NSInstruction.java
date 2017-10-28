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
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class NSInstruction extends NSBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final boolean showSecond = getIntent().getBooleanExtra("second", false);
        NSExample mExample = new Gson().fromJson(getString(showSecond ? R.string.ns_example2 : R.string.ns_example1), NSExample.class);

        ActivityNsMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_ns_main);
        binding.setItem(mExample);

        LinearLayout series = findViewById(R.id.series);
        answer = findViewById(R.id.answer);

        series.removeView(answer);
        series.addView(answer, mExample.getAnsPosition());

        setNumPad();

        (findViewById(R.id.next)).setOnClickListener(v -> {
            Intent intent = new Intent(this, NSExAnswer.class);
            intent.putExtra("example", mExample);
            startActivityForResult(intent, 1);
        });
    }
}
