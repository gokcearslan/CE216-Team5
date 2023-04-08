package dictionary.ce216team5_04_6_last;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        primaryStage.setTitle("CE216-TEAM5");
        primaryStage.setMinWidth(750);
        primaryStage.setMinHeight(600);
        primaryStage.setMaxWidth(900);
        primaryStage.setMaxHeight(800);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}