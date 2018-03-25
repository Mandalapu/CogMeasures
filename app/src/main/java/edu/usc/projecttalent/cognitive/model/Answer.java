package edu.usc.projecttalent.cognitive.model;

/**
 * Model for answer for retrofit. A set of answers make up a block.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class Answer {
    /**
     * The answer that the user selects.
     */
    private long ans;
    /**
     * Time in seconds user takes for this answer.
     */
    private long time;

    /**
     * Create a new answer: record the start time.
     */
    public Answer() {
        this.time = System.currentTimeMillis();
    }

    /**
     * End the answer. Record the end time, the answer and if it was correct.
     * @param ans the answer the user sent.
     */
    public void endAnswer(long ans) {
        this.ans = ans;
        this.time = (System.currentTimeMillis() - time) / 1000;
    }
}
