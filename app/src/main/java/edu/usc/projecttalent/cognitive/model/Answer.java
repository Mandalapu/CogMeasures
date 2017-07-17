package edu.usc.projecttalent.cognitive.model;

import java.util.Date;

/**
 * Created by anind on 5/22/2017.
 */

public class Answer {
    Date start;
    long user_ans;
    boolean correct;
    Date end;

    public Answer() {
        this.start = new Date();
    }

    public void endAnswer(long user_ans, boolean correct) {
        this.user_ans = user_ans;
        this.correct = correct;
        this.end = new Date();
    }

    public void endAnswer(long user_ans) {
        this.user_ans = user_ans;
        this.end = new Date();
    }
}
