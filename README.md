## TinyList

An ArrayList with very limited features.

### Why TinyList?

It has been a while since I did something basic. ArrayList makes use of generics, higher order functions (lambdas),
JUnit 5 and Github actions.

### TinyList provides the following methods

```kotlin
interface TinyList<T : Any> {
    fun add(element: T)
    fun removeAt(index: Int) // Throws IndexOutOfBoundsException if index >= size
    fun size(): Int
    fun indexOf(element: T): Int // Returns -1 if not found in the list
    fun elementAt(index: Int): T // Throws IndexOutOfBoundsException if index >= size
    operator fun iterator(): Iterator<T>
    fun filter(predicate: (T) -> Boolean): TinyList<T>
    fun <R : Any> map(transform: (T) -> R): TinyList<R>
}
```

### Bonus code

- Write unit tests (JUnit 5)
- Setup Github actions (CI pipeline to run unit tests whenever a PR is raised to main branch)
