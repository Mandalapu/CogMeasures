package edu.usc.projecttalent.cognitive.numbers;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.Locale;

import edu.usc.projecttalent.cognitive.QuestionActivity;
import edu.usc.projecttalent.cognitive.R;

abstract class NSBase extends QuestionActivity {

    protected EditText answer, answer2;

    protected void setNumPad() {
        LinearLayout numPad = findViewById(R.id.numpad);

        View.OnTouchListener listener = (v, event) -> {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                if (answer2 != null && answer2.hasFocus())
                    answer2.append(((Button) v).getText());
                else
                    answer.append(((Button) v).getText());
                v.setBackgroundResource(R.drawable.circle_dark);
                ((Button) v).setTextColor(getResources().getColor(android.R.color.white));
            } else {
                v.setBackgroundResource(R.drawable.circle);
                ((Button) v).setTextColor(getResources().getColor(android.R.color.black));
            }
            v.performClick();
            return true;
        };

        for (int i = 0; i < numPad.getChildCount(); i++) {
            ((Button) (numPad.getChildAt(i))).setText(String.format(Locale.getDefault(), "%d", i));
            (numPad.getChildAt(i)).setOnTouchListener(listener);
        }

        (findViewById(R.id.undo)).setOnClickListener(v -> {
            EditText hasFocus = answer2 != null && answer2.hasFocus() ? answer2 : answer;
            int length = hasFocus.length();
            if (length > 0)
                hasFocus.getText().delete(length - 1, length);
        });
    }
}
