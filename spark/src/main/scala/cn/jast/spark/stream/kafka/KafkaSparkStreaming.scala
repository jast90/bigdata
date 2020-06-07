package cn.jast.spark.stream.kafka

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Minutes, Seconds, StreamingContext}

object KafkaSparkStreaming {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("kafkaSparkStreaming")
    val ssc = new StreamingContext(sparkConf,Seconds(5))
    ssc.checkpoint("file:///Users/zhangzhiwen/gitlab/bigdata/spark/src/main/scala/cn/jast/spark/stream/kafka/stateful")
    val topics = Array("source")
    val consumerGroup = "spark1"
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
    kafkaDStream.map(_.value()).flatMap(_.split(" ")).map(x=>(x,1L))
      .reduceByKeyAndWindow(_+_,_-_,Minutes(2),Seconds(10),2).print()
    ssc.start()
    ssc.awaitTermination()
  }
}
