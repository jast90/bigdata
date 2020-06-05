package cn.jast.spark.stream

import java.io.{BufferedReader, InputStreamReader}
import java.net.Socket
import java.nio.charset.StandardCharsets

import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.receiver.Receiver

class CustomerReceiver(host: String,port: Int ) extends Receiver[String](StorageLevel.MEMORY_ONLY){
  override def onStart(): Unit = {
    new Thread("Socket Receiver") {
      override def run(): Unit = {
        receive()
      }
    }.start()
  }

  def receive():Unit={
    var socket:Socket = new Socket(host,port)
    var input:String = null
    var reader = new BufferedReader(new InputStreamReader(socket.getInputStream,StandardCharsets.UTF_8))
    input = reader.readLine()
    while (isStopped()&&input!=null){
      store(input)
      input = reader.readLine()
    }
    reader.close()
    socket.close()
    restart("restart")
  }

  override def onStop(): Unit = {


  }
}
