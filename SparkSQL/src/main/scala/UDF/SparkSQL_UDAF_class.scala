package UDF

import org.apache.spark.SparkConf
import org.apache.spark.sql.expressions.{Aggregator, MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, DoubleType, LongType, StructType}
import org.apache.spark.sql.{DataFrame, Dataset, Encoder, Encoders, Row, SparkSession, TypedColumn}

object SparkSQL_uadf_class {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSQL_uadf_class")
    val spark: SparkSession = SparkSession.builder().config(sparkConf).getOrCreate()

    val frame: DataFrame = spark.read.json("input/user.json")
    import spark.implicits._
    //自定义聚合函数对象
    //创建聚合函数对象
    val udaf = new MyAgeAvgFunction1

    //将聚合函数转换为查询列
    val avgCol: TypedColumn[User, Double] = udaf.toColumn.name("avgAge")

    val userDS: Dataset[User] = frame.as[User]
    //应用函数
    userDS.select(avgCol).show()
    spark.stop()
  }
}
case class User(name:String,age:BigInt)
case class AvgBuffer(var sum:BigInt,var count:Int)
//声明用户自定义聚合函数(强类型)
//继承Aggregator  设定泛型
//实现方法
class MyAgeAvgFunction1 extends Aggregator[ User, AvgBuffer, Double]{
  //初始化
  override def zero: AvgBuffer = {
    AvgBuffer(0,0)
  }
  //聚合数据
  override def reduce(b: AvgBuffer, a: User): AvgBuffer = {
    b.sum = b.sum+a.age
    b.count = b.count+1
    b
  }
  //缓冲区的合并操作
  override def merge(b1: AvgBuffer, b2: AvgBuffer): AvgBuffer = {
    b1.sum = b1.sum+b2.sum
    b1.count = b1.count+b2.count
    b1
  }

  //完成计算
  override def finish(reduction: AvgBuffer): Double = {
    reduction.sum.toDouble/reduction.count
  }

  override def bufferEncoder: Encoder[AvgBuffer] = Encoders.product

  override def outputEncoder: Encoder[Double] = Encoders.scalaDouble
}
