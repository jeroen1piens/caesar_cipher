import java.nio.file.Path;
import java.util.HashSet;
import java.util.TreeMap;

public class StatisticalAnalyser {

    private static Path encryptedFilePath;



    private static Path exampleTextFilePath;
    private static HashSet<String> exampleWordSet;
    private static TreeMap<Integer, Integer> wordLengthTreeMap;
    private static int startKey = 1;

    public static void executeStatisticalAnalysis() {
        System.out.println("A valid filepath to an encrypted .txt file to perform a statistical analysis on for assessing the correct key needs to be entered.\n");
        String encryptedText = FileManager.getTextFromFile();
        encryptedFilePath = FileManager.getInputFilePath();
        String exampleText = getExampleText();
        exampleWordSet = getExampleWordSet(exampleText);
        String statisticalAnalysisOutput = "";
        int key = startKey;
        Cipher cipher = new Cipher(key);

        while(Validator.isValidKey(Integer.toString(key), cipher)) {
            cipher.setKey(key);
            String candDecrText = cipher.decrypt(encryptedText);
            statisticalAnalysisOutput += "Statistics for key " + key + ":\n\n";
            statisticalAnalysisOutput += "Words in common between example text and the text decrypted with candidate key:\n";
            statisticalAnalysisOutput += calculateCommonWordsCount(candDecrText) + "\n\n";
            statisticalAnalysisOutput += "Word length overview for the text decrypted with the candidate key:";
            statisticalAnalysisOutput += "\n";
            statisticalAnalysisOutput += getWordLengthOverview(candDecrText);
            statisticalAnalysisOutput += "\n\n";
            key++;
        }
        String pathStatAnalysisOverview = encryptedFilePath.getParent() + "\\" +
                getFileNameWithoutExt(encryptedFilePath.getFileName()) + "_statistical_analysis_overview.txt";
        FileManager.writeFile(statisticalAnalysisOutput, pathStatAnalysisOverview);
        System.out.println("Overview of the statistical analysis can be seen in the file: "  + pathStatAnalysisOverview);

    }


    private static String getExampleText() {
        String exampleText;
        System.out.println("A valid filepath to a .txt file with an example text needs to be entered.\n");
        exampleText = FileManager.getTextFromFile();
        return exampleText;
    }
    private static void setExampleTextFilePath(Path exampleTextFilePath) {
        System.out.println("A valid filepath to a .txt file with an example text needs to be entered.\n");
        StatisticalAnalyser.exampleTextFilePath = Path.of(FileManager.getTextFromFile());
    }

    private static HashSet<String> getExampleWordSet(String exampleText) {
        String[] exampleWordsArray = exampleText.split(" ");
        HashSet<String> exampleWordSet = new HashSet<>();
        for(String word : exampleWordsArray) {
            exampleWordSet.add(word);
        }
        return exampleWordSet;
    }

    private static int calculateCommonWordsCount(String candDecrText) {
        int count = 0;
        String[] wordArray = candDecrText.split(" ");
        for (String word : wordArray) {
            if(exampleWordSet.contains(word)) {
                count++;
            }
        }
        return count;
    }

    private static String getWordLengthOverview(String candDecrText) {
        TreeMap<Integer, Integer> wordLengthTreeMap = new TreeMap<>();
        String[] wordArray = candDecrText.split(" ");
        for (String word : wordArray) {
            if(!wordLengthTreeMap.containsKey(word.length())) {
                wordLengthTreeMap.put(word.length(), 1);
            }
            else {
                wordLengthTreeMap.replace(word.length(), wordLengthTreeMap.get(word.length()) + 1);
            }
        }
        String output = "";
        output += "Word length\tCount\n";
        for (int key : wordLengthTreeMap.keySet()) {
            output += key;
            output+= "\t\t";
            output+= wordLengthTreeMap.get(key);
            output+="\n";
        }
        return output;
    }

    private static String getFileNameWithoutExt(Path filePath) {
        String fileNameWithExtension = filePath.getFileName().toString();
        String[] strArray = fileNameWithExtension.split("\\.");
        String fileName = strArray[0];
        return fileName;
    }

}
