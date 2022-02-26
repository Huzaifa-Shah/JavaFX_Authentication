package Connectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionClass {
    //Variables for Database Connectivity
    private static final String dbName = "Auth"; 
    private static final String dbUsername = "root"; 
    private static final String dbPassword = ""; 
    private static final String dbURL= "jdbc:mysql://localhost/"+dbName;
    private static final String SELECT_QUERY = "INSERT INTO users(email,password,username) VALUES(?,?,?)";

    public boolean signup(String email, String password, String username) throws SQLException{
        // Step 1: Establishing a Connection and 
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
            .getConnection(dbURL, dbUsername, dbPassword);

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, username);

            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
           e.printStackTrace();
        }
        return false;
    }
}
