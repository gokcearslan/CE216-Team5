package dictionary.ce216team5_04_6_last;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
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
    private ChoiceBox SourceCB = new ChoiceBox<>();
    @FXML
    private ChoiceBox TargetCB = new ChoiceBox<>();
    @FXML
    private ChoiceBox addSourceCB = new ChoiceBox<>();
    @FXML
    private ChoiceBox addTargetCB = new ChoiceBox<>();
    @FXML
    private TextField addSourceTxt;
    @FXML
    private TextField addTranslationTxt;

    @FXML
    private TableView<Language> addedTable = new TableView<>();

    @FXML
    private TableColumn<Language, String> addedWord = new TableColumn<>();

    @FXML
    private TableColumn<Language, String> addedTranslation = new TableColumn<>();

    @FXML
    private ChoiceBox editSrcCB = new ChoiceBox<>();
    @FXML
    private ChoiceBox editTargetCB = new ChoiceBox<>();

    @FXML
    private TextField wordTxtEdit;
    @FXML
    private TextField newWordTxtEdit;


    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToMainPage(ActionEvent e) throws IOException {

        root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToAddPage(ActionEvent e) throws IOException {

        root = FXMLLoader.load(getClass().getResource("add.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void search() {
        Language language = new Language();
        String sourceLang = (String) SourceCB.getValue();
        String targetLang = (String) TargetCB.getValue();
        if (sourceLang == null || targetLang == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please select source and target languages");
            alert.showAndWait();
            return;
        }
        else if (sourceLang.equals("Source") || targetLang.equals("Target")) {
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
      //String srcSource = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "dictionary" + File.separator + "ce216team5_04_6_last" + File.separator;
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
    public void addWord() {
        //PATH
        //arama için hashmap
        String sourceLangAdd = (String) addSourceCB.getValue();
        String targetLangAdd = (String) addTargetCB.getValue();

        String srcTxt = ".txt";
        String filePath = sourceLangAdd + targetLangAdd + srcTxt;
        //String path = "CE216-Team5/src/main/resources/dictionary/ce216team5_04_6_last/";
        String path = "C:\\Users\\pc\\IdeaProjects\\sample\\src\\sample\\";
        String lastFilePath = path + filePath;

        File file = new File(lastFilePath);
        InputStream inputStream = null;


        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Language languageAdd = new Language();
        languageAdd.loadWordsFromFile(inputStream, StandardCharsets.UTF_8);

        if (languageAdd.getHashMap().containsKey(addSourceTxt.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("The word already exists in the dictionary");
            alert.showAndWait();
        } else {
            try {
                FileWriter fw = new FileWriter(lastFilePath, true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(addSourceTxt.getText() + "//");
                bw.newLine();
                List<String>  addedTranslation = new ArrayList<>();
                addedTranslation.add(addTranslationTxt.getText());
                for (String item : addedTranslation) {
                    bw.write(item + "\n");
                }
                bw.close();

                // Create a new Word object with the source and translation
                Language newWord = new Language(addSourceTxt.getText(), addTranslationTxt.getText());

                // Add the new Word to the addedTable's ObservableList
                addedTable.getItems().add(newWord);

            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("ADDED");
        }
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