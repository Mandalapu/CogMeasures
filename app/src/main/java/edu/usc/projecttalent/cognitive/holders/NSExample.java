package edu.usc.projecttalent.cognitive.holders;

import java.io.Serializable;

import lombok.Data;

/**
 * Number section example JSON encapsulation.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

@Data
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
}
