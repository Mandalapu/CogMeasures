package edu.usc.projecttalent.cognitive.holders;

/**
 * Encapsulates an item. Superclass for all subitems in holders package.
 */

public abstract class Item {
    /**
     * position in the array that is the answer.
     */
    protected int ansPosition;

    /**
     * @return the position of the answer.
     */
    public int getAnsPosition() {
        return ansPosition;
    }

    /**
     * @param ansPosition set the position of the answer.
     */
    public void setAnsPosition(int ansPosition) {
        this.ansPosition = ansPosition;
    }
}
