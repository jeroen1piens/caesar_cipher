import java.nio.file.Path;
import java.util.Scanner;

public class BruteForcer {

    private static Path inputFilePath;
    private static int startKey = 1;
    public static void bruteForce() {
        System.out.println("A valid filepath to a .txt file to brute force needs to be entered.\n");
        String encryptedText = FileManager.readFile(FileManager.askInputFilePath());
        inputFilePath = FileManager.getInputFilePath();
        int key = startKey;
        Cipher cipher = new Cipher(key);
        while(Validator.isValidKey(Integer.toString(key), cipher)) {
            cipher.setKey(key);
            String decryptedText = cipher.decrypt(encryptedText);
            String outputFilePath = inputFilePath.getParent().toString() + "\\" + getFileName(inputFilePath)  +
                    "_bruteforce_with_key_" + key + ".txt";
            FileManager.writeFile(decryptedText, outputFilePath);
            key++;
        }
    }

    private static String getFileName(Path filePath) {
        String fileNameWithExtension = filePath.getFileName().toString();
        String[] strArray = fileNameWithExtension.split("\\.");
        String fileName = strArray[0];
        return fileName;
    }

}
