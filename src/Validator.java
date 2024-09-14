import java.nio.file.Files;
import java.nio.file.Path;

public class Validator {
    public static boolean isValidKey(int key) {
        if (key >= 1) {
            return true;
        }
        else {
            return false;
        }
    }
    public static boolean isFileExists(Path path) {
        return Files.exists(path);
    }
}
