package edu.usc.projecttalent.cognitive.reasoning;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.usc.projecttalent.cognitive.R;

public class ARInstructions extends AppCompatActivity {

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arinstructions);

        mContext = this;

        Button next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(mContext)
                        .setTitle(R.string.start_now)
                        .setMessage(R.string.start_task)
                        .setNegativeButton(R.string.example, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(mContext, ARpractice1_Activity.class);
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
