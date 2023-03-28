import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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

    public void loadWordsFromFile(String filePath) {
        System.out.println("loadWordsFromFile");

        try {
            InputStream is = getClass().getResourceAsStream("/deneme.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            String text = sb.toString();

            // Split the text into words and translations
            Pattern pattern = Pattern.compile("^(\\w+)\\s+/[^/]+/\n((\\d+\\.\\s+.+?\n)+)", Pattern.MULTILINE | Pattern.DOTALL);
            Matcher matcher = pattern.matcher(text);

            // Upload the words and their translations to a HashMap
            hashMap = new HashMap<>();
            setHashMap(hashMap);

            while (matcher.find()) {
                String word = matcher.group(1).trim();
                String translation = matcher.group(2).trim();
                hashMap.put(word, translation);
                System.out.println(word);
                System.out.println(translation);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void loadsFromFilefra_tr(String filename) {

    }

}