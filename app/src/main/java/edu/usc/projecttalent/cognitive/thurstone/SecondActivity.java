package edu.usc.projecttalent.cognitive.thurstone;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import edu.usc.projecttalent.cognitive.BaseActivity;
import edu.usc.projecttalent.cognitive.R;

/**
 * @author Kay, updated by Anindya.
 * @version 1.0
 */

public class SecondActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_second_page);

        mContext = this;
        ImageView img1 = (ImageView) findViewById(R.id.truck_one),
                img2 = (ImageView) findViewById(R.id.truck_two),
                img3 = (ImageView) findViewById(R.id.truck_three),
                img4 = (ImageView) findViewById(R.id.truck_four);

        img1.setOnClickListener(listener);
        img2.setOnClickListener(listener);
        img3.setOnClickListener(listener);
        img4.setOnClickListener(listener);
    }

    private View.OnClickListener listener = v -> {
        int layout = (v.getId() == R.id.truck_three) ? R.layout.popup_correct : R.layout.popup_wrong;
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        View view = getLayoutInflater().inflate(layout, null);
        (view.findViewById(R.id.next)).setOnClickListener(v1 ->
                startActivityForResult(new Intent(mContext, ExampleImageChange.class), 1));

        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
    };
}
