package edu.usc.projecttalent.cognitive.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Model for section for retrofit.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class Section {
    private String name;
    private Date start;
    private ArrayList<Block> blocks;
    private Date end;

    public Section(String name) {
        this.name = name;
        this.start = new Date();
        blocks = new ArrayList<>();
    }

    public void addBlock(Block block) {
        blocks.add(block);
    }

    public int getBlockSize() {
        return blocks.size();
    }

    public void endSection() {
        this.end = new Date();
    }

}
