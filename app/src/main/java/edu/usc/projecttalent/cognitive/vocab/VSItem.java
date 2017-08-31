package edu.usc.projecttalent.cognitive.vocab;

import edu.usc.projecttalent.cognitive.Item;

/**
 * JSON encapsulation for vocabulary question.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class VSItem extends Item {
    private String question;
    private String[] options;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }
}
