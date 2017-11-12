package edu.usc.projecttalent.cognitive.holders;

/**
 * Number section question JSON encapsulation.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

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

    /**
     * @return the set of options.
     */
    public int[] getOptions() {
        return options;
    }

    /**
     * @param options the different numbers which are the hints to fill in the answer.
     */
    public void setOptions(int[] options) {
        this.options = options;
    }

    /**
     * @return the answer options that have been entered by user.
     */
    public int[] getAnsOptions() {
        return ansOptions;
    }

    /**
     * @param ansOptions set the answer options chosen by the user.
     */
    public void setAnsOptions(int[] ansOptions) {
        this.ansOptions = ansOptions;
    }

    /**
     * For question 5.3 specifically which has 2 options.
     * @return the positions where the answer needs to be filled.
     */
    public int[] getAnsPositions() {
        return ansPositions;
    }

    /**
     * @param ansPositions set the positions where the blank needs to be added.
     */
    public void setAnsPositions(int[] ansPositions) {
        this.ansPositions = ansPositions;
    }

    /**
     * @return get the instructions for a question.
     */
    public String getInstr() {
        return instr;
    }

    /**
     * @param instr set the instructions for a question.
     */
    public void setInstr(String instr) {
        this.instr = instr;
    }
}
