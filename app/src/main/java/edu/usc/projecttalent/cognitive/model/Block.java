package edu.usc.projecttalent.cognitive.model;

import java.util.ArrayList;

/**
 * Model for block for retrofit.
 * @author Anindya Dutta
 * @version 2.0
 */

public class Block {
    private int number;
    private ArrayList<Answer> answers;
    private int score;

    public Block(int number){
        this.number = number;
        answers = new ArrayList<>();
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public void endBlock(int score) {
        this.score = score;
    }
}
