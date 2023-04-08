package dictionary.ce216team5_04_6_last;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Controller implements Initializable {

    @FXML
    private TableColumn<Language, String> Translation = new TableColumn<>();
    @FXML
    private TableView<Language> sourceLangTable = new TableView<>();
    @FXML
    private TextField searchBox;
    @FXML
    private ChoiceBox SourceCB=new ChoiceBox<>();
    @FXML
    private ChoiceBox TargetCB=new ChoiceBox<>();

    public void search() {
        String sourceLang= (String) SourceCB.getValue();
        String targetLang= (String) TargetCB.getValue();
        String srcSource = "src/main/resources/dictionary/ce216team5_04_6_last/";
        String srcTxt = ".txt";
        String filePath= srcSource +sourceLang+targetLang+srcTxt;

        Language language = new Language();
        System.out.println("search");

        language.loadWordsFromFile(filePath,StandardCharsets.UTF_8);


        List<String> values = null;

        for (String word : language.getHashMap().keySet()) {
            if (word.equalsIgnoreCase(searchBox.getText())) {
                values = language.getHashMap().get(word);
                break;
            }
        }

        // Set the column name to the target language
        setColumnName(targetLang);

        if (values != null && !values.isEmpty()) {
            ObservableList<Language> list = FXCollections.observableArrayList();
            for (String value : values) {
                list.add(new Language(value));
            }
            sourceLangTable.setItems(list);
        } else {
            sourceLangTable.getItems().clear();
        }
    }

    // Method to set the column name to the target language
    private void setColumnName(String targetLang) {
        Translation.setText(targetLang);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Translation.setCellValueFactory(new PropertyValueFactory<Language, String>("trgLang"));
        SourceCB.getItems().addAll("English", "French", "German","Italian", "Greek", "Turkish");
        TargetCB.getItems().addAll("English", "French", "German","Italian", "Greek", "Turkish");
    }
}