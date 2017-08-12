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
import edu.usc.projecttalent.cognitive.reasoning.Item;

/**
 * Spatial Visualization practice without scoring and with solutions.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class Practice extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Resources res = getResources();

        Queue<Item> mQueue = new LinkedList<>();
        TypedArray options = res.obtainTypedArray(R.array.sp_ex_1);
        mQueue.add(new Item(getString(R.string.sp_1_instr), options, getString(R.string.sp_1_sol), false));

        options = res.obtainTypedArray(R.array.sp_ex_2);
        mQueue.add(new Item(getString(R.string.sp_2_instr), options, getString(R.string.sp_next), false));

        ActivitySpPracticeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_sp_practice);
        binding.setItem(mQueue.remove());

        Button button = (Button) findViewById(R.id.next);
        button.setOnClickListener(v -> {
            if (!mQueue.isEmpty())
                binding.setItem(mQueue.remove());
            else
                startActivityForResult(new Intent(this, Instruction.class), 1);
        });
    }
}
