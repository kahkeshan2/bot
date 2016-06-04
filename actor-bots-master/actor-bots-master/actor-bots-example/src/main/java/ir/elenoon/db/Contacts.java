package ir.elenoon.db;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by mohammad on 5/26/16.
 */
@Entity
@Table(name = "contacts", schema = "competition", catalog = "")
public class Contacts {

    private int id;
    private String username;
    private String standard_phone;
    private String name;

    public Contacts() {
    }

    public Contacts(String username, String standard_phone, String name) {
        this.username = username;
        this.standard_phone = standard_phone;
        this.name = name;
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public int getId() {
        return id;
    }


    @Basic
    @Column(name = "username", nullable = true, length = 50)
    public String getUsername() {
        return username;
    }


    @Basic
    @Column(name = "standard_phone", nullable = false, length = 20)
    public String getStandard_phone() {
        return standard_phone;
    }


    @Basic
    @Column(name = "name", nullable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStandard_phone(String standard_phone) {
        this.standard_phone = standard_phone;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(int id) {
        this.id = id;
    }
}
