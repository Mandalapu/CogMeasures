package edu.usc.projecttalent.cognitive.thurstone;

import edu.usc.projecttalent.cognitive.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ImageView;

/**
 * @author Kay
 * @version 1.0
 */

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_second_page);

        ImageView imageView_one = (ImageView) findViewById(R.id.truck_one),
                imageView_two = (ImageView) findViewById(R.id.truck_two),
                imageView_three = (ImageView) findViewById(R.id.truck_three),
                imageView_four = (ImageView) findViewById(R.id.truck_four);

        TextView txtView = (TextView) findViewById(R.id.textView2);

        Button btn = (Button) findViewById(R.id.btnNext);
        btn.setOnClickListener(v -> {
            txtView.setText(R.string.click_picture);
            btn.setVisibility(View.INVISIBLE);

            IncorrectClick incorrect = new IncorrectClick();
            CorrectClick correct = new CorrectClick();

            imageView_one.setOnClickListener(incorrect);
            imageView_two.setOnClickListener(incorrect);
            imageView_three.setOnClickListener(correct);
            imageView_four.setOnClickListener(incorrect);
        });

    }

    private class IncorrectClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //image 1,2,4
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(SecondActivity.this);
            View myView = getLayoutInflater().inflate(R.layout.popup_wrong_examples, null);

            //The start example test button
            Button nextBtn = (Button) myView.findViewById(R.id.bntNextExample);
            nextBtn.setOnClickListener(v1 -> startActivityForResult(new Intent(getApplicationContext(), ExampleImageChange.class), 1));

            mBuilder.setView(myView);
            AlertDialog dialog = mBuilder.create();
            dialog.show();
            dialog.setCanceledOnTouchOutside(false);
        }
    }

    private class CorrectClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //image 3
            AlertDialog.Builder builder = new AlertDialog.Builder(SecondActivity.this);
            View view = getLayoutInflater().inflate(R.layout.popup_correctwrong_examples, null);

            //The start example test button
            Button nextBtn = (Button) view.findViewById(R.id.bntNextExample);
            nextBtn.setOnClickListener(v1 -> startActivityForResult(new Intent(getApplicationContext(), ExampleImageChange.class), 1));

            builder.setView(view);
            AlertDialog dialog = builder.create();
            dialog.show();
            dialog.setCanceledOnTouchOutside(false);
        }
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
