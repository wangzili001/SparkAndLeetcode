package Action

import org.apache.spark.{SparkConf, SparkContext}

/**
 * . 作用：通过func函数聚集RDD中的所有元素，先聚合分区内数据，再聚合分区间数据。
 * 2. 需求：创建一个RDD，将所有元素聚合得到结果。
 */
object Spark_Reduce {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("Spark_Reduce").setMaster("local[2]")
    val sc = new SparkContext(sparkConf)

    val rdd1 = sc.makeRDD(1 to 10,2)
    val i1 = rdd1.reduce(_+_)
    println(i1)
    val rdd2 = sc.makeRDD(Array(("a",1),("a",3),("c",3),("d",5)))
    val i2 = rdd2.reduce((x,y)=>(x._1 + y._1,x._2 + y._2))
    println(i2)
  }
}
