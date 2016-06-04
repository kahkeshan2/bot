
package ir.elenoon.db;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mohammad on 5/26/16.
 */
@Entity
@Table(name = "users_answers", schema = "competition", catalog = "")
public class UsersAnswers {

    private int users_answer_id;
    private Date date;

    private Contacts contacts;
    private Questions questions;
    private Options options;
    private Series series;

    public UsersAnswers(Date date, Contacts contacts, Questions questions, Options options, Series series) {
        this.date = date;
        this.contacts = contacts;
        this.questions = questions;
        this.options = options;
        this.series = series;
    }

    @Id
    @Column(name = "users_answer_id", nullable = false)
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public int getUsers_answer_id() {
        return users_answer_id;
    }

    @Basic
    @Column(name = "date", nullable = true)
    public Date getDate() {
        return date;
    }

    public Contacts getContacts() {
        return contacts;
    }


    public Questions getQuestions() {
        return questions;
    }


    public Options getOptions() {
        return options;
    }


    public Series getSeries() {
        return series;
    }


    //    @Basic
//    @Column(name = "user_id", nullable = false, length = 6)
//    public int getUser_id() {
//        return user_id;
//    }
//
//    @Basic
//    @Column(name = "option_id", nullable = false, length = 6)
//    public int getOption_id() {
//        return option_id;
//    }
//
//    @Basic
//    @Column(name = "question_id", nullable = false, length = 6)
//    public int getQuestion_id() {
//        return question_id;
//    }


    public void setUsers_answer_id(int users_answer_id) {
        this.users_answer_id = users_answer_id;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    public void setQuestions(Questions questions) {
        this.questions = questions;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setSeries(Series series) {
        this.series = series;
    }
}


