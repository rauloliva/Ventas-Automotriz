package com.automotriz.Presentacion;

import com.automotriz.logger.Logger;
import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import org.apache.commons.codec.binary.Base64;

public class Hashing {

    private final String UNICODE_FORMAT = ReadProperties.props.getProperty("hash.format");
    private final String SCHEME = ReadProperties.props.getProperty("hash.scheme");
    private final String KEY = ReadProperties.props.getProperty("hash.key");
    private Cipher cipher;
    private SecretKey key;
    private String str;

    /**
     * Initializes the necessary objects to hash the String str
     *
     * @param str the string to hash
     */
    public Hashing(String str) {
        try {
            this.str = str;
            byte[] arrayBytes = KEY.getBytes(UNICODE_FORMAT);
            KeySpec ks = new DESedeKeySpec(arrayBytes);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(SCHEME);
            cipher = Cipher.getInstance(SCHEME);
            key = skf.generateSecret(ks);
        } catch (Exception e) {
            Logger.error(e.getMessage());
            Logger.error(e.getStackTrace());
        }
    }

    public String encrypt() {
        String encryptedString = "";
        if (!this.str.equals("") && this.str != null) {
            try {
                Logger.log("Encrypting String...");
                cipher.init(Cipher.ENCRYPT_MODE, key);
                byte[] plainText = str.getBytes(UNICODE_FORMAT);
                byte[] encryptedText = cipher.doFinal(plainText);
                encryptedString = new String(Base64.encodeBase64(encryptedText));
            } catch (Exception e) {
                Logger.error(e.getMessage());
                Logger.error(e.getStackTrace());
            }
        }
        return encryptedString;
    }

    public String decrypt() {
        String decryptedText = "";
        if (!this.str.equals("") && this.str != null) {
            try {
                Logger.log("Decrypting String...");
                cipher.init(Cipher.DECRYPT_MODE, key);
                byte[] encryptedText = Base64.decodeBase64(str);
                byte[] plainText = cipher.doFinal(encryptedText);
                decryptedText = new String(plainText);
            } catch (Exception e) {
                Logger.error(e.getMessage());
                Logger.error(e.getStackTrace());
            }
        }
        return decryptedText;
    }
}
