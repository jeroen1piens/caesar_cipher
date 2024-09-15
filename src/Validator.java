import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Validator {
    public static boolean isValidKey(String keyInput, Cipher cipher) {
        Scanner scanner = new Scanner(keyInput);
        if (!scanner.hasNextInt()) {
            System.out.println("The entered key is not an integer!");
            return false;
        }
        int key = scanner.nextInt();
        if (key < 1) {
            System.out.println("The entered key cannot be smaller than 1!");
            return false;
        }
        else if (key >= cipher.getAlphabet().size()) {
            System.out.println("The entered key cannot be equal or bigger than the size of the used alphabet. Size used alphabet: " + cipher.getAlphabet().size());
            return false;
        }
        else {
            return true;
        }
    }
    public static boolean isFileExists(String filePath) {
        Path path = Path.of(filePath);
        return Files.exists(path);
    }
}
