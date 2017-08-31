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

public class ARInstruction extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Queue<ARItem> itemList = new LinkedList<>();
        TypedArray arr = getResources().obtainTypedArray(R.array.ar_ex_1);
        itemList.add(new ARItem(getString(R.string.ar_text), arr, getString(R.string.ar_text1), false));
        itemList.add(new ARItem("", arr, getString(R.string.ar_text3), true));

        final ActivityArMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_ar_main);
        binding.setItem(itemList.remove());
        Button button = (Button) findViewById(R.id.next);
        button.setOnClickListener(v -> {
            if (!itemList.isEmpty())
                binding.setItem(itemList.remove());
            else {
                startActivityForResult(new Intent(this, ARInstruction2.class), 1);
            }
        });
    }
}
