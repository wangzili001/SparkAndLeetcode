import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

object SparkSQL_SQL {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSQL_SQL")
    val spark: SparkSession = SparkSession.builder().config(sparkConf).getOrCreate()
    val frame: DataFrame = spark.read.json("input/user.json")
    //采用sql方式来访问
//    frame.show()
    frame.createOrReplaceTempView("user");
    spark.sql("select * from user").show()
    spark.stop()
  }
}
