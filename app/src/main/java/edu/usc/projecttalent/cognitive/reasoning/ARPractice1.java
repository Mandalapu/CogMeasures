package edu.usc.projecttalent.cognitive.reasoning;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.Button;

import edu.usc.projecttalent.cognitive.ARBase;
import edu.usc.projecttalent.cognitive.R;
import edu.usc.projecttalent.cognitive.databinding.ActivityArMainBinding;
import edu.usc.projecttalent.cognitive.holders.ARItem;

public class ARPractice1 extends ARBase {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Resources res = getResources();

        TypedArray arr = res.obtainTypedArray(R.array.ar_ex_2);
        ARItem item = new ARItem(getString(R.string.ar_instr_header), arr, getString(R.string.pr_wrong), true);

        ActivityArMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_ar_main);
        binding.setItem(item);

        Button button = findViewById(R.id.next);
        button.setOnClickListener(v -> startTask(2));
    }
}
