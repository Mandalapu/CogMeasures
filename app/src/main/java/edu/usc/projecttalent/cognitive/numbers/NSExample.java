package edu.usc.projecttalent.cognitive.numbers;

import java.io.Serializable;

/**
 * Number section example JSON encapsulation.
 * @author Anindya Dutta
 * @version 2.0
 */

public class NSExample implements Serializable {
    public int id;
    public String question;
    public int[] options;
    public int ansPosition;
    public String answerText;
}
