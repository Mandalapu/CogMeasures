package edu.usc.projecttalent.cognitive.reasoning;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.LinkedList;
import java.util.Queue;

import edu.usc.projecttalent.cognitive.R;
import edu.usc.projecttalent.cognitive.databinding.ActivitySecArBinding;

public class ARpractice1_Activity extends Activity {

    View oldView;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Queue<ARExample> exampleList = new LinkedList<>();
		Resources res = getResources();
		TypedArray arr = res.obtainTypedArray(R.array.ar_ex_2);
		exampleList.add(new ARExample(getString(R.string.ar_instr_header), arr, getString(R.string.pr_wrong), true));

        ActivitySecArBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_sec_ar_);
        binding.setItem(exampleList.remove());

        Button button = (Button) findViewById(R.id.next);
        button.setOnClickListener(v -> {
            if(!exampleList.isEmpty()) {
                binding.setItem(exampleList.remove());
            }
            else {
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle(R.string.start_now)
                        .setMessage(R.string.start_task)
                        .setNegativeButton(R.string.example, (dialog1, which) -> {
                            Intent intent = new Intent(this, ARpractice2_Activity.class);
                            startActivityForResult(intent, 1);
                        })
                        .setPositiveButton(R.string.start_task_confirm, (dialog2, which) -> {
                            Intent intent = new Intent(this, ARIntro_Activity.class);
                            startActivityForResult(intent, 1);
                        })
                        .setCancelable(false).create();
                dialog.show();
            }
        });

        LinearLayout options = (LinearLayout) findViewById(R.id.options);
        for(int i=0; i<options.getChildCount(); i++) {
            options.getChildAt(i).setOnClickListener(v -> {
                if(!exampleList.isEmpty()) {
                    v.setPadding(1, 1, 1, 1);
                    v.setBackgroundColor(getResources().getColor(R.color.black));
                    if (oldView != null)
                        oldView.setBackground(null);
                    oldView = v;
                }
            });
        }
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
