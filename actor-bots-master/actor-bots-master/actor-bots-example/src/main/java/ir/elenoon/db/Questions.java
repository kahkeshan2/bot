
package ir.elenoon.db;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mohammad on 5/26/16.
 */
@Entity
@Table(name = "questions", schema = "competition", catalog = "")
public class Questions {

    private int questions_id;
    private String text;
    private Date date;
    private Series series;


    public Questions() {
    }

    public Questions(String text, Date date, Series series) {
        this.text = text;
        this.date = date;
        this.series = series;
    }

    public Series getSeries() {
        return series;
    }


    @Id
    @Column(name = "question_id", nullable = false)
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public int getQuestions_id() {
        return questions_id;
    }

//    @Basic
//    @Column(name = "series_id", nullable = false, length = 6)
//    public int getSeries_id() {
//        return series_id;
//    }

    @Basic
    @Column(name = "text", nullable = false)
    public String getText() {
        return text;
    }

    @Basic
    @Column(name = "date", nullable = true)
    public Date getDate() {
        return date;
    }

    public void setQuestions_id(int questions_id) {
        this.questions_id = questions_id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

//    public void setSeries_id(int series_id) {
//        this.series_id = series_id;
//    }
}


