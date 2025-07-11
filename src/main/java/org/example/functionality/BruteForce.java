package org.example.functionality;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BruteForce {
	Decryption decryption = new Decryption();

	private void bruteForce(Path inputPath) throws IOException {
		String encryptedText = Files.readString(inputPath);
		Set<String> popularWords = new HashSet<>(Arrays.asList(
				"не", "на", "що", "це", "до", "ти", "він", "ми", "як", "та",
				"ж", "вони", "її", "був", "за", "але", "вона", "ще", "вже", "коли",
				"його", "всі", "так", "тебе", "мене", "про", "або", "де", "щоб", "тоді",
				"якщо", "дуже", "ось", "тому", "без", "навіть", "після", "між", "свій", "кожен",
				"нічого", "щось", "тільки", "зараз", "можна", "треба", "хоч", "раз", "знову", "інший",
				"перший", "день", "ніч", "рік", "час", "людина", "життя", "очі", "рука", "слово",
				"голос", "серце", "думка", "шлях", "світ", "дім", "місто", "країна", "війна", "мир",
				"правда", "любов", "страх", "радість", "біль", "надія", "мрія", "сила", "воля", "честь",
				"пам’ять", "майбутнє", "минуле", "сьогодні", "завтра", "вчора", "завжди", "ніколи", "іноді", "часто",
				"далі", "разом", "біля", "хтось", "інколи", "завдяки", "згідно", "проти", "понад", "поруч",
				"далеко", "близько", "навіщо", "чому", "якраз", "щойно", "щасливо", "сумно", "весело", "страшно",
				"цікаво", "швидко", "повільно", "голосно", "тихо", "раптом", "особливо", "звичайно", "можливо",
				"просто",
				"однак", "хоча", "зокрема", "наприклад", "майже", "перед", "через", "під", "над", "навколо"));
		// Перебираємо ключі від 1 до 99
		for (int k = 1; k < 100; k++) {
			String candidate = decryption.decrypt(encryptedText, k);
			String[] words = candidate.toLowerCase().split("[\\s.,!?:;\"«»()\\-\\n\\t]+");
			for (String word : words) {
				if (popularWords.contains(word)) {
					Path outputPath = Path.of(inputPath.toString().replace(".txt", "_BFdecrypted.txt"));
					Files.writeString(outputPath, candidate);
					System.out.println("Розшифровано з ключем: " + k);
					System.out.println(">>> Результат збережено у: " + outputPath);
					return;
				}
			}
		}
		System.out.println("Розшифрувати не вдалося.");
	}

	public void startBruteForce() throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Введи шлях до файлу:");
		String inputPath = sc.nextLine();
		bruteForce(Path.of(inputPath));
	}
}