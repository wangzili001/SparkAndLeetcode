package note

import org.apache.spark.{SparkConf, SparkContext}

object Share_Date {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext(new SparkConf().setAppName("Share_Date").setMaster("local[*]"))

    val rdd1 = sc.makeRDD(List(1, 2, 3, 4, 5, 6), 2)

//    val rdd2 = rdd1.reduce(_ + _)

    var sum = 0
    rdd1.foreach(i=>sum=sum+i)

    sc.stop()
  }
}
