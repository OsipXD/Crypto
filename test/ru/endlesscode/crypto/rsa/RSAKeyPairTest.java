package ru.endlesscode.crypto.rsa;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by OsipXD on 20.12.2016
 * It is part of the Crypto.
 * All rights reserved 2014 - 2016 © «EndlessCode Group»
 */
public class RSAKeyPairTest {
    @Test
    public void keyTest() {
        RSAKeyPair.Key key = new RSAKeyPair.Key(5, 91);

        Assert.assertEquals(29, key.modPow(22));
    }
}