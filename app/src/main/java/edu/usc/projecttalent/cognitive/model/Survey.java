package edu.usc.projecttalent.cognitive.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by anind on 5/22/2017.
 */

public class Survey {
    int userId;
    Date start;
    ArrayList<Section> sections;
    Date end;

    static Survey survey;

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
        if(survey == null)
            survey = new Survey();
        return survey;
    }

    public void setUser(int id) {
        survey.userId = id;
    }
}
