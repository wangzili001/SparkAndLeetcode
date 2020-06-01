package ex.controller

import ex.entity.{tbDate, tbStock, tbStockDetail}
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Dataset, SparkSession}

import scala.collection.mutable.ArrayOps

/**
 * @author Jerry
 * @date 2020/5/29 15:57
 * @version 1.0
 */
object EXController {
  def main(args: Array[String]): Unit = {
    //获取sparkSQL
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("EXController")
    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()
    import spark.implicits._
    //数据加载
    val tbDateRDD: RDD[String] = spark.sparkContext.textFile("input/SparkSql/tbDate.txt")
    val tbStockRDD = spark.sparkContext.textFile("input/SparkSql/tbStock.txt")
    val tbStockDetailRDD = spark.sparkContext.textFile("input/SparkSql/tbStockDetail.txt")

    //将RDD转成DateSet
    val tbDateDS: Dataset[tbDate] = tbDateRDD.map(line => {
      val lines: ArrayOps.ofRef[String] = line.split(",")
      tbDate(lines(0), lines(1).toInt, lines(2).toInt, lines(3).toInt, lines(4).toInt, lines(5).toInt, lines(6).toInt, lines(7).toInt, lines(8).toInt, lines(9).toInt)
    }).toDS()

    val tbStockDS: Dataset[tbStock] = tbStockRDD.map(line => {
      val lines: ArrayOps.ofRef[String] = line.split(",")
      tbStock(lines(0), lines(1), lines(2))
    }).toDS()

    val tbStockDetailDS = tbStockDetailRDD.map(line => {
      val lines: ArrayOps.ofRef[String] = line.split(",")
      tbStockDetail(lines(0), lines(1).toInt, lines(2), lines(3).toInt, lines(4).toDouble, lines(5).toDouble)
    }).toDS()

    tbDateDS.createTempView("tbDate")
    tbStockDS.createTempView("tbStock")
    tbStockDetailDS.createTempView("tbStockDetail")

    spark.sql("select * from tbDate").show()
    spark.sql("select * from tbStock").show()
    spark.sql("select * from tbStockDetail").show()
    /**
     * 计算所有订单中每年的销售单数、销售总额
     */
    spark.sql("select\n td.theyear,\n count(distinct ts.ordernumber),\n sum(tsd.amount)\nfrom\ntbStock ts\nleft join tbStockDetail tsd on ts.ordernumber = tsd.ordernumber\nleft join tbDate td on ts.dateid=td.dateid\ngroup by td.theyear order by td.theyear").show()
  }
}
