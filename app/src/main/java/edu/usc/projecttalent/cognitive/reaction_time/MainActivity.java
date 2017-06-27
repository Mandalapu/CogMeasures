package edu.usc.projecttalent.cognitive.reaction_time;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import edu.usc.projecttalent.cognitive.R;

public class MainActivity extends AppCompatActivity {

    Button spacebar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_reaction);

        spacebar = (Button) findViewById(R.id.buttonSpace);

        spacebar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ReactionTime.class));
            }
        });
    }
    @Override
    public void onBackPressed() {
    }
}
