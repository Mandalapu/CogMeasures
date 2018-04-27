package edu.usc.projecttalent.cognitive.numbers;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.Locale;

import edu.usc.projecttalent.cognitive.QuestionActivity;
import edu.usc.projecttalent.cognitive.R;

/**
 * Abstract common class to number section activities.
 * This class extracts the functionalities of the number pad used in almost all the activities. The
 * number pad has digits from 0-9 and an 'Undo' button to delete the characters.
 */
abstract class NSBase extends QuestionActivity {

    /**
     *  The views for the answers. All activities use the 'answer' view. Q5.3 also uses the
     *  answer2 view for the second option in the question.
     */
    protected EditText answer, answer2;

    /**
     * set the number pad for use. Check which textbox is being currently focused and add
     * animations to the number keys.
     */
    protected void setNumPad() {
        LinearLayout numPad = findViewById(R.id.numpad);

        View.OnTouchListener listener = (v, event) -> {
            Button npBtn = (Button) v;
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                EditText active = answer2 != null && answer2.hasFocus() ? answer2 : answer;
                active.append(npBtn.getText());
                setBgFg(npBtn, R.drawable.circle_dark, android.R.color.white);
            } else {
                setBgFg(npBtn, R.drawable.circle, android.R.color.black);
            }
            v.performClick();
            return true;
        };

        for (int i = 0; i < numPad.getChildCount(); i++) {
            Button npBtn = (Button) (numPad.getChildAt(i));
            npBtn.setText(String.format(Locale.getDefault(), "%d", i));
            npBtn.setOnTouchListener(listener);
        }

        (findViewById(R.id.undo)).setOnClickListener(v -> {
            EditText hasFocus = answer2 != null && answer2.hasFocus() ? answer2 : answer;
            int length = hasFocus.length();
            if (length > 0)
                hasFocus.getText().delete(length - 1, length);
        });
    }

    private void setBgFg(Button button, int bg, int fg) {
        button.setBackgroundResource(bg);
        button.setTextColor(getResources().getColor(fg));
    }
}
