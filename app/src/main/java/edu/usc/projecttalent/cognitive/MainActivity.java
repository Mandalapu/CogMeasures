package edu.usc.projecttalent.cognitive;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import edu.usc.projecttalent.cognitive.model.Survey;
import edu.usc.projecttalent.cognitive.reaction_time.MainActivity_Reac;
import edu.usc.projecttalent.cognitive.thurstone.MainActivity_Th;
import edu.usc.projecttalent.cognitive.thurstone.TestImageAnswers;
import edu.usc.projecttalent.cognitive.vocab.VocabIntro;

public class MainActivity extends AppCompatActivity {

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        Button button = (Button) findViewById(R.id.next);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
              //  Intent intent = new Intent(mContext, VocabIntro.class);
                Intent intent = new Intent(mContext, MainActivity_Reac.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        setResult(Activity.RESULT_OK, data);
        super.finish();
    }

    @Override
    public void onBackPressed() {}
}
