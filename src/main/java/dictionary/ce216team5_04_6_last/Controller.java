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
import java.util.stream.Collectors;

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

    @FXML
    private ChoiceBox addTargetCB = new ChoiceBox<>();
    @FXML
    private TextField addSourceTxt;
    @FXML
    private TextField addTranslationTxt;
    @FXML
    private TextField OldTranslationTxtEdit;
    @FXML
    private TextField NewTranslationEditTxt;

    @FXML
    private ChoiceBox addSourceCB = new ChoiceBox<>();


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

    @FXML
    private Tab editPane;
    @FXML
    private Tab searchPane;

    @FXML
    private Tab addPane;


    @FXML
    private TabPane TabPane;
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

    Language language = new Language();

    static String staticWord;

    public void goToSearchPane(){
        TabPane.getSelectionModel().select(searchPane);
    }
    public void goToEditPane(){
        TabPane.getSelectionModel().select(editPane);
    }
    public void goToAddPane(){
        TabPane.getSelectionModel().select(addPane);
    }

    public void addWord() {
        String sourceLangAdd = (String) addSourceCB.getValue();
        String targetLangAdd = (String) addTargetCB.getValue();
        String hashmapAdd = sourceLangAdd + targetLangAdd;
        HashMap<String, List<String>> dictionary = language.getHashmapNames().get(hashmapAdd);

        String srcTxt = ".txt";
        String filePath = sourceLangAdd + targetLangAdd + srcTxt;
        String projectResourcesPath = "src/main/resources";
        String dictionariesFolder = "dictionary/ce216team5_04_6_last";
        File dictionaryFolderFile = new File(projectResourcesPath, dictionariesFolder);
        if (!dictionaryFolderFile.exists()) {
            dictionaryFolderFile.mkdirs();
        }

        String outputFilePath = new File(dictionaryFolderFile, filePath).getAbsolutePath();

        try (InputStream inputStream = new FileInputStream(outputFilePath)) {
            Language.loadWordsFromFile(dictionary, inputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String newWord = addSourceTxt.getText();
        String newTranslation = addTranslationTxt.getText();

        if (!language.getHashmapNames().get(hashmapAdd).containsKey(newWord)) {
            List<String> translations = new ArrayList<>();
            translations.add(newTranslation);
            language.getHashmapNames().get(hashmapAdd).put(newWord, translations);

            // Write the updated hash map to the file
            try {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(outputFilePath), StandardCharsets.UTF_8));

                for (Map.Entry<String, List<String>> entry : language.getHashmapNames().get(hashmapAdd).entrySet()) {
                    writer.write(entry.getKey() + "//" + "\n");
                    for (String element : entry.getValue()) {
                        writer.write(element + "\n");
                    }
                }

                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Word added.");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("The word was not found in the dictionary!");
            alert.showAndWait();
        }
    }

    public void addTranslation() {
        String sourceLangAdd = (String) editSrcCB.getValue();
        String targetLangAdd = (String) editTargetCB.getValue();
        String hashmapAdd=sourceLangAdd+targetLangAdd;
        HashMap<String, List<String>> dictionary  = language.getHashmapNames().get(hashmapAdd);

        String srcTxt = ".txt";
        String filePath = sourceLangAdd + targetLangAdd + srcTxt;
        String projectResourcesPath = "src/main/resources";
        String dictionariesFolder = "dictionary/ce216team5_04_6_last";
        File dictionaryFolderFile = new File(projectResourcesPath, dictionariesFolder);
        if (!dictionaryFolderFile.exists()) {
            dictionaryFolderFile.mkdirs();
        }

        String outputFilePath = new File(dictionaryFolderFile, filePath).getAbsolutePath();

        try (InputStream inputStream = new FileInputStream(outputFilePath)) {
            Language.loadWordsFromFile(dictionary, inputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String currentWord = wordTxtEdit.getText();
        String newTranslation = OldTranslationTxtEdit.getText();

        if (language.getHashmapNames().get(hashmapAdd).containsKey(currentWord)) {
            List<String> translations = language.getHashmapNames().get(hashmapAdd).get(currentWord);
            List<String> newTranslations = new ArrayList<>();
            newTranslations.addAll(translations);
            newTranslations.add(newTranslation);
            language.getHashmapNames().get(hashmapAdd).remove(currentWord, translations);
            language.getHashmapNames().get(hashmapAdd).put(currentWord,newTranslations);

            // Write the updated hash map to the file
            try {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(outputFilePath), StandardCharsets.UTF_8));

                for (Map.Entry<String, List<String>> entry : language.getHashmapNames().get(hashmapAdd).entrySet()) {
                    writer.write(entry.getKey() + "//" + "\n");
                    for (String element : entry.getValue()) {
                        writer.write(element + "\n");
                    }
                }

                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Translation added.");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("The word was not found in the dictionary!");
            alert.showAndWait();
        }
    }
    public void deleteWord(){
        String sourceLangAdd = (String) editSrcCB.getValue();
        String targetLangAdd = (String) editTargetCB.getValue();
        String hashmapAdd=sourceLangAdd+targetLangAdd;
        HashMap<String, List<String>> dictionary  = language.getHashmapNames().get(hashmapAdd);

        String srcTxt = ".txt";
        String filePath = sourceLangAdd + targetLangAdd + srcTxt;
        String projectResourcesPath = "src/main/resources";
        String dictionariesFolder = "dictionary/ce216team5_04_6_last";
        File dictionaryFolderFile = new File(projectResourcesPath, dictionariesFolder);
        if (!dictionaryFolderFile.exists()) {
            dictionaryFolderFile.mkdirs();
        }

        String outputFilePath = new File(dictionaryFolderFile, filePath).getAbsolutePath();

        try (InputStream inputStream = new FileInputStream(outputFilePath)) {
            Language.loadWordsFromFile(dictionary, inputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String currentWord = wordTxtEdit.getText();
        if (language.getHashmapNames().get(hashmapAdd).containsKey(currentWord)) {
            language.getHashmapNames().get(hashmapAdd).remove(currentWord);

            // Write the updated hash map to the file
            try {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(outputFilePath), StandardCharsets.UTF_8));

                for (Map.Entry<String, List<String>> entry :  language.getHashmapNames().get(hashmapAdd).entrySet()) {
                    writer.write(entry.getKey() + "//" + "\n");
                    for (String element : entry.getValue()) {
                        writer.write(element + "\n");
                    }

                }

                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Word deleted.");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("The word was not found in the dictionary!");
            alert.showAndWait();
        }
    }



    public void search(ActionEvent e) {

        language.getHashmapNames().get("");
        if (firstBox.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please enter a word to search");
            alert.showAndWait();
            return;
        }

        staticWord = firstBox.getText();
        passInformation(staticWord);



        try {
            switchToFullSearchPage(e);
            searchBox.setText(staticWord);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
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

        setColumnName(chosenTarget);

        System.out.println(hashmapNameForSearch);

        System.out.println("Selected source lang: " + chosenSource);

        Map<String, HashMap<String,List<String>>> tempHashMapCollection = language.getHashmapNames();

        HashMap<String, List<String>> desiredHashMap = tempHashMapCollection.get(hashmapNameForSearch);

        List<String> bridgeLanguages = Arrays.asList("English", "German", "French", "Italian","Greek","Swedish","Turkish");

        // If the desiredHashMap is not found, try using bridge languages
        if (desiredHashMap == null) {
            for (String bridgeLang : bridgeLanguages) {
                String srcToBridgeKey = chosenSource + bridgeLang;
                String bridgeToTargetKey = bridgeLang + chosenTarget;

                HashMap<String, List<String>> srcToBridgeMap = tempHashMapCollection.get(srcToBridgeKey);
                HashMap<String, List<String>> bridgeToTargetMap = tempHashMapCollection.get(bridgeToTargetKey);

                if (srcToBridgeMap != null && bridgeToTargetMap != null) {
                    String searchWord = searchBox.getText();
                    List<String> bridgeWords = srcToBridgeMap.get(searchWord);

                    if (bridgeWords != null && !bridgeWords.isEmpty()) {
                        List<String> targetWords = new ArrayList<>();
                        String firstBridgeWord = bridgeWords.get(0);

                        String[] parts = firstBridgeWord.split(",\s");
                        String X = parts[0].replaceAll("\\d+\\.\s", "");

                        // Search for the value of X in the bridgeToTargetMap
                        List<String> tempList = bridgeToTargetMap.get(X);

                        if (tempList != null) {
                            targetWords.addAll(tempList);
                        }

                        ObservableList<Language> list2 = FXCollections.observableArrayList();
                        for (String targetWord : targetWords) {
                            list2.add(new Language(targetWord));
                        }

                        for (String targetWord : targetWords) {
                            System.out.println("target:" + targetWord);
                        }

                        sourceLangTable.setItems(list2);
                        break;
                    }
                }
            }
        }

        if (desiredHashMap != null) {
            List<String> values = null;

            for (String word : desiredHashMap.keySet()) {
                if (word.equalsIgnoreCase(searchBox.getText())) {
                    values = desiredHashMap.get(word);
                    break;
                }
            }

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

    public void findSynonyms() {
        String searchWord = searchBox.getText();
        String chosenSource = SourceCB.getValue().toString();
        String chosenTarget = TargetCB.getValue().toString();

        String hashmapNameForSearch = chosenSource + chosenTarget;
        Map<String, HashMap<String,List<String>>> tempHashMapCollection = language.getHashmapNames();
        HashMap<String, List<String>> desiredHashMap = tempHashMapCollection.get(hashmapNameForSearch);

        if (desiredHashMap != null) {
            List<String> targetTranslations = desiredHashMap.get(searchWord);

            if (targetTranslations != null && !targetTranslations.isEmpty()) {
                String targetTranslationsLine = targetTranslations.get(0);

                // Split and clean the translation line to get the first word
                String[] parts = targetTranslationsLine.split(",\s");
                String targetWord = parts[0].replaceAll("\\d+\\.\s", "");

                // searching for synonyms using the reverse HashMap
                String reverseHashmapName = chosenTarget + chosenSource;
                HashMap<String, List<String>> reverseHashMap = tempHashMapCollection.get(reverseHashmapName);

                if (reverseHashMap != null) {
                    List<String> synonyms = reverseHashMap.get(targetWord);

                    if (synonyms != null && !synonyms.isEmpty()) {
                        List<String> filteredSynonyms = synonyms.stream()
                                .filter(s -> s.contains(","))
                                .collect(Collectors.toList());

                        String firstSynonymsLine = filteredSynonyms.isEmpty() ? null : filteredSynonyms.get(0);

                        if (firstSynonymsLine != null) {
                            String[] synonymParts = firstSynonymsLine.split(",\s*");

                            ObservableList<Language> list = FXCollections.observableArrayList();
                            for (String synonym : synonymParts) {
                                list.add(new Language(synonym));
                            }
                            sourceLangTable.setItems(list);
                        } else {
                            System.out.println("No comma-separated synonyms found");
                            sourceLangTable.getItems().clear();
                        }
                    } else {
                        System.out.println("No synonyms found");
                        sourceLangTable.getItems().clear();
                    }
                } else {
                    System.out.println("Error: Reverse HashMap not found for " + reverseHashmapName);
                }
            } else {
                System.out.println("No translations found for the search word");
            }
        } else {
            System.out.println("Error: HashMap not found for " + hashmapNameForSearch);
        }
    }

    public void switchToFullSearchPage(ActionEvent e) throws IOException {


        root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        // stage.setResizable(false);

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

    public void editTranslation(){
        String sourceLangAdd = (String) editSrcCB.getValue();
        String targetLangAdd = (String) editTargetCB.getValue();
        String hashmapAdd=sourceLangAdd+targetLangAdd;
        HashMap<String, List<String>> dictionary  = language.getHashmapNames().get(hashmapAdd);

        String srcTxt = ".txt";
        String filePath = sourceLangAdd + targetLangAdd + srcTxt;
        String projectResourcesPath = "src/main/resources";
        String dictionariesFolder = "dictionary/ce216team5_04_6_last";
        File dictionaryFolderFile = new File(projectResourcesPath, dictionariesFolder);
        if (!dictionaryFolderFile.exists()) {
            dictionaryFolderFile.mkdirs();
        }

        String outputFilePath = new File(dictionaryFolderFile, filePath).getAbsolutePath();

        try (InputStream inputStream = new FileInputStream(outputFilePath)) {
            Language.loadWordsFromFile(dictionary, inputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String currentWord = wordTxtEdit.getText();
        String translation = OldTranslationTxtEdit.getText();
        String newTranslation = NewTranslationEditTxt.getText();

        if (language.getHashmapNames().get(hashmapAdd).containsKey(currentWord)) {
            List<String> translations = language.getHashmapNames().get(hashmapAdd).get(currentWord);
            List<String> newTranslations = new ArrayList<>();
            newTranslations.addAll(translations);
            int index = newTranslations.indexOf(translation);
            if (index != -1) {
                newTranslations.set(index, newTranslation);
            }
            language.getHashmapNames().get(hashmapAdd).remove(currentWord, translations);
            language.getHashmapNames().get(hashmapAdd).put(currentWord,newTranslations);

            // Write the updated hash map to the file
            try {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(outputFilePath), StandardCharsets.UTF_8));

                for (Map.Entry<String, List<String>> entry : language.getHashmapNames().get(hashmapAdd).entrySet()) {
                    writer.write(entry.getKey() + "//" + "\n");
                    for (String element : entry.getValue()) {
                        writer.write(element + "\n");
                    }

                }

                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Translation edited.");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("The word was not found in the dictionary!");
            alert.showAndWait();
        }

    }

    public void editWord() {
        String sourceLangAdd = (String) editSrcCB.getValue();
        String targetLangAdd = (String) editTargetCB.getValue();
        String hashmapAdd=sourceLangAdd+targetLangAdd;
        HashMap<String, List<String>> dictionary  = language.getHashmapNames().get(hashmapAdd);

        String srcTxt = ".txt";
        String filePath = sourceLangAdd + targetLangAdd + srcTxt;
        String projectResourcesPath = "src/main/resources";
        String dictionariesFolder = "dictionary/ce216team5_04_6_last";
        File dictionaryFolderFile = new File(projectResourcesPath, dictionariesFolder);
        if (!dictionaryFolderFile.exists()) {
            dictionaryFolderFile.mkdirs();
        }

        String outputFilePath = new File(dictionaryFolderFile, filePath).getAbsolutePath();

        try (InputStream inputStream = new FileInputStream(outputFilePath)) {
            Language.loadWordsFromFile(dictionary, inputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String currentWord = wordTxtEdit.getText();
        String newWord = newWordTxtEdit.getText();

        if (language.getHashmapNames().get(hashmapAdd).containsKey(currentWord)) {
            List<String> translations = language.getHashmapNames().get(hashmapAdd).get(currentWord);
            String translation = translations != null && !translations.isEmpty() ? translations.get(0) : null;
            if (translation != null) {
                language.getHashmapNames().get(hashmapAdd).remove(currentWord);
                List<String> newTranslations = new ArrayList<>();
                newTranslations.add(translation);
                language.getHashmapNames().get(hashmapAdd).put(newWord, newTranslations);
            }

            // Write the updated hash map to the file
            try {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(outputFilePath), StandardCharsets.UTF_8));

                for (Map.Entry<String, List<String>> entry :language.getHashmapNames().get(hashmapAdd).entrySet()) {
                    writer.write(entry.getKey() + "//" + "\n");
                    for (String element : entry.getValue()) {
                        writer.write(element + "\n");
                    }

                }

                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Word edited.");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("The word was not found in the dictionary!");
            alert.showAndWait();
        }
    }

    public void deleteTranslation(){
        String sourceLangAdd = (String) editSrcCB.getValue();
        String targetLangAdd = (String) editTargetCB.getValue();
        String hashmapAdd=sourceLangAdd+targetLangAdd;
        HashMap<String, List<String>> dictionary  = language.getHashmapNames().get(hashmapAdd);

        String srcTxt = ".txt";
        String filePath = sourceLangAdd + targetLangAdd + srcTxt;
        String projectResourcesPath = "src/main/resources";
        String dictionariesFolder = "dictionary/ce216team5_04_6_last";
        File dictionaryFolderFile = new File(projectResourcesPath, dictionariesFolder);
        if (!dictionaryFolderFile.exists()) {
            dictionaryFolderFile.mkdirs();
        }

        String outputFilePath = new File(dictionaryFolderFile, filePath).getAbsolutePath();

        try (InputStream inputStream = new FileInputStream(outputFilePath)) {
            Language.loadWordsFromFile(dictionary, inputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String currentWord = wordTxtEdit.getText();
        String translation = OldTranslationTxtEdit.getText();

        if (language.getHashmapNames().get(hashmapAdd).containsKey(currentWord)) {
            List<String> translations = language.getHashmapNames().get(hashmapAdd).get(currentWord);
            List<String> newTranslations = new ArrayList<>();
            newTranslations.addAll(translations);
            newTranslations.remove(translation);

            language.getHashmapNames().get(hashmapAdd).remove(currentWord, translations);
            language.getHashmapNames().get(hashmapAdd).put(currentWord,newTranslations);

            // Write the updated hash map to the file
            try {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(outputFilePath), StandardCharsets.UTF_8));

                for (Map.Entry<String, List<String>> entry : language.getHashmapNames().get(hashmapAdd).entrySet()) {
                    writer.write(entry.getKey() + "//" + "\n");
                    for (String element : entry.getValue()) {
                        writer.write(element + "\n");
                    }

                }

                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Translation deleted.");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("The word was not found in the dictionary!");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle){
        Translation.setCellValueFactory(new PropertyValueFactory<Language, String>("trgLang"));
        SourceCB.getItems().addAll();
        TargetCB.getItems().addAll("English", "French", "German", "Italian", "Greek", "Turkish", "Swedish");
        SourceCB.setValue("Source");
        TargetCB.setValue("Target");

        addSourceCB.getItems().addAll("English", "French", "German","Italian", "Greek", "Turkish","Swedish");
        addTargetCB.getItems().addAll("English", "French", "German","Italian", "Greek", "Turkish","Swedish");
        addSourceCB.setValue("Source");
        addTargetCB.setValue("Target");
        editSrcCB.getItems().addAll("English", "French", "German","Italian", "Greek", "Turkish","Swedish");
        editTargetCB.getItems().addAll("English", "French", "German","Italian", "Greek", "Turkish","Swedish");
        editSrcCB.setValue("Source");
        editTargetCB.setValue("Target");

        language.loadAllHashmaps();

        if(hashMapName!=null) {
            SourceCB.setItems(FXCollections.observableArrayList(hashMapName));
        }
        //SourceCB.getItems().setAll(hashMapName);

    }
}

