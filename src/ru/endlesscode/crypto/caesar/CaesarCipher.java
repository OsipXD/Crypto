package ru.endlesscode.crypto.caesar;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Created by OsipXD on 17.12.2016
 * It is part of the Blowfish.
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
        char[] alphabetChars = alphabet.getChars();

        for (int i = 0; i < encrypted.length; i++) {
            boolean isLowercase = Character.isLowerCase(encrypted[i]);
            int charPos = alphabet.getCharPosition(encrypted[i]);

            if (charPos != -1) {
                encrypted[i] = alphabetChars[(charPos + key) % alphabet.getStrength()];

                if (isLowercase) {
                    encrypted[i] = Character.toLowerCase(encrypted[i]);
                }
            }
        }

        return new String(encrypted);
    }

    public String decrypt(String encrypted, Alphabet alphabet) {
        char[] decrypted = encrypted.toCharArray();
        char[] alphabetChars = alphabet.getChars();

        for (int i = 0; i < decrypted.length; i++) {
            boolean isLowercase = Character.isLowerCase(decrypted[i]);
            int charPos = alphabet.getCharPosition(decrypted[i]);

            if (charPos != -1) {
                decrypted[i] = alphabetChars[(charPos - key + alphabet.getStrength()) % alphabet.getStrength()];

                if (isLowercase) {
                    decrypted[i] = Character.toLowerCase(decrypted[i]);
                }
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

        @NotNull
        public char[] getChars() {
            return symbols.toCharArray();
        }

        @Contract(pure = true)
        public int getStrength() {
            return symbols.length();
        }

        @Contract(pure = true)
        public int getCharPosition(char sym) {
            return symbols.indexOf(Character.toUpperCase(sym));
        }
    }
}
