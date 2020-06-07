package cn.jast.spark.stream

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object WordCountByStreamWithSocket {
  /**
   * 在hadoop100上启动 netcat，nc -lk 9999
   * netcat 的安装参考：https://github.com/jast90/awesome-learning/issues/20
   * @param args
   */
  def main(args: Array[String]): Unit = {

    val updateFunc = (values:Seq[Int],state:Option[Int])=>{
      val currentCount = values.foldLeft(0)(_+_)
      val previousCount = state.getOrElse(0)
      Some(currentCount+previousCount)
    }

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("StreamWordCount")
    val ssc = new StreamingContext(sparkConf,Seconds(5))
    //设置检查点，检查点具有容错机制
    ssc.checkpoint("file:///Users/zhangzhiwen/gitlab/bigdata/spark/src/main/scala/cn/jast/spark/stream/stateful/")

    val lineStreams = ssc.socketTextStream("hadoop100",9999)
    val wordStreams = lineStreams.flatMap(_.split(" "))
    val wordAndOneStreams = wordStreams.map(x=>(x,1))
//    val wordAndCountStreams = wordAndOneStreams.reduceByKey(_+_)
//    wordAndCountStreams.print()

    wordAndOneStreams.updateStateByKey(updateFunc).print()
    ssc.start()
    ssc.awaitTermination()
  }

}
