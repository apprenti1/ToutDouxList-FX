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
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(algo);
            Cipher.getInstance(algo).init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(), algo));
            return Base64.getEncoder().encodeToString(cipher.doFinal(value.getBytes()));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {throw new RuntimeException(e);}
    }
    public static String encrypt(String value) {
        return  encrypt(value, defaultKey);
    }
    public static String decrypt(String encryptedValue, String key) {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(algo);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(), algo));
            return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedValue)));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {throw new RuntimeException(e);}
    }
    public static String decrypt(String value) {
        return  decrypt(value, defaultKey);
    }
}

