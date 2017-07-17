package edu.usc.projecttalent.cognitive.thurstone;

import edu.usc.projecttalent.cognitive.R;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

import android.os.Build;
import android.view.WindowManager;

public class MainActivity_Th extends AppCompatActivity {

    private Button btn;
    private Intent myIntent;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_main_thurstone);
        btn = (Button) findViewById(R.id.btnNext); //initialize the next button
        MyOnClickListener myOnClickListener = new MyOnClickListener();
        btn.setOnClickListener(myOnClickListener);

    }

    private class MyOnClickListener implements OnClickListener{
        @Override
        public void onClick(View v) {
            startActivity(new Intent (getApplicationContext(), SecondActivity.class));
        }
    }
    @Override
    public void onBackPressed() {
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
