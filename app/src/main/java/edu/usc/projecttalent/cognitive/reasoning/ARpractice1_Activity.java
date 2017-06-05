package edu.usc.projecttalent.cognitive.reasoning;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.LinkedList;
import java.util.Queue;

import edu.usc.projecttalent.cognitive.ImageDecoder;
import edu.usc.projecttalent.cognitive.R;
import edu.usc.projecttalent.cognitive.databinding.ActivitySecArBinding;

public class ARpractice1_Activity extends Activity {

    View oldView;
    Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final Queue<ARExample> exampleList = new LinkedList<>();
		Resources res = getResources();
		TypedArray arr = res.obtainTypedArray(R.array.ar_ex_2);
		exampleList.add(new ARExample(getString(R.string.practice), arr, getString(R.string.practiceq1), false, 0));
		exampleList.add(new ARExample("", arr, getString(R.string.pr_wrong), true, 5));

        final ActivitySecArBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_sec_ar_);
        binding.setItem(exampleList.remove());
        mContext = this;


        Button button = (Button) findViewById(R.id.next);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!exampleList.isEmpty()) {
                    binding.setItem(exampleList.remove());
                }
                else {
                    AlertDialog dialog = new AlertDialog.Builder(mContext)
                            .setTitle(R.string.start_now)
                            .setMessage(R.string.start_task)
                            .setNegativeButton(R.string.example, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(mContext, ARpractice2_Activity.class);
                                    startActivityForResult(intent, 1);
                                }
                            })
                            .setPositiveButton(R.string.start_task_confirm, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(mContext, ARIntro_Activity.class);
                                    startActivityForResult(intent, 1);
                                }
                            })
                            .setCancelable(false).create();
                    dialog.show();
                }
            }
        });

        LinearLayout options = (LinearLayout) findViewById(R.id.options);
        for(int i=0; i<options.getChildCount(); i++) {
            options.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!exampleList.isEmpty()) {
                        v.setPadding(1, 1, 1, 1);
                        v.setBackgroundColor(getResources().getColor(R.color.black));
                        if (oldView != null)
                            oldView.setBackground(null);
                        oldView = v;
                    }
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
