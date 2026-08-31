import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class RemoveTest {

    /** A FULL array - no null anywhere. */
    private TestObject[] fullArray() {
        TestObject[] tArr = {new TestObject(2, "Wilton"),
                             new TestObject(4, "Sarah"),
                             new TestObject(7, "Peter"),
                             new TestObject(5, "Agnes")};
        return tArr;
    }

    @DisplayName("remove(1) on [Wilton, Sarah, Peter, Agnes] -> returns Sarah; Peter moves to 1; index 3 becomes null")
    @Test
    void remove_Test01() {
        ArrayClass arr = new ArrayClass(fullArray());
        TestObject removed = arr.remove(1);
        assertNotNull(removed, "remove must return the removed object");
        assertEquals("Test Object: 4 : Sarah", removed.toString(), "Sarah was at index 1");
        assertEquals("Test Object: 7 : Peter", arr.get(1).toString(), "Peter shifts left into index 1");
        assertEquals("Test Object: 5 : Agnes", arr.get(2).toString(), "Agnes shifts left into index 2");
        assertNull(arr.get(3), "the last slot is null after the shift");
    }

    @DisplayName("remove(0) then remove(1) -> [Sarah, Agnes, null, null]")
    @Test
    void remove_Test02() {
        ArrayClass arr = new ArrayClass(fullArray());
        arr.remove(0);
        arr.remove(1);
        assertEquals("Test Object: 4 : Sarah", arr.get(0).toString(), "Sarah at 0");
        assertEquals("Test Object: 5 : Agnes", arr.get(1).toString(), "Agnes at 1");
        assertNull(arr.get(2), "index 2 null");
        assertNull(arr.get(3), "index 3 null");
    }

    @DisplayName("remove(1) twice -> [Wilton, Agnes, null, null]")
    @Test
    void remove_Test03() {
        ArrayClass arr = new ArrayClass(fullArray());
        arr.remove(1);
        arr.remove(1);
        assertEquals("Test Object: 2 : Wilton", arr.get(0).toString(), "Wilton at 0");
        assertEquals("Test Object: 5 : Agnes", arr.get(1).toString(), "Agnes at 1");
        assertNull(arr.get(2), "index 2 null");
    }

    @DisplayName("remove(3) on a FULL array -> returns Agnes (the last object), index 3 becomes null")
    @Test
    void remove_Test04() {
        ArrayClass arr = new ArrayClass(fullArray());
        TestObject removed = arr.remove(3);
        assertNotNull(removed, "index 3 holds Agnes");
        assertEquals("Test Object: 5 : Agnes", removed.toString(), "Agnes removed");
        assertNull(arr.get(3), "index 3 null");
        assertEquals("Test Object: 7 : Peter", arr.get(2).toString(), "Peter untouched at 2");
    }

    @DisplayName("remove(2) where index 2 is already null -> returns null and changes nothing")
    @Test
    void remove_Test05() {
        TestObject[] half = {new TestObject(1, "A"), new TestObject(2, "B"), null, null};
        ArrayClass arr = new ArrayClass(half);
        assertNull(arr.remove(2), "nothing at index 2 -> null");
        assertEquals("Test Object: 1 : A", arr.get(0).toString(), "A unchanged");
        assertEquals("Test Object: 2 : B", arr.get(1).toString(), "B unchanged");
    }

    @DisplayName("remove on an EMPTY ArrayClass(3) -> null, no exception")
    @Test
    void remove_Test06() {
        ArrayClass arr = new ArrayClass(3);
        assertNull(arr.remove(0), "empty array -> null");
        assertNull(arr.remove(2), "empty array -> null");
    }

    @DisplayName("remove(0) on a partially filled array {A, B, C, null, null} -> [B, C, null, null, null], returns A")
    @Test
    void remove_Test07() {
        TestObject[] part = {new TestObject(1, "A"), new TestObject(2, "B"), new TestObject(3, "C"), null, null};
        ArrayClass arr = new ArrayClass(part);
        assertEquals("Test Object: 1 : A", arr.remove(0).toString(), "A removed");
        assertEquals("Test Object: 2 : B", arr.get(0).toString(), "B shifted to 0");
        assertEquals("Test Object: 3 : C", arr.get(1).toString(), "C shifted to 1");
        assertNull(arr.get(2), "index 2 null - no gap and no duplicate C");
    }
}
