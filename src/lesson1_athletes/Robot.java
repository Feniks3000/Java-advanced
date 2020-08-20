package lesson1_athletes;

public class Robot implements Sportable {
    private String name;
    private Integer maxDistance;
    private Integer maxJumpHeight;

    public Robot(String name, Integer maxDistance, Integer maxJumpHeight) {
        this.name = name;
        this.maxDistance = maxDistance;
        this.maxJumpHeight = maxJumpHeight;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getMaxDistance() {
        return maxDistance;
    }

    @Override
    public Integer getMaxJumpHeight() {
        return maxJumpHeight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaxDistance(Integer maxDistance) {
        this.maxDistance = maxDistance;
    }

    public void setMaxJumpHeight(Integer maxJumpHeight) {
        this.maxJumpHeight = maxJumpHeight;
    }
}
