package cn.jast.spark.stream.kafka

import java.util

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.kafka.common.serialization.{StringDeserializer, StringSerializer}

import scala.util.Random

object KafkaWordProducer {

  def main(args: Array[String]): Unit = {
    val kafkaParams = new util.HashMap[String,Object]()
    kafkaParams.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop100:9092")
    kafkaParams.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,classOf[StringSerializer])
    kafkaParams.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,classOf[StringSerializer])
    val producer = new KafkaProducer[String,String](kafkaParams)
    while (true){
      (1 to 10).foreach(messageNum=>{
        val str = (1 to 10).map(x=>Random.nextInt(10).toString).mkString(" ")
        print(str)
        println()
        val message = new ProducerRecord[String,String]("source",null,str)
        producer.send(message)
      })
      Thread.sleep(1000)
    }
  }
}
