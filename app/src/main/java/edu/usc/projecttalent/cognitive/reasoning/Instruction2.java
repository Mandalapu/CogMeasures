package edu.usc.projecttalent.cognitive.reasoning;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import edu.usc.projecttalent.cognitive.BaseActivity;
import edu.usc.projecttalent.cognitive.R;

/**
 * Instruction for Abstract reasoning section.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class Instruction2 extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar_instr);

        (findViewById(R.id.next)).setOnClickListener(v -> {
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle(R.string.start_now)
                    .setMessage(R.string.start_task)
                    .setNegativeButton(R.string.example, (dialog1, which) -> {
                        Intent intent = new Intent(this, Practice1.class);
                        startActivityForResult(intent, 1);
                    })
                    .setPositiveButton(R.string.start_task_confirm, (dialog2, which) -> {
                        Intent intent = new Intent(this, Introduction.class);
                        startActivityForResult(intent, 1);
                    })
                    .setCancelable(false).create();
            dialog.show();
        });
    }
}
