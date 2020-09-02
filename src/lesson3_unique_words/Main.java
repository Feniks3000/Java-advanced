package lesson3_unique_words;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Введи слова через пробел или нажми Enter, чтобы воспользоваться тестовым набором:");
        String input = in.nextLine();
        if (StringUtils.isEmpty(input)) {
            input = "ананас апельсин манго виноград абрикос персик манго яблоко дыня арбуз ананас вишня черника малина манго";
        }
        String[] allWords = input.toLowerCase().split(" ");
        System.out.println("Все слова: " + Arrays.toString(allWords));

        Map<String, Integer> uniqueWords = new HashMap<>();
        for (String word : allWords) {
            uniqueWords.put(word, uniqueWords.getOrDefault(word, 0) + 1);
        }
        System.out.println("Уникальные слова и их количество в строке: " + uniqueWords);
    }
}
