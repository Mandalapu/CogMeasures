package edu.usc.projecttalent.cognitive.spatial;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.Button;

import java.util.LinkedList;
import java.util.Queue;

import edu.usc.projecttalent.cognitive.R;
import edu.usc.projecttalent.cognitive.databinding.ActivitySppracticeBinding;
import edu.usc.projecttalent.cognitive.reasoning.ARExample;

/**
 * Spatial Visualization sample and instructions on how to solve the questions.
 * @author Anindya Dutta
 * @version 2.0
 */

public class SPSampleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sppractice_);

        Resources r = getResources();
        Queue<ARExample> mQueue = new LinkedList<>();
        TypedArray arr = r.obtainTypedArray(R.array.sp_ex_3);

        mQueue.add(new ARExample(getString(R.string.sp_3_instr), arr, getString(R.string.sp_next), false));
        mQueue.add(new ARExample(getString(R.string.sp_3_sol), arr, getString(R.string.sp_begin), true));

        ActivitySppracticeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_sppractice_);
        binding.setItem(mQueue.remove());

        Button next = (Button) findViewById(R.id.next);
        next.setOnClickListener(v -> {
            if(!mQueue.isEmpty())
                binding.setItem(mQueue.remove());
            else
                startActivityForResult(new Intent(this, SP31_Activity.class), 1);

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                setResult(Activity.RESULT_OK, data);
                super.finish();
            }
        }
    }
}
