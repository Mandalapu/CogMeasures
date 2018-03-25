package edu.usc.projecttalent.cognitive.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Model for block for retrofit. A set of blocks make up a section.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class Block {
    /**
     * A list of user answers for the block. The length of the list is usually 3.
     */
    private List<Answer> answers;
    /**
     * Score for a particular block.
     */
    private int score;

    public Block() {
        answers = new ArrayList<>();
    }

    /**
     * Adds a user answer to the block.
     * @param answer the user answer.
     */
    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    /**
     * Ends the block by adding the score to the block.
     * @param score the user score.
     */
    public void endBlock(int score) {
        this.score = score;
    }
}
