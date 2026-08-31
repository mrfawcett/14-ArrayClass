import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class DumpTest {

    private PrintStream originalOut;
    private ByteArrayOutputStream captured;

    @BeforeEach
    void captureOutput() {
        originalOut = System.out;
        captured = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captured));
    }

    @AfterEach
    void restoreOutput() {
        System.setOut(originalOut);
    }

    /** Everything printed so far, with Windows line endings normalized to \n. */
    private String output() {
        System.out.flush();
        return captured.toString().replace("\r\n", "\n");
    }

    @DisplayName("dump on an empty ArrayClass -> only the header line")
    @Test
    void dump_Test01() {
        ArrayClass arr = new ArrayClass(4);
        arr.dump();
        assertEquals("Printing contents of ArrayClass:\n", output(),
                "an empty ArrayClass prints the header and nothing else - no \"null\" lines");
    }

    @DisplayName("dump on [Ron] -> header then one object line")
    @Test
    void dump_Test02() {
        TestObject[] one = {new TestObject(1, "Ron"), null, null};
        ArrayClass arr = new ArrayClass(one);
        arr.dump();
        assertEquals("Printing contents of ArrayClass:\nTest Object: 1 : Ron\n", output(),
                "header, then the object's toString on its own line, then stop at the first null");
    }

    @DisplayName("dump on a FULL array of three -> header then three lines in order")
    @Test
    void dump_Test03() {
        TestObject[] full = {new TestObject(1, "A"), new TestObject(2, "B"), new TestObject(3, "C")};
        ArrayClass arr = new ArrayClass(full);
        arr.dump();
        String expected = "Printing contents of ArrayClass:\n"
                + "Test Object: 1 : A\n"
                + "Test Object: 2 : B\n"
                + "Test Object: 3 : C\n";
        assertEquals(expected, output(), "a full array prints every element - do not crash on findFirstNull() == -1");
    }

    @DisplayName("dump on [A, B, null, null, null] -> two lines, no \"null\" lines for the empty slots")
    @Test
    void dump_Test04() {
        TestObject[] part = {new TestObject(1, "A"), new TestObject(2, "B"), null, null, null};
        ArrayClass arr = new ArrayClass(part);
        arr.dump();
        String expected = "Printing contents of ArrayClass:\n"
                + "Test Object: 1 : A\n"
                + "Test Object: 2 : B\n";
        assertEquals(expected, output(), "stop printing at the first null");
    }

    @DisplayName("dump does not change the contents")
    @Test
    void dump_Test05() {
        TestObject[] part = {new TestObject(1, "A"), new TestObject(2, "B"), null};
        ArrayClass arr = new ArrayClass(part);
        arr.dump();
        assertEquals("Test Object: 1 : A", arr.get(0).toString(), "A still at 0");
        assertEquals("Test Object: 2 : B", arr.get(1).toString(), "B still at 1");
        assertNull(arr.get(2), "index 2 still null");
    }
}
