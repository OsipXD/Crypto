package ru.endlesscode.crypto.caesar;

import org.jetbrains.annotations.Contract;

/**
 * Created by OsipXD on 17.12.2016
 * It is part of the Crypto.
 * All rights reserved 2014 - 2016 © «EndlessCode Group»
 */
@SuppressWarnings("WeakerAccess")
public class CaesarCipher {
    private final int key;

    public CaesarCipher(int key) {
        this.key = key;
    }

    public String encrypt(String data, Alphabet alphabet) {
        char[] encrypted = data.toCharArray();
        for (int i = 0; i < encrypted.length; i++) {
            char sym = alphabet.getCharWithOffset(encrypted[i], key);

            if (sym != 0) {
                encrypted[i] = sym;
            }
        }

        return new String(encrypted);
    }

    public String decrypt(String encrypted, Alphabet alphabet) {
        char[] decrypted = encrypted.toCharArray();
        for (int i = 0; i < decrypted.length; i++) {
            char sym = alphabet.getCharWithOffset(decrypted[i], - key + alphabet.getStrength());

            if (sym != 0) {
                decrypted[i] = sym;
            }
        }

        return new String(decrypted);
    }

    public enum Alphabet {
        RUSSIAN("АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"), ENGLISH("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

        String symbols;

        Alphabet(String symbols) {
            this.symbols = symbols;
        }

        @Contract(pure = true)
        public int getStrength() {
            return symbols.length();
        }

        public char getCharWithOffset(char sym, int offset) {
            boolean isLowercase = Character.isLowerCase(sym);
            int charPos = symbols.indexOf(Character.toUpperCase(sym));
            if (charPos == -1) {
                return 0;
            }

            sym = symbols.charAt((charPos + offset) % getStrength());
            if (isLowercase) {
                sym = Character.toLowerCase(sym);
            }

            return sym;
        }
    }
}
