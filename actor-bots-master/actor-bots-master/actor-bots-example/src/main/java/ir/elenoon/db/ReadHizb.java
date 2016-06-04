package ir.elenoon.db;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by mohammad on 5/26/16.
 */
public class ReadHizb {

    private int id;
    private Integer hizb_number;
    private String standard_phone;
    private Integer read_state;
    private Integer quran_count;
    private Timestamp time_stamp;
    public ReadHizb() {}
    public ReadHizb(Integer hizb_number, String standard_phone, Integer read_state, Integer quran_count, Timestamp time_stamp) {
        this.hizb_number = hizb_number;
        this.standard_phone = standard_phone;
        this.read_state = read_state;
        this.quran_count = quran_count;
        this.time_stamp = time_stamp;

    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator="increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Basic
    @Column(name = "hizb_number", nullable = false)
    public Integer getHizb_number() {
        return hizb_number;
    }

    public void setHizb_number(Integer hizb_number) {
        this.hizb_number = hizb_number;
    }

    @Basic
    @Column(name = "standard_phone", nullable = false, length = 20)
    public String getStandard_phone() {
        return standard_phone;
    }

    public void setStandard_phone(String standard_phone) {
        this.standard_phone = standard_phone;
    }

    @Basic
    @Column(name = "read_state", nullable = true)
    public Integer getRead_state() {
        return read_state;
    }

    public void setRead_state(Integer read_state) {
        this.read_state = read_state;
    }

    @Basic
    @Column(name = "quran_count", nullable = true)
    public Integer getQuran_count() {
        return quran_count;
    }

    public void setQuran_count(Integer quran_count) {
        this.quran_count = quran_count;
    }

    @Basic
    @Column(name = "time_stamp", nullable = false)
    public Timestamp getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(Timestamp time_stamp) {
        this.time_stamp = time_stamp;
    }
}
