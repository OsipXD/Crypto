package ru.endlesscode.crypto.blowfish;

import java.util.Arrays;

/**
 * Created by OsipXD on 17.12.2016
 * It is part of the Crypto.
 * All rights reserved 2014 - 2016 © «EndlessCode Group»
 */
class BlowfishBlock {
    private final BlowfishCipher cipher;

    private int hi;
    private int lo;

    BlowfishBlock(int hi, int lo, BlowfishCipher cipher) {
        this.cipher = cipher;
        this.hi = hi;
        this.lo = lo;
    }

    BlowfishBlock(byte[] bytes, int index, BlowfishCipher cipher) {
        this.cipher = cipher;
        this.hi = BlowfishUtils.bytesToInt(Arrays.copyOfRange(bytes, index, index + BlowfishCipher.BLOCK_SIZE / 2));
        this.lo = BlowfishUtils.bytesToInt(Arrays.copyOfRange(bytes, index + BlowfishCipher.BLOCK_SIZE / 2, index + BlowfishCipher.BLOCK_SIZE));
    }

    private void swap() {
        int tmp = this.hi;
        this.hi = this.lo;
        this.lo = tmp;
    }

    void encryptBlock() {
        for (int i = 0; i < 16; i++) {
            hi ^= cipher.getPBox()[i];
            lo ^= cipher.func(hi);
            this.swap();
        }

        this.swap();
        lo ^= cipher.getPBox()[16];
        hi ^= cipher.getPBox()[17];
    }


    void decryptBlock() {
        for (int i = 0; i < 16; i++) {
            hi ^= cipher.getPBox()[17 - i];
            lo ^= cipher.func(hi);
            this.swap();
        }

        this.swap();
        lo ^= cipher.getPBox()[1];
        hi ^= cipher.getPBox()[0];
    }

    byte[] toBytes() {
        byte[] bytes = Arrays.copyOf(BlowfishUtils.intToBytes(hi), BlowfishCipher.BLOCK_SIZE);
        System.arraycopy(BlowfishUtils.intToBytes(lo), 0, bytes, BlowfishCipher.BLOCK_SIZE / 2, BlowfishCipher.BLOCK_SIZE / 2);
        return bytes;
    }

    int getHi() {
        return hi;
    }

    int getLo() {
        return lo;
    }
}
