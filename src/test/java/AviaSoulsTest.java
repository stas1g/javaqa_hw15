import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Comparator;

public class AviaSoulsTest {
    @Test
    public void testCompareTo() {
        Ticket ticket1 = new Ticket("DME", "LED", 5000, 10, 12);
        Ticket ticket2 = new Ticket("DME", "LED", 7000, 14, 16);
        assertTrue(ticket1.compareTo(ticket2) < 0); // 5000 < 7000
    }

    @Test
    public void testSearchSortByPrice() {
        AviaSouls manager = new AviaSouls();
        manager.add(new Ticket("DME", "LED", 7000, 10, 12));
        manager.add(new Ticket("DME", "LED", 5000, 14, 16));
        manager.add(new Ticket("DME", "LED", 6000, 18, 20));

        Ticket[] result = manager.search("DME", "LED");
        assertEquals(5000, result[0].getPrice());
        assertEquals(6000, result[1].getPrice());
        assertEquals(7000, result[2].getPrice());
    }

    @Test
    public void testTicketTimeComparator() {
        TicketTimeComparator comparator = new TicketTimeComparator();
        Ticket ticket1 = new Ticket("DME", "LED", 5000, 10, 12); // 2 часа
        Ticket ticket2 = new Ticket("DME", "LED", 7000, 14, 18); // 4 часа
        assertTrue(comparator.compare(ticket1, ticket2) < 0); // 2 < 4
    }

    @Test
    public void testSearchAndSortByTime() {
        AviaSouls manager = new AviaSouls();
        manager.add(new Ticket("DME", "LED", 7000, 10, 14)); // 4 часа
        manager.add(new Ticket("DME", "LED", 5000, 10, 12)); // 2 часа
        manager.add(new Ticket("DME", "LED", 6000, 10, 13)); // 3 часа

        Ticket[] result = manager.searchAndSortBy("DME", "LED", new TicketTimeComparator());
        assertEquals(2, result[0].getTimeTo() - result[0].getTimeFrom());
        assertEquals(3, result[1].getTimeTo() - result[1].getTimeFrom());
        assertEquals(4, result[2].getTimeTo() - result[2].getTimeFrom());
    }
}