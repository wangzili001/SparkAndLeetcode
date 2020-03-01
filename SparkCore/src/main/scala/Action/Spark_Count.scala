package Action

import org.apache.spark.{SparkConf, SparkContext}

/**
 * collect()案例
 * 1. 作用：在驱动程序中，以数组的形式返回数据集的所有元素。
 * 2. 需求：创建一个RDD，并将RDD内容收集到Driver端打印\
 * count()案例
 * 1. 作用：返回RDD中元素的个数
 * 2. 需求：创建一个RDD，统计该RDD的条数
 *
 * first()案例
 * 1. 作用：返回RDD中的第一个元素
 * 2. 需求：创建一个RDD，返回该RDD中的第一个元素
 * take(n)案例
 * 1. 作用：返回一个由RDD的前n个元素组成的数组
 * 2. 需求：创建一个RDD，统计该RDD的条数
 * takeOrdered(n)案例
 * 1. 作用：返回该RDD排序后的前n个元素组成的数组
 * 2. 需求：创建一个RDD，统计该RDD的条数
 */
object Spark_Count {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("Spark_Count").setMaster("local[2]")
    val sc = new SparkContext(sparkConf)

    val rdd = sc.parallelize(1 to 10)
    rdd.collect().foreach(println)
    println(rdd.count())
    println(rdd.first())
    rdd.takeOrdered(3).foreach(println)
  }
}
