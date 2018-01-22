package edu.usc.projecttalent.cognitive.model;

import java.util.Date;

/**
 * Model for answer for retrofit. A set of answers make up a block.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class Answer {
    /**
     * The timestamp when the user first sees the question.
     */
    // private Date start;
    /**
     * The answer that the user selects.
     */
    private long ans;
    /**
     * whether or not his answer was correct.
     */
    // private boolean correct;
    /**
     * The timestamp when the user clicks the Next button.
     */
    // private Date end;
    /**
     * Time in seconds user takes for this answer.
     */
    private long time;

    /**
     * Create a new answer: record the start time.
     */
    public Answer() {
        // this.start = new Date();
        this.time = System.currentTimeMillis();
    }

    /**
     * End the answer. Record the end time, the answer and if it was correct.
     * @param ans the answer the user sent.
     * @param correct whether or not it was correct.
     */
    public void endAnswer(long ans, boolean correct) {
        this.ans = ans;
        // this.correct = correct;
        // this.end = new Date();
        this.time = (System.currentTimeMillis() - time) / 1000;
    }
}
