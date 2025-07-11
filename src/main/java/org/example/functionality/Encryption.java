package org.example.functionality;

import org.example.Alphabet;

import java.io.IOException;

public class Encryption {

	public String encrypt(String str, int key) throws IOException {

		char[] charArray = str.toCharArray();
		char[] result = new char[charArray.length];

		for (int i = 0; i < charArray.length; i++) {
			int length = Alphabet.ALPHABET.length();
			for (int j = 0; j < length; j++) {
				if (Alphabet.ALPHABET.charAt(j) == charArray[i]) {
					result[i] = Alphabet.ALPHABET.charAt((j + key) % length);
					break;
				}
			}
		}
		return new String(result);
	}
}
