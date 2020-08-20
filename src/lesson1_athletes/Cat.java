package lesson1_athletes;

public class Cat implements Sportable {
    private String name;
    private Integer maxDistance;
    private Integer maxJumpHeight;

    public Cat(String name, Integer maxDistance, Integer maxJumpHeight) {
        this.name = name;
        this.maxDistance = maxDistance;
        this.maxJumpHeight = maxJumpHeight;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Integer getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(Integer maxDistance) {
        this.maxDistance = maxDistance;
    }

    @Override
    public Integer getMaxJumpHeight() {
        return maxJumpHeight;
    }

    public void setMaxJumpHeight(Integer maxJumpHeight) {
        this.maxJumpHeight = maxJumpHeight;
    }
}
