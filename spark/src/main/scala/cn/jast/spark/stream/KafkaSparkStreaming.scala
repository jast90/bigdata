package cn.jast.spark.stream

import org.apache.commons.codec.StringDecoder
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, HasOffsetRanges, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}


object KafkaSparkStreaming {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("kafkaSparkStreaming")
    val ssc = new StreamingContext(sparkConf,Seconds(5))
    val topics = Array("source")
    val consumerGroup = "spark"
    val kafkaParams = Map[String,Object](
      ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG->"hadoop100:9092",
      ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG->classOf[StringDeserializer],
      ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG->classOf[StringDeserializer],
      ConsumerConfig.GROUP_ID_CONFIG->consumerGroup,
      ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG->(true:java.lang.Boolean),
      ConsumerConfig.AUTO_OFFSET_RESET_CONFIG->"latest"
    )
    val kafkaDStream = KafkaUtils.createDirectStream[String,String](
      ssc,
      LocationStrategies.PreferBrokers,
      ConsumerStrategies.Subscribe[String,String](topics,kafkaParams)
    )
    kafkaDStream.foreachRDD{ rdd=>
      val offsetRanges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
      rdd.foreach(r=>{
        print(r.value())
      })
    }
    ssc.start()
    ssc.awaitTermination()
  }
}
