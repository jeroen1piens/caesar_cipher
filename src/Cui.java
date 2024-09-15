import java.nio.file.Path;
import java.util.Scanner;


public class Cui {

    public static Cipher cipher;
    static Scanner scanner = new Scanner(System.in);
    static Path inputFilePath;


    public static void main(String[] args) {
        while(true) {
            System.out.println(
                    "This is the menu from the caesar cipher program.\n\n"
                            + "Which of the below options do you want to do?\n\n"
                            + "Encryption:             write 1 and click on Enter\n"
                            + "Decryption with a key:  write 2 and click on Enter\n"
                            + "Brute force:            write 3 and click on Enter\n"
                            + "Statistical analysis:   write 4 and click on Enter\n"
                            + "Exit the program:       write 0 and click on Enter\n"

            );
            String input = scanner.nextLine();
            switch (input) {
                case "1" -> encrypt();
                case "2" -> decrypt();
                case "0" -> System.exit(0);
                default -> System.out.println("The entered value is not an option. Please enter a value from the menu.");

            };
        }
    }

    public static void encrypt() {
        System.out.println("Which number do you want to use for encryption? Write a whole number >= 1 and < " + Cipher.getAlphabet().size() + " click on Enter:\n");
        int key = askKey();
        System.out.println("A valid filepath to a .txt file to be encrypted needs to be entered.\n");
        String originalText = getTextFromFile();
        cipher = new Cipher(key);
        String encryptedText = cipher.encrypt(originalText);
        System.out.println("The encrypted text will be saved in a .txt file.\n");
        saveTextToFile(encryptedText);
    }

    public static void decrypt() {
        System.out.println("Which number do you want to use for decryption? Write a whole number >= 1 and < " + + Cipher.getAlphabet().size() + " click on Enter:\n");
        int key = askKey();
        System.out.println("A valid filepath to the .txt file to be decrypted needs to be entered.\n");
        String encryptedText = getTextFromFile();
        cipher = new Cipher(key);
        String decryptedText = cipher.decrypt(encryptedText);
        System.out.println("The decrypted text needs to be saved in a .txt file.\n");
        saveTextToFile(decryptedText);
    }

    public static int askKey() {
        int key;
        String keyInput;
        while (true) {
            keyInput = scanner.nextLine();
            if(Validator.isValidKey(keyInput, cipher)) {
                break;
            }
            else {
                System.out.println("The number that was entered is not a valid key!\n");
                System.out.println("Please enter a valid key.");
            }
        }
        key = new Scanner(keyInput).nextInt();
        return key;
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
}
