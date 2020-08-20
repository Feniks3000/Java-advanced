package lesson1_athletes;

public interface Runnable {
    String getName();
    Integer getMaxDistance();
    default Boolean run(int distance) {
        if (distance > getMaxDistance()) {
            System.out.printf("\t%s не пробежал %d м, потому что это больше его возможностей (%d м)\n", getName(), distance, getMaxDistance());
            return false;
        } else {
            System.out.printf("\t%s пробежал %d м\n", getName(), distance);
            return true;
        }
    }
}
