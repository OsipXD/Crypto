package ru.endlesscode.crypto.vertical;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by OsipXD on 17.12.2016
 * It is part of the Crypto.
 * All rights reserved 2014 - 2016 © «EndlessCode Group»
 */
public class VerticalCipherTest {
    private VerticalCipher cipher;

    @Before
    public void setUp() {
        byte[] key = {5, 3, 2, 4, 1};
        cipher = new VerticalCipher(key);
    }

    @Test
    public void route() {
        String message = "VERTICAL CIPHER WORKS";

        System.out.println("Routed:");
        byte[][] routed = VerticalCipher.route(message.replaceAll(" ", "").getBytes());
        for (byte[] row : routed) {
            for (byte sym : row) {
                System.out.print((char) sym);
            }

            System.out.println();
        }
    }

    @Test
    public void encrypt() {
        String message = "VERTICAL CIPHER WORKS";

        System.out.println(cipher.encrypt(message));
    }
}