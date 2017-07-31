package edu.usc.projecttalent.cognitive.reasoning;

import android.content.res.TypedArray;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

/**
 * JSON object encapsulation for Abstract reasoning examples.
 * @author Anindya Dutta
 * @version 2.0
 */

public class ARExample {
    public String instructions;
    public TypedArray options;
    public String details;
    public boolean answer;
    public int ansOption;

    @SuppressWarnings("ResourceType")
    public ARExample(String instructions, TypedArray options, String details, boolean answer) {
        this.instructions = instructions;
        this.options = options;
        this.details = details;
        this.answer = answer;
        if(answer)
            this.ansOption = options.getInt(6, -1);
    }

    @SuppressWarnings("ResourceType")
    public ARExample(TypedArray options) {
        this.options = options;
        this.ansOption = options.getInt(6, -1);
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, int imageUrl) {
        view.setImageResource(imageUrl);
    }
}
