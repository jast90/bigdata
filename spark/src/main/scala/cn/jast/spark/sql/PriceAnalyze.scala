package cn.jast.spark.sql

import org.apache.spark.sql.SparkSession

object PriceAnalyze {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[*]").appName("priceAnalyze").getOrCreate()
    val df = spark.read.json("/Users/zhangzhiwen/prices/jsonl")
    df.printSchema()
    df.createOrReplaceTempView("price")
//    val sqlDF = spark.sql("select farmProduceName,averagePrice,maxPrice,minPrice from price where farmProduceCode='AA01002' order by maxPrice desc limit 10")
//    sqlDF.show()
    val marketSQLDF = spark.sql("select marketName,marketCode from price group by marketCode,marketName order by marketCode desc")
    marketSQLDF.show(1000)
    marketSQLDF.repartition(1).write.format("json").save("/Users/zhangzhiwen/prices/market/")
    spark.stop()
  }
}
