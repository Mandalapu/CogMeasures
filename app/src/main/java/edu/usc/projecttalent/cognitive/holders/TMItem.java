package edu.usc.projecttalent.cognitive.holders;

import android.content.res.TypedArray;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import lombok.Data;

/**
 * Model for thurstone question.
 *
 * @author Anindya Dutta
 * @version 1.0
 */

@Data
public class TMItem extends Item {
    /**
     * a set of images for each question.
     */
    private TypedArray options;

    /**
     * Constructor to initialize the thurstone question with the images and correct answer.
     * @param options
     */
    @SuppressWarnings("ResourceType")
    public TMItem(TypedArray options) {
        this.options = options;
        this.ansPosition = options.getInt(4, -1);
    }

    /**
     * Add images to the image view via data binding.
     * @param view the view in which the image has to be attached.
     * @param imageUrl the image url that needs to be attached to the view.
     */
    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, int imageUrl) {
        view.setImageResource(imageUrl);
    }

}
