package cn.jast.spark.stream

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable


object RDDQueueStream {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("RDDStream")
    val ssc = new StreamingContext(conf,Seconds(4))

    val rddQueue = new mutable.Queue[RDD[Int]]()

    ssc.queueStream(rddQueue,oneAtATime = false).map((_,1)).reduceByKey(_+_).print()

    ssc.start()

    for(i<-1 to 5){
      rddQueue+=ssc.sparkContext.makeRDD(1 to 300,10)
      Thread.sleep(2000)
    }
    ssc.awaitTermination()
  }
}
