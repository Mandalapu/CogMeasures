package edu.usc.projecttalent.cognitive.numbers;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

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
        mContext = this;
        final boolean showSecond = getIntent().getBooleanExtra("second", false);
        List<NSExample> nsList = null;
        try (InputStreamReader reader = new InputStreamReader(mContext.getResources().openRawResource(R.raw.ns_example))) {
            nsList = new Gson().fromJson(reader, new TypeToken<List<NSExample>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert nsList != null;
        NSExample mExample = nsList.get(showSecond ? 1 : 0);

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
