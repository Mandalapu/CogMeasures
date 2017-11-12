package edu.usc.projecttalent.cognitive.reasoning;

import android.content.Intent;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.Button;

import java.util.LinkedList;
import java.util.Queue;

import edu.usc.projecttalent.cognitive.BaseActivity;
import edu.usc.projecttalent.cognitive.R;
import edu.usc.projecttalent.cognitive.databinding.ActivityArMainBinding;
import edu.usc.projecttalent.cognitive.holders.ARItem;

/**
 * Show instructions and first example.
 */
public class ARInstruction extends BaseActivity {

    /**
     * set up to retrieve the first question and example for AR.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Queue<ARItem> itemList = new LinkedList<>();
        TypedArray arr = getResources().obtainTypedArray(R.array.ar_ex_1);
        //add instructions
        itemList.add(new ARItem(getString(R.string.ar_text), arr, getString(R.string.ar_text1), false));
        //add first example
        itemList.add(new ARItem("", arr, getString(R.string.ar_text3), true));

        ActivityArMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_ar_main);
        //bind instructions
        binding.setItem(itemList.remove());
        Button button = findViewById(R.id.next);
        button.setOnClickListener(v -> {
            if (!itemList.isEmpty())
                binding.setItem(itemList.remove()); //bind example.
            else {
                //move to instructions activity.
                startActivityForResult(new Intent(this, ARInstruction2.class), 1);
            }
        });
    }
}
