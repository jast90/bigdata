package cn.jast.spark.stream

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object StreamWordCount {
  /**
   * 在hadoop100上启动 netcat，nc -lk 9999
   * netcat 的安装参考：https://github.com/jast90/awesome-learning/issues/20
   * @param args
   */
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("StreamWordCount")
    val ssc = new StreamingContext(sparkConf,Seconds(5))
    val lineStreams = ssc.socketTextStream("hadoop100",9999)
    val wordStreams = lineStreams.flatMap(_.split(" "))
    val wordAndOneStreams = wordStreams.map((_,1))
    val wordAndCountStreams = wordAndOneStreams.reduceByKey(_+_)
    wordAndCountStreams.print()
    ssc.start()
    ssc.awaitTermination()
  }
}
