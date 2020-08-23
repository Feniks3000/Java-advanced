package lesson1_athletes;

public class Wall implements Obstacle {
    Integer height;

    public Wall(Integer height) {
        this.height = height;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Стена высотой " + height + " метров";
    }

    @Override
    public Boolean doIt(Sportable sportable) {
        return sportable.jump(height);
    }
}
