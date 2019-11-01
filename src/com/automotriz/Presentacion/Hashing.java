package com.automotriz.Presentacion;

import com.automotriz.logger.Logger;
import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import org.apache.commons.codec.binary.Base64;
import com.automotriz.Constantes.Constants;

public class Hashing {

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
            byte[] arrayBytes = Constants.KEY.getBytes(Constants.UNICODE_FORMAT);
            KeySpec ks = new DESedeKeySpec(arrayBytes);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(Constants.SCHEME);
            cipher = Cipher.getInstance(Constants.SCHEME);
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
                byte[] plainText = str.getBytes(Constants.UNICODE_FORMAT);
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
