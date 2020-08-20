package lesson1_athletes;

public interface Sportable extends Runnable, Jumpable {
    default Boolean overcome(Obstacle obstacle) {
        if (obstacle instanceof Wall) {
            return jump(((Wall) obstacle).getHeight());
        } else {
            return run(((Treadmill) obstacle).getDistance());
        }
    }
}
