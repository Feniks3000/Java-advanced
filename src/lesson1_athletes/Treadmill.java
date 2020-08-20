package lesson1_athletes;

public class Treadmill implements Obstacle {
    Integer distance;

    public Treadmill(Integer distance) {
        this.distance = distance;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Беговая дорожка на " + distance + " метров";
    }
}
