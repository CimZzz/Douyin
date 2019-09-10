import java.io.InputStream

class HandShakeProtocol(
    val type: Int, /// 1 byte
    val length: Int, /// 3 byte
    val content: Array<Int> /// determined by length
) {
    override fun toString(): String {
        return "type: $type, length: $length"
    }
}

fun parseHandShakeProtocol(inputStream: InputStream): HandShakeProtocol? {
    val byteOperatorStream = ByteOperatorStream(inputStream)
    val type = byteOperatorStream.readOneByte()
    println("type: $type")
    val length = byteOperatorStream.readIndicateByteInt(3)
    println("length: $length")
    val content = byteOperatorStream.readByteArray(length)
    return HandShakeProtocol(type, length, content)
}