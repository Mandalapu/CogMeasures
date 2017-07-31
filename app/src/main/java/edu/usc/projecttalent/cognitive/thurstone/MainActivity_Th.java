package edu.usc.projecttalent.cognitive.thurstone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import edu.usc.projecttalent.cognitive.R;

public class MainActivity_Th extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_thurstone);
        (findViewById(R.id.btnNext)).setOnClickListener(v -> startActivityForResult(new Intent (this, SecondActivity.class), 1));

    }

    @Override
    public void onBackPressed() {}

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
