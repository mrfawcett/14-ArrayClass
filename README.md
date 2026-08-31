# ArrayClass: Build Your Own ArrayList

**Unit 3 — Arrays and ArrayList** · Pairs with lectures 3.1.1 Array Algorithms and 3.3/3.4 ArrayList & Collections · CED 4.5, 4.8

`ArrayList` lets you `add`, insert, and `remove` without thinking about where
things go. Under the hood it is a plain array plus a lot of shifting. In this
assignment you write that shifting yourself: `ArrayClass` wraps a
fixed-length `TestObject[]` and offers `add`, `add(index, obj)`, `removeEnd`,
`remove(index)` and `dump`. When you are done you will know exactly what an
`ArrayList` is doing for you — and why `add(0, x)` on a big list is slow.

As you know, if you want anything done with arrays in Java you have to do it
all yourself. And that does mean **do it yourself**: no `ArrayList`, no
`Arrays`, no `System.arraycopy`, no other shortcut that does the work for you.

## What you are given
| File | Status | Purpose |
|---|---|---|
| `src/main/java/ArrayClass.java` | **you complete this** | the five methods below; three helpers and `get` are provided |
| `src/main/java/TestObject.java` | provided | a number and a string; `toString()` gives `"Test Object: 1 : Ron"` |
| `src/main/java/ArrayClassTester.java` | provided, **not graded** | a `main` that runs your class next to a real `ArrayList` |
| `src/main/java/BetterArrayClassTester.java` | provided, **not graded** | same idea, each test independent of the others |
| `src/test/java/*Test.java` | provided | the autograder's tests — read them |

The two `*Tester` classes are drivers for you to run by hand. They print your
`ArrayClass` and an `ArrayList` side by side; when the two dumps match you are
probably right. The autograder ignores them and runs only the JUnit tests.

## How the array is used

`arr` has a fixed length — its **capacity**. Objects live in `arr[0]`,
`arr[1]`, … with **no gaps**, and every unused slot is `null`. The number of
objects stored (the **size**) is therefore the index of the first `null`, or
`arr.length` when the array is full.

```
capacity 5, size 3:   [A, B, C, null, null]
capacity 4, size 4:   [A, B, C, D]            <- FULL: no null anywhere
```

Three private helpers are written for you:

| Helper | What it does |
|---|---|
| `findFirstNull()` | index of the first `null`, or **`-1` if the array is full** |
| `moveForward(i)` | shifts `arr[i..size-1]` one slot right to open a hole at `i`; returns `false` and moves nothing if there is no room |
| `moveBack(i)` | shifts `arr[i+1..end]` one slot left, closing the hole at `i`, and sets the last slot to `null` |

### The trap: `findFirstNull()` returns `-1` on a full array
The tests build **full** arrays on purpose with the
`ArrayClass(TestObject[])` constructor — `{Wilton, Sarah, Peter, Agnes}` has
no `null` in it. Any method that assumes "the last object is at
`findFirstNull() - 1`" computes index `-2` on that array and throws
`ArrayIndexOutOfBoundsException`. When `findFirstNull()` is `-1`, the last
object is at `arr.length - 1`. Handle that case explicitly in `removeEnd`
(and think about it in `dump`).

## What to write

| Method | Points | What it does |
|---|---|---|
| `boolean add(TestObject obj)` | 20 | append at the first `null`; `false` if full |
| `boolean add(int index, TestObject obj)` | 25 | insert at `index`, shifting later objects right; `false` if full |
| `TestObject removeEnd()` | 20 | remove and return the last object; `null` if empty |
| `TestObject remove(int index)` | 25 | remove and return `arr[index]`, shifting later objects left; `null` if that slot is empty |
| `void dump()` | 10 | print the header line, then each object on its own line |

### `add(TestObject obj)`
Find the first `null` with `findFirstNull()`. If it is `-1`, return `false`
and change nothing. Otherwise store `obj` there and return `true`. Filling the
array to exactly its capacity is still a success.

### `add(int index, TestObject obj)`
Precondition: `0 <= index <= size` — `index` is an occupied slot or the first
`null`. Call `moveForward(index)`. If it returns `false` (no room), return
`false` and change nothing. Otherwise the hole at `index` is open: store `obj`
there and return `true`.

```
[A, B, C, null].add(1, X)  ->  [A, X, B, C]   true
[A, B, C].add(0, X)        ->  unchanged      false
```

### `removeEnd()`
Remove the **last object**, set its slot to `null`, and return it. Three
cases, in this order:
1. `findFirstNull()` is `-1` → the array is full; the last object is at
   `arr.length - 1`.
2. `findFirstNull()` is `0` → the array is empty; return `null`.
3. otherwise the last object is at `findFirstNull() - 1`.

The starter comment on the original said "this should be easy". It is — once
you handle case 1. Most wrong solutions crash on the very first test.

### `remove(int index)`
If `arr[index]` is `null`, return `null` and change nothing. Otherwise save
`arr[index]`, call `moveBack(index)` to close the gap, and return what you
saved. `moveBack` already handles full arrays and nulls the last slot.

```
[Wilton, Sarah, Peter, Agnes].remove(1)  ->  [Wilton, Peter, Agnes, null]   returns Sarah
```

### `dump()`
Print the header `Printing contents of ArrayClass:` (that line is already in
the starter), then each stored object on its own line with
`System.out.println(arr[i])`. Stop at the first `null` — never print a line
for an empty slot. A full array prints every element. The tests capture
`System.out` and compare it exactly.

```
Printing contents of ArrayClass:
Test Object: 1 : A
Test Object: 2 : B
```

## Examples

Starting from `new ArrayClass(3)` (capacity 3, empty):
| Call | Returns | Array afterwards |
|---|---|---|
| `add(Ron)` | `true` | `[Ron, null, null]` |
| `add(0, Paul)` | `true` | `[Paul, Ron, null]` |
| `add(1, Neir)` | `true` | `[Paul, Neir, Ron]` |
| `add(1, Simone)` | `false` | `[Paul, Neir, Ron]` — full |
| `add(Simone)` | `false` | `[Paul, Neir, Ron]` — full |
| `removeEnd()` | `Ron` | `[Paul, Neir, null]` |
| `remove(0)` | `Paul` | `[Neir, null, null]` |
| `remove(2)` | `null` | `[Neir, null, null]` — nothing there |
| `removeEnd()` | `Neir` | `[null, null, null]` |
| `removeEnd()` | `null` | `[null, null, null]` — empty |

Starting from `new ArrayClass(new TestObject[] {Wilton, Sarah, Peter, Agnes})` (full):
| Call | Returns | Array afterwards |
|---|---|---|
| `removeEnd()` | `Agnes` | `[Wilton, Sarah, Peter, null]` |
| `remove(1)` (fresh copy) | `Sarah` | `[Wilton, Peter, Agnes, null]` |

## Running the tests
`mvn test` runs everything; `mvn test -Dtest=<ClassName>` runs one rubric line.

| Test class | Rubric line | Points |
|---|---|---|
| `AddTest` | `add(obj)` | 20 |
| `AddOverloadedTest` | `add(index, obj)` | 25 |
| `RemoveEndTest` | `removeEnd` | 20 |
| `RemoveTest` | `remove(index)` | 25 |
| `DumpTest` | `dump` | 10 |

The autograder awards a rubric line only when every test in that class passes.

## Suggested order
1. `add(obj)` — three lines around `findFirstNull()`. Run `AddTest`.
2. `dump()` — so you can see what you are doing. Run `DumpTest`, then run
   `BetterArrayClassTester` and look at Test One.
3. `add(index, obj)` — `moveForward` does the shifting; you store one object.
   Run `AddOverloadedTest`.
4. `remove(index)` — `moveBack` does the shifting. Run `RemoveTest`.
5. `removeEnd()` — write the three cases in order. Run `RemoveEndTest`; a
   crash on the first test means you subtracted 1 from `-1`.

## Rules of the road
- AP Java subset only: arrays, loops, `if`, `null` checks. **No `ArrayList`,
  no `Arrays.*`, no `System.arraycopy`, no `List`, no streams** inside
  `ArrayClass`. (The tester drivers use `ArrayList` on purpose — that is the
  thing you are imitating.)
- Do not change method headers or provided code (`TestObject`, the
  constructors, `get`, `findFirstNull`, `moveForward`, `moveBack`). Keep
  `arr` `private`.
- Do not touch `src/test`, `pom.xml`, `grading.json`, or `.github`. The
  autograder checks that they are byte-identical to the template before it
  runs a single test; if they differ it stops and awards nothing, and the
  change shows up in the roster.
- Never leave a gap: after any operation the objects are contiguous from
  index 0 and everything after them is `null`.
