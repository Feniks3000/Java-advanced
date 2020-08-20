package lesson1_enum;

public enum DayOfWeek {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

    int getWorkingHours() {
        return getWorkingHours(this);
    }

    static int getWorkingHours(DayOfWeek day) {
        switch (day) {
            case SATURDAY:
            case SUNDAY:
                return 0;
            default:
                return (5 - day.ordinal()) * 8;
        }
    }
}
