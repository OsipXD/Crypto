package ru.endlesscode.crypto.blowfish;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by OsipXD on 17.12.2016
 * It is part of the Blowfish.
 * All rights reserved 2014 - 2016 © «EndlessCode Group»
 */
public class BlowfishBlockTest {
    @Test
    public void encryptDecryptBlock() throws Exception {
        byte[] key = "BFA48BBC86EFDCEC".getBytes();
        BlowfishCipher cipher = new BlowfishCipher(key);

        int hi = 0xAA55AB94;
        int lo = 0xF005B1F8;

        BlowfishBlock block = new BlowfishBlock(hi, lo, cipher);
        block.encryptBlock();

        Assert.assertNotEquals(hi, block.getHi());
        Assert.assertNotEquals(lo, block.getLo());

        block.decryptBlock();

        Assert.assertEquals(hi, block.getHi());
        Assert.assertEquals(lo, block.getLo());
    }
}