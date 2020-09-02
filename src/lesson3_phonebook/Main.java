package lesson3_phonebook;

public class Main {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Иванов", "911");
        phoneBook.add("Иванов", "912");
        phoneBook.add("Петров", "03");
        phoneBook.add("Сидоров", "02");
        phoneBook.add("Ефремов", "01");
        phoneBook.add("Ефремов", "112");
        phoneBook.info("Загогулин");
        phoneBook.info("Иванов");
        phoneBook.info("Ефремов");
        phoneBook.info("Сидоров");
        phoneBook.info("Петров");
    }
}
