package edu.usc.projecttalent.cognitive.reasoning;

import android.content.res.TypedArray;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import edu.usc.projecttalent.cognitive.Item;

/**
 * JSON object encapsulation for Abstract reasoning examples.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class ARItem extends Item {
    private String instructions;
    private TypedArray options;
    private String details;
    private boolean answer;

    @SuppressWarnings("ResourceType")
    public ARItem(String instructions, TypedArray options, String details, boolean answer) {
        this.instructions = instructions;
        this.options = options;
        this.details = details;
        this.answer = answer;
        this.ansPosition = answer ? options.getInt(6, -1) : -1;
    }

    @SuppressWarnings("ResourceType")
    public ARItem(TypedArray options) {
        this.options = options;
        this.ansPosition = options.getInt(6, -1);
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, int imageUrl) {
        view.setImageResource(imageUrl);
    }


    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public TypedArray getOptions() {
        return options;
    }

    public void setOptions(TypedArray options) {
        this.options = options;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
