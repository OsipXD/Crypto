package ru.endlesscode.crypto.rsa;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by OsipXD on 20.12.2016
 * It is part of the Crypto.
 * All rights reserved 2014 - 2016 © «EndlessCode Group»
 */
public class RSAUtilTest {
    @Test
    public void getRandomPrime() {
        System.out.println(RSAUtil.getShortPrime());
        System.out.println(RSAUtil.getShortPrime());
    }

    @Test
    public void getLowerBytesPair() {
        int a = 0xFFFFFFFF;
        Assert.assertArrayEquals(new byte[]{(byte) 0xff, (byte) 0xff}, RSAUtil.getLowerBytesPair(a));

        a = 0x00000000;
        Assert.assertArrayEquals(new byte[]{0x00, 0x00}, RSAUtil.getLowerBytesPair(a));

        a = 0x01020304;
        Assert.assertArrayEquals(new byte[]{0x03, 0x04}, RSAUtil.getLowerBytesPair(a));
    }

    @Test
    public void bytesPairToInt() {
        byte[] a = new byte[]{(byte) 0xff, (byte) 0xff};
        Assert.assertEquals(0xffff, RSAUtil.bytesPairToInt(a));

        a = new byte[]{0x00, 0x00};
        Assert.assertEquals(0, RSAUtil.bytesPairToInt(a));

        a = new byte[]{0x03, 0x04};
        Assert.assertEquals(0x0304, RSAUtil.bytesPairToInt(a));
    }
}