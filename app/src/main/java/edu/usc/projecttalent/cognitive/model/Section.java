package edu.usc.projecttalent.cognitive.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Model for section for retrofit. A set of sections make a survey.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class Section {
    /**
     * The name of the section: Vocabulary, Number section, Thurstone, etc.
     */
    private String name;
    /**
     * The timestamp when the user started this section.
     */
    private Date start;
    /**
     * A list of two blocks (except Thurstone and Reaction time which have one block).
     */
    private List<Block> blocks;
    /**
     * The timestamp when the user ended this section.
     */
    private Date end;

    /**
     * Initialize this section with the start time and parameterized section name.
     * @param name Name of the section.
     */
    public Section(String name) {
        this.name = name;
        this.start = new Date();
        blocks = new ArrayList<>();
    }

    /**
     * Add a new block to the section.
     * @param block block to be added.
     */
    public void addBlock(Block block) {
        blocks.add(block);
    }

    /**
     * @return the number of blocks in this section.
     */
    public int getBlockSize() {
        return blocks.size();
    }

    /**
     * End this section by updating the end time for this section.
     */
    public void endSection() {
        this.end = new Date();
    }

}
