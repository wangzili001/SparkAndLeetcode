import org.apache.spark.{SparkConf, SparkContext, sql}
import org.apache.spark.sql.{DataFrame, SparkSession}

object SparkSQL_class1 {
  def main(args: Array[String]): Unit = {
    /**
     * /**
     * * 创建SparkSession方式1
     **/
     * val sparkSession1 = SparkSession.builder()
     * .appName("SparkSQLDemo")
     * .master("local")
     * .getOrCreate()
     * /**
     * * 创建SparkSession方式2
     **/
     * val conf = new SparkConf()
     * .setAppName("SparkSQLDemo")
     * .setMaster("local")
     * val sparkSession2 = SparkSession.builder()
     * .config(conf)
     * .getOrCreate()
     * /**
     * * 创建SparkSession方式3(操作hive)
     **/
     * val sparkSession3 = SparkSession.builder()
     * .appName("SparkSQLDemo")
     * .master("local")
     * .enableHiveSupport()
     * .getOrCreate()
     * */
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSQL_class1")
    val spark: SparkSession = SparkSession.builder().config(sparkConf).getOrCreate()
    val frame: DataFrame = spark.read.json("input/user.json")
    frame.show()
    println(spark)
    spark.stop()
  }
}
