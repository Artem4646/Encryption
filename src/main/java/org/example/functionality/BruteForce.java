package org.example.functionality;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BruteForce {
    private int key = 1;

    public BruteForce() {
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    // Метод для розшифрування тексту з використанням ключа
    public String decryptWithKey(String text, int key) {
        char[] isUppercase = "АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯ".toCharArray();
        char[] isLowercase = "абвгґдеєжзиіїйклмнопрстуфхцчшщьюя".toCharArray();
        char[] isDigital = "0123456789".toCharArray();
        char[] isPunctuation = {
                '.', ',', ';', ':', '…', '?', '!', '(', ')', '"',
                '\'', '’', 'ʼ', '‘', '-', '—', '/', '«', '»', '[', ']',
                '{', '}', ' ', '\n', '\t'
        };
        char[] strCharArray = text.toCharArray();
        char[] result = new char[strCharArray.length];

        for (int i = 0; i < strCharArray.length; i++) {
            boolean replaced = false;

            // Перевірка великих літер
            for (int j = 0; j < isUppercase.length; j++) {
                if (strCharArray[i] == isUppercase[j]) {
                    int length = isUppercase.length;
                    result[i] = isUppercase[((j - key) % length + length) % length];
                    replaced = true;
                    break;
                }
            }
            if (replaced) continue;

            // Перевірка малих літер
            for (int j = 0; j < isLowercase.length; j++) {
                if (strCharArray[i] == isLowercase[j]) {
                    int length = isLowercase.length;
                    result[i] = isLowercase[((j - key) % length + length) % length];
                    replaced = true;
                    break;
                }
            }
            if (replaced) continue;

            // Перевірка цифр
            for (int j = 0; j < isDigital.length; j++) {
                if (strCharArray[i] == isDigital[j]) {
                    int length = isDigital.length;
                    result[i] = isDigital[((j - key) % length + length) % length];
                    replaced = true;
                    break;
                }
            }
            if (replaced) continue;

            // Перевірка розділових знаків
            for (int j = 0; j < isPunctuation.length; j++) {
                if (strCharArray[i] == isPunctuation[j]) {
                    int length = isPunctuation.length;
                    result[i] = isPunctuation[((j - key) % length + length) % length];
                    replaced = true;
                    break;
                }
            }
            if (!replaced) {
                result[i] = strCharArray[i];
            }
        }
        return new String(result);
    }

    public void bruteForce(Path inputPath) throws IOException {
        String encryptedText = Files.readString(inputPath);

        Path outputPath = Path.of(inputPath.toString().replace(".txt", "_BFdecrypted.txt"));

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
                "цікаво", "швидко", "повільно", "голосно", "тихо", "раптом", "особливо", "звичайно", "можливо", "просто",
                "однак", "хоча", "зокрема", "наприклад", "майже", "перед", "через", "під", "над", "навколо"
        ));

        // Перебираємо ключі від 1 до 99
        for (int k = 1; k < 100; k++) {
            String candidate = decryptWithKey(encryptedText, k);

            // Перетворюємо текст у нижній регістр і розбиваємо на слова
            String[] words = candidate.toLowerCase().split("[\\s.,!?:;\"«»()\\-\\n\\t]+");

            // Перевіряємо чи є хоч одне слово зі словника
            for (String word : words) {
                if (popularWords.contains(word)) {
                    this.key = k;
                    Files.writeString(outputPath, candidate);
                    System.out.println("Розшифровано з ключем: " + k);
                    System.out.println(">>> Результат збережено у: " + outputPath);
                    return;
                }
            }
        }

        System.out.println("Розшифрувати не вдалося.");
    }

    // Метод запуску з консолі для вводу шляху
    public void startBruteForce() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введи шлях до файлу:");
        String inputPath = sc.nextLine();
        bruteForce(Path.of(inputPath));
    }
}