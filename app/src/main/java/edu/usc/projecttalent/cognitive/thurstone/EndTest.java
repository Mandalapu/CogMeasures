package edu.usc.projecttalent.cognitive.thurstone;

import edu.usc.projecttalent.cognitive.R;
import edu.usc.projecttalent.cognitive.reasoning.ARIntro_Activity;
import edu.usc.projecttalent.cognitive.reasoning.SecAR_Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

/**
 * Created by kayigwe on 6/26/17.
 */

public class EndTest extends Activity {

    Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_test);

        btn = (Button) findViewById(R.id.bntNextExample);
        MyOnClickListener myOnClickListener = new MyOnClickListener();
        btn.setOnClickListener(myOnClickListener);


    }
    @Override
    public void onBackPressed() {
    }

    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(), SecAR_Activity.class));
        }
    }
}
