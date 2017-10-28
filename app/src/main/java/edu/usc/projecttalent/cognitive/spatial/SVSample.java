package edu.usc.projecttalent.cognitive.spatial;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.Button;

import java.util.LinkedList;
import java.util.Queue;

import edu.usc.projecttalent.cognitive.ARBase;
import edu.usc.projecttalent.cognitive.R;
import edu.usc.projecttalent.cognitive.databinding.ActivitySpPracticeBinding;
import edu.usc.projecttalent.cognitive.holders.ARItem;

/**
 * Spatial Visualization sample and instructions on how to solve the questions.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class SVSample extends ARBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_practice);

        Resources r = getResources();
        Queue<ARItem> mQueue = new LinkedList<>();
        TypedArray arr = r.obtainTypedArray(R.array.sp_ex_3);

        mQueue.add(new ARItem(getString(R.string.sp_3_instr), arr, getString(R.string.sp_next), false));
        mQueue.add(new ARItem(getString(R.string.sp_3_sol), arr, getString(R.string.sp_begin), true));

        ActivitySpPracticeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_sp_practice);
        binding.setItem(mQueue.remove());

        setupOptionsListener(true, false);

        Button next = findViewById(R.id.next);
        next.setOnClickListener(v -> {
            if (!mQueue.isEmpty())
                binding.setItem(mQueue.remove());
            else
                startActivityForResult(new Intent(this, SVQuestion.class), 1);

        });
    }
}
