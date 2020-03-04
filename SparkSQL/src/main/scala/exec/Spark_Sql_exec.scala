package exec

import java.util.Date

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

object Spark_Sql_exec {
  def main(args: Array[String]): Unit = {
    //创建sparkSession
    val conf: SparkConf = new SparkConf().setAppName("Spark_Sql_exec").setMaster("local[*]")
    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()
    //隐式转换
    import spark.implicits._
    //导入数据
    val tbDateFrame: Dataset[String] = spark.read.textFile("SparkSQL-Data/tbDate.txt")
    val tbStockFrame: Dataset[String] = spark.read.textFile("SparkSQL-Data/tbStock.txt")
    val tbStockDetailFrame: Dataset[String] = spark.read.textFile("SparkSQL-Data/tbStockDetail.txt")

    val tbStockDS: Dataset[tbStock] = tbStockFrame.map(_.split(",")).map(attr => tbStock(attr(0), attr(1), attr(2)))

    val tbDateDS: Dataset[tbDate] = tbDateFrame.map { x => {
      val attr: Array[String] = x.split(",")
      tbDate(
        attr(0),attr(1).trim().toInt,
        attr(2).trim().toInt,attr(3).trim().toInt,
        attr(4).trim().toInt, attr(5).trim().toInt,
        attr(6).trim().toInt, attr(7).trim().toInt,
        attr(8).trim().toInt, attr(9).trim().toInt)
      }
    }

    val tbStockDetailDS:Dataset[tbStockDetail] = tbStockDetailFrame.map{x=>{
      val attr: Array[String] = x.split(",")
      tbStockDetail(attr(0),attr(1).trim().toInt,attr(2),attr(3).trim().toInt,attr(4).trim().toDouble, attr(5).trim().toDouble)
      }
    }

    tbStockDS.createOrReplaceTempView("tbStock")
    tbDateDS.createOrReplaceTempView("tbDate")
    tbStockDetailDS.createOrReplaceTempView("tbStockDetail")
    spark.sql("select * from tbStock").show()
    //计算所有订单中每年的销售单数、销售总额
    spark.sql(
      "SELECT c.theyear, COUNT(DISTINCT a.ordernumber), SUM(b.amount) " +
        "FROM " +
        "tbStock a " +
        "JOIN tbStockDetail b ON a.ordernumber = b.ordernumber " +
        "JOIN tbDate c ON a.dateid = c.dateid " +
        "GROUP BY c.theyear ORDER BY c.theyear").show()
  }
}

case class tbDate(dateid:String, years:Int, theyear:Int, month:Int, day:Int, weekday:Int, week:Int, quarter:Int, period:Int, halfmonth:Int) extends Serializable

case class tbStockDetail(ordernumber:String, rownum:Int, itemid:String, number:Int, price:Double, amount:Double) extends Serializable

case class tbStock(ordernumber:String,locationid:String,dateid:String) extends Serializable