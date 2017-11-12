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
import edu.usc.projecttalent.cognitive.holders.ARItem;

/**
 * The last example in the AR series. Shows the example and then moves to give introduction to section.
 */
public class ARPractice2 extends ARBase {

    /**
     * Obtain the example and explanation for the example. Once done, move to the introduction page.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Queue<ARItem> itemList = new LinkedList<>();
        TypedArray arr = getResources().obtainTypedArray(R.array.ar_ex_3);
        //add example 2
        itemList.add(new ARItem(getString(R.string.practiceq2), arr, "", false));
        //add example explanation.
        itemList.add(new ARItem("", arr, getString(R.string.pr_explain), true));

        ActivityArMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_ar_main);
        //set binding to the first question.
        binding.setItem(itemList.remove());

        Button button = findViewById(R.id.next);
        button.setOnClickListener(v -> {
            if (!itemList.isEmpty()) {
                binding.setItem(itemList.remove());
            } else {
                startActivityForResult(new Intent(this, ARIntroduction.class), 1);
            }
        });

        //set up the listener for click by user.
        setupOptionsListener(false, true);
    }
}
