package org.example;

import org.example.console.Console;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		Console console = new Console();

		while (true) {
			System.out.println(
					">>> Виберіть опцію: \n>>> 1 - Шифрування \n>>> 2 - Дешифрування \n>>> 3 - Brute Force \n>>> 4 - Завершити");
			String choice = scanner.nextLine();
			System.out.println("Ваш вибір: " + choice);
			switch (choice) {
				case "1": {
					console.startEnc(6);
					System.out.println();
					break;
				}
				case "2": {
					console.startDec(6);
					System.out.println();
					break;
				}
				case "3": {
					console.startBf();
					System.out.println();
					break;
				}
				case "4": {
					System.exit(0);
					break;
				}
				default: {
					System.out.println("Не коректне введення.");
				}
			}
		}
	}
}