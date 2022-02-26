package Connectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Auth {
    private static final String dbName = "Auth"; 
    private static final String dbUsername = "root"; 
    private static final String dbPassword = ""; 
    private static final String dbURL= "jdbc:mysql://localhost/"+dbName;
    private static final String SELECT_QUERY = "SELECT * FROM users WHERE email = ? and password = ?";

    public boolean validate(String email, String password) throws SQLException{
        // Step 1: Establishing a Connection and 
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
            .getConnection(dbURL, dbUsername, dbPassword);

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return false;
    }
}
