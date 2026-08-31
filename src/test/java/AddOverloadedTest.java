import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class AddOverloadedTest {

    @DisplayName("add(0, obj) into an empty ArrayClass(5) -> true, object at index 0")
    @Test
    void addOverloaded_Test01() {
        ArrayClass arr = new ArrayClass(5);
        assertEquals(true, arr.add(0, new TestObject(1, "Ron")), "room available -> true");
        assertNotNull(arr.get(0), "the object should be stored at index 0");
        assertEquals("Test Object: 1 : Ron", arr.get(0).toString(), "wrong object at index 0");
    }

    @DisplayName("add(0, obj) three times -> each new object pushes the others right; first one ends at index 2")
    @Test
    void addOverloaded_Test02() {
        ArrayClass arr = new ArrayClass(5);
        boolean result = arr.add(0, new TestObject(2, "Alfred"));
        result = result && arr.add(0, new TestObject(3, "Paul"));
        result = result && arr.add(0, new TestObject(7, "Simone"));
        assertEquals(true, result, "all three inserts fit");
        assertEquals("Test Object: 7 : Simone", arr.get(0).toString(), "last inserted at 0 is first");
        assertEquals("Test Object: 3 : Paul", arr.get(1).toString(), "Paul was pushed to index 1");
        assertEquals("Test Object: 2 : Alfred", arr.get(2).toString(), "Alfred was pushed to index 2");
    }

    @DisplayName("add(index, obj) into a full ArrayClass(3) -> false and nothing changes")
    @Test
    void addOverloaded_Test03() {
        ArrayClass arr = new ArrayClass(3);
        boolean result = arr.add(0, new TestObject(1, "Ron"));
        result = result && arr.add(0, new TestObject(3, "Paul"));
        result = result && arr.add(1, new TestObject(2, "Neir"));
        assertEquals(true, result, "three inserts fill the array");
        assertEquals(false, arr.add(1, new TestObject(7, "Simone")), "array is full -> false");
        assertEquals("Test Object: 3 : Paul", arr.get(0).toString(), "index 0 unchanged after the failed insert");
        assertEquals("Test Object: 2 : Neir", arr.get(1).toString(), "index 1 unchanged after the failed insert");
        assertEquals("Test Object: 1 : Ron", arr.get(2).toString(), "index 2 unchanged after the failed insert");
    }

    @DisplayName("add(1, X) into [A, B, C, null] -> [A, X, B, C]: everything from index 1 shifts right")
    @Test
    void addOverloaded_Test04() {
        TestObject[] start = {new TestObject(1, "A"), new TestObject(2, "B"), new TestObject(3, "C"), null};
        ArrayClass arr = new ArrayClass(start);
        assertEquals(true, arr.add(1, new TestObject(9, "X")), "one free slot -> true");
        assertEquals("Test Object: 1 : A", arr.get(0).toString(), "A stays at 0");
        assertEquals("Test Object: 9 : X", arr.get(1).toString(), "X is at 1");
        assertEquals("Test Object: 2 : B", arr.get(2).toString(), "B shifted to 2");
        assertEquals("Test Object: 3 : C", arr.get(3).toString(), "C shifted to 3");
    }

    @DisplayName("add(size, obj) - inserting at the first null - behaves like an append")
    @Test
    void addOverloaded_Test05() {
        TestObject[] start = {new TestObject(1, "A"), new TestObject(2, "B"), null, null};
        ArrayClass arr = new ArrayClass(start);
        assertEquals(true, arr.add(2, new TestObject(3, "C")), "index 2 is the first free slot");
        assertEquals("Test Object: 3 : C", arr.get(2).toString(), "C at index 2");
        assertEquals("Test Object: 1 : A", arr.get(0).toString(), "A unchanged");
        assertEquals("Test Object: 2 : B", arr.get(1).toString(), "B unchanged");
        assertNull(arr.get(3), "index 3 still free");
    }

    @DisplayName("add(index, obj) when exactly one slot is free -> true, fills the array completely")
    @Test
    void addOverloaded_Test06() {
        TestObject[] start = {new TestObject(1, "A"), new TestObject(2, "B"), null};
        ArrayClass arr = new ArrayClass(start);
        assertEquals(true, arr.add(0, new TestObject(0, "Z")), "one slot free -> true");
        assertEquals("Test Object: 0 : Z", arr.get(0).toString(), "Z at 0");
        assertEquals("Test Object: 1 : A", arr.get(1).toString(), "A shifted to 1");
        assertEquals("Test Object: 2 : B", arr.get(2).toString(), "B shifted to 2");
        assertEquals(false, arr.add(0, new TestObject(5, "Q")), "now full -> false");
    }
}
