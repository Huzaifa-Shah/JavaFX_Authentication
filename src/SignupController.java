import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

import Connectivity.ConnectionClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.Window;

public class SignupController{
    //Fields from .fxml files
    @FXML
    private TextField username_field, email_field;
    @FXML
    private PasswordField password_field;
    @FXML
    private Button signupBtn;

    //Basic Scene Switching
    public void loginScene(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("./Scenes/Login_Scene.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("./styles/styles.css").toExternalForm());

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    //signup functionality
    public void signup(ActionEvent event) throws SQLException, IOException{
        //fetching textfields from .fxml files
        String email = email_field.getText();
        String password = password_field.getText();
        String username = username_field.getText();
        Window owner = signupBtn.getScene().getWindow();
        String regexPattern = "^(.+)@(\\S+)$";

        //Checking for Corner Cases
        if(username.isEmpty()){
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please Enter your username");
            return;
        }
        if(!isValidEmail(email, regexPattern)){
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please Enter Valid Email");
            if(email.isEmpty()){
                showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please Enter your email ID");
            }
            return;
        }
        if(password.length() <= 5){
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Password length should be greater then 5");
            if(password.isEmpty()){
                showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please Enter your password");
            }
            return;
        }
        
        //Calling connectivity Class
        ConnectionClass connectionClass = new ConnectionClass();
        boolean flag = connectionClass.signup(email, password, username);

        if (!flag) {
            infoBox("Database Connection Error", null, "Failed");
        } else {
            loginScene(event);
        }
    }

    //Helper functions for corner cases
    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    //Checking if the format of the email is valid
    public static boolean isValidEmail(String emailAddress, String regexPattern){
        return Pattern.compile(regexPattern).matcher(emailAddress).matches();
    }
}