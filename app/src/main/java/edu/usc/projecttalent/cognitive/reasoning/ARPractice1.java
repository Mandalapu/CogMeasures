package edu.usc.projecttalent.cognitive.reasoning;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.LinkedList;
import java.util.Queue;

import edu.usc.projecttalent.cognitive.BaseActivity;
import edu.usc.projecttalent.cognitive.R;
import edu.usc.projecttalent.cognitive.databinding.ActivityArMainBinding;

public class ARPractice1 extends BaseActivity {

    View oldView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Queue<ARItem> itemList = new LinkedList<>();
        Resources res = getResources();
        TypedArray arr = res.obtainTypedArray(R.array.ar_ex_2);
        itemList.add(new ARItem(getString(R.string.ar_instr_header), arr, getString(R.string.pr_wrong), true));

        ActivityArMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_ar_main);
        binding.setItem(itemList.remove());

        Button button = (Button) findViewById(R.id.next);
        button.setOnClickListener(v -> {
            if (!itemList.isEmpty()) {
                binding.setItem(itemList.remove());
            } else {
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle(R.string.start_now)
                        .setMessage(R.string.start_task)
                        .setNegativeButton(R.string.example, (dialog1, which) -> {
                            Intent intent = new Intent(this, ARPractice2.class);
                            startActivityForResult(intent, 1);
                        })
                        .setPositiveButton(R.string.start_task_confirm, (dialog2, which) -> {
                            Intent intent = new Intent(this, ARIntroduction.class);
                            startActivityForResult(intent, 1);
                        })
                        .setCancelable(false).create();
                dialog.show();
            }
        });

        LinearLayout options = (LinearLayout) findViewById(R.id.options);
        for (int i = 0; i < options.getChildCount(); i++) {
            options.getChildAt(i).setOnClickListener(v -> {
                if (!itemList.isEmpty()) {
                    v.setPadding(1, 1, 1, 1);
                    v.setBackgroundColor(ContextCompat.getColor(this, R.color.black));
                    if (oldView != null)
                        oldView.setBackground(null);
                    oldView = v;
                }
            });
        }
    }
}
