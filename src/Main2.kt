import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream
import java.io.File
import java.io.FileOutputStream
import java.net.ServerSocket
import java.util.*


fun main(args: Array<String>) {
    val server = ServerSocket(9990)
    val socket = server.accept()

    val inputStream = socket.getInputStream()
//    val outputStream = socket.getOutputStream()

//    val proxySocket = Socket("114.99.25.25", 57114)

//    val byteOutputStream = ByteOutputStream()
//    Timer().schedule(object: TimerTask() {
//        override fun run() {
//            val name = "1.resp"
//            try {
//                writeFile(name, byteOutputStream.bytes)
//                println("$name 写入成功")
//            }
//            catch (e: Throwable) {
//                print("$name 写入失败")
//            }
//        }
//    }, 0L, 2000L)

    var handleShakeProtocol: HandShakeProtocol? = null

    handleShakeProtocol = parseHandShakeProtocol(inputStream)


    println(handleShakeProtocol)
}


private fun writeFile(name: String, byteArray: ByteArray) {
    val file = File("/Users/wangyanxiong/Desktop/HTTPS", name)
    val outputStream = FileOutputStream(file)
    outputStream.write(byteArray)
    outputStream.flush()
    outputStream.close()

}