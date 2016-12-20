package ru.endlesscode.crypto.rsa;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * Created by OsipXD on 20.12.2016
 * It is part of the Crypto.
 * All rights reserved 2014 - 2016 © «EndlessCode Group»
 */
class RSAUtil {
    final private static char[] hexArray = "0123456789ABCDEF".toCharArray();

    static short getShortPrime() {
        Random random = new Random();
        short prime;

        do {
            prime = (short) random.nextInt(256);
        } while (!isPrime(prime));

        return prime;
    }

    private static boolean isPrime(short num) {
        if (num <= 3 || num % 2 == 0) {
            return num <= 3;
        }

        for (short divisor = 3; divisor < Math.sqrt(num); divisor += 2) {
            if (num % divisor == 0) {
                return false;
            }
        }

        return true;
    }

    @Contract("_ -> !null")
    static byte[] getLowerBytesPair(int value) {
        return new byte[]{(byte) ((value >>> 8) & 0xFF), (byte) (value & 0xFF)};
    }

    @Contract(pure = true)
    static int bytesPairToInt(byte[] bytes) {
        return (bytes[0] & 0x0FF) << 8 | (bytes[1] & 0x0FF);
    }

    static String bytesToHexString(@NotNull byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }

        return new String(hexChars);
    }
}
