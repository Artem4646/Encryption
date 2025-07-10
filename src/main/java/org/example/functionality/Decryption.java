package org.example.functionality;

import java.io.IOException;

public class Decryption {
    public int key;
    public Decryption() {

    }

    public Decryption(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String decrypt(String str) throws IOException {

//        АЛФАВІТ
        char[] isUppercase = "АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯ".toCharArray();
        char[] isLowercase = "абвгґдеєжзиіїйклмнопрстуфхцчшщьюя".toCharArray();
        char[] isDigital   = "0123456789".toCharArray();
        char[] isPunctuation = {
                '.', ',', ';', ':', '…', '?', '!', '(', ')', '"',
                '\'', '’', 'ʼ', '‘', '-', '—', '/', '«', '»', '[', ']',
                '{', '}', ' ', '\n', '\t'
        };

        char[] strCharArray = str.toCharArray();
        char[] result = new char[strCharArray.length];

        for (int i = 0; i < strCharArray.length; i++) {
            boolean replaced = false;
            if (Character.isUpperCase(strCharArray[i])) {
                for (int j = 0; j < isUppercase.length; j++) {
                    if (isUppercase[j] == strCharArray[i]) {
                        int length = isUppercase.length;
                        result[i] = isUppercase[((j - key) % length + length) % length];
                        replaced = true;
                        break;
                    }
                }
            }
            if (replaced) continue;
            else if (Character.isLowerCase(strCharArray[i])) {
                for (int j = 0; j < isLowercase.length; j++) {
                    if (isLowercase[j] == strCharArray[i]) {
                        int length = isLowercase.length;
                        result[i] = isLowercase[((j - key) % length + length) % length];
                        replaced = true;
                        break;
                    }
                }
            }
            if (replaced) continue;
            else if (Character.isDigit(strCharArray[i])) {
                for (int j = 0; j < isDigital.length; j++) {
                    if (isDigital[j] == strCharArray[i]) {
                        int length = isDigital.length;
                        result[i] = isDigital[((j - key) % length + length) % length];
                        replaced = true;
                        break;
                    }
                }
            }
            if (replaced) continue;
            else {
                for (int j = 0; j < isPunctuation.length; j++) {
                    if (isPunctuation[j] == strCharArray[i]) {
                        int length = isPunctuation.length;
                        result[i] = isPunctuation[((j - key) % length + length) % length];
                        replaced = true;
                        break;
                    }
                }
            }
            if (!replaced) {
                result[i] = strCharArray[i];
            }
        }
        return new String(result);
    }
}
