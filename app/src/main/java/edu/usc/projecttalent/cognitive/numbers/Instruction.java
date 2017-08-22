package edu.usc.projecttalent.cognitive.numbers;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.gson.Gson;

import edu.usc.projecttalent.cognitive.BaseActivity;
import edu.usc.projecttalent.cognitive.R;
import edu.usc.projecttalent.cognitive.databinding.ActivityNsMainBinding;

/**
 * Number section introduction and instructions.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class Instruction extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final boolean showSecond = getIntent().getBooleanExtra("second", false);
        Example mExample = new Gson().fromJson(getString(showSecond ? R.string.ns_example2 : R.string.ns_example1), Example.class);

        ActivityNsMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_ns_main);
        binding.setItem(mExample);

        LinearLayout series = (LinearLayout) findViewById(R.id.series);
        LinearLayout numPad = (LinearLayout) findViewById(R.id.numpad);
        final EditText answer = (EditText) findViewById(R.id.answer);

        series.removeView(answer);
        series.addView(answer, mExample.getAnsPosition());

        View.OnTouchListener listener = (v, event) -> {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                answer.append(((Button) v).getText());
                v.setBackgroundResource(R.drawable.circle_dark);
                ((Button) v).setTextColor(getResources().getColor(android.R.color.white));
            } else {
                v.setBackgroundResource(R.drawable.circle);
                ((Button) v).setTextColor(getResources().getColor(android.R.color.black));
            }
            return true;
        };

        for (int i = 0; i < numPad.getChildCount(); i++) {
            ((Button) (numPad.getChildAt(i))).setText(Integer.toString(i));
            (numPad.getChildAt(i)).setOnTouchListener(listener);
        }

        (findViewById(R.id.undo)).setOnClickListener(v -> {
            int length = answer.length();
            if (length > 0)
                answer.getText().delete(length - 1, length);
        });

        (findViewById(R.id.next)).setOnClickListener(v -> {
            Intent intent = new Intent(this, ExAnswer.class);
            intent.putExtra("example", mExample);
            startActivityForResult(intent, 1);
        });
    }
}
