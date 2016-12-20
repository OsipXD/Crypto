package ru.endlesscode.crypto.rsa;

/**
 * Created by OsipXD on 20.12.2016
 * It is part of the Crypto.
 * All rights reserved 2014 - 2016 © «EndlessCode Group»
 */
public class RSACipher {
    private RSAKeyPair keyPair;

    public RSACipher(RSAKeyPair keyPair) {
        this.keyPair = keyPair;
    }

    public byte[] encrypt(byte[] data) {
        byte[] encrypted = new byte[data.length * 2];

        for (int i = 0; i < data.length; i++) {;
            byte[] bytes = RSAUtil.getLowerBytesPair(keyPair.getPublicKey().modPow(data[i]));
            encrypted[i * 2] = bytes[0];
            encrypted[i * 2 + 1] = bytes[1];
        }

        return encrypted;
    }

    public byte[] decrypt(byte[] encrypted) {
        byte[] decrypted = new byte[encrypted.length / 2];

        for (int i = 0; i < encrypted.length - 1; i += 2) {
            int decByte = keyPair.getPrivateKey().modPow(RSAUtil.bytesPairToInt(new byte[]{encrypted[i], encrypted[i + 1]}));
            decrypted[i / 2] = (byte) decByte;
        }

        return decrypted;
    }
}
