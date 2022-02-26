import java.io.IOException;

import Connectivity.Auth;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class LoginController{
    //Fields from .fxml files
    @FXML
    private TextField email_field;
    @FXML
    private PasswordField password_field;
    @FXML
    private Button loginBtn;

    //Basic Scene Switching
    public void signupScene(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("./Scenes/Signup_Scene.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("./styles/styles.css").toExternalForm());

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    //Basic Scene Switching
    public void homeScene(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("./Scenes/Home_Scene.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("./styles/styles.css").toExternalForm());
        
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    //Basic Scene Switching
    public void logout(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("./Scenes/Login_Scene.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("./styles/styles.css").toExternalForm());
        
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    //signup functionality
    public void login(ActionEvent event) throws SQLException, IOException{
        //fetching textfields from .fxml files
        String email = email_field.getText();
        String password = password_field.getText();
        Window owner = loginBtn.getScene().getWindow();

        //Checking for Corner Cases
        if(email.isEmpty()){
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please Enter your email ID");
            return;
        }
        
        if(password.isEmpty()){
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please Enter your password");
            return;
        }  

        //Calling Auth Class
        Auth auth = new Auth();
        boolean flag = auth.validate(email, password);

        if (!flag) {
            infoBox("Please enter correct Email and Password", null, "Failed");
        } else {
            homeScene(event);
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
}
