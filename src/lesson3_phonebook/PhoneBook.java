package lesson3_phonebook;

import java.util.*;

public class PhoneBook {
    private final Map<String, List<String>> subscribers = new HashMap<>();

    public void add(String surname, String phoneNumber) {
        List<String> phoneNumbers = subscribers.getOrDefault(surname, new ArrayList<>());
        phoneNumbers.add(phoneNumber);
        subscribers.put(surname, phoneNumbers);
    }

    public List<String> get(String surname) {
        return subscribers.getOrDefault(surname, new ArrayList<>());
    }

    public void info(String surname) {
        List<String> phoneNumbers = get(surname);
        System.out.printf("Aбонент: %s\n\tТелефонные номера:\t%s\n\n", surname, phoneNumbers.isEmpty() ? "Не найдены" : phoneNumbers);
    }
}
