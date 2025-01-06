import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static java.util.Objects.isNull;

public class Horse {

    private static final Logger logger = LogManager.getLogger(Horse.class);
    private final String name;
    private final double speed;
    private double distance;

    public Horse(String name, double speed, double distance) {
        if (isNull(name)) {

            // Логируем если в конструктор был передан null
            logger.error("Name is null");

            throw new IllegalArgumentException("Name cannot be null.");
        } else if (name.isBlank()) {

            // Логируем если переданное в конструктор имя пустое
            logger.error("Name is blank");


            throw new IllegalArgumentException("Name cannot be blank.");
        }
        if (speed < 0) {

            // Логируем если переданная в конструктор скорость меньше нуля
            logger.error("Speed is negative");

            throw new IllegalArgumentException("Speed cannot be negative.");
        }
        if (distance < 0) {

            // Логируем если переданная в конструктор дистанция меньше нуля
            logger.error("Distance is negative");

            throw new IllegalArgumentException("Distance cannot be negative.");
        }

        this.name = name;
        this.speed = speed;
        this.distance = distance;

        // Логируем создание объекта Horse
        logger.debug("Создание Horse, имя [{}], скорость [{}]", this.name,this.speed);
    }

    public Horse(String name, double speed) {
        this(name, speed, 0);
    }

    public String getName() {
        return name;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDistance() {
        return distance;
    }

    public void move() {
        distance += speed * getRandomDouble(0.2, 0.9);
    }

    public static double getRandomDouble(double min, double max) {
        return (Math.random() * (max - min)) + min;
    }
}
