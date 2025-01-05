import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

class MainTest {

    @Test
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    @Disabled
    void mainTest() throws Exception {
        //Проверяем, что метод main выполняется не дольше 22 секунд.
        Main.main(new String[]{});
    }
}