package edu.usc.projecttalent.cognitive.holders;

import lombok.Data;

/**
 * Encapsulates an item. Superclass for all subitems in holders package.
 */

@Data
public abstract class Item {
    /**
     * position in the array that is the answer.
     */
    protected int ansPosition;
}
