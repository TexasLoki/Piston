package org.pistonmc.util;

import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.engines.AESFastEngine;
import org.bouncycastle.crypto.io.CipherInputStream;
import org.bouncycastle.crypto.io.CipherOutputStream;
import org.bouncycastle.crypto.modes.CFBBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.pistonmc.logging.Logging;

import javax.crypto.SecretKey;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;

public class EncryptionUtils {

    private static SecureRandom random = new SecureRandom();

    private static KeyPair keys;

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

    public static KeyPair getKeys() {
        if (keys == null) keys = generateKeyPair();
        return keys;
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

    public static OutputStream encryptOutputStream(OutputStream outputStream, SecretKey key) {
        return new CipherOutputStream(outputStream, createBlockCipher(key, true));
    }

    public static InputStream decryptInputStream(InputStream inputStream, SecretKey key) {
        return new CipherInputStream(inputStream, createBlockCipher(key, false));
    }

    public static BufferedBlockCipher createBlockCipher(SecretKey key, boolean out) {
        BufferedBlockCipher blockCipher = new BufferedBlockCipher(new CFBBlockCipher(new AESFastEngine(), 8));
        blockCipher.init(out, new ParametersWithIV(new KeyParameter(key.getEncoded()), key.getEncoded(), 0, 16));
        return blockCipher;
    }

}
