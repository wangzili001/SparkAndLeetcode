package Action

import org.apache.spark.{SparkConf, SparkContext}

/**
 * aggregate案例
 * 1. 参数：(zeroValue: U)(seqOp: (U, T) ⇒ U, combOp: (U, U) ⇒ U)
 * 2. 作用：aggregate函数将每个分区里面的元素通过seqOp和初始值进行聚合，然后用combine函数将每个分区的结果和初始值(zeroValue)进行combine操作。这个函数最终返回的类型不需要和RDD中元素类型一致。
 * 3. 需求：创建一个RDD，将所有元素相加得到结果
 *
 *  fold(num)(func)案例
 *  1. 作用：折叠操作，aggregate的简化操作，seqop和combop一样。
 * 2. 需求：创建一个RDD，将所有元素相加得到结果
 */
object Spark_aggregate {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("Spark_aggregate").setMaster("local[2]")
    val sc = new SparkContext(sparkConf)

    var rdd1 = sc.makeRDD(1 to 10,2)
    val i = rdd1.aggregate(0)(_+_,_+_)
    println(i)//55
    val i1 = rdd1.aggregate(10)(_+_,_+_)
    println(i1)//85

    val fold = rdd1.fold(0)(_+_)
    println(fold)//55
  }
}
