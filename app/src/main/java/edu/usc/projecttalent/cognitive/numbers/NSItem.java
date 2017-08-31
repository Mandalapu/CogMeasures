package edu.usc.projecttalent.cognitive.numbers;

import edu.usc.projecttalent.cognitive.Item;

/**
 * Number section question JSON encapsulation.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class NSItem extends Item {
    private int[] options;
    private int[] ansOptions;
    private int[] ansPositions;
    private String instr;

    public int[] getOptions() {
        return options;
    }

    public void setOptions(int[] options) {
        this.options = options;
    }

    public int[] getAnsOptions() {
        return ansOptions;
    }

    public void setAnsOptions(int[] ansOptions) {
        this.ansOptions = ansOptions;
    }

    public int[] getAnsPositions() {
        return ansPositions;
    }

    public void setAnsPositions(int[] ansPositions) {
        this.ansPositions = ansPositions;
    }

    public String getInstr() {
        return instr;
    }

    public void setInstr(String instr) {
        this.instr = instr;
    }
}
