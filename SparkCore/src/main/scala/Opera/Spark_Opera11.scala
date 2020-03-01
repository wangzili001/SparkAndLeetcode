package Opera

import org.apache.spark.{Partitioner, SparkConf, SparkContext}

//自定义分区类
object Spark_Opera11 {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Spark_Opera11")
    val sc = new SparkContext(sparkConf)
    val RDD = sc.makeRDD(List(("lpl",1),("lck",2),("lsc",3)))

    val result = RDD.partitionBy(new MyPartitions(3))
    result.saveAsTextFile("out")
  }
}
//声明分区器
//继承Partitioner
class MyPartitions(partition: Int) extends Partitioner{
  override def numPartitions: Int = {
    partition
  }

  override def getPartition(key: Any): Int = {
    key match {
      case "lpl" =>0
      case "lck" =>1
      case "lsc" =>2
    }
  }
}
