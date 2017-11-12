package edu.usc.projecttalent.cognitive.spatial;

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
import edu.usc.projecttalent.cognitive.databinding.ActivitySpPracticeBinding;
import edu.usc.projecttalent.cognitive.holders.ARItem;

/**
 * Spatial Visualization practice without scoring and with solutions.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class SVPractice extends BaseActivity {

    /**
     * Add practice examples to the activity and then move on to show instructions for this section.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Resources res = getResources();

        Queue<ARItem> mQueue = new LinkedList<>();
        mQueue.add(new ARItem(getString(R.string.sp_1_instr), res.obtainTypedArray(R.array.sp_ex_1), getString(R.string.sp_1_sol), false));
        mQueue.add(new ARItem(getString(R.string.sp_2_instr),  res.obtainTypedArray(R.array.sp_ex_2), getString(R.string.sp_next), false));

        ActivitySpPracticeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_sp_practice);
        binding.setItem(mQueue.remove());

        (findViewById(R.id.next)).setOnClickListener(v -> {
            if (!mQueue.isEmpty())
                binding.setItem(mQueue.remove());
            else
                startActivityForResult(new Intent(this, SVInstruction.class), 1);
        });
    }
}
