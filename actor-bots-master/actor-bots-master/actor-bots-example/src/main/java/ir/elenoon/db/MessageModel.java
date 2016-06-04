package ir.elenoon.db;

import java.util.Date;

/**
 * Created by mohammad on 5/18/16.
 */
public class MessageModel {

    private static MessageModel instance;
    String username;
    String name;
    String phone;
    String text;
    Date date;

    public static MessageModel getInstance() {
        if (instance == null)
            instance = new MessageModel();
        return instance;
    }

    public void set(String username, String name, String phone, String text, Date date) {

        setUsername(username);
        setName(name);
        setPhone(phone);
        setText(text);
        setDate(date);

        MessageModel model = new MessageModel();
        model.setUsername(username);
        model.setName(name);
        model.setPhone(phone);
        model.setText(text);
        model.setDate(date);


        JDBCExample jdbcExample = new JDBCExample();
        jdbcExample.insert(model);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
