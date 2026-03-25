import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SLListTest {

    @Test
    void normalUsage() {
        SLList t1 = new SLList();
        t1.addFirst(7); // [7]
        assertEquals(7, t1.getFirst());
        t1.addFirst(4); // [4, 7]
        t1.addFirst(2); // [2, 4, 7]
        t1.addFirst(8); // [8, 2, 4, 7]
        assertEquals(4, t1.get(2));
    }

    /*
    @Test
    void exceptionThrown() {
        try {
            throw new IllegalArgumentException("IndexOutOfBoundsException");
        } catch (IllegalArgumentException e) {
            assertEquals("IndexOutOfBoundsException", e.getMessage());
        }
    }*/

    @Test
    void exceptionThrown() {
        SLList t2 = new SLList();

        assertThrows(IndexOutOfBoundsException.class, t2::getFirst);
        assertThrows(IndexOutOfBoundsException.class, () -> t2.get(0));

        t2.addFirst(42);
        assertEquals(42, t2.getFirst());
        assertThrows(IndexOutOfBoundsException.class, () -> t2.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> t2.get(-1));
    }
}
