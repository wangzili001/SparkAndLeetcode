package Opera

import org.apache.spark.{SparkConf, SparkContext}

/**
 * combineByKey[C] 案例
 * 作用：对相同K，把V合并成一个集合。
 */
object Spark_Opera13 {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("Spark_Opera13").setMaster("local[*]")
    val sc = new SparkContext(sparkConf)
    //需求：创建一个pairRDD，根据key计算每种key的均值。（先计算每个key出现的次数以及可以对应值的总和，再相除得到结果）
    val input = sc.parallelize(Array(("a", 88), ("b", 95), ("a", 91), ("b", 93), ("a", 95), ("b", 98)),2)
    val combine = input.combineByKey((_,1),(acc:(Int,Int),v)=>(acc._1+v,acc._2+1),(acc1:(Int,Int),acc2:(Int,Int))=>(acc1._1+acc2._1,acc1._2+acc2._2))
    combine.collect().foreach(println)
  }
}
