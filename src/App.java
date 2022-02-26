import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        try {
            //Starting setup for login scenes and nodes
            Parent root = FXMLLoader.load(getClass().getResource("./Scenes/Login_Scene.fxml"));
            Scene scene = new Scene(root,1920,1080);
            scene.getStylesheets().add(getClass().getResource("./styles/styles.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
