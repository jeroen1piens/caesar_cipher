import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class FileManager {

    private static Scanner scanner = new Scanner(System.in);

    private static Path inputFilePath;

    public static Path getInputFilePath() {
        return inputFilePath;
    }

    public static String readFile(String filePath) {
        Path path = Path.of(filePath);
        String content = "";
        try {
            byte[] byteArray = Files.readAllBytes(path);
            content = new String(byteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public static void writeFile(String content, String file) {
        Path path = Path.of(file);
        try {
            Files.write(path, content.getBytes());
        }
        catch (IOException e) {
            System.out.println("An error occurred:");
            e.printStackTrace();
        }
    }

    public static String getTextFromFile() {
        String inputText;

        while (true) {
            System.out.println("Please type in a valid filepath to a .txt file and click on Enter:\n");
            String filePath = scanner.nextLine();
            if (Validator.isFileExists(filePath)) {
                inputFilePath = Path.of(filePath);
                inputText = FileManager.readFile(filePath);
                break;
            } else {
                System.out.println("The entered file was not found in the file folder of the caesar_cipher package.\n");
                System.out.println("Please make sure the .txt file is added in the file folder of the caesar_cipher package and no typos are made when typing in the name of the .txt file.\n");
            }
        }
        return inputText;
    }
    public static void saveTextToFile(String outputText) {
        System.out.println("Please type in the path to the file where you want to save the output, subsequently click on enter:");
        String outputFilePath = scanner.nextLine();
        FileManager.writeFile(outputText, outputFilePath);
    }

    public static void saveTextToFile(String outputText, String outputFilePath) {
        FileManager.writeFile(outputText, outputFilePath);
    }
}

