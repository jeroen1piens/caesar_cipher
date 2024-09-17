import java.nio.file.Path;
import java.util.Scanner;


public class Cui {

    public static Cipher cipher;
    static Scanner scanner = new Scanner(System.in);


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
                case "3" -> BruteForcer.bruteForce();
                case "4" -> StatisticalAnalyser.executeStatisticalAnalysis();
                case "0" -> System.exit(0);
                default -> System.out.println("The entered value is not an option. Please enter a value from the menu.");

            };
        }
    }

    public static void encrypt() {
        System.out.println("Which number do you want to use for encryption? Write a whole number >= 1 and < " + Cipher.getAlphabet().size() + " click on Enter:\n");
        int key = askKey();
        System.out.println("A valid filepath to a .txt file to be encrypted needs to be entered.\n");
        String originalText = FileManager.readFile(FileManager.askInputFilePath());
        cipher = new Cipher(key);
        String encryptedText = cipher.encrypt(originalText);
        System.out.println("The encrypted text will be saved in a .txt file.\n");
        FileManager.writeFile(encryptedText, FileManager.askOutputFilePath());
    }

    public static void decrypt() {
        System.out.println("Which number do you want to use for decryption? Write a whole number >= 1 and < " + + Cipher.getAlphabet().size() + " click on Enter:\n");
        int key = askKey();
        System.out.println("A valid filepath to the .txt file to be decrypted needs to be entered.\n");
        String encryptedText = FileManager.readFile(FileManager.askInputFilePath());
        cipher = new Cipher(key);
        String decryptedText = cipher.decrypt(encryptedText);
        System.out.println("The decrypted text needs to be saved in a .txt file.\n");
        FileManager.writeFile(decryptedText, FileManager.askOutputFilePath());
    }

    private static int askKey() {
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

}
