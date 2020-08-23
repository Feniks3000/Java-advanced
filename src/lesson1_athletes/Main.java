package lesson1_athletes;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Sportable> heroes = new ArrayList<>();
        heroes.add(new Human("Вася", 100, 2));
        heroes.add(new Cat("Мурзик", 10, 2));
        heroes.add(new Robot("K700", 10000, 1));
        heroes.add(new Human("Олег", 50, 1));
        heroes.add(new Cat("Рыжик", 30, 3));
        heroes.add(new Robot("R2D2", 7000, 3));

        List<Obstacle> obstacles = new ArrayList<>();
        obstacles.add(new Treadmill(70));
        obstacles.add(new Wall(2));
        obstacles.add(new Treadmill(50));
        obstacles.add(new Wall(1));
        obstacles.add(new Treadmill(100));
        obstacles.add(new Wall(2));
        obstacles.add(new Treadmill(100));

        for (Sportable hero : heroes) {
            System.out.printf("\n\n%s приступил к прохождению полосы препятствий\n", hero.getName());
            int step = 0;
            for (Obstacle obstacle : obstacles) {
                if (obstacle.doIt(hero)) {
                    step++;
                } else {
                    System.out.printf("%s выбыл из соревнований, потому что ему не покорилась %s\n", hero.getName(), obstacle);
                    break;
                }
            }
            if (step == obstacles.size()) {
                System.out.printf("%s успешно преодолел полосу из %d препятствий\n", hero.getName(), obstacles.size());
            }
        }
    }
}
