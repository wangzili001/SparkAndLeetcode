package ShareData

import org.apache.spark.rdd.RDD
import org.apache.spark.util.LongAccumulator
import org.apache.spark.{SparkConf, SparkContext}

object Spark_Accumulator {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("Spark_Accumulator").setMaster("local[2]")
    val sc = new SparkContext(sparkConf)

    val rdd: RDD[Int] = sc.makeRDD(List(1,2,3,4))
    //使用累加器来共享变量，来累加数据
    val accumulator: LongAccumulator = sc.longAccumulator

    rdd.foreach{
      case i =>{
        accumulator.add(i)
      }
    }
    //获取累加器的值
    println("sum="+accumulator.value)
    sc.stop()
  }
}
