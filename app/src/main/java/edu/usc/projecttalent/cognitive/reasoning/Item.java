package edu.usc.projecttalent.cognitive.reasoning;

import android.content.res.TypedArray;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

/**
 * JSON object encapsulation for Abstract reasoning examples.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class Item {
    private String instructions;
    private TypedArray options;
    private String details;
    private boolean answer;
    private int ansOption;

    @SuppressWarnings("ResourceType")
    public Item(String instructions, TypedArray options, String details, boolean answer) {
        this.instructions = instructions;
        this.options = options;
        this.details = details;
        this.answer = answer;
        this.ansOption = answer ? options.getInt(6, -1) : -1;
    }

    @SuppressWarnings("ResourceType")
    public Item(TypedArray options) {
        this.options = options;
        this.ansOption = options.getInt(6, -1);
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

    public int getAnsOption() {
        return ansOption;
    }

    public void setAnsOption(int ansOption) {
        this.ansOption = ansOption;
    }
}
