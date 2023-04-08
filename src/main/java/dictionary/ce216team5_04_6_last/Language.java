package dictionary.ce216team5_04_6_last;
import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class Language {

    private String srcLang;
    private String trgLang;
    private Map<String, String> hashMap;

    public Map<String, String> getHashMap() {
        return hashMap;
    }

    public void setHashMap(Map<String, String> hashMap) {
        this.hashMap = hashMap;
    }

    public Language(String trgLang) {
        this.trgLang = trgLang;
    }

    public Language() {

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


    //loadWordsFromFile method to read from a file and put the words and translations to related hashmap
    // main usage of the method is to find how to read from a file and put it to a hashmap

    public void loadWordsFromFile(String filePath) {
        System.out.println("loadWordsFromFile");

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            String text = sb.toString();

            String[] lines = text.split("\n");
            hashMap = new HashMap<>();

            for (int i = 0; i < lines.length; i++) {
                String string = lines[i].trim();
                if (string.endsWith("/")) {
                    String[] parts = string.split("/");
                    String word = parts[0].trim();
                    String translations = "";
                    for (int j = i+1; j < lines.length; j++) {
                        String nextLine = lines[j].trim();
                        if (nextLine.isEmpty()) {
                            continue;
                        } else if (nextLine.endsWith("/")) {
                            break;
                        } else {
                            translations += nextLine.trim() + " ";
                        }
                    }
                    // add word and its translations to the HashMap
                    hashMap.put(word, translations);
                    System.out.println(word);
                    System.out.println(translations);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
