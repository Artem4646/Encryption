package org.example.console;

import org.example.functionality.BruteForce;
import org.example.functionality.Decryption;
import org.example.functionality.Encryption;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Console {
	public void startEnc(int key) throws IOException {
		Path inputPath = Path
				.of("/Users/quiet/Desktop/Encryption/src/main/java/org/example/textFiles/startFileText.txt");
		Path outputPath = Path.of(inputPath.toString().replace(".txt", "_Encrypted.txt"));

		String currentText = Files.readString(inputPath);
		Encryption encryption = new Encryption();
		String result = encryption.encrypt(currentText, 6);
		Files.writeString(outputPath, result);
		System.out.println("Encrypted: \n" + result);
		System.out.println(">>> Результат збережено у: " + outputPath);
	}

	public void startDec(int key) throws IOException {
		Path inputPath = Path
				.of("/Users/quiet/Desktop/Encryption/src/main/java/org/example/textFiles/startFileText_Encrypted.txt");
		Path outputPath = Path.of((inputPath.toString().replace(".txt", "_Decrypted.txt")));

		String currentText = Files.readString(inputPath);
		Decryption decryption = new Decryption();
		String result = decryption.decrypt(currentText, 6);
		Files.writeString(outputPath, result);
		System.out.println("Decrypted: \n" + result);
		System.out.println(">>> Результат збережено у: " + outputPath);
	}

	public void startBf() throws IOException {
		BruteForce bruteForce = new BruteForce();
		bruteForce.startBruteForce();
	}
}
