package edu.usc.projecttalent.cognitive.reasoning;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.Button;

import java.util.LinkedList;
import java.util.Queue;

import edu.usc.projecttalent.cognitive.BaseActivity;
import edu.usc.projecttalent.cognitive.R;
import edu.usc.projecttalent.cognitive.databinding.ActivitySecArBinding;

public class SecAR_Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Queue<ARExample> exampleList = new LinkedList<>();
        Resources res = getResources();
        TypedArray arr = res.obtainTypedArray(R.array.ar_ex_1);
        exampleList.add(new ARExample(getString(R.string.ar_text), arr, getString(R.string.ar_text1), false));
        exampleList.add(new ARExample("", arr, getString(R.string.ar_text3), true));

        final ActivitySecArBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_sec_ar_);
        binding.setItem(exampleList.remove());
        Button button = (Button) findViewById(R.id.next);
        button.setOnClickListener(v -> {
            if (!exampleList.isEmpty())
                binding.setItem(exampleList.remove());
            else {
                Intent intent = new Intent(SecAR_Activity.this, ARInstructions.class);
                startActivityForResult(intent, 1);
            }

        });
    }
}
