/** READ FIRST
 * The purpose of this class is to mimic what an ArrayList does - add to the
 * end, insert at an index, remove from the end, remove at an index - using
 * nothing but a plain array.
 *
 * As you know, if you want anything done with arrays in Java you have to do
 * it all yourself. And that does mean DO IT YOURSELF: this class may NOT use
 * ArrayList, the Arrays class, System.arraycopy, or any other shortcut that
 * does the work for you. Loops and index arithmetic only.
 *
 * ---- How the array is used ----
 * arr has a fixed length (its CAPACITY). The objects live in arr[0], arr[1],
 * ... with no gaps, and every unused slot is null. So the number of objects
 * currently stored (the SIZE) is the index of the first null - or arr.length
 * when the array is completely full.
 *
 *     capacity 5, size 3:   [A, B, C, null, null]
 *     capacity 4, size 4:   [A, B, C, D]           <- FULL: no null anywhere
 *
 * Three helper methods are PROVIDED:
 *   findFirstNull()   index of the first null, or -1 if the array is FULL
 *   moveForward(i)    shifts arr[i .. size-1] one slot to the right to open a
 *                     hole at i; returns false (and moves nothing) if there is
 *                     no room
 *   moveBack(i)       shifts arr[i+1 .. end] one slot to the left, closing the
 *                     hole at i, and nulls the last slot
 *
 * TRAP: findFirstNull() returns -1 on a FULL array. Any method that computes
 * "the last object is at findFirstNull() - 1" will read arr[-2] and crash when
 * the array is full. The tests build full arrays on purpose with the
 * ArrayClass(TestObject[]) constructor. Handle the -1 case: when the array is
 * full, the last object is at arr.length - 1.
 *
 * ---- What you write ----
 *   add(obj)          append; false if full
 *   add(index, obj)   insert at index, shifting later objects right; false if full
 *   dump()            print the header line then every object, one per line
 *   removeEnd()       remove and return the last object; null if empty
 *   remove(index)     remove and return arr[index], shifting later objects left;
 *                     null if there is nothing at index
 *
 * ArrayClassTester and BetterArrayClassTester are drivers you can run from
 * main to compare your ArrayClass with a real ArrayList side by side. They
 * are not graded; the JUnit tests in src/test/java are.
 */
public class ArrayClass {

    private TestObject[] arr;

    /** PROVIDED - do not change
     * Sets up an ArrayClass that can hold size objects. Because we are working
     * with arrays we must know the size up front - an ArrayList does not.
     */
    public ArrayClass(int size) {
        arr = new TestObject[size];
        for (int i = 0; i < size; i++) {
            // null marks an empty slot
            arr[i] = null;
        }
    }

    /** PROVIDED - do not change
     * Sets up an ArrayClass around an existing array. The tests use this to
     * hand you an array that is already full (no nulls at all).
     */
    public ArrayClass(TestObject[] a) {
        arr = a;
    }

    /** PROVIDED - do not change
     * Default constructor, in case we don't want to specify a size. No
     * parameter? Let's make it big!
     */
    public ArrayClass() {
        arr = new TestObject[1024];
    }

    /** COMPLETE THIS METHOD
     * Precondition: none.
     * Adds obj at the end (the first null slot). Returns true if the array
     * changed, false if it was already full and nothing was added.
     * Example: [A, B, null, null].add(C) -> [A, B, C, null], returns true
     *          [A, B, C].add(D)          -> unchanged, returns false
     * Hint: use findFirstNull(); if it is -1 there is no room.
     */
    public boolean add(TestObject obj) {
        // Insert your code below

        return false;
    }

    /** COMPLETE THIS METHOD
     * Precondition: 0 <= index <= number of objects stored (so index is either
     *               an occupied slot or the first null).
     * Inserts obj at index. Everything from index onward moves one slot to the
     * right first. Returns true if the array changed, false if it was already
     * full and nothing was inserted.
     * Example: [A, B, C, null].add(1, X) -> [A, X, B, C], returns true
     *          [A, B, C].add(0, X)       -> unchanged, returns false
     * Hint: moveForward(index) opens the hole and tells you whether it could.
     */
    public boolean add(int index, TestObject obj) {
        // Insert your code below

        return false;
    }

    /** COMPLETE THIS METHOD
     * Prints the contents of the ArrayClass: first the header line
     * "Printing contents of ArrayClass:" (already written for you), then each
     * stored object on its own line using System.out.println(arr[i]). Stop at
     * the first null - do not print "null" lines for empty slots.
     * Example: [A, B, null, null].dump() prints
     *     Printing contents of ArrayClass:
     *     Test Object: 1 : A
     *     Test Object: 2 : B
     * Hint: a for loop that breaks (or a while loop that stops) at null.
     */
    public void dump() {
        System.out.println("Printing contents of ArrayClass:");
        // Insert your code below

    }

    /** COMPLETE THIS METHOD
     * Precondition: none.
     * Removes the LAST object (the one just before the first null, or the one
     * at arr.length - 1 if the array is full), sets that slot to null, and
     * returns the removed object. Returns null if the array is empty.
     * Example: [A, B, C, null].removeEnd() -> [A, B, null, null], returns C
     *          [A, B, C, D].removeEnd()    -> [A, B, C, null], returns D
     *          [null, null].removeEnd()    -> unchanged, returns null
     * Hint: this is NOT as easy as it looks. findFirstNull() returns -1 when
     *       the array is full; handle that case before subtracting 1.
     */
    public TestObject removeEnd() {
        // Insert your code below

        return null;
    }

    /** COMPLETE THIS METHOD
     * Precondition: 0 <= index < arr.length.
     * Removes and returns the object at index; everything after it moves one
     * slot to the left so there is still no gap. Returns null (and changes
     * nothing) if there is nothing at index.
     * Example: [A, B, C, D].remove(1) -> [A, C, D, null], returns B
     *          [A, null, null].remove(2) -> unchanged, returns null
     * Hint: save arr[index], call moveBack(index), return what you saved.
     */
    public TestObject remove(int index) {
        // Insert your code below

        return null;
    }

    /** PROVIDED - do not change
     * Returns the object at index (null if the slot is empty).
     */
    public TestObject get(int index) {
        return arr[index];
    }

    /** PROVIDED - do not change
     * Returns the index of the first null slot, or -1 if the array is FULL.
     */
    private int findFirstNull() {
        for (int i = 0; i < arr.length; i++) {
            if (null == arr[i]) {
                return i;
            }
        }
        // No null anywhere: the array is full
        return -1;
    }

    /** PROVIDED - do not change
     * Moves everything from index onward one slot later, opening a hole at
     * index. Returns false and moves nothing if there is no free slot.
     */
    private boolean moveForward(int index) {
        // There must be at least one null for anything to move into
        int firstNull = findFirstNull();

        if (-1 == firstNull)
            return false;

        for (int i = firstNull - 1; i >= index; i--) {
            // Move everyone over by one spot, starting at the last filled index
            arr[i + 1] = arr[i];
        }
        return true;
    }

    /** PROVIDED - do not change
     * Moves everything after index one slot earlier (overwriting arr[index])
     * and sets the last slot to null.
     */
    private void moveBack(int index) {
        for (int i = index; i < arr.length - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[arr.length - 1] = null;
    }
}
