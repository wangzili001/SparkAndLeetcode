package Opera

import org.apache.spark.{SparkConf, SparkContext}
/**
 * aggregateByKey算子
 * 作用：在kv对的RDD中，，按key将value进行分组合并，合并时，将每个value和初始值作为seq函数的参数，进行计算，返回的结果作为一个新的kv对，然后再将结果按照key进行合并，
 * 最后将每个分组的value传递给combine函数进行计算（先将前两个value进行计算，将返回结果和下一个value传给combine函数，以此类推），将key与计算结果作为一个新的kv对输出。
 */
object Spark_Opera12 {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("Spark_Opera8").setMaster("local[*]")
    val sc = new SparkContext(sparkConf)
    //需求：创建一个pairRDD，取出每个分区相同key对应值的最大值，然后相加
    val rdd = sc.parallelize(List(("a",3),("a",2),("c",4),("b",3),("c",6),("c",8)),2)
   rdd.saveAsTextFile("out")
    val agg = rdd.aggregateByKey(0)(math.max(_,_),_+_)
    agg.collect().foreach(println)
  }
}
