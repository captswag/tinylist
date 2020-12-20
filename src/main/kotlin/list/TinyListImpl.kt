package list

class TinyListImpl<T>: TinyList<T> {
    private val list = mutableListOf<T>()

    override fun add(element: T) {
        list.add(element)
    }

    override fun removeAt(index: Int) {
        list.removeAt(index)
    }

    override fun size(): Int = list.size

    override fun indexOf(element: T): Int = list.indexOf(element)

    override fun elementAt(index: Int): T = list.elementAt(index)
}