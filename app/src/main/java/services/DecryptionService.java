package services;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class DecryptionService {
    public static String decrypt(String str) {
        String seed = "secret";

        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(seed);

        String decrypted = encryptor.decrypt(str);

        return decrypted;
    }
}
