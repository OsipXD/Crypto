package ru.endlesscode.crypto.blowfish;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by OsipXD on 17.12.2016
 * It is part of the Crypto.
 * All rights reserved 2014 - 2016 © «EndlessCode Group»
 */
public class BlowfishCipherTest {
    private BlowfishCipher cipher;
    private String message;

    @Before
    public void setUp() {
        byte[] key = "3973996CCE756".getBytes();
        cipher = new BlowfishCipher(key);
        message = "(Super-secret message) Hello, Blowfish!  ";
    }

    @Test
    public void encryptAndDecrypt() throws Exception {
        byte[] encrypted = cipher.encrypt(message.getBytes());
        Assert.assertNotEquals(message, new String(encrypted));
        System.out.println("|" + message + "| -> |" + BlowfishUtils.bytesToHexString(encrypted) + "|");

        byte[] decrypted = cipher.decrypt(encrypted);
        Assert.assertArrayEquals(message.getBytes(), decrypted);
        System.out.println("|" + BlowfishUtils.bytesToHexString(encrypted) + "| -> |" + new String(decrypted) + "|");
    }
}