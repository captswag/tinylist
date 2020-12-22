package list

class TinyListImpl<T : Any> : TinyList<T>, Iterator<T> {

    private var currentIndex = 0
    private var iteratorIndex = 0
    private var size = 5 // Size keeps on changing when the currentIndex reaches size - 1
    private var elements = arrayOfNulls<Any>(size)

    /**
     * Element is added to the current value of index.
     * So basically if you just created an empty TinyList, the underlying system would be as follows.
     * currentIndex = 0
     * elements[0] = element
     * currentIndex = currentIndex + 1
     */
    override fun add(element: T) {
        if (currentIndex < size) {
            elements[currentIndex++] = element
        } else {
            // Double the size of the array
            size *= 2
            elements = elements.copyOf(size)
            add(element)
        }
    }

    override fun removeAt(index: Int) {
    }

    /**
     * Following the example of adding an element to a newly created TinyList, the currentIndex
     * in such a situation will be 1 and that's the size of the TinyList.
     */
    override fun size(): Int = currentIndex

    override fun indexOf(element: T): Int {
        elements.forEachIndexed { index, item ->
            if (item == element) return index
        }
        return -1
    }

    @Suppress("UNCHECKED_CAST")
    override fun elementAt(index: Int): T {
        if (index < currentIndex) {
            val element = elements[index]
            return element as T
        } else {
            val message =
                if (size() == 0) {
                    "Empty list doesn't contain element at index $index"
                } else {
                    "Index: $index, Size: ${size()}"
                }
            throw IndexOutOfBoundsException(message)
        }
    }

    // Always reset iteratorIndex when iterator() is called
    override operator fun iterator(): Iterator<T> {
        iteratorIndex = 0
        return this
    }

    override fun hasNext(): Boolean = iteratorIndex < currentIndex

    @Suppress("UNCHECKED_CAST")
    override fun next(): T {
        return elements[iteratorIndex++] as T
    }

    /**
     * equals() function is required to be overridden because == checks for two TinyLists are checked
     * based on the variable it's pointed to. So for two different objects, they're always false,
     * even though the underlying objects have the same list elements.
     */
    override fun equals(other: Any?): Boolean {
        try {
            val otherList = other as TinyList<T> // Can result in ClassCastException
            if (size() != otherList.size()) {
                return false
            } else {
                var index = 0
                while (index < size()) {
                    if (elementAt(index) != otherList.elementAt(index)) {
                        return false
                    }
                    index++
                }
                return true
            }
        } catch (exception: ClassCastException) {
            return false
        }
    }
}