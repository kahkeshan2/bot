
package ir.elenoon.db;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mohammad on 5/26/16.
 */
@Entity
@Table(name = "options", schema = "competition", catalog = "")
public class Options {

    private int option_id;
    private String text;
    private Date date;
    private boolean is_true_option;
    private Questions questions;
    private Series series;

    public Options(String text, Date date, boolean is_true_option, Questions questions, Series series) {
        this.text = text;
        this.date = date;
        this.is_true_option = is_true_option;
        this.questions = questions;
        this.series = series;

    }

    public Options() {

    }

    @Basic
    @Column(name = "is_true_option", nullable = false)
    public boolean getIs_true_option() {
        return is_true_option;
    }


    public Series getSeries() {
        return series;
    }


    public Questions getQuestions() {
        return questions;
    }


    @Id
    @Column(name = "option_id", nullable = false)
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public int getOption_id() {
        return option_id;
    }

//    @Basic
//    @ManyToOne(optional = false)
//    @JoinColumn(name = "questions", referencedColumnName = "id")
//    @Column(name = "question_id", nullable = false, length = 6)
//    public int getQuestion_id() {
//        return question_id;
//    }

    @Basic
    @Column(name = "text", nullable = false)
    public String getText() {
        return text;
    }

    @Basic
    @Column(name = "date", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDate() {
        return date;
    }

    public void setOption_id(int option_id) {
        this.option_id = option_id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setIs_true_option(boolean is_true_option) {
        this.is_true_option = is_true_option;
    }

    public void setQuestions(Questions questions) {
        this.questions = questions;
    }

    public void setSeries(Series series) {
        this.series = series;
    }
}


