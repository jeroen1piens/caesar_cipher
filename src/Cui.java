import java.util.Scanner;


public class Cui {

    Cipher cipher;
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
                case "0" -> System.exit(0);
                default -> System.out.println("The entered value is not an option. Please enter a value from the menu.");

            };
        }
    }

    public static void encrypt() {
        System.out.println("Which number do you want to use for encryption? Write a whole number >= 1 and click on Enter:\n");
        int key = askKey();
        System.out.println("Please add the .txt file to be encrypted under the \"file\" folder of the caesar_cipher package. Click on Enter when added:\n");
        String originalText = getTextFromFile();
        Cipher cipher = new Cipher(key);
        String encryptedText = cipher.encrypt(originalText);
        System.out.println("The encrypted text needs to be saved in a .txt file.\n");
        saveTextToFile(encryptedText);
    }

    public static void decrypt() {
        System.out.println("Which number do you want to use for decryption? Write a whole number >= 1 and click on Enter:\n");
        int key = askKey();
        System.out.println("Please add the .txt file to be encrypted under the \"file\" folder of the caesar_cipher package. Click on Enter when added:\n");
        String encryptedText = getTextFromFile();
        Cipher cipher = new Cipher(key);
        String decryptedText = cipher.decrypt(encryptedText);
        System.out.println("The decrypted text needs to be saved in a .txt file.\n");
        saveTextToFile(decryptedText);
    }

    public static int askKey() {
        int key;
        while (true) {
            key = scanner.nextInt();
            if(Validator.isValidKey(key)) {
                break;
            }
            else {
                System.out.println("The number that was entered is not a valid key!\n");
                System.out.println("Please enter a valid key.");
            }
        }
        return key;
    }
    public static String getTextFromFile() {
        String text;

        while (true) {
            System.out.println("Please type in the name of the .txt file and click on Enter:\n");
            String fileName = scanner.nextLine();
            String filePath = "file\\" + fileName;
            if (Validator.isFileExists(filePath)) {
                text = FileManager.readFile(filePath);
                break;
            } else {
                System.out.println("The entered file was not found in the file folder of the caesar_cipher package.\n");
                System.out.println("Please make sure the .txt file is added in the file folder of the caesar_cipher package and no typos are made when typing in the name of the .txt file.\n");
            }
        }
        return text;
    }
    public static void saveTextToFile(String encryptedText) {
        System.out.println("Please type in the name you want to give to the .txt file, subsequently click on enter:");
        while (true) {
            String fileName = scanner.nextLine();
            String filePath ="file\\" + fileName;
            if (Validator.isFileExists(filePath)) {
                System.out.println("A file with name \"" + fileName + "\" already exists under the file folder!\n");
                System.out.println("Please write another name and click on enter:\n");
            } else {
                FileManager.writeFile(encryptedText, filePath);
                break;
            }
        }
    }
}
