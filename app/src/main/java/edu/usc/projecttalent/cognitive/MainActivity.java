package edu.usc.projecttalent.cognitive;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import edu.usc.projecttalent.cognitive.vocab.VocabIntro;

/**
 * Introduction page that welcomes users to Cognitive Measures.
 * @author Anindya Dutta
 * @version 2.0
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.next);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(this, VocabIntro.class);
            startActivityForResult(intent, 1);
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
