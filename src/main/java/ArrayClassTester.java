import java.util.ArrayList;

/** PROVIDED - do not change. NOT GRADED.
 * A driver you can run from main to compare how arrays and ArrayLists behave.
 * To really show the difference, we make an ArrayClass behave sort of, kind of
 * like an ArrayList. What do we have to do to make them about as useful?
 *
 * Run it: the ArrayClass dump and the ArrayList dump should print the same
 * lines. The autograder never runs this file; it runs src/test/java.
 *
 * Note: these four tests are not independent - if removeEnd is wrong, Test
 * Four's output is affected too. BetterArrayClassTester fixes that.
 */
public class ArrayClassTester {

    public static TestObject t1 = new TestObject(1, "Harry Potter");
    public static TestObject t2 = new TestObject(2, "Hermione Granger");
    public static TestObject t3 = new TestObject(3, "Ron Weasley");
    public static TestObject t4 = new TestObject(4, "Minerva McGonagall");
    public static TestObject t5 = new TestObject(5, "Albus Dumbledore");
    public static TestObject t6 = new TestObject(6, "Ginny Weasley");
    public static TestObject t7 = new TestObject(7, "Draco Malfoy");

    public static void main(String[] args) {

        System.out.println("Welcome to the ArrayClassTester!");
        System.out.println();
        System.out.println("You will see this class print out the contents of ArrayClass and ArrayList.");
        System.out.println("THEY SHOULD BE THE SAME.");
        System.out.println("That will be when you know your code works.");
        System.out.println();
        System.out.println();
        System.out.println("Test One - Just adding stuff: ***********************************************************");

        // We need a class for arrays since arrays don't do add and remove
        ArrayClass arr = new ArrayClass();

        // We do *not* need a class for ArrayList since it already does all that.
        // We do specify what kind of object our ArrayList will hold.
        ArrayList<TestObject> arrList = new ArrayList<TestObject>();

        // Let's add some stuff -- add adds to the end
        arr.add(t1);
        arrList.add(t1);

        arr.add(t2);
        arrList.add(t2);

        // Now let's add to the front
        arr.add(0, t3);
        arrList.add(0, t3);

        // Now let's see what we have
        arr.dump();
        dump(arrList);

        System.out.println();
        System.out.println("You should have seen the contents of the ArrayClass and ArrayList as being exactly the same.");
        System.out.println();
        System.out.println("Test Two - Adding to the middle : ***********************************************************");

        // Now let's add to the middle
        arr.add(1, t4);
        arrList.add(1, t4);

        arr.dump();
        dump(arrList);

        System.out.println();
        System.out.println("You should have seen the contents of the ArrayClass and ArrayList as being exactly the same.");
        System.out.println();

        System.out.println("Test Three - Removing some stuff : ***********************************************************");
        // Let's remove some stuff -- remove from the end
        arr.removeEnd();
        arrList.remove(3);

        arr.dump();
        dump(arrList);
        System.out.println();
        System.out.println("You should have seen the contents of the ArrayClass and ArrayList as being exactly the same.");
        System.out.println();
        System.out.println("Test Four - Removing from the beginning : ***********************************************************");
        // Let's remove from the beginning!
        arr.remove(0);
        arrList.remove(0);

        arr.dump();
        dump(arrList);
        System.out.println();
        System.out.println("You should have seen the contents of the ArrayClass and ArrayList as being exactly the same.");
        System.out.println();
    }

    /** Prints the contents of an ArrayList of TestObjects in the same format as ArrayClass.dump(). */
    public static void dump(ArrayList<TestObject> a) {
        System.out.println("Printing contents of ArrayList:");
        for (int i = 0; i < a.size(); i++) {
            System.out.println(a.get(i));
        }
    }
}
