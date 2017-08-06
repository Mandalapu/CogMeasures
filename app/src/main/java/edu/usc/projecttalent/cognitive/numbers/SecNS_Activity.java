package edu.usc.projecttalent.cognitive.numbers;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.gson.Gson;

import edu.usc.projecttalent.cognitive.BaseActivity;
import edu.usc.projecttalent.cognitive.R;
import edu.usc.projecttalent.cognitive.databinding.ActivitySecNsBinding;

/**
 * Number section introduction and instructions.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class SecNS_Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final boolean showSecond = getIntent().getBooleanExtra("second", false);
        NSExample mExample = new Gson().fromJson(getString(showSecond ? R.string.ns_example2 : R.string.ns_example1), NSExample.class);

        ActivitySecNsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_sec_ns_);
        binding.setItem(mExample);

        LinearLayout series = (LinearLayout) findViewById(R.id.series);
        LinearLayout numPad = (LinearLayout) findViewById(R.id.numpad);
        final EditText answer = (EditText) findViewById(R.id.answer);

        series.removeView(answer);
        series.addView(answer, mExample.getAnsPosition());

        View.OnClickListener listener = v -> answer.append(((Button) v).getText());
        for (int i = 0; i < numPad.getChildCount(); i++) {
            ((Button) (numPad.getChildAt(i))).setText(Integer.toString(i));
            (numPad.getChildAt(i)).setOnClickListener(listener);
        }

        (findViewById(R.id.undo)).setOnClickListener(v -> {
            int length = answer.length();
            if (length > 0)
                answer.getText().delete(length - 1, length);
        });

        (findViewById(R.id.next)).setOnClickListener(v -> {
            Intent intent = new Intent(this, SecNSEx1AActivity.class);
            intent.putExtra("example", mExample);
            startActivityForResult(intent, 1);
        });
    }
}
