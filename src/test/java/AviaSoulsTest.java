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
        Ticket t1 = new Ticket("DME", "LED", 7000, 10, 12);
        Ticket t2 = new Ticket("DME", "LED", 5000, 14, 16);
        Ticket t3 = new Ticket("DME", "LED", 6000, 18, 20);

        manager.add(t1);
        manager.add(t2);
        manager.add(t3);

        Ticket[] result = manager.search("DME", "LED");
        Ticket[] expected = {t2, t3, t1}; // Ожидаемый порядок по цене

        assertArrayEquals(expected, result);
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
        Ticket t1 = new Ticket("DME", "LED", 7000, 10, 14); // 4 часа
        Ticket t2 = new Ticket("DME", "LED", 5000, 10, 12); // 2 часа
        Ticket t3 = new Ticket("DME", "LED", 6000, 10, 13); // 3 часа

        manager.add(t1);
        manager.add(t2);
        manager.add(t3);

        Ticket[] result = manager.searchAndSortBy("DME", "LED", new TicketTimeComparator());
        Ticket[] expected = {t2, t3, t1}; // Ожидаемый порядок по времени

        assertArrayEquals(expected, result);
    }

    @Test
    public void testSearchSortedByPriceWithSamePrices() {
        AviaSouls manager = new AviaSouls();
        Ticket ticket1 = new Ticket("DME", "LED", 5000, 10, 12);
        Ticket ticket2 = new Ticket("DME", "LED", 5000, 14, 16);
        manager.add(ticket1);
        manager.add(ticket2);

        Ticket[] result = manager.search("DME", "LED");
        assertArrayEquals(new Ticket[]{ticket1, ticket2}, result);
    }
}