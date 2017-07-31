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
    private int userId;
    private Date start;
    private ArrayList<Section> sections;
    private Date end;

    private static Survey survey;

    public Survey() {
        start = new Date();
        sections = new ArrayList<>();
    }

    public void addSection(Section section) {
        survey.sections.add(section);
    }

    public void endSurvey() {
        survey.end = new Date();
    }

    public static Survey getSurvey() {
        if (survey == null)
            survey = new Survey();
        return survey;
    }

    public void setUser(int id) {
        survey.userId = id;
    }
}
