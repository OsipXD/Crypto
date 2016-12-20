package ru.endlesscode.crypto.rsa;

import org.jetbrains.annotations.Contract;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by OsipXD on 20.12.2016
 * It is part of the Crypto.
 * All rights reserved 2014 - 2016 © «EndlessCode Group»
 */
public class RSAKeyPair {
    private final Key privateKey, publicKey;

    private RSAKeyPair(int p, int q) {
        int n = p * q;
        int fi = (p - 1) * (q - 1);

        Random random = new Random();
        char e;
        do {
            e = (char) (random.nextInt(fi - 1) + 1);
        } while (e % p == 0 || e % q == 0);

        this.publicKey = new Key(e, n);
        char d = 0;
        for (int k = 1; d == 0 || d % e != 0; k++) {
            d = (char) (k * fi + 1);
        }
        d /= e;

        this.privateKey = new Key(d, n);
    }

    @Contract(" -> !null")
    public static RSAKeyPair generate() {
        return new RSAKeyPair(RSAUtil.getShortPrime(), RSAUtil.getShortPrime());
    }

    public Key getPrivateKey() {
        return privateKey;
    }

    public Key getPublicKey() {
        return publicKey;
    }

    static class Key {
        private final int power;
        private final int module;

        Key(int power, int module) {
            this.power = power;
            this.module = module;
        }

        public int modPow(int value) {
            return BigInteger.valueOf(value).modPow(BigInteger.valueOf(power), BigInteger.valueOf(module)).intValue();
        }
    }
}
