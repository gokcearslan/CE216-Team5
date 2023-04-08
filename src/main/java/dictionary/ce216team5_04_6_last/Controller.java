package dictionary.ce216team5_04_6_last;
    import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

    public class Controller implements Initializable {

        @FXML
        private TableColumn<Language, String> column1 = new TableColumn<>();

        @FXML
        private TableColumn<Language, String> column2 = new TableColumn<>();

        @FXML
        private TableView<Language> table = new TableView<>();


        @FXML
        private TextField sourceLang;





        public void search() {
            String filePath = getClass().getResource("tur-eng.txt").getPath();
            Language language = new Language();
            System.out.println("search");

            // Load the words into the hashMap using threads
            language.loadWordsFromFile(filePath);

            // Get the translation for the input word from the hashMap
            String value = null;
            for (String word : language.getHashMap().keySet()) {
                if (word.equalsIgnoreCase(sourceLang.getText())) {
                    value = language.getHashMap().get(word);
                    break;
                }
            }

            // Create a new observable list with the translation and set it to the table
            if (value != null) {
                ObservableList<Language> list = FXCollections.observableArrayList(new Language(value));
                table.setItems(list);
            } else {
                table.getItems().clear();
            }
        }





        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

            column1.setCellValueFactory(new PropertyValueFactory<Language, String>("trgLang"));




        }
    }

