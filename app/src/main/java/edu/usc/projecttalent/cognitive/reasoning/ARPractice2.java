package edu.usc.projecttalent.cognitive.reasoning;

import android.content.Intent;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.Button;

import java.util.LinkedList;
import java.util.Queue;

import edu.usc.projecttalent.cognitive.ARBase;
import edu.usc.projecttalent.cognitive.R;
import edu.usc.projecttalent.cognitive.databinding.ActivityArMainBinding;

public class ARPractice2 extends ARBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Queue<ARItem> itemList = new LinkedList<>();
        TypedArray arr = getResources().obtainTypedArray(R.array.ar_ex_3);
        itemList.add(new ARItem(getString(R.string.practiceq2), arr, "", false));
        itemList.add(new ARItem(getString(R.string.ar_instr_header), arr, getString(R.string.pr_explain), true));

        ActivityArMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_ar_main);
        binding.setItem(itemList.remove());

        Button button = (Button) findViewById(R.id.next);
        button.setOnClickListener(v -> {
            if (!itemList.isEmpty()) {
                binding.setItem(itemList.remove());
            } else {
                startActivityForResult(new Intent(this, ARIntroduction.class), 1);
            }
        });

        setupOptionsListener(true);
    }
}
