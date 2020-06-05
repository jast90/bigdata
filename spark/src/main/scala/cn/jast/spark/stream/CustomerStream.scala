package cn.jast.spark.stream

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object CustomerStream {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("StreamWordCont")
    val ssc = new StreamingContext(sparkConf,Seconds(5))
    ssc.receiverStream(new CustomerReceiver("hadoop100",9999))
      .flatMap(_.split("\t")).map((_,1)).reduceByKey(_+_).print()
    ssc.start()
    ssc.awaitTermination()
  }
}
