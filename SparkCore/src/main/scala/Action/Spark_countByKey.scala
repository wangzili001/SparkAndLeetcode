package Action

import org.apache.spark.{SparkConf, SparkContext}

/**
 * countByKey()案例
 * 1. 作用：针对(K,V)类型的RDD，返回一个(K,Int)的map，表示每一个key对应的元素个数。
 * 2. 需求：创建一个PairRDD，统计每种key的个数
 *
 * foreach(func)案例
 * 1. 作用：在数据集的每一个元素上，运行函数func进行更新。
 * 2. 需求：创建一个RDD，对每个元素进行打印
 */
object Spark_countByKey {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("Spark_countByKey").setMaster("local[2]")
    val sc = new SparkContext(sparkConf)

    val rdd = sc.parallelize(List((1,3),(1,2),(1,4),(2,3),(3,6),(3,8)),3)
    println(rdd.countByKey)

    rdd.foreach(println(_))
  }
}
