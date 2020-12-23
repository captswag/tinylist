package list

interface TinyList<T : Any> {
    fun add(element: T)
    fun removeAt(index: Int) // Throws IndexOutOfBoundsException if index >= size
    fun size(): Int
    fun indexOf(element: T): Int // Returns -1 if not found in the list
    fun elementAt(index: Int): T // Throws IndexOutOfBoundsException if index >= size
    operator fun iterator(): Iterator<T>
    fun filter(predicate: (T) -> Boolean): TinyList<T>
}