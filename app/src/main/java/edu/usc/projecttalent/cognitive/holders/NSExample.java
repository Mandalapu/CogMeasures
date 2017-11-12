package edu.usc.projecttalent.cognitive.holders;

import java.io.Serializable;

/**
 * Number section example JSON encapsulation.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class NSExample implements Serializable {
    /**
     * The unique ID for the example.
     */
    private int id;
    /**
     * Question string; typically appears at the top of the question.
     */
    private String question;
    /**
     * A set of options for the question. These are the items amongst which one is blank.
     */
    private int[] options;
    /**
     * the position where the answer needs to be filled.
     */
    private int ansPosition;
    /**
     * The correct answer.
     */
    private String answerText;

    /**
     * @return the ID for the example.
     */
    public int getId() {
        return id;
    }

    /**
     * @param id sets the ID for the NS item.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the question string.
     */
    public String getQuestion() {
        return question;
    }

    /**
     *
     * @param question set the question for this item.
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * @return a set of options, typically all the numbers in the series.
     */
    public int[] getOptions() {
        return options;
    }

    /**
     * @param options set the numbers in the series.
     */
    public void setOptions(int[] options) {
        this.options = options;
    }

    /**
     * @return the position where the answer should be typed in.
     */
    public int getAnsPosition() {
        return ansPosition;
    }

    /**
     * @return the solution for the question.
     */
    public String getAnswerText() {
        return answerText;
    }
}
