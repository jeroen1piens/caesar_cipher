import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManager {

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
}

