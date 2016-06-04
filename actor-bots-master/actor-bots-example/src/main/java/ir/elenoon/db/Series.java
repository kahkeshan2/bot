
package ir.elenoon.db;

import akka.http.javadsl.model.DateTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mohammad on 5/26/16.
 */
@Entity
@Table(name = "series", schema = "competition", catalog = "")
public class Series {

    private int series_id;
    private int seris_no;
    private Date expire_time;
    private Date start_time;
    private Date date;

    public Series() {
    }

    public Series(int seris_no, Date expireDate, Date startTime, Date date) {
        this.seris_no = seris_no;
        this.expire_time = expireDate;
        this.start_time = startTime;
        this.date = date;
    }

    @Id
    @Column(name = "series_id", nullable = false)
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public int getSeries_id() {
        return series_id;
    }


    @Basic
    @Column(name = "series_no", nullable = false, length = 10)
    public int getSeris_no() {
        return seris_no;
    }

    @Basic
    @Column(name = "expire_time", nullable = false)
    public Date getExpire_time() {
        return expire_time;
    }

    @Basic
    @Column(name = "start_time", nullable = false)
    public Date getStart_time() {
        return start_time;
    }


    @Basic
    @Column(name = "date", nullable = true, columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public void setExpire_time(Date expire_time) {
        this.expire_time = expire_time;
    }

    public void setSeris_no(int seris_no) {
        this.seris_no = seris_no;
    }

    public void setSeries_id(int series_id) {
        this.series_id = series_id;
    }


}


