package ir.elenoon.models;

import ir.elenoon.db.Options;
import ir.elenoon.db.Questions;
import ir.elenoon.db.Series;

import java.util.List;

/**
 * Created by mohammad on 6/3/16.
 */
public class QuestionsModel {

    private Questions question;
    private List<Options> optionses;
    private Series series;
    public QuestionsModel(Series series, Questions question, List<Options> optionses) {
        this.question = question;
        this.optionses = optionses;
        this.series = series;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public Questions getQuestion() {
        return question;
    }

    public void setQuestion(Questions question) {
        this.question = question;
    }

    public List<Options> getOptionses() {
        return optionses;
    }

    public void setOptionses(List<Options> optionses) {
        this.optionses = optionses;
    }
}
