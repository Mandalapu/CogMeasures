package edu.usc.projecttalent.cognitive;

/**
 * Item as a super-class to all item objects in the application.
 * Created by anind on 8/31/2017.
 */

public abstract class Item {
    protected int ansPosition;

    public int getAnsPosition() {
        return ansPosition;
    }

    public void setAnsPosition(int ansPosition) {
        this.ansPosition = ansPosition;
    }
}
