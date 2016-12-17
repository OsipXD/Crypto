package ru.endlesscode.crypto.vertical;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Created by OsipXD on 17.12.2016
 * It is part of the Blowfish.
 * All rights reserved 2014 - 2016 © «EndlessCode Group»
 */
@SuppressWarnings("WeakerAccess")
public class VerticalCipher {
    private final static int TABLE_SIZE = 5;

    private final byte[] key;

    public VerticalCipher(byte[] key) {
        if (key.length != 5) {
            throw new IllegalArgumentException("Key must contains " + TABLE_SIZE + " bytes");
        }

        //{5, 3, 2, 4, 1} -> {4, 2, 1, 5, 3}
        byte[] privateKey = {4, 3, 5, 1, 2};
        this.key = encrypt(key, privateKey);
    }

    public String encrypt(String message) {
        return new String(encrypt(message.replaceAll(" ", "").getBytes(), this.key));
    }

    @NotNull
    private byte[] encrypt(byte[] data, byte[] key) {
        byte[][] routed = route(data);

        String encrypted = "";
        for (int col = 0; col < TABLE_SIZE; col++) {
            for (byte[] row : routed) {
                char sym = (char) row[key[col] - 1];
                if (sym != ' ') {
                    encrypted += (char) row[key[col] - 1];
                }
            }
        }

        return encrypted.getBytes();
    }

    @Contract(pure = true)
    static byte[][] route(byte[] message) {
        int rowsCount = (message.length - 1) / TABLE_SIZE + 1;
        byte[][] routed = new byte[rowsCount][TABLE_SIZE];

        int count = 0;
        for (int row = 0; row < rowsCount; row++) {
            for (int col = 0; col < TABLE_SIZE; col++, count++) {
                routed[row][col] = count < message.length ? message[count] : (byte) ' ';
            }
        }

        return routed;
    }
}
