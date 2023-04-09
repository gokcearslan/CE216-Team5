package dictionary.ce216team5_04_6_last;
import java.io.*;
import java.nio.charset.Charset;
import java.util.*;


public class Language {

    private String srcLang;
    private String trgLang;
    private Map<String, List<String>> hashMap;
    private HashMap<String, ArrayList<String>> dictionary;

    public Language(String text, String translation) {
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

    public void loadWordsFromFile(InputStream filePath, Charset charset) {
       // System.out.println("loadWordsFromFile");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(filePath,charset))) {

            hashMap = new HashMap<>();

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
                //System.out.println(entry.getKey() + " -> " + entry.getValue());
            }

            */
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}