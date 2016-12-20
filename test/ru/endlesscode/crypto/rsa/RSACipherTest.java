package ru.endlesscode.crypto.rsa;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by OsipXD on 20.12.2016
 * It is part of the Crypto.
 * All rights reserved 2014 - 2016 © «EndlessCode Group»
 */
public class RSACipherTest {
    private RSACipher cipher;

    @Before
    public void setUp() {
        cipher = new RSACipher(RSAKeyPair.generate());
    }

    @Test
    public void encryptDecrypt() throws Exception {
        String message = "Hello, RSA!";

        byte[] encrypted = cipher.encrypt(message.getBytes());
        Assert.assertNotEquals(new String(encrypted), message);
        System.out.println(message + " -> " + RSAUtil.bytesToHexString(encrypted));

        byte[] decrypted = cipher.decrypt(encrypted);
        Assert.assertArrayEquals(message.getBytes(), decrypted);
        System.out.println(RSAUtil.bytesToHexString(encrypted) + " -> " + new String(decrypted));
    }
}