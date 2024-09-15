import java.util.*;

public class Cipher {

    private final List<Character> ALPHABET;
    private int key;

    public Cipher(int key) {
        this.key = key;
        Character[] alphabetArray = new Character[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '.', ',', '?', '!', ' ', '-', '\'', '\"'};
        ALPHABET = Arrays.asList(alphabetArray);
        }
    public Cipher(int key, Character[] alphabetArray) {
        this.key = key;
        ALPHABET = Arrays.asList(alphabetArray);
    }

    public List<Character> getALPHABET() {
        return ALPHABET;
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
            if (ALPHABET.contains(c)) {
                encryptedText += ALPHABET.get((ALPHABET.indexOf(c) + key)%ALPHABET.size());
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
            if (ALPHABET.contains(c)) {
                decryptedText += ALPHABET.get((ALPHABET.indexOf(c) - key + ALPHABET.size()) % ALPHABET.size());
            }
            else {
                decryptedText += c;
            }
        }
        return decryptedText;
    }

}
