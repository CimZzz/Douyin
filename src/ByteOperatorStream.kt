import java.io.InputStream

class ByteOperatorStream(
    private val inputStream: InputStream
) {
    fun readOneByte(): Int = inputStream.read()

    fun readByteArray(count: Int): Array<Int> {
        return (0 until count).map {
            println("readCount: ${it + 1}, $count")
            inputStream.read()
        }.toTypedArray()
    }

    fun readIndicateByteInt(count: Int): Int {
        val byteArr = readByteArray(count)
        var i: Int = 0
        (0 until count).forEach {
            i = i shl 8
            i = i or byteArr[count - 1 - it]
        }

        return i
    }
}