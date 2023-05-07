package dictionary.ce216team5_04_6_last;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
        primaryStage.setTitle("The Offline Dictionary");
        primaryStage.setMinWidth(750);
        primaryStage.setMinHeight(600);
        primaryStage.setMaxWidth(900);
        primaryStage.setMaxHeight(800);

        primaryStage.setResizable(true);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}