import java.util.Scanner;


public class Cui {

    Cipher cipher;
    Scanner scanner = new Scanner(System.in);

    public Cui() {
        System.out.println("Please specify the number of places you want to shift the characters for your encryption");
        int key;
        key = scanner.nextInt();
    }
}
