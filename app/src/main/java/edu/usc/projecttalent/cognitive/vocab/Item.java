package edu.usc.projecttalent.cognitive.vocab;

/**
 * JSON encapsulation for vocabulary question.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class Item {
    private String question;
    private String[] options;
    private int answer;

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

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
