package edu.usc.projecttalent.cognitive.holders;

import android.content.res.TypedArray;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import lombok.Data;

/**
 * Encapsulates Abstract Reasoning questions.
 *
 * @author Anindya Dutta
 */

@Data
public class ARItem extends Item {
    /**
     * The instruction line for each question, typically the first line on the top.
     */
    private String instructions;
    /**
     * Contains a set of question drawable, five option drawables and the answer to the question on the sixth index.
     */
    private TypedArray options;
    /**
     * The detail s line for a question. Not available for all questions. Typically appears below the question image.
     */
    private String details;
    /**
     * Contains whether or not this item is an answer item. False if it is a question item.
     */
    private boolean answer;

    /**
     * Constructor for ARItem
     * @param instructions instruction for the question
     * @param options question and option drawables
     * @param details extra instructions to show below question
     * @param answer whether or not this is an answer item
     */
    @SuppressWarnings("ResourceType")
    public ARItem(String instructions, TypedArray options, String details, boolean answer) {
        this.instructions = instructions;
        this.options = options;
        this.details = details;
        this.answer = answer;
        this.ansPosition = answer ? options.getInt(6, -1) : -1;
    }

    /**
     * Smaller constructor for ARItem
     * @param options question and option drawables
     */
    @SuppressWarnings("ResourceType")
    public ARItem(TypedArray options) {
        this.options = options;
        this.ansPosition = options.getInt(6, -1);
    }

    /**
     * Set an image to the ImageView.
     * @param view the ImageView in which image needs to be displayed.
     * @param imageUrl the ID of the drawable.
     */
    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, int imageUrl) {
        view.setImageResource(imageUrl);
    }

    /**
     * @return True if this item is an answer item, else false.
     */
    public boolean isAnswer() {
        return answer;
    }
}
