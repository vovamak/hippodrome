import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mockStatic;

class HorseTest {
    private static Stream<String> emptyStrings() {
        return Stream.of("", "   ", "\t", "\n", "\r");
    }


    @Test
    public void testConstructorThrowsExceptionWhenValueIsNull() {
        // Проверяем, что при передаче null в конструктор выбрасывается IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> new Horse(null,0,0));
    }
    @Test
    public void testConstructorMessageWhenValueIsNull() {
        // Проверяем, что при передаче null в конструктор выброшенное исключение будет содержать сообщение "Name cannot be null."
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null,1.2));
        assertEquals("Name cannot be null.", exception.getMessage());
    }
    @ParameterizedTest
    @MethodSource("emptyStrings")
    public void testConstructorThrowsExceptionWhenEmptyString(String param) {
        // Проверяем, что при передаче пустой строки или строки содержащей только пробельные символы в конструктор выбрасывается IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> new Horse(param,1.33));

    }
    @ParameterizedTest
    @MethodSource("emptyStrings")
    public void testConstructorMessageWhenEmptyString(String param) {
        // Проверяем, что при передаче пустой строки или строки содержащей только пробельные символы в конструктор выброшенное исключение будет содержать сообщение "Name cannot be blank."
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(param,1.33));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }
    @Test
    public void testConstructorThrowsExceptionWithNegativeSpeedValue() {
        // Проверяем, что при передаче в конструктор вторым параметром отрицательного числа, будет выброшено IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> new Horse("Horse with negative speed",-1.33));
    }
    @Test
    public void testConstructorMessageWithNegativeSpeedValue() {
        // Проверяем, что при передаче в конструктор вторым параметром отрицательного числа, исключение будет содержать сообщение "Speed cannot be negative."
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Horse with negative speed",-1.33));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }
    @Test
    public void testConstructorThrowsExceptionWithNegativeDistanceValue() {
        // Проверяем, что при передаче в конструктор третьим параметром отрицательного числа, будет выброшено IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> new Horse("Horse with negative distance",1.3,-2));
    }
    @Test
    public void testConstructorMessageWithNegativeDistanceValue() {
        // Проверяем, что при передаче в конструктор третьим параметром отрицательного числа, исключение будет содержать сообщение "Distance cannot be negative."
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Horse with negative distance",1.2,-3.5));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }
    private Horse horseWithDistance;
    private Horse horseNoDistance;
    @BeforeEach
    public void setUpTestHorse() {
        horseNoDistance = new Horse("test string name no distance",2.66);
        horseWithDistance = new Horse("test string name with distance",1.95,16.7);
    }
    @Test
    public void testGetName() {
        // Проверяем, что метод возвращает строку, которая была передана первым параметром в конструктор;
        String result1 = horseNoDistance.getName();
        String result2 = horseWithDistance.getName();
        assertEquals("test string name no distance", result1);
        assertEquals("test string name with distance", result2);
    }
    @Test
    public void testGetSpeed() {
        // Проверяем, что метод возвращает число, которое было передано вторым параметром в конструктор;
        double result1 = horseNoDistance.getSpeed();
        double result2 = horseWithDistance.getSpeed();
        assertEquals(2.66, result1);
        assertEquals(1.95, result2);
    }
    @Test
    public void testGetDistance() {
        // Проверяем, что метод возвращает число, которое было передано третьим параметром в конструктор;
        double result = horseWithDistance.getDistance();
        assertEquals(16.7, result);
    }
    @Test
    public void testGetDistanceNoParam() {
        // Проверяем, что метод возвращает ноль, если объект был создан с помощью конструктора с двумя параметрами;
        double result = horseNoDistance.getDistance();
        assertEquals(0, result);
    }
    @Test
    public void testMove() {
        // Проверяем, что метод Horse.move() вызывает внутри метод getRandomDouble с параметрами 0.2 и 0.9;
        try (MockedStatic<Horse> theMock = mockStatic(Horse.class)) {
            horseWithDistance.move();
            theMock.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }
    @ParameterizedTest
    @CsvSource({
            "1.0, 2.0, 0.5, 2.0",
            "2.0, 3.0, 0.8, 4.4",
            "0.0 ,4.0, 0.3, 1.2"
    })
    public void testMove(double initialDistance, double speed, double randomValue, double expectedDistance) {
        //Проверяем, что метод Horse.move() присваивает дистанции значение высчитанное по формуле: distance + speed * getRandomDouble(0.2, 0.9).
        Horse myHorse =new Horse("Horse test",speed,initialDistance);
        try (var mock = mockStatic(Horse.class)) {
            mock.when(() -> Horse.getRandomDouble(anyDouble(), anyDouble())).thenReturn(randomValue);
            myHorse.move();
            assertEquals(expectedDistance, myHorse.getDistance());
        }


    }











}