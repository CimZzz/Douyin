import java.io.*
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet
import kotlin.streams.toList


class FirstMap: HashMap<Int, SecondMap>()

class SecondMap: HashMap<Int, ThirdMap>()

class ThirdMap: HashMap<Int, String>()


fun main(args: Array<String>) {
    val rootMap = readFile()
    println("完成解析")
    while(true) {
        val line = readLine()?:break
        val lineArr = line.split(" ")
        if(lineArr.size == 2) {
            if(lineArr[0] == "read") {
                try {
                    println(readStr(lineArr[1], rootMap))
                }
                catch (e: Throwable) {
                    println("读取字符发生错误: $e")
                }
            }
            else {
                val list = readFile(lineArr[0], rootMap)
                if (list != null) {
                    if (!list.isEmpty()) {
                        if (writeFile(lineArr[1], list))
                            println("写入文件成功")
                        else println("写入文件失败")
                    } else println("不存在指令")
                }
            }
        }
        else println("错误指令")
    }

    println("结束")
}


private fun readStr(line: String, rootMap: FirstMap): String {
    val lineArr = line.split("-")
    if(lineArr.size != 3)
        throw RuntimeException("格式错误: $line")

    val first = lineArr[0].toIntOrNull()?:throw RuntimeException("第一个下标不存在: $line")
    val second = lineArr[1].toIntOrNull()?:throw RuntimeException("第二个下标不存在: $line")
    val third = lineArr[2].toIntOrNull()?:throw RuntimeException("第三个下标不存在: $line")

    val secondMap = rootMap[first]?:throw RuntimeException("第一个下标 Map 不存在: $line")
    val thirdMap = secondMap[second]?:throw RuntimeException("第二个下标 Map 不存在: $line")
    return thirdMap[third]?:throw RuntimeException("第三个下标 命令 不存在: $line")
}

private fun readFile(path: String, rootMap: FirstMap): List<String>? {
    try {
        val file = File(path)
        val reader = BufferedReader(InputStreamReader(FileInputStream(file)))
        return reader.lines().map {
            readStr(line = it, rootMap = rootMap)
        }.toList()
    }
    catch (e: Throwable) {
        println("读取文件发生错误: $e")
        return null
    }
}

private fun writeFile(path: String, list: List<String>): Boolean {
    try {
        val file = File(path)
        val writer = PrintWriter(FileWriter(file))
        list.forEach {
            writer.println(it)
        }
        writer.flush()
        writer.close()
        return true
    }
    catch (e: Throwable) {
        println("写入文件发生错误: $e")
        return false
    }
}

private fun readFile(): FirstMap {

    class BufferTreeNode {
        var type: Int? = null
        val next: HashMap<Char, BufferTreeNode>? = null

        fun add(value: Char, node: BufferTreeNode): BufferTreeNode {
            val map = next?:{
                val tmpMap = HashMap<Char, BufferTreeNode>()
                tmpMap[value] = node
                tmpMap
            }()
            map[value] = node
            return node
        }

        fun get(value: Char): BufferTreeNode? {
            return next?.get(value)
        }
    }

    fun buildNode(value: String, type: Int, rootNode: BufferTreeNode) {
        var currentNode = rootNode
        value.forEach {
            currentNode = currentNode.add(it, rootNode)
        }

        currentNode.type = type
    }


    val sourceFile = File("/Users/wangyanxiong/WebstormProjects/SSS/src", "um.js")
    val reader = BufferedInputStream(BufferedInputStream(FileInputStream(sourceFile)))
//    var writer = ByteArrayOutputStream()


    val TYPE_DEFAULT = 0
    val TYPE_SWITCH = 1
    val TYPE_CASE = 2
    val TYPE_BREAK = 3
    val TYPE_RETURN = 4

    val rootMap = FirstMap()
    var valueBuffer: String = ""
    var totalScopeCount = 0
    var secondScopeIdx = 0
    var thirdScopeIdx = 0
    var type = 0

    val rootNode = BufferTreeNode()
    var bufferNode: BufferTreeNode = rootNode
    buildNode("switch", 1, rootNode)
    buildNode("case", 2, rootNode)
    buildNode("break", 3, rootNode)
    buildNode("return", 4, rootNode)



    fun dropBuffer() {
        bufferNode = rootNode
    }

    fun appendBuffer(char: Char) {
        val nextNode = bufferNode.get(char)
        if(nextNode == null) {
            dropBuffer()
            return
        }

        bufferNode = nextNode
    }

    fun checkBuffer() {
        val newType = bufferNode.type
        if(newType == null) {
            dropBuffer()
            return
        }

        type = newType
    }

    fun

    fun checkScope() {

    }



    while(true) {
        val readByte = reader.read()

        when(readByte.toChar()) {
            '{' -> {
                totalScopeCount ++
                dropBuffer()
                checkScope()
            }
            '}' -> {
                totalScopeCount --
                dropBuffer()
                checkScope()
            }
            '(', ')', ' ' -> {
                checkBuffer()
            }
            else -> {

            }
        }
    }

    return rootMap
}

private fun findCaseCode(line: String): Int {
    val idx = line.indexOf(" ")
    val endIdx = line.indexOf(":")
    try {
        return line.substring(idx + 1, endIdx).toInt()
    }
    catch (e: Throwable) {
        println(line)
        throw e
    }
}