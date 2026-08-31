import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class AddTest {

    @DisplayName("add: first add into an empty ArrayClass(5) -> true, object lands at index 0")
    @Test
    void add_Test01() {
        ArrayClass arr = new ArrayClass(5);
        assertEquals(true, arr.add(new TestObject(1, "Ron")), "there is room, so add returns true");
        assertNotNull(arr.get(0), "the object should be stored at index 0");
        assertEquals("Test Object: 1 : Ron", arr.get(0).toString(), "wrong object at index 0");
    }

    @DisplayName("add: three adds -> stored in order at 0, 1, 2 and every add returned true")
    @Test
    void add_Test02() {
        ArrayClass arr = new ArrayClass(5);
        boolean result = arr.add(new TestObject(1, "Ron"));
        result = result && arr.add(new TestObject(3, "Paul"));
        result = result && arr.add(new TestObject(7, "Simone"));
        assertEquals(true, result, "all three adds should return true");
        assertEquals("Test Object: 3 : Paul", arr.get(1).toString(), "second object added should be at index 1");
        assertEquals("Test Object: 7 : Simone", arr.get(2).toString(), "third object added should be at index 2");
        assertNull(arr.get(3), "index 3 should still be empty");
    }

    @DisplayName("add: fourth add into ArrayClass(3) -> false (full), first three still in place")
    @Test
    void add_Test03() {
        ArrayClass arr = new ArrayClass(3);
        boolean result = arr.add(new TestObject(1, "Ron"));
        result = result && arr.add(new TestObject(3, "Paul"));
        result = result && arr.add(new TestObject(2, "Neir"));
        assertEquals(true, result, "the first three adds fit and return true");
        assertEquals(false, arr.add(new TestObject(7, "Simone")), "the array is full: add must return false");
        assertEquals("Test Object: 2 : Neir", arr.get(2).toString(), "a failed add must not overwrite anything");
    }

    @DisplayName("add: filling to EXACTLY capacity -> the last add still returns true")
    @Test
    void add_Test04() {
        ArrayClass arr = new ArrayClass(2);
        assertEquals(true, arr.add(new TestObject(1, "A")), "1 of 2");
        assertEquals(true, arr.add(new TestObject(2, "B")), "2 of 2 - exactly full is still a successful add");
        assertEquals("Test Object: 2 : B", arr.get(1).toString(), "B at index 1");
    }

    @DisplayName("add: into an array that was constructed already full -> false, no exception")
    @Test
    void add_Test05() {
        TestObject[] full = {new TestObject(1, "A"), new TestObject(2, "B")};
        ArrayClass arr = new ArrayClass(full);
        assertEquals(false, arr.add(new TestObject(3, "C")), "no null slot anywhere -> false");
        assertEquals("Test Object: 1 : A", arr.get(0).toString(), "unchanged");
        assertEquals("Test Object: 2 : B", arr.get(1).toString(), "unchanged");
    }

    @DisplayName("add: into a half-full array {A, B, null, null} -> C goes to index 2, not index 0")
    @Test
    void add_Test06() {
        TestObject[] half = {new TestObject(1, "A"), new TestObject(2, "B"), null, null};
        ArrayClass arr = new ArrayClass(half);
        assertEquals(true, arr.add(new TestObject(3, "C")), "two free slots -> true");
        assertEquals("Test Object: 3 : C", arr.get(2).toString(), "C belongs in the first null slot, index 2");
        assertEquals("Test Object: 1 : A", arr.get(0).toString(), "A must not be overwritten");
        assertNull(arr.get(3), "index 3 is still free");
    }
}
