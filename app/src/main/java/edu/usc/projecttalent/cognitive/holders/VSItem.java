package edu.usc.projecttalent.cognitive.holders;

import lombok.Data;

/**
 * JSON encapsulation for vocabulary question.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

@Data
public class VSItem extends Item {
    /**
     * The question for the vocabulary item/
     */
    private String question;
    /**
     * A set of synonyms for the questions.
     */
    private String[] options;
}
