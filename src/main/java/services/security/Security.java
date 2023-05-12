package services.security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Security {

    private static String defaultKey;
    private static final String algo = "AES";
    public static String encrypt(String value, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), algo);
            Cipher cipher = Cipher.getInstance(algo);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] encryptedValue = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encryptedValue);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {throw new RuntimeException(e);}
    }
    public static String encrypt(String value) {
        return  encrypt(value, defaultKey);
    }
    public static String decrypt(String encryptedValue, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), algo);
            Cipher cipher = Cipher.getInstance(algo);
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] decryptedValue = cipher.doFinal(Base64.getDecoder().decode(encryptedValue));
            return new String(decryptedValue);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {throw new RuntimeException(e);}
    }
    public static String decrypt(String value) {
        return  decrypt(value, defaultKey);
    }
    public static void setDefaultKey(String defaultKey) {Security.defaultKey = defaultKey;}
}

