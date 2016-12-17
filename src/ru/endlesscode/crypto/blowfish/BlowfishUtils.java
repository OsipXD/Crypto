package ru.endlesscode.crypto.blowfish;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * Created by OsipXD on 17.12.2016
 * It is part of the Blowfish.
 * All rights reserved 2014 - 2016 © «EndlessCode Group»
 */
class BlowfishUtils {
    final private static char[] hexArray = "0123456789ABCDEF".toCharArray();

    @Contract(pure = true)
    static int bytesToInt(@NotNull byte[] bytes) {
        if (bytes.length != 4) {
            throw new IllegalArgumentException("Byte array must contains 4 bytes");
        }

        int value = 0;
        for (int i = 0; i < 4; i++) {
            value |= (bytes[i] & 0x0ff) << 8 * (3 - i);
        }

        return value;
    }

    @Contract("_ -> !null")
    static byte[] intToBytes(int value) {
        byte[] bytes = new byte[4];

        for (int i = 0; i < 4; i++) {
            bytes[i] = (byte) (value >>> (3 - i) * 8);
        }

        return bytes;
    }

    @Contract(pure = true)
    static byte[] addPadding(@NotNull byte[] data) {
        int oldLen = data.length;
        int len = ((oldLen + 1) / BlowfishCipher.BLOCK_SIZE + ((oldLen + 1) % BlowfishCipher.BLOCK_SIZE == 0 ? 0 : 1)) * BlowfishCipher.BLOCK_SIZE;
        byte[] result = Arrays.copyOf(data, len);

        result[oldLen] = (byte) 0xff;
        for (int i = oldLen + 1; i < len; i++) {
            result[i] = 0x00;
        }

        return result;
    }

    @NotNull
    static byte[] removePadding(@NotNull byte[] data) {
        int index;
        for (index = data.length - 1; index >= 0; index--) {
            if (data[index] == (byte) 0xff) {
                break;
            }
        }

        return Arrays.copyOf(data, index);
    }

    static String bytesToHexString(@NotNull byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }

        return new String(hexChars);
    }
}
