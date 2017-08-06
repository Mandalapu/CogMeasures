package edu.usc.projecttalent.cognitive.reasoning;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import edu.usc.projecttalent.cognitive.R;

/**
 * Instructions for Abstract reasoning section.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class ARInstructions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arinstructions);

        Button next = (Button) findViewById(R.id.next);
        next.setOnClickListener(v -> {
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle(R.string.start_now)
                    .setMessage(R.string.start_task)
                    .setNegativeButton(R.string.example, (dialog1, which) -> {
                        Intent intent = new Intent(this, ARpractice1_Activity.class);
                        startActivityForResult(intent, 1);
                    })
                    .setPositiveButton(R.string.start_task_confirm, (dialog2, which) -> {
                        Intent intent = new Intent(this, ARIntro_Activity.class);
                        startActivityForResult(intent, 1);
                    })
                    .setCancelable(false).create();
            dialog.show();
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
