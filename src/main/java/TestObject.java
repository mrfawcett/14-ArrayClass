/** PROVIDED - do not change
 * A simple object holding a number and a string. ArrayClass stores these, and
 * the tests compare them through toString():
 *     new TestObject(1, "Ron").toString()  ->  "Test Object: 1 : Ron"
 */
public class TestObject {

    private int num;
    private String str;

    public TestObject(int num, String str) {
        this.num = num;
        this.str = str;
    }

    public String toString() {
        return "Test Object: " + num + " : " + str;
    }
}
