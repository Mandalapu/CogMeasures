package edu.usc.projecttalent.cognitive.numbers;

import java.io.Serializable;

/**
 * Number section example JSON encapsulation.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class Example implements Serializable {
    private int id;
    private String question;
    private int[] options;
    private int ansPosition;
    private String answerText;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int[] getOptions() {
        return options;
    }

    public void setOptions(int[] options) {
        this.options = options;
    }

    public int getAnsPosition() {
        return ansPosition;
    }

    public String getAnswerText() {
        return answerText;
    }
}
