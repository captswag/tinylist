package list

interface TinyList<T> {
    fun size(): Int
    fun indexOf(element: T): Int // Returns -1 if not found in the list
    fun elementAt(index: Int): T // Throws IndexOutOfBoundsException if index >= size
    fun subList(fromIndex: Int, toIndex: Int): TinyList<T> // Throws either IllegalArgumentException or IndexOutOfBoundsException
}