package dictionary.ce216team5_04_6_last;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.File;
import java.io.InputStream;
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
        Language language = new Language();
        String sourceLang = (String) SourceCB.getValue();
        String targetLang = (String) TargetCB.getValue();
        if (sourceLang.equals("Source Language") || targetLang.equals("Target Language")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please select a source and target language");
            alert.showAndWait();
            return;
        }
        else if (sourceLang.equals(targetLang)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please select different source and target languages");
            alert.showAndWait();
            return;
        }
        else if (searchBox.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please enter a word to search");
            alert.showAndWait();
            return;
        }
        //If they add searchBox a number add alert
        else if (searchBox.getText().matches(".*\\d.*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please enter a word to search");
            alert.showAndWait();
            return;
        }
        else if (searchBox.getText().matches(".*\\W.*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please enter a word to search");
            alert.showAndWait();
            return;
        }
        else if (searchBox.getText().matches(".*\\s.*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please enter a word to search");
            alert.showAndWait();
            return;
        }
      //  String srcSource = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "dictionary" + File.separator + "ce216team5_04_6_last" + File.separator;
        String srcTxt = ".txt";
        String filePath=sourceLang + targetLang + srcTxt;
        InputStream absolutepath =getClass().getResourceAsStream(filePath);

        //File file = new File(filePath);

        if (absolutepath==null) {
            String srcTxt2 = "English.txt";
            String filePath2=sourceLang+srcTxt2;
            InputStream absolutepath2 =getClass().getResourceAsStream(filePath2);

            language.loadWordsFromFile(absolutepath2, StandardCharsets.UTF_8);
            List<String> values2 = null;
            for (String word : language.getHashMap().keySet()) {
                if (word.equalsIgnoreCase(searchBox.getText())) {
                    values2 = language.getHashMap().get(word);
                    break;
                }
            }
            String newKey = values2.get(0);
            String srcTxt3 = "English";

            String filePath3=srcTxt3+targetLang+srcTxt;
            InputStream absolutepath3=getClass().getResourceAsStream(filePath3);
            //String filePath3 = "src/resources/" + "English" + targetLang + ".txt";
            language.loadWordsFromFile(absolutepath3, StandardCharsets.UTF_8);
            List<String> values3 = null;
            for (String word : language.getHashMap().keySet()) {
                if (word.equalsIgnoreCase(newKey)) {
                    values3 = language.getHashMap().get(word);
                    break;
                }
            }
            setColumnName(targetLang);

            if (values3 != null && !values3.isEmpty()) {
                ObservableList<Language> list = FXCollections.observableArrayList();
                for (String value : values3) {
                    list.add(new Language(value));
                }
                sourceLangTable.setItems(list);
            } else {
                sourceLangTable.getItems().clear();
            }

        } else {

         //   System.out.println("search");

            language.loadWordsFromFile(absolutepath, StandardCharsets.UTF_8);


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
    }
    // Method to set the column name to the target language
    private void setColumnName (String targetLang){
        Translation.setText(targetLang);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Translation.setCellValueFactory(new PropertyValueFactory<Language, String>("trgLang"));
        SourceCB.getItems().addAll("English", "French", "German","Italian", "Greek", "Turkish","Swedish");
        TargetCB.getItems().addAll("English", "French", "German","Italian", "Greek", "Turkish","Swedish");
        SourceCB.setValue("Source");
        TargetCB.setValue("Target");

    }
}