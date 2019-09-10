fun main(args: Array<String>) {
    var i = 0
    val list = listOf(0x2, 0x3)

    (0 until list.size).forEach {
        i = i shl 8
        i = i or list[it]
    }

    println(i.toString(16))
}