import org.junit.Test;
import ru.boxberger.Comparer;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ComparerTest {

    @Test
    public void testMainFuncWithPositive(){
        Map<String, String> todayPairs = new HashMap<>();
        todayPairs.put("page1", "html_new");
        todayPairs.put("page2", "html2");

        Map<String, String> yesterdayPairs = new HashMap<>();
        yesterdayPairs.put("page3", "html3");
        yesterdayPairs.put("page1", "html_old");

        Comparer comparer = new Comparer(todayPairs, yesterdayPairs);
        String message = comparer.createMessage();

        assertTrue(message.contains("Исчезли следующие страницы:"));
        assertTrue(message.contains("page3"));
        assertTrue(message.contains("Появились следующие новые страницы:"));
        assertTrue(message.contains("page2"));
        assertTrue(message.contains("Изменились следующие страницы:"));
        assertTrue(message.contains("page1"));
    }

    @Test
    public void testMainFuncWithEmptyList(){
        Map<String, String> todayPairs = new HashMap<>();
        Map<String, String> yesterdayPairs = new HashMap<>();

        Comparer comparer = new Comparer(todayPairs, yesterdayPairs);
        String message = comparer.createMessage();

        assertFalse(message.contains("Исчезли следующие страницы:"));
        assertFalse(message.contains("Появились следующие новые страницы:"));
        assertFalse(message.contains("Изменились следующие страницы:"));
    }
}
