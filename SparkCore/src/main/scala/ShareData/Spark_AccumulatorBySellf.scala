package ShareData

import java.util

import org.apache.spark.rdd.RDD
import org.apache.spark.util.{AccumulatorV2, LongAccumulator}
import org.apache.spark.{SparkConf, SparkContext}

object Spark_AccumulatorBySellf {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("Spark_AccumulatorBySellf").setMaster("local[2]")
    val sc = new SparkContext(sparkConf)

    val rdd: RDD[String] = sc.makeRDD(List("hadoop","spark","hbase"))
    //使用累加器来共享变量，来累加数据
    //TODO 创建累加器
    val accumulator = new WordAccumulator
    sc.register(accumulator)

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
//声明累加器
//继承AccumulatorV2
//实现抽象方法
class WordAccumulator extends AccumulatorV2[String,util.ArrayList[String]]{
  val list = new util.ArrayList[String]()

  override def isZero: Boolean = {
    list.isEmpty
  }

  override def copy(): AccumulatorV2[String, util.ArrayList[String]] = {
    new WordAccumulator
  }
  //重置
  override def reset(): Unit = list.clear()

  //向累加器种增加数据
  override def add(v: String): Unit = {
    list.add(v)
  }
  //合并
  override def merge(other: AccumulatorV2[String, util.ArrayList[String]]): Unit = {
    list.addAll(other.value)
  }

  override def value: util.ArrayList[String] = {
    list
  }
}
