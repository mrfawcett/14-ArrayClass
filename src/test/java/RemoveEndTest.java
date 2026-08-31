import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class RemoveEndTest {

    /** A FULL array - no null anywhere. findFirstNull() returns -1 for it. */
    private TestObject[] fullArray() {
        TestObject[] tArr = {new TestObject(2, "Wilton"),
                             new TestObject(4, "Sarah"),
                             new TestObject(7, "Peter"),
                             new TestObject(5, "Agnes")};
        return tArr;
    }

    @DisplayName("removeEnd on a FULL array -> returns Agnes, index 3 becomes null, index 2 untouched")
    @Test
    void removeEnd_Test01() {
        ArrayClass arr = new ArrayClass(fullArray());
        TestObject removed = arr.removeEnd();
        assertNotNull(removed, "removeEnd must return the removed object, not null - the array was full (findFirstNull() == -1)");
        assertEquals("Test Object: 5 : Agnes", removed.toString(), "the last object is Agnes");
        assertNull(arr.get(3), "the slot Agnes was in should now be null");
        assertEquals("Test Object: 7 : Peter", arr.get(2).toString(), "Peter is now the last object");
    }

    @DisplayName("removeEnd twice on a full array -> Sarah is last, index 2 null")
    @Test
    void removeEnd_Test02() {
        ArrayClass arr = new ArrayClass(fullArray());
        arr.removeEnd();
        TestObject second = arr.removeEnd();
        assertNotNull(second, "second removeEnd should return Peter");
        assertEquals("Test Object: 7 : Peter", second.toString(), "second removal returns Peter");
        assertNull(arr.get(2), "index 2 should be null after two removals");
        assertEquals("Test Object: 4 : Sarah", arr.get(1).toString(), "Sarah is now the last object");
    }

    @DisplayName("removeEnd three times -> only Wilton remains at index 0")
    @Test
    void removeEnd_Test03() {
        ArrayClass arr = new ArrayClass(fullArray());
        arr.removeEnd();
        arr.removeEnd();
        arr.removeEnd();
        assertNull(arr.get(1), "index 1 should be null after three removals");
        assertEquals("Test Object: 2 : Wilton", arr.get(0).toString(), "Wilton is the only one left");
    }

    @DisplayName("removeEnd on a partially filled array {A, B, null, null} -> returns B (the last OBJECT, not arr[length-1])")
    @Test
    void removeEnd_Test04() {
        TestObject[] half = {new TestObject(1, "A"), new TestObject(2, "B"), null, null};
        ArrayClass arr = new ArrayClass(half);
        TestObject removed = arr.removeEnd();
        assertNotNull(removed, "there are objects to remove");
        assertEquals("Test Object: 2 : B", removed.toString(), "B is the last object; index 3 is just an empty slot");
        assertNull(arr.get(1), "B's slot should be null now");
        assertEquals("Test Object: 1 : A", arr.get(0).toString(), "A untouched");
    }

    @DisplayName("removeEnd on an EMPTY ArrayClass(3) -> null, no exception")
    @Test
    void removeEnd_Test05() {
        ArrayClass arr = new ArrayClass(3);
        assertNull(arr.removeEnd(), "nothing to remove -> return null (firstNull is 0, do not read arr[-1])");
        assertNull(arr.get(0), "still empty");
    }

    @DisplayName("removeEnd until empty, then once more -> the extra call returns null")
    @Test
    void removeEnd_Test06() {
        TestObject[] one = {new TestObject(1, "Solo")};
        ArrayClass arr = new ArrayClass(one);
        assertEquals("Test Object: 1 : Solo", arr.removeEnd().toString(), "removes the only object (full array of length 1)");
        assertNull(arr.get(0), "now empty");
        assertNull(arr.removeEnd(), "empty -> null");
    }
}
