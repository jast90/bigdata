package cn.jast.spark.stream

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object FileSystemStream {
  /**
   * 注意：
   * 监听目录下，只能监控新文件，不能监听修改文件即：文件追加的内容无法读取
   * @param args
   */
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("FileStream")
    val ssc = new StreamingContext(sparkConf,Seconds(5))
    val dirStream = ssc.textFileStream("file:///Users/zhangzhiwen/data/spark-data/wc/")
    dirStream.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).print()
    ssc.start()
    ssc.awaitTermination()
  }
}
