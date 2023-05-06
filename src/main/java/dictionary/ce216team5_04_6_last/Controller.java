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

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {
    @FXML
    private TextField firstBox;

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

    Language language = new Language();

    static String staticWord;



    public void search(ActionEvent e) {



        language.getHashmapNames().get("");
        if (firstBox.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please enter a word to search");
            alert.showAndWait();
            return;
        }

        /*String searchWord = firstBox.getText();


        // Clear the SourceCB items
        SourceCB.getItems().clear();
        String hashMapName = language.getHashMapName(language.hashMapCollection, searchWord);
        System.out.println(hashMapName);

        SourceCB.getItems().add(hashMapName);


         */
        staticWord = firstBox.getText();
        passInformation(staticWord);



        try {
            switchToFullSearchPage(e);
            searchBox.setText(staticWord);

        } catch (IOException ex) {
            ex.printStackTrace();
        }


        // Iterate through all available HashMaps and find the source languages
        //List<String> sourceLanguages = language.getSourceLanguagesForWord(searchWord);
        //  System.out.println(sourceLanguages);
       /* for (String sourceLanguage : sourceLanguages) {
            if (!SourceCB.getItems().contains(sourceLanguage)) {
                SourceCB.getItems().add(sourceLanguage);

            }
        }

        */




    }
    static List<String> hashMapName;
    public void passInformation(String word){

        System.out.println("works?");
        if (word.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please enter a word to search");
            alert.showAndWait();
            return;
        }

        //searchBox.setText(word);
        String searchWord=word;

        // Clear the SourceCB items
        //SourceCB.getItems().clear();


        hashMapName = language.getHashMapName(language.hashMapCollection, searchWord);

        System.out.println(hashMapName + "?");



        System.out.println("var mı");
        HashMap<String, HashMap<String, List<String>>> hashMapCollection = language.getHashMapCollection();
        System.out.println("yok mı");
        for (String key : hashMapCollection.keySet()) {
            System.out.println(key);
        }



        // If no source languages found, show an error message


    }

    public void setSourceBoxes(){
        String searchWord= searchBox.getText();
        passInformation(searchWord);

        if(hashMapName!=null) {
            SourceCB.setItems(FXCollections.observableArrayList(hashMapName));
        }

    }
    public void search2() {
        //searchBox.setText(staticWord);
        System.out.println("search2");

        String chosenSource = SourceCB.getValue().toString();
        String chosenTarget = TargetCB.getValue().toString();
        String hashmapNameForSearch = chosenSource + chosenTarget;

        if (chosenSource == null || chosenTarget == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please select source and target languages");
            alert.showAndWait();
            return;
        }
        else if (chosenSource.equals("Source") || chosenTarget.equals("Target")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please select a source and target language");
            alert.showAndWait();
            return;
        }
        else if (chosenSource.equals(chosenTarget)) {
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

        System.out.println(hashmapNameForSearch);

        System.out.println("Selected source lang: " + chosenSource);

        Map<String, HashMap<String,List<String>>> tempHashMapCollection = language.getHashmapNames();

        HashMap<String, List<String>> desiredHashMap = tempHashMapCollection.get(hashmapNameForSearch);

        // Check if the desired HashMap is not null
        if (desiredHashMap != null) {
            List<String> values = null;

            for (String word : desiredHashMap.keySet()) {
                if (word.equalsIgnoreCase(searchBox.getText())) {
                    values = desiredHashMap.get(word);
                    break;
                }
            }

            // Set the column name to the target language
            setColumnName(chosenTarget);

            if (values != null && !values.isEmpty()) {
                ObservableList<Language> list = FXCollections.observableArrayList();
                for (String value : values) {
                    list.add(new Language(value));
                }
                sourceLangTable.setItems(list);
            } else {
                sourceLangTable.getItems().clear();
            }
        } else {
            System.out.println("Error: HashMap not found for " + hashmapNameForSearch);
        }
        searchBox.clear();
        SourceCB.getSelectionModel().clearSelection();

    }
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void switchToFullSearchPage(ActionEvent e) throws IOException {


        root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        // stage.setResizable(false);

    }
    public void switchToOpeningPage() {
        try {
            root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
            stage = (Stage) scene.getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // stage.setResizable(false);

    }


    // Method to update the translations in the TableView3
    private void updateTranslations () {
        String sourceLang = (String) SourceCB.getValue();
        String targetLang = (String) TargetCB.getValue();
        String searchWord = searchBox.getText();
        String hashmapName = sourceLang + targetLang;


        if (sourceLang != null && !sourceLang.equals("Source") && targetLang != null && !targetLang.equals("Target")) {
            Map<String, List<String>> dictionary = language.getHashMap();

            if (dictionary != null) {
                List<String> values = dictionary.get(searchWord);

                if (values != null && !values.isEmpty()) {
                    ObservableList<Language> list = FXCollections.observableArrayList();
                    for (String value : values) {
                        list.add(new Language(value, sourceLang));
                    }
                    sourceLangTable.setItems(list);

                    // Update the ChoiceBox with source languages for the search word
                       /* List<String> sourceLanguages = language.getHashMapName(searchWord);
                        SourceCB.getItems().clear();
                        SourceCB.getItems().addAll(sourceLanguages);
                        SourceCB.setValue(sourceLang);
                    }

                        else {

                    sourceLangTable.getItems().clear();
                }

                    */


                }
            }


        }
    }

    boolean isTextSet = false;
    public void setBox(){

        if (!isTextSet) {
            searchBox.setText(staticWord);
            isTextSet = true;
        }

    }


    // Method to set the column name to the target language
    private void setColumnName (String targetLang){
        Translation.setText(targetLang);
    }

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle){
        Translation.setCellValueFactory(new PropertyValueFactory<Language, String>("trgLang"));
        SourceCB.getItems().addAll();
        TargetCB.getItems().addAll("English", "French", "German", "Italian", "Greek", "Turkish", "Swedish");
        SourceCB.setValue("Source");
        TargetCB.setValue("Target");


        // Add a listener to update the translations when the source language is changed
        // SourceCB.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> updateTranslations());
        language.loadAllHashmaps();


        if(hashMapName!=null) {
            SourceCB.setItems(FXCollections.observableArrayList(hashMapName));
        }

        //SourceCB.getItems().setAll(hashMapName);

    }
}

