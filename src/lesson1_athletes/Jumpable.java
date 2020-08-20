package lesson1_athletes;

public interface Jumpable {
    String getName();
    Integer getMaxJumpHeight();
    default Boolean jump(int height) {
        if (height > getMaxJumpHeight()) {
            System.out.printf("\t%s не прыгнул на %d м, потому что это больше его возможностей (%d м)\n", getName(), height, getMaxJumpHeight());
            return false;
        } else {
            System.out.printf("\t%s прыгнул на %d м\n", getName(), height);
            return true;
        }
    }
}
