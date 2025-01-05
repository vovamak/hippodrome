import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
class HippodromeTest {
    @Test
    public void testConstructorThrowsException() {
        // Проверяем, что при передаче null и пустой lIst в конструктор выбрасывается IllegalArgumentException, с соответвующим сообщением.
        IllegalArgumentException nullException = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", nullException.getMessage());
        IllegalArgumentException emptyException = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", emptyException.getMessage());
    }
    private List<Horse> list30Horses;
    private List<Horse> ListMaxDistanceHorse;
    private Hippodrome hippodrome;

    @BeforeEach
    public void setUpTestHorsesList() {
        list30Horses =  new ArrayList<>();

        // Создаем 30 разных лошадей для выполенения теста метода getHorses
        for (int i = 1; i <= 30; i++) {
            list30Horses.add(new Horse("Horse " + i, i * 0.3, i * 0.25));
        }

        // Создаем 3 лошади для выполенения теста метода getWinner
        ListMaxDistanceHorse = new ArrayList<>();
        ListMaxDistanceHorse.add(new Horse("Horse A", 2.6, 2.7));
        ListMaxDistanceHorse.add(new Horse("Horse B", 2.9, 3.0));
        ListMaxDistanceHorse.add(new Horse("Horse C", 2.7, 2.8));

    }

    @Test
    void getHorsesTest() {
        //Проверяем, что метод getHorses() возвращает список, который содержит те же объекты и в той же последовательности, что и список который был передан в конструктор.
        hippodrome = new Hippodrome(list30Horses);
        List<Horse> returnedHorses = hippodrome.getHorses();
        assertEquals(list30Horses,returnedHorses);
    }


    @Test
    void moveTest() {
        // Создаем 50 mock лошадей для выполенения теста метода move
        List<Horse> listMockHorses = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            Horse mockHorse = Mockito.mock(Horse.class);
            listMockHorses.add(mockHorse);
        }
        //Проверяем, что метод move()  вызывает метод move у всех лошадей
        hippodrome = new Hippodrome(listMockHorses);
        hippodrome.move();
        for (Horse horse : listMockHorses) {
            verify(horse).move();
        }
    }

    @Test
    void getWinnerTest() {
        //Проверяем, что метод getWinner() возвращает лошадь с самым большим значением distance.
        hippodrome = new Hippodrome(ListMaxDistanceHorse);
        Horse winner = hippodrome.getWinner();
        assertEquals("Horse B", winner.getName());
        assertEquals(3.0, winner.getDistance());

    }
}