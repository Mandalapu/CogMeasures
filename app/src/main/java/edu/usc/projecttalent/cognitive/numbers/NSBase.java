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
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                if (answer2 != null && answer2.hasFocus()) //check which textbox has current focus.
                    answer2.append(((Button) v).getText());
                else
                    answer.append(((Button) v).getText());
                v.setBackgroundResource(R.drawable.circle_dark);
                ((Button) v).setTextColor(getResources().getColor(android.R.color.white));
            } else {
                //on action up, we need to change the color of the circle.
                v.setBackgroundResource(R.drawable.circle);
                ((Button) v).setTextColor(getResources().getColor(android.R.color.black));
            }
            v.performClick(); //perform the click that was originally intended.
            return true;
        };

        for (int i = 0; i < numPad.getChildCount(); i++) {
            ((Button) (numPad.getChildAt(i))).setText(String.format(Locale.getDefault(), "%d", i));
            (numPad.getChildAt(i)).setOnTouchListener(listener);
        }

        //set the undo button.
        (findViewById(R.id.undo)).setOnClickListener(v -> {
            EditText hasFocus = answer2 != null && answer2.hasFocus() ? answer2 : answer;
            int length = hasFocus.length();
            if (length > 0) //delete the last character if there is at least one character.
                hasFocus.getText().delete(length - 1, length);
        });
    }
}
