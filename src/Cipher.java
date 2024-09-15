import java.util.*;

public class Cipher {
    private static Character[] alphabetArray = new Character[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '.', ',', '?', '!', ' ', '-', '\'', '\"'};
    private static List<Character> alphabet = Arrays.asList(alphabetArray);
    private int key;

    public Cipher(int key) {
        this.key = key;
        }
    public Cipher(int key, Character[] alphabetArray) {
        this.key = key;
        alphabet = Arrays.asList(alphabetArray);
    }

    public static List<Character> getAlphabet() {
        return alphabet;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String encrypt(String text) {
        String encryptedText = "";
        for (char c : text.toCharArray()) {
            c = Character.toLowerCase(c);
            if (alphabet.contains(c)) {
                encryptedText += alphabet.get((alphabet.indexOf(c) + key)% alphabet.size());
            }
            else {
                encryptedText += c;
            }
        }
        return encryptedText;
    }

    public String decrypt(String encryptedText) {
        String decryptedText = "";
        for (char c : encryptedText.toCharArray()) {
            c = Character.toLowerCase(c);
            if (alphabet.contains(c)) {
                decryptedText += alphabet.get((alphabet.indexOf(c) - key + alphabet.size()) % alphabet.size());
            }
            else {
                decryptedText += c;
            }
        }
        return decryptedText;
    }

}
