package org.pistonmc.util;

import org.pistonmc.logging.Logging;

import java.security.*;
import java.security.spec.X509EncodedKeySpec;

public class EncryptionUtils {

    private static SecureRandom random = new SecureRandom();

    private EncryptionUtils() {
    }

    public static KeyPair generateKeyPair() {
        KeyPair keyPair = null;
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024);

            keyPair = generator.generateKeyPair();
        } catch (NoSuchAlgorithmException ex) {
            Logging.getLogger().severe("Unable to generate RSA key pair: " + ex.getMessage());
        }

        return keyPair;
    }

    public static byte[] generateVerifyToken() {
        byte[] token = new byte[4];
        random.nextBytes(token);
        return token;
    }

    public static Key generateX509Key(Key base) {
        Key key = null;
        try {
            X509EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(base.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            key = keyFactory.generatePublic(encodedKeySpec);
        } catch (Exception ex) {
            Logging.getLogger().severe("Unable to generate X509 encoded key: " + ex.getMessage());
        }

        return key;
    }

}
