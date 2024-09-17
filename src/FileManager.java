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

    public static void writeFile(String content, String filePath) {
        Path path = Path.of(filePath);
        try {
            Files.write(path, content.getBytes());
        } catch (IOException e) {
            System.out.println("An error occurred:");
            e.printStackTrace();
        }
    }

    public static String askInputFilePath() {
        ;
        String filePath;
        while (true) {
            System.out.println("Please type in a valid filepath to a .txt file and click on Enter:\n");
            filePath = scanner.nextLine();
            if (Validator.isFileExists(filePath)) {
                inputFilePath = Path.of(filePath);
                break;
            } else {
                System.out.println("The entered file was not found.\n");
                System.out.println("Please make sure no typos are made when typing in the name of the file.\n");
            }
        }
        return filePath;
    }

    public static String askOutputFilePath() {
        String outputFilePath;
        while (true) {
            System.out.println("Please type in the path to the .txt file where you want to save the output, subsequently click on enter:");
            outputFilePath = scanner.nextLine();
            if (Validator.isFileExists(outputFilePath)) {
                System.out.println("OK to overwrite the file?");
                System.out.println("If OK, write 'Y' + click on Enter to proceed:");
                String feedbackUser = scanner.nextLine();
                if (feedbackUser.equals("Y")) {
                    break;
                }
            }
            else {
                System.out.println("The entered file was not found.\n");
                System.out.println("Please make sure no typos are made when typing in the name of the file.\n");
            }
        }
        return outputFilePath;
    }
}


