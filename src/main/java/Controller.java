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
        String filePath = getClass().getResource("/deneme.txt").getPath();
        Language language = new Language();
        System.out.println("search");
        //file reader sourcelang,
        //  hashMap.put(sourceLang.getText(),);
        //trgtLang cevabı read file methodundan gelecek
        //sourceLang.getText()
        //read file and find trgtLang value
        language.loadWordsFromFile(filePath);

        //value aslında target language

        String value = language.getHashMap().get(sourceLang.getText());

        ObservableList<Language> list = FXCollections.observableArrayList(new Language(value));
        table.setItems(list);


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        column1.setCellValueFactory(new PropertyValueFactory<Language, String>("trgLang"));




    }
}
