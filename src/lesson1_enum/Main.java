package lesson1_enum;

public class Main {
    public static void main(String[] args) {
        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            int workingHours = dayOfWeek.getWorkingHours();
            if (workingHours > 0) {
                System.out.printf("Сегодня %s и до выходного осталось %d часов\n", dayOfWeek, workingHours);
            } else {
                System.out.printf("Сегодня выходной %s\n", dayOfWeek);
            }
        }

        System.out.printf("\nСегодня %s и до выходного осталось %d часов\n", DayOfWeek.THURSDAY, DayOfWeek.getWorkingHours(DayOfWeek.THURSDAY));
    }
}
