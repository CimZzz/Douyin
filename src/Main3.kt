import com.sun.prism.PhongMaterial
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream
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
        if(lineArr.size == 1) {
            if(lineArr[0] == "count") {
                println("数量为: ${rootMap.size}")
            }
            else if(lineArr[0] == "allIdx") {
                printAllIdx(rootMap)
            }
        }
        else if(lineArr.size == 2) {
            if(lineArr[0] == "read") {
                try {
                    println(readStr(lineArr[1], rootMap))
                }
                catch (e: Throwable) {
                    println("读取字符发生错误: $e")
                }
            }
            else if(lineArr[0] == "count") {
                try {
                    println("数量为: ${readCount(lineArr[1], rootMap)}")
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

private fun printAllIdx(rootMap: FirstMap) {
    rootMap.forEach { idx, second ->
        second.forEach { secondIdx, third ->
            third.forEach {thirdIdx, _ ->
                println("$idx-$secondIdx-$thirdIdx")
            }
        }
    }
}

private fun readCount(line: String, rootMap: FirstMap): Int{
    val lineArr = line.split("-")

    when(lineArr.size) {
        1 -> {
            val first = lineArr[0].toIntOrNull()?:throw RuntimeException("第一个下标不存在: $line")
            val secondMap = rootMap[first]?:throw RuntimeException("第一个下标 Map 不存在: $line")
            return secondMap.size
        }
        2 -> {
            val first = lineArr[0].toIntOrNull()?:throw RuntimeException("第一个下标不存在: $line")
            val second = lineArr[1].toIntOrNull()?:throw RuntimeException("第二个下标不存在: $line")

            val secondMap = rootMap[first]?:throw RuntimeException("第一个下标 Map 不存在: $line")
            val thirdMap = secondMap[second]?:throw RuntimeException("第二个下标 Map 不存在: $line")
            return thirdMap.size
        }
        else -> throw RuntimeException("格式错误: $line")
    }
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

    val sourceFile = File("/Users/cimzzz/WebstormProjects/SSS/src", "um.js")
    val reader = BufferedInputStream(BufferedInputStream(FileInputStream(sourceFile)))
//    var writer = ByteArrayOutputStream()

    return makeFirstMap(reader)
}


class BufferTreeNode(
    private val value: Int? = null,
    private val parent: BufferTreeNode? = null
) {
    var type: Int? = null
    var next: HashMap<Int, BufferTreeNode>? = null

    fun add(value: Int, node: BufferTreeNode): BufferTreeNode {
        val map = next?:{
            val tmpMap = HashMap<Int, BufferTreeNode>()
            this.next = tmpMap
            tmpMap
        }()
        map[value] = node
        return node
    }

    fun get(value: Int): BufferTreeNode? {
        return next?.get(value)
    }

    fun getValue(): String {
        val str = ByteOutputStream()
        var curParent = parent
        while(true) {
            val byte = curParent?.value?:break
            curParent = curParent.parent
            str.write(byte)
        }

        if(str.count != 0)
            return str.toString()
        else return ""
    }
}

fun buildNode(value: String, type: Int, rootNode: BufferTreeNode? = null): BufferTreeNode {
    val realRootNode = rootNode?:BufferTreeNode()
    var currentNode = realRootNode

    value.forEach {
        val byte = it.toInt()
        currentNode = currentNode.add(byte, BufferTreeNode(byte, currentNode))
    }

    currentNode.type = type
    return realRootNode
}

private fun findCaseNumber(reader: InputStream): Int {
    var isLooping = true
    val buffer = ByteOutputStream()
    while (isLooping)  {
        val readByte = reader.read()
        val char = readByte.toChar()
        when(char) {
            ':' -> {
                isLooping = false
            }
            else -> buffer.write(readByte)
        }
    }
    return buffer.toString().trim().toInt()
}

private fun makeFirstMap(reader: InputStream): FirstMap {
    val map = FirstMap()
    var scopeCount = 0
    var isLooping = true
    val rootBuffer = buildNode("case", 0)
    var buffer = rootBuffer

    while(isLooping) {
        val readByte = reader.read()
        if(readByte == -1)
            break
        val char = readByte.toChar()
        when(char) {
            '{' -> {
                scopeCount ++
                buffer = rootBuffer
            }
            '}' -> {
                scopeCount --
                if(scopeCount <= 0) {
                    isLooping = false
                }
                buffer = rootBuffer
            }
            ' ' -> {
                if(buffer.type != null) {
                    /// 表示找到了 case
                    val caseNumber = findCaseNumber(reader)
                    val secondMap = SecondMap()
                    map[caseNumber] = secondMap
                    val newScopeCount = makeSecondMap(reader, caseNumber, secondMap)
                    scopeCount += newScopeCount
                    if(scopeCount <= 0) {
                        isLooping = false
                    }
                }
                buffer = rootBuffer
            }
            else -> {
                buffer = buffer.get(readByte)?:rootBuffer
            }
        }
    }

    return map
}

/// 如果多读取了一次返回花括号，会将其返回
private fun makeSecondMap(reader: InputStream, caseIdx: Int, map: SecondMap): Int {
    var scopeCount = 0
    var isLooping = true
    val rootBuffer = buildNode("case", 0)
    var buffer = rootBuffer
    while(isLooping) {
        val readByte = reader.read()
        if(readByte == -1) {
            scopeCount = Int.MIN_VALUE
            break
        }
        val char = readByte.toChar()
        when(char) {
            '{' -> {
                scopeCount ++
                buffer = rootBuffer
            }
            '}' -> {
                scopeCount --
                if(scopeCount <= 0) {
                    isLooping = false
                }
                buffer = rootBuffer
            }
            ' ' -> {
                if(buffer.type != null) {
                    /// 表示找到了 case
                    val caseNumber = findCaseNumber(reader)
                    val thirdMap = ThirdMap()
                    map[caseNumber] = thirdMap
                    scopeCount += makeThirdMap(reader, caseIdx, caseNumber, thirdMap)
                    if(scopeCount <= 0) {
                        isLooping = false
                    }
                }
                buffer = rootBuffer
            }
            else -> {
                buffer = buffer.get(readByte)?:rootBuffer
            }
        }
    }

    return scopeCount
}

/// 如果多读取了一次返回花括号，会将其返回
private fun makeThirdMap(reader: InputStream, secondIdx: Int, caseIdx: Int, map: ThirdMap): Int {
    var scopeCount = 0
    var isLooping = true
    val rootBuffer = buildNode("case", 0)
    var buffer = rootBuffer
    while(isLooping) {
        val readByte = reader.read()
        if(readByte == -1) {
            scopeCount = Int.MIN_VALUE
            break
        }
        val char = readByte.toChar()
        when(char) {
            '{' -> {
                scopeCount ++
                buffer = rootBuffer
            }
            '}' -> {
                scopeCount --
                if(scopeCount <= 0) {
                    isLooping = false
                }
                buffer = rootBuffer
            }
            ' ' -> {
                if(buffer.type != null) {
                    val helper = ThirdMapFindHelper()
                    helper.content = ""
                    helper.scopeCount = 0
                    helper.hasCase = true
                    while (isLooping && helper.hasCase) {
                        helper.content = ""
                        helper.scopeCount = 0
                        helper.hasCase = false
                        /// 表示找到了 case
                        val caseNumber = findCaseNumber(reader)
                        if(secondIdx == 1 && caseIdx == 15 && caseNumber == 0) {
                            var i = 0
                            i++
                        }
                        findCmd(reader, caseIdx, helper)
                        map[caseNumber] = helper.content
                        if(secondIdx == 1 && caseIdx == 15 && caseNumber == 0) {
                            var i = 0
                            i++
                        }
                        scopeCount += helper.scopeCount


                        if(scopeCount <= 0) {
                            isLooping = false
                        }
                    }

                }
                buffer = rootBuffer
            }
            else -> {
                buffer = buffer.get(readByte)?:rootBuffer
            }
        }
    }

    return scopeCount
}

private fun readLine(reader: InputStream): String {
    val outputStream = ByteOutputStream()
    while(true) {
        val readByte = reader.read()
        val char = readByte.toChar()
        outputStream.write(readByte)
        if(char == '\n')
            break
    }

    return outputStream.toString()
}

private fun findCmd(reader: InputStream, caseIdx: Int, helper: ThirdMapFindHelper) {
    var scopeCount = 0
    var isLooping = true
    var isReturning = false
    var isMore = false
    val rootBuffer = buildNode("case", 0)
    buildNode("break", 1, rootBuffer)
    buildNode("return", 2, rootBuffer)
    buildNode("\\", 3, rootBuffer)
    var buffer = rootBuffer
    val valueBuffer = ByteArrayOutputStream()

    fun flushBuffer() {
        val bufferStr = buffer.getValue()
        if(bufferStr.isNotBlank())
            valueBuffer.write(bufferStr.toByteArray())
        buffer = rootBuffer
    }

    while(isLooping) {
        val readByte = reader.read()
        if(readByte == -1) {
            flushBuffer()
            scopeCount = Int.MIN_VALUE
            break
        }
        val char = readByte.toChar()
        when(char) {
            '{' -> {
                if(buffer.type != 3)
                    scopeCount ++
                flushBuffer()
                valueBuffer.write(readByte)
            }
            '}' -> {
                if(buffer.type != 3)
                    scopeCount --
                flushBuffer()
                if(scopeCount < 0) {
                    isLooping = false
                }
                else valueBuffer.write(readByte)
            }
            ',' -> {
                flushBuffer()
                valueBuffer.write(readByte)
                if(isReturning) {
                    isMore = true
                }
            }
            '\n' -> {
                flushBuffer()
                valueBuffer.write(readByte)
                if(isReturning) {
                    if(isMore) {
                        isMore = false
                    }
                    else isLooping = false
                }
            }
            else -> {
                buffer = buffer.get(readByte)?:{
                    flushBuffer()
                    valueBuffer.write(readByte)
                   rootBuffer.get(readByte)?:rootBuffer
                }()


                if(scopeCount <= 0) {
                    when (buffer.type) {
                        0 -> {
                            helper.hasCase = true
                            isLooping = false
                        }
                        1 -> {
                            if(!isReturning)
                                isLooping = false
                        }
                        2 -> {
                            isReturning = true
                        }
                    }
                }
            }
        }
    }

    helper.content = valueBuffer.toString()
    helper.scopeCount = scopeCount
}

class ThirdMapFindHelper {
    var content: String = ""
    var scopeCount: Int = 0
    var hasCase: Boolean = false
}
