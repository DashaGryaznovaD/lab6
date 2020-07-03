import CommandsProcessing.Commands;
import PersonData.AllException;
import CommandsProcessing.Receiver;
import java.io.IOException;
import java.sql.*;

public class Server {

    public static void main(String args[]) throws  IOException, AllException, SQLException {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return;
        }
        System.out.println("PostgreSQL JDBC Driver successfully connected");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(Receiver.URL, Receiver.USERNAME, Receiver.PASSWORD);
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }
        if (connection != null) {
            System.out.println("You successfully connected to database now");
        } else {
            System.out.println("Failed to make connection to database");
        }
        Statement statement = connection.createStatement();
        String sqlNewTable = "CREATE TABLE users1 " +
                "(login VARCHAR(50), " +
                "password VARCHAR(50));";
        String sqlCommand = "CREATE TABLE PersonLists1 " +
                "(id  SERIAL PRIMARY KEY NOT NULL, " +
                "creationDate DATE, " +
                "name VARCHAR(50), " +
                "height FLOAT, " +
                "key FLOAT, " +
                "color VARCHAR(50), " +
                "color1 VARCHAR(50), " +
                "country VARCHAR(50), " +
                "x FLOAT, " +
                "y FLOAT, " +
                "locationX INTEGER, " +
                "locationY INTEGER, " +
                "locationZ INTEGER, " +
                "login VARCHAR(50), " +
                "password VARCHAR(50));";
        String delete = "DROP TABLE PersonLists1;";
        String delete1 = "DROP TABLE users1;";
        //statement.executeUpdate(sqlNewTable);
        //statement.executeUpdate(delete);
        //statement.executeUpdate(sqlCommand);
        Commands commands = new Commands();
        commands.loadDB();
        new Receiver();
    }}
