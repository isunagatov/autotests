package utils;


import java.sql.*;
import java.util.List;
import java.util.UUID;

public class DbHelper {

    public static Connection getConnection(ReadConfig.DataBase.DB dbBySubSystem) {
        List<ReadConfig.DataBase.DB> dbConfig = ReadConfig.DataBase.getListDB();
        String DbHost = dbConfig.get(0).getDatabaseHost();
        String DbUser = dbConfig.get(0).getDatabaseUser();
        String DbPass = dbConfig.get(0).getDatabasePass();
        String DbName = dbConfig.get(0).getDatabaseName();

        String DB_URL = "jdbc:postgresql://" + DbHost + "/" + DbName;
        //System.out.println("Testing connection to PostgreSQL JDBC");
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return null;
        }

        // System.out.println("PostgreSQL JDBC Driver successfully connected");
        Connection connection = null;

        try {
            connection = DriverManager
                    .getConnection(DB_URL, DbUser, DbPass);

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return null;
        }

        if (connection != null) {
            String result = "Success.DB connect";
            //       System.out.println("result);

        } else {
            String result = "Failed to make connection to database";
            System.out.println("Failed to make connection to database");

        }
        return connection;
    }


}
