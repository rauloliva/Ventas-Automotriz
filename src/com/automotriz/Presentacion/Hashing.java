package com.automotriz.Presentacion;

import com.automotriz.logger.Logger;
import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import org.apache.commons.codec.binary.Base64;

public class Hashing {

    private final String UNICODE_FORMAT = "UTF8";
    private final String myEncryptionScheme = "DESede";
    private Cipher cipher;
    private final String myEncryptionKey = "ThisIsSpartaThisIsSparta";
    private SecretKey key;
    private String str;

    public Hashing(String str) {
        try {
            this.str = str;
            byte[] arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
            KeySpec ks = new DESedeKeySpec(arrayBytes);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(myEncryptionScheme);
            cipher = Cipher.getInstance(myEncryptionScheme);
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
    public static void main(String[] args) {
        Hashing m = new Hashing("07WfylTarjY=");
        System.out.println(m.encrypt());
        System.out.println(m.decrypt());
    }
}
