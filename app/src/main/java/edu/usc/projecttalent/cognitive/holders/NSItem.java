package edu.usc.projecttalent.cognitive.holders;

import lombok.Data;

/**
 * Number section question JSON encapsulation.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

@Data
public class NSItem extends Item {
    /**
     *  The set of numbers for the question
     */
    private int[] options;
    /**
     * Answer options for the question
     */
    private int[] ansOptions;
    /**
     * Contains more than one number in case there are more than one blanks to fill in.
     */
    private int[] ansPositions;
    /**
     * The instructions for the question.
     */
    private String instr;
}
