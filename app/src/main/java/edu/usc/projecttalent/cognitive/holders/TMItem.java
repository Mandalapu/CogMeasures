package edu.usc.projecttalent.cognitive.holders;

import android.content.res.TypedArray;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

/**
 * Model for thurstone question.
 *
 * @author Anindya Dutta
 * @version 1.0
 */

public class TMItem extends Item {
    private TypedArray options;

    @SuppressWarnings("ResourceType")
    public TMItem(TypedArray options) {
        this.options = options;
        this.ansPosition = options.getInt(4, -1);
    }

    public TypedArray getOptions() {
        return options;
    }

    public void setOptions(TypedArray options) {
        this.options = options;
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, int imageUrl) {
        view.setImageResource(imageUrl);
    }

}
