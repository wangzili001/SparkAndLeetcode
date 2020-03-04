import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

object SparkSQL_udf {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSQL_udf")
    val spark: SparkSession = SparkSession.builder().config(sparkConf).getOrCreate()

    val frame: DataFrame = spark.read.json("input/user.json")

    spark.udf.register("addName",(name:String)=>{"NAME"+name})
    frame.createOrReplaceTempView("user");
    spark.sql("select name from user").show()
    spark.sql("select addName(name) from user").show()
    spark.stop()
  }
}
