package edu.usc.projecttalent.cognitive.model;

import java.util.Date;

/**
 * Model for answer for retrofit.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class Answer {
    private Date start;
    private long user_ans;
    private boolean correct;
    private Date end;

    public Answer() {
        this.start = new Date();
    }

    public void endAnswer(long user_ans, boolean correct) {
        this.user_ans = user_ans;
        this.correct = correct;
        this.end = new Date();
    }
}
