import java.nio.file.Files;
import java.nio.file.Path;

public class FileManager {

    static Path path;
    public static readFile(String filePath) {
        path = Path.of(filePath);
        Files.readAllBytes(path);
    }
}
