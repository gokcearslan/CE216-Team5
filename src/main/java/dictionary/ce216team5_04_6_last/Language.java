package dictionary.ce216team5_04_6_last;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Language {
    private String srcLang;
    private Map<String, List<String>> hashMap;
    private HashMap<String, ArrayList<String>> dictionary;

    private Map<String, HashMap<String,List<String>>> hashmapNames;

    //create hashmaps for each file

    HashMap<String, List<String>> FrenchEnglish = new HashMap<>();
    HashMap<String, List<String>> GermanEnglish = new HashMap<>();
    HashMap<String, List<String>> EnglishFrench = new HashMap<>();
    HashMap<String, List<String>> EnglishGerman = new HashMap<>();
    HashMap<String, List<String>> EnglishGreek = new HashMap<>();
    HashMap<String, List<String>> EnglishItalian = new HashMap<>();
    HashMap<String, List<String>> EnglishSwedish = new HashMap<>();
    HashMap<String, List<String>> EnglishTurkish = new HashMap<>();
    HashMap<String, List<String>> FrenchGerman = new HashMap<>();
    HashMap<String, List<String>> FrenchGreek = new HashMap<>();
    HashMap<String, List<String>> FrenchItalian = new HashMap<>();
    HashMap<String, List<String>> FrenchSwedish = new HashMap<>();
    HashMap<String, List<String>> FrenchTurkish = new HashMap<>();
    HashMap<String, List<String>> GermanFrench = new HashMap<>();
    HashMap<String, List<String>> GermanGreek = new HashMap<>();
    HashMap<String, List<String>> GermanItalian = new HashMap<>();
    HashMap<String, List<String>> GermanSwedish = new HashMap<>();
    HashMap<String, List<String>> GermanTurkish = new HashMap<>();
    HashMap<String, List<String>> GreekEnglish = new HashMap<>();
    HashMap<String, List<String>> GreekFrench = new HashMap<>();
    HashMap<String, List<String>> GreekItalian = new HashMap<>();
    HashMap<String, List<String>> GreekSwedish = new HashMap<>();

    HashMap<String, List<String>> ItalianEnglish = new HashMap<>();
    HashMap<String, List<String>> ItalianGerman = new HashMap<>();
    HashMap<String, List<String>> ItalianGreek = new HashMap<>();
    HashMap<String, List<String>> ItalianSwedish = new HashMap<>();
    HashMap<String, List<String>> ItalianTurkish = new HashMap<>();

    HashMap<String, List<String>> SwedishEnglish = new HashMap<>();
    HashMap<String, List<String>> SwedishFrench = new HashMap<>();
    HashMap<String, List<String>> SwedishGerman = new HashMap<>();
    HashMap<String, List<String>> SwedishGreek = new HashMap<>();
    HashMap<String, List<String>> SwedishItalian = new HashMap<>();
    HashMap<String, List<String>> SwedishTurkish = new HashMap<>();

    HashMap<String, List<String>> TurkishEnglish = new HashMap<>();
    HashMap<String, List<String>> TurkishGerman = new HashMap<>();

    //

    private Map<String, String> sourceLanguages;

    public Map<String, HashMap<String, List<String>>> getHashmapNames() {
        return hashmapNames;
    }

    public void setHashmapNames(Map<String, HashMap<String, List<String>>> hashmapNames) {
        this.hashmapNames = hashmapNames;
    }

    private String trgLang;
    private String sourceLang;

    public String getSourceLang() {
        return sourceLang;
    }
    public Language(String trgLang) {
        this.trgLang = trgLang;
    }

    HashMap<String, HashMap<String, List<String>>> hashMapCollection;
    public Language() {

    }

    public HashMap<String, HashMap<String, List<String>>> getHashMapCollection() {
        return hashMapCollection;
    }

    public void setHashMapCollection(HashMap<String, HashMap<String, List<String>>> hashMapCollection) {
        this.hashMapCollection = hashMapCollection;
    }

    public Language(String trgLang, String sourceLang) {
        this.trgLang = trgLang;
        this.sourceLang = sourceLang;
        this.hashMap = new HashMap<>();

    }

    public void loadAllHashmaps() {

        hashMapCollection = new HashMap<>();

        //Add all language hashmaps to hashMapCollection by using their source language as keys
        hashMapCollection.put("English", EnglishFrench);
        hashMapCollection.put("English", EnglishGerman);
        hashMapCollection.put("English", EnglishGreek);
        hashMapCollection.put("English", EnglishItalian);
        hashMapCollection.put("English", EnglishSwedish);
        hashMapCollection.put("English", EnglishTurkish);

        hashMapCollection.put("French", FrenchEnglish);
        hashMapCollection.put("French", FrenchGerman);
        hashMapCollection.put("French", FrenchGreek);
        hashMapCollection.put("French", FrenchItalian);
        hashMapCollection.put("French", FrenchSwedish);
        hashMapCollection.put("French", FrenchTurkish);

        hashMapCollection.put("German", GermanEnglish);
        hashMapCollection.put("German", GermanFrench);
        hashMapCollection.put("German", GermanGreek);
        hashMapCollection.put("German", GermanItalian);
        hashMapCollection.put("German", GermanSwedish);
        hashMapCollection.put("German", GermanTurkish);

        hashMapCollection.put("Greek", GreekFrench);
        hashMapCollection.put("Greek", GreekItalian);
        hashMapCollection.put("Greek", GreekSwedish);
        hashMapCollection.put("Greek", GreekEnglish);

        hashMapCollection.put("Italian", ItalianGerman);
        hashMapCollection.put("Italian", ItalianGreek);
        hashMapCollection.put("Italian", ItalianSwedish);
        hashMapCollection.put("Italian", ItalianTurkish);
        hashMapCollection.put("Italian", ItalianEnglish);

        hashMapCollection.put("Swedish", SwedishFrench);
        hashMapCollection.put("Swedish", SwedishGerman);
        hashMapCollection.put("Swedish", SwedishGreek);
        hashMapCollection.put("Swedish", SwedishItalian);
        hashMapCollection.put("Swedish", SwedishTurkish);
        hashMapCollection.put("Swedish", SwedishEnglish);

        hashMapCollection.put("Turkish", TurkishGerman);
        hashMapCollection.put("Turkish", TurkishEnglish);

        //

        hashmapNames = new HashMap<>();
        //Add all language hashmaps to hashmapNames by using their filenames as keys

        hashmapNames.put("EnglishFrench", EnglishFrench);
        hashmapNames.put("EnglishGerman", EnglishGerman);
        hashmapNames.put("EnglishGreek", EnglishGreek);
        hashmapNames.put("EnglishItalian", EnglishItalian);
        hashmapNames.put("EnglishSwedish", EnglishSwedish);
        hashmapNames.put("EnglishTurkish", EnglishTurkish);

        hashmapNames.put("FrenchGerman", FrenchGerman);
        hashmapNames.put("FrenchGreek", FrenchGreek);
        hashmapNames.put("FrenchItalian", FrenchItalian);
        hashmapNames.put("FrenchSwedish", FrenchSwedish);
        hashmapNames.put("FrenchTurkish", FrenchTurkish);
        hashmapNames.put("FrenchEnglish",FrenchEnglish);

        hashmapNames.put("GermanEnglish",GermanEnglish);
        hashmapNames.put("GermanFrench", GermanFrench);
        hashmapNames.put("GermanGreek", GermanGreek);
        hashmapNames.put("GermanItalian", GermanItalian);
        hashmapNames.put("GermanSwedish", GermanSwedish);
        hashmapNames.put("GermanTurkish", GermanTurkish);

        hashmapNames.put("GreekFrench",GreekFrench);
        hashmapNames.put("GreekItalian",GreekItalian);
        hashmapNames.put("GreekSwedish",GreekSwedish);
        hashmapNames.put("GreekEnglish",GreekEnglish);

        hashmapNames.put("ItalianGerman",ItalianGerman);
        hashmapNames.put("ItalianGreek",ItalianGreek);
        hashmapNames.put("ItalianSwedish",ItalianSwedish);
        hashmapNames.put("ItalianTurkish",ItalianTurkish);
        hashmapNames.put("ItalianEnglish",ItalianEnglish);

        hashmapNames.put("SwedishFrench",SwedishFrench);
        hashmapNames.put("SwedishGerman",SwedishGerman);
        hashmapNames.put("SwedishGreek",SwedishGreek);
        hashmapNames.put("SwedishItalian",SwedishItalian);
        hashmapNames.put("SwedishTurkish",SwedishTurkish);
        hashmapNames.put("SwedishEnglish",SwedishEnglish);

        hashmapNames.put("TurkishGerman",TurkishGerman);
        hashmapNames.put("TurkishEnglish",TurkishEnglish);

        // Load the HashMaps with data from text files and set source languages
        loadHashmapsInParallel();
    }

    public void loadHashMap(Map<String, List<String>> hashMap, String fileName) {
        InputStream filePath = getClass().getResourceAsStream(fileName);
        loadWordsFromFile(hashMap, filePath, StandardCharsets.UTF_8);

    }

    public HashMap<String, ArrayList<String>> getDictionary() {
        return dictionary;
    }

    public void setDictionary(HashMap<String, ArrayList<String>> dictionary) {
        this.dictionary = dictionary;
    }


    public Map<String, List<String>> getHashMap() {
        return hashMap;
    }

    public void setHashMap(Map<String, List<String>> hashMap) {
        this.hashMap = hashMap;
    }

    public String getSrcLang() {
        return srcLang;
    }

    public void setSrcLang(String srcLang) {
        this.srcLang = srcLang;
    }

    public String getTrgLang() {
        return trgLang;
    }

    public void setTrgLang(String trgLang) {
        this.trgLang = trgLang;
    }

    public static void loadWordsFromFile(Map<String, List<String>> hashMap, InputStream filePath, Charset charset) {
        // System.out.println("loadWordsFromFile");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(filePath, charset))) {

            String line;
            String currentWord = null;
            List<String> translations = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                if (line.contains("<")) {
                    if (currentWord != null && !translations.isEmpty()) {
                        hashMap.put(currentWord, translations);
                        translations = new ArrayList<>();
                    }

                    int bracketIndex = line.indexOf("<");
                    int slashIndex = line.indexOf("/");

                    if (slashIndex != -1 && slashIndex < bracketIndex) {
                        String[] parts = line.split("/");
                        currentWord = parts[0].trim();
                    } else {
                        String[] parts = line.split(" ");
                        currentWord = parts[0].trim();
                    }
                } else if (line.contains("/")) {
                    if (currentWord != null && !translations.isEmpty()) {
                        hashMap.put(currentWord, translations);
                        translations = new ArrayList<>();
                    }

                    String[] parts = line.split("/");
                    currentWord = parts[0].trim();
                } else if (!line.trim().isEmpty()) {
                    translations.add(line.trim());
                }
            }

            if (currentWord != null && !translations.isEmpty()) {
                hashMap.put(currentWord, translations);
            }

           /* for (Map.Entry<String, List<String>> entry : hashMap.entrySet()) {
                System.out.println(entry.getKey() + " -> " + entry.getValue());
            }

            */

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getSourceLanguage(String languagePair) {
        return sourceLanguages.get(languagePair);
    }


    public static List<String> getHashMapName(HashMap<String, HashMap<String, List<String>>> hashMapCollection, String searchWord) {
        if (hashMapCollection == null) {
            return null;
        }

        List<String> matchingHashMapNames = new ArrayList<>();

        for (Map.Entry<String, HashMap<String, List<String>>> entry : hashMapCollection.entrySet()) {
            String hashMapName = entry.getKey();
            HashMap<String, List<String>> hashMap = entry.getValue();

            if (hashMap.containsKey(searchWord)) {
                matchingHashMapNames.add(hashMapName);
            }
        }

        if (matchingHashMapNames.isEmpty()) {
            return null;
        } else {
            return matchingHashMapNames;
        }
    }

    public void loadHashmapsInParallel() {
        ExecutorService executor = Executors.newFixedThreadPool(35);

        executor.submit(() -> loadHashMap(EnglishFrench, "EnglishFrench.txt"));
        executor.submit(() -> loadHashMap(EnglishGerman, "EnglishGerman.txt"));
        executor.submit(() -> loadHashMap(EnglishGreek, "EnglishGreek.txt"));
        executor.submit(() -> loadHashMap(EnglishItalian, "EnglishItalian.txt"));
        executor.submit(() -> loadHashMap(EnglishSwedish, "EnglishSwedish.txt"));
        executor.submit(() -> loadHashMap(EnglishTurkish, "EnglishTurkish.txt"));

        executor.submit(() -> loadHashMap(FrenchEnglish, "FrenchEnglish.txt"));
        executor.submit(() -> loadHashMap(FrenchGerman, "FrenchGerman.txt"));
        executor.submit(() -> loadHashMap(FrenchGreek, "FrenchGreek.txt"));
        executor.submit(() -> loadHashMap(FrenchItalian, "FrenchItalian.txt"));
        executor.submit(() -> loadHashMap(FrenchSwedish, "FrenchSwedish.txt"));
        executor.submit(() -> loadHashMap(FrenchTurkish, "FrenchTurkish.txt"));

        executor.submit(() -> loadHashMap(GermanEnglish, "GermanEnglish.txt"));
        executor.submit(() -> loadHashMap(GermanFrench, "GermanFrench.txt"));
        executor.submit(() -> loadHashMap(GermanGreek, "GermanGreek.txt"));
        executor.submit(() -> loadHashMap(GermanItalian, "GermanItalian.txt"));
        executor.submit(() -> loadHashMap(GermanSwedish, "GermanSwedish.txt"));
        executor.submit(() -> loadHashMap(GermanTurkish, "GermanTurkish.txt"));

        executor.submit(() -> loadHashMap(GreekFrench, "GreekFrench.txt"));
        executor.submit(() -> loadHashMap(GreekItalian, "GreekItalian.txt"));
        executor.submit(() -> loadHashMap(GreekSwedish, "GreekSwedish.txt"));
        executor.submit(() -> loadHashMap(GreekEnglish, "GreekEnglish.txt"));

        executor.submit(() -> loadHashMap(ItalianGerman, "ItalianGerman.txt"));
        executor.submit(() -> loadHashMap(ItalianGreek, "ItalianGreek.txt"));
        executor.submit(() -> loadHashMap(ItalianSwedish, "ItalianSwedish.txt"));
        executor.submit(() -> loadHashMap(ItalianTurkish, "ItalianTurkish.txt"));
        executor.submit(() -> loadHashMap(ItalianEnglish, "ItalianEnglish.txt"));

        executor.submit(() -> loadHashMap(SwedishFrench, "SwedishFrench.txt"));
        executor.submit(() -> loadHashMap(SwedishGerman, "SwedishGerman.txt"));
        executor.submit(() -> loadHashMap(SwedishGreek, "SwedishGreek.txt"));
        executor.submit(() -> loadHashMap(SwedishItalian, "SwedishItalian.txt"));
        executor.submit(() -> loadHashMap(SwedishTurkish, "SwedishTurkish.txt"));
        executor.submit(() -> loadHashMap(SwedishEnglish, "SwedishEnglish.txt"));

        executor.submit(() -> loadHashMap(TurkishGerman, "TurkishGerman.txt"));
        executor.submit(() -> loadHashMap(TurkishEnglish, "TurkishEnglish.txt"));

        executor.shutdown();

        try {
            // Wait for all tasks to complete before continuing
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
                System.out.println("Timeout occurred while loading hashmaps");
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            e.printStackTrace();
        }
    }

}