package Opera

import org.apache.spark.{SparkConf, SparkContext}

object Checkpoint {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("Checkpoint").setMaster("local[*]")
    val sc = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(List(1, 2, 3, 4))

    val rdd2 = rdd.map((_, 1))

    val rdd3 = rdd2.reduceByKey(_+_)

    rdd2.checkpoint()
    println(rdd3.toDebugString)

    sc.stop()
  }
}
