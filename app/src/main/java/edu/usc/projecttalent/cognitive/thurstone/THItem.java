package edu.usc.projecttalent.cognitive.thurstone;

import android.content.res.TypedArray;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

/**
 * Model for thurstone question.
 *
 * @author Anindya Dutta
 * @version 1.0
 */

public class THItem {
    private TypedArray options;
    private int ansOption;

    @SuppressWarnings("ResourceType")
    public THItem(TypedArray options) {
        this.options = options;
        this.ansOption = options.getInt(4, -1);
    }

    public TypedArray getOptions() {
        return options;
    }

    public void setOptions(TypedArray options) {
        this.options = options;
    }

    public int getAnsOption() {
        return ansOption;
    }

    public void setAnsOption(int ansOption) {
        this.ansOption = ansOption;
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, int imageUrl) {
        view.setImageResource(imageUrl);
    }

}
