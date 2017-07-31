package edu.usc.projecttalent.cognitive.numbers;

/**
 * Number section question JSON encapsulation.
 * @author Anindya Dutta
 * @version 2.0
 */

public class NSQuestion {
    public int[] options;
    public int ansPosition;
    int[] ansOptions;
    public int[] ansPositions;
    public String instr;

    void setInstr(String instr) {
        this.instr = instr;
    }
}
