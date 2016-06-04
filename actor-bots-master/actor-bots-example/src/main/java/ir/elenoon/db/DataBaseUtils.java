package ir.elenoon.db;

/**
 * Created by mohammad on 5/18/16.
 */
public class DataBaseUtils {
    private static DataBaseUtils instance;

    public static DataBaseUtils getInstance() {
        if (instance == null)
            instance = new DataBaseUtils();
        return instance;
    }
    public void insert(){

        String query ="INSERT INTO botMessages (username, name, phone, text, reg_date)" +
                "VALUES (value1, value2, value3,...)";
    }
}
