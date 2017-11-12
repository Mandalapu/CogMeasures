package edu.usc.projecttalent.cognitive.holders;

/**
 * JSON encapsulation for vocabulary question.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class VSItem extends Item {
    /**
     * The question for the vocabulary item/
     */
    private String question;
    /**
     * A set of synonyms for the questions.
     */
    private String[] options;

    /**
     * @return the question word for this item.
     */
    public String getQuestion() {
        return question;
    }

    /**
     * @param question set the question word for this item.
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * @return the set of synonym options for this question.
     */
    public String[] getOptions() {
        return options;
    }

    /**
     * @param options set the options for this question.
     */
    public void setOptions(String[] options) {
        this.options = options;
    }
}
