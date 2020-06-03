package cn.jast

import org.apache.spark.{SparkConf,SparkContext}

object WordCount{
    /**
     * Program argments 设置args参数：用空格分割，如：/Users/zhangzhiwen/data/spark-data/wc /Users/zhangzhiwen/data/spark-data/wc/out
     * @param args
     */
    def main(args: Array[String]):Unit={
        val conf = new SparkConf().setAppName("WC").setMaster("local[*]")
        val sc = new SparkContext(conf)
        sc.textFile(args(0)).flatMap(_.split(" "))
          .map((_,1)).reduceByKey(_+_/*,1*/)//指定分区数时，本地文件系统会一致报`java.io.IOException: Not a file: file:/Users/zhangzhiwen/data/spark-data/wc/out`
          .sortBy(_._2,false).saveAsTextFile(args(1))
        sc.stop()
    }
}