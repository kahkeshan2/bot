package ir.elenoon.db;//STEP 1. Import required packages

import java.sql.*;

public class JDBCExample {

    private static JDBCExample instance;

    public static JDBCExample getInstance() {
        if (instance == null)
            instance = new JDBCExample();
        return instance;
    }

    String insertField=  "(username, name, phone, text)";
    String tableName = "botMessages";
    String dataBase = "actor_bot";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "mojahed";
    // JDBC driver name and database URL
    final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";


//    final String DB_URL = "jdbc:mysql://172.31.1.3/" + dataBase + "?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
    final String DB_URL = "jdbc:mysql://127.0.0.1/" + dataBase + "?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";

    public void insert(MessageModel model) {
        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Inserting records into the table...");
            stmt = conn.createStatement();

//            String sql = "INSERT INTO Registration " +
//                    "VALUES (100, 'Zara', 'Ali', 18)";
//            stmt.executeUpdate(sql);
//            sql = "INSERT INTO Registration " +
//                    "VALUES (101, 'Mahnaz', 'Fatma', 25)";
//            stmt.executeUpdate(sql);
//            sql = "INSERT INTO Registration " +
//                    "VALUES (102, 'Zaid', 'Khan', 30)";
//            stmt.executeUpdate(sql);
//            sql = "INSERT INTO Registration " +
//                    "VALUES(103, 'Sumit', 'Mittal', 28)";

//            String query = "INSERT INTO " + tableName +
//                    " VALUES (" + model.getUsername() + "," + model.getName() + "," + model.getPhone() + "," + model.getText() + ")"
//            +" (username, name, phone, text) " ;

//            String query = "INSERT INTO " + tableName + " (username, name, phone, text) " +
//                    "VALUES ( " ""+ model.getUsername() + "," + model.getName() + "," + model.getPhone() + "," + model.getText() + ");";



            String sql = "insert into " + tableName + " " + insertField+ " values (" + createQuestionsForInsert() + ")";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, model.getUsername());
            preparedStatement.setString(2, model.getName());
            preparedStatement.setString(3, model.getPhone());
            preparedStatement.setString(4, model.getText());
            preparedStatement.executeUpdate();


//            stmt.executeUpdate(a);
            System.out.println("Inserted records into the table...");

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end main

    private String createQuestionsForInsert() {
        StringBuilder result = new StringBuilder("?");
        int count = insertField.split(",").length;

        for(int i = 1; i < count; ++i) {
            result.append(",?");
        }

        return result.toString();
    }

}//end JDBCExample