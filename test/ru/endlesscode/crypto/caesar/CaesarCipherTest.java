package ru.endlesscode.crypto.caesar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by OsipXD on 17.12.2016
 * It is part of the Crypto.
 * All rights reserved 2014 - 2016 © «EndlessCode Group»
 */
public class CaesarCipherTest {
    private CaesarCipher cipher;

    @Before
    public void setUp() {
        cipher = new CaesarCipher(7);
    }

    @Test
    public void encryptDecryptEnglish() {
        String message = "Hello, Cesar!";

        String encrypted = cipher.encrypt(message, CaesarCipher.Alphabet.ENGLISH);
        Assert.assertEquals("Olssv, Jlzhy!", encrypted);
        System.out.println(message + " -> " + encrypted);

        String decrypted = cipher.decrypt(encrypted, CaesarCipher.Alphabet.ENGLISH);
        Assert.assertEquals(message, decrypted);
        System.out.println(encrypted + " -> " + message);
    }

    @Test
    public void encryptDecryptRussian() {
        String message = "Привет, Цезарь!";

        String encrypted = cipher.encrypt(message, CaesarCipher.Alphabet.RUSSIAN);
        Assert.assertEquals("Цчпилщ, Эложчг!", encrypted);
        System.out.println(message + " -> " + encrypted);

        String decrypted = cipher.decrypt(encrypted, CaesarCipher.Alphabet.RUSSIAN);
        Assert.assertEquals(message, decrypted);
        System.out.println(encrypted + " -> " + message);
    }
}