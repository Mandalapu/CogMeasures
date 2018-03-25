package edu.usc.projecttalent.cognitive.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Model for survey for retrofit.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class Survey {
    /**
     * Timestamp for when the user began the survey.
     */
    private Date start;
    /**
     * A list of sections that the user answered in the survey.
     */
    private ArrayList<Section> sections;
    /**
     * Timestamp for when the user ended the survey.
     */
    private Date end;

    /**
     * Singleton class structure survey instance.
     */
    private static Survey survey;

    /**
     * Constructor for the survey. Initialize the start time for the survey.
     */
    private Survey() {
        start = new Date();
        sections = new ArrayList<>();
    }

    /**
     * Adds a new section to the survey.
     * @param section section to be added.
     */
    public void addSection(Section section) {
        survey.sections.add(section);
    }

    /**
     * Ends the survey by recording the end time.
     */
    public void endSurvey() {
        survey.end = new Date();
    }

    /**
     * The getInstance method for the singleton class.
     * @return the survey object.
     */
    public static Survey getSurvey() {
        if (survey == null) {
            survey = new Survey();

        }

        return survey;
    }

    public boolean isEmpty() {
        return survey.sections.isEmpty();
    }

    public static void setSurvey(Survey s) {
        survey = s;
    }
}
