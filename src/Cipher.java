import java.util.*;

public class Cipher {

    int key;

    // In the actualToEncryptedMap the keys are the actual characters, the values are the encrypted characters
    HashMap<Character, Character> actualToEncryptedMap = new HashMap<Character, Character>();

    // In the encryptedToActualMap the keys are the encrypted characters, the values are the actual characters
    HashMap<Character, Character> encryptedToActualMap = new HashMap<Character, Character>();

    // For construction of a Cipher object an array with all the possible characters needs to be entered as an argument together with an integer that wil serve as the key for encryption
    public Cipher(char[] charArray, int key) {

        for (int i = 0; i < charArray.length; i++) {
            actualToEncryptedMap.put(charArray[i], charArray[(i + key)%charArray.length]);
            encryptedToActualMap.put(charArray[(i + key)%charArray.length], charArray[i]);
        }
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
